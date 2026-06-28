package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.*;
import autotest.pages.PageObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class H1Test {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public H1Test(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright H1 header page")
    public void iOpenThePlaywrightWelcomePage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check for the H1 header")
    public void iCheckForTheWelcomeHeader() {
        Locator h1 = pageObject.getPage().getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setLevel(1));

        assertThat(h1).isVisible();
        assertThat(h1).containsText("Каталог");
    }

    @Then("I close the browser after checking tests")
    public void iCloseTheBrowserAfterCheckingTests() {
        pageObject.close();
    }
}
