package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SamplePage;
//import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.Assertions;

public class SampleStepDefinitions {

    SamplePage samplePage = new SamplePage();

//    @Step
    @Given("I am on the Google search page")
    public void i_am_on_the_Google_search_page() {
        samplePage.open();
    }

//    @Step
    @When("I search for {string}")
    public void i_search_for(String keyword) {
        samplePage.searchFor(keyword);
    }

//    @Step
    @Then("I should see results containing {string}")
    public void i_should_see_results_containing(String expected) {
        Assertions.assertTrue(samplePage.getResults().stream()
                .anyMatch(result -> result.contains(expected)));
    }
}

