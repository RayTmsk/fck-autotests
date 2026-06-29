package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.*;
import autotest.pages.PageObject;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;


public class ViewAllButtonTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public ViewAllButtonTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright all button page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check the View all button")
    public void iCheckTheViewAllButton() {
        //Код...

        Locator btn = pageObject.getPage().locator(Locators.VIEW_ALL_BUTTON_SELECTOR);

        assertThat(btn).isVisible();

        btn.click();
    }

    @Then("I verify the View all button redirects")
    public void iVerifyTheViewAllButtonRedirects() {
        String currentUrl = pageObject.getPage().url();

        assertTrue(currentUrl.contains("/vue-app/index.html#/"));
    }
}
