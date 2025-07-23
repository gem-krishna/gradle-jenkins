FROM openjdk:17-slim

RUN apt-get update && apt-get install -y wget unzip curl

# Install Microsoft Edge (stable)
RUN wget https://msedgeselenium.blob.core.windows.net/edgewebdriver/stable/linux64/microsoft-edge-stable.deb && \
    dpkg -i microsoft-edge-stable.deb || apt-get -f install -y && \
    rm microsoft-edge-stable.deb

# Install EdgeDriver (matching Edge version)
RUN EDGE_VERSION=$(microsoft-edge --version | grep -oP '\d+' | head -1) && \
    EDGEDRIVER_VERSION=$(curl -sS https://msedgedriver.azureedge.net/LATEST_RELEASE_${EDGE_VERSION}) && \
    wget -O /tmp/edgedriver.zip https://msedgedriver.azureedge.net/${EDGEDRIVER_VERSION}/edgedriver_linux64.zip && \
    unzip /tmp/edgedriver.zip -d /usr/local/bin/ && \
    chmod +x /usr/local/bin/msedgedriver && \
    rm /tmp/edgedriver.zip

ENV PATH="/usr/local/bin:${PATH}"

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

CMD ["./gradlew", "clean", "test", "aggregate"]
