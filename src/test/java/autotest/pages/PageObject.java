package autotest.pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageObject {
    private final Page page;
    private final Browser browser;

    @Autowired
    public PageObject(Page page, Browser browser) {
        this.page = page;
        this.browser = browser;
    }

    public Page getPage() {
        return page;
    }

    public String getTitle() {
        return page.title();
    }

    public String getHeaderText() {
        return page.locator("h1").textContent();
    }

    public void close() {
        page.close();
        browser.close();
    }
}