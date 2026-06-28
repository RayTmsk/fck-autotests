package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.*;
import autotest.texts.*;
import autotest.pages.PageObject;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class FooterTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public FooterTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright footer page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check for the footer")
    public void iCheckForTheFooter() {
        boolean footerExists = pageObject
                .getPage().locator(Locators.FOOTER_SELECTOR)
                .count() > 0;

        assertTrue("Футер должен присутствовать на странице", footerExists);
    }

    @Then("I verify the footer is displayed")
    public void iVerifyTheFooterIsDisplayed() {
        Locator footer = pageObject.getPage().locator(Locators.FOOTER_SELECTOR);
        assertThat(footer).isVisible();

        Locator itmContact = pageObject.getPage().locator(Locators.CONTACT_LINK_SELECTOR);
        assertThat(itmContact).isVisible();

        Locator itemsPrivacy = pageObject.getPage().locator(Locators.PRIVACY_POLICY_LINK_SELECTOR);
        Locator itmPrivacy = itemsPrivacy.filter(new Locator.FilterOptions()
                .setHasText(Texts.PRIVACY_LINK_TEXT));
        assertThat(itmPrivacy).isVisible();
    }
}
