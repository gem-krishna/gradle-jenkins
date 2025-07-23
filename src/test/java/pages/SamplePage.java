package pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.google.com")
public class SamplePage extends PageObject {

    @FindBy(name = "q")
    WebElement searchField;

    @FindBy(css = "h3")
    List<WebElement> searchResults;

    public void searchFor(String term) {
        searchField.sendKeys(term);
        searchField.submit();
    }

    public List<String> getResults() {
        return searchResults.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
