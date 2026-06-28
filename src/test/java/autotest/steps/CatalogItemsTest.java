package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.*;
import autotest.pages.PageObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class CatalogItemsTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public CatalogItemsTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright catalog items page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check the catalog items")
    public void iCheckTheCatalogItems() {
        Locator items = pageObject.getPage().locator(Locators.CATALOG_ITEMS_LIST);
        assertThat(items.first()).isVisible();
    }

    @Then("I verify catalog items are displayed")
    public void iVerifyCatalogItemsAreDisplayed() {
        Locator items = pageObject.getPage().locator(Locators.CATALOG_ITEMS_LIST);
        assertThat(items.first()).isVisible();

        int itemCount = items.count();

        assertTrue("Должно быть больше 2 товаров в каталоге, найдено: " + itemCount,
                itemCount > 2);
    }

}
