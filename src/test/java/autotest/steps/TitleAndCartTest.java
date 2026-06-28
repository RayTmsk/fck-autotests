package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.*;
import autotest.pages.PageObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class TitleAndCartTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public TitleAndCartTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright title page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig
                        .getTestPageUrl());
    }

    @When("I check the page title")
    public void iCheckThePageTitle() {
        String title = pageObject.getTitle();
        assertTrue(title.contains("Каталог"));
    }

    @Then("I click on the cart item")
    public void iClickOnTheCard() {
        pageObject
                .getPage()
                .locator(Locators.CART_LINK_SELECTOR)
                .click();
    }

}
