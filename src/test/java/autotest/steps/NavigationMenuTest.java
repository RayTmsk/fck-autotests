package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.*;
import autotest.pages.PageObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class NavigationMenuTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public NavigationMenuTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright pagination page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check for the pagination number")
    public void iCheckForThePaginationNumber() {
        Locator pagination = pageObject.getPage().locator(Locators.PAGINATION_SELECTOR);

        // Ждём появления пагинации в DOM
        pagination.first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(5000));

        // Прокручиваем к пагинации
        pagination.scrollIntoViewIfNeeded();
        assertThat(pagination).isVisible();

        Locator secondItem = pageObject.getPage().locator(Locators.PAGE_LINK_SELECTOR).nth(1);
        boolean isVisible = secondItem.isVisible();

        System.out.println("Элемент видим: " + isVisible);
        assertTrue("Второй элемент пагинации должен быть видим", isVisible);
    }

    @Then("I verify the pagination is displayed")
    public void iVerifyTheNavigationMenuIsDisplayed() {
        Locator pagination = pageObject.getPage().locator(Locators.PAGINATION_SELECTOR);

        pagination.first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(5000));

        pagination.scrollIntoViewIfNeeded();

        boolean menuVisible = pagination.isVisible();

        assertTrue("Меню навигации должно быть видимым", menuVisible );
    }
}
