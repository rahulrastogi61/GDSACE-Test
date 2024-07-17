package org.cognizant.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.cognizant.utils.ApplicationInformation;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class ProposalPage {
    private static Page page;
    private final Locator PROPOSAL_MENU;
    private final Locator PROJECT_TITLE;
    private final Locator START_DATE;
    private final Locator END_DATE;
    private final Locator PROJECT_DESCRIPTION;
    private final Locator ACTIVITY_DROPDOWN;
    private final Locator TARGET_MARKET_DROPDOWN;
    private final Locator IS_FIRST_TIME_EXPAND_TRUE_RADIO;
    private final Locator IS_FIRST_TIME_EXPAND_FALSE_RADIO;
    private final Locator REMARKS_DESCRIPTION;
    private final Locator SAVE_BUTTON;
    private final Faker faker;
    private static ApplicationInformation applicationInformation;

    public ProposalPage(Page page, ApplicationInformation applicationInformation) {
        ProposalPage.page = page;
        this.PROPOSAL_MENU = page.locator("//span[@class='menu-text' and text()='Proposal']");
        this.PROJECT_TITLE = page.locator("//input[@data-testid='text-field' and @id='react-project-title']");
        this.START_DATE = page.locator("//input[@type='text' and @id='react-project-start_date']");
        this.END_DATE = page.locator("//input[@type='text' and @id='react-project-end_date']");
        this.PROJECT_DESCRIPTION = page.locator("//textarea[@id='react-project-description']");
        this.ACTIVITY_DROPDOWN = page.locator("//input[@aria-activedescendant='react-select-project-activity--value']");
        this.TARGET_MARKET_DROPDOWN = page.locator("//input[@aria-activedescendant='react-select-project-primary_market--value']");
        this.IS_FIRST_TIME_EXPAND_TRUE_RADIO = page.locator("#react-project-is_first_time_expand-true");
        this.IS_FIRST_TIME_EXPAND_FALSE_RADIO = page.locator("#react-project-is_first_time_expand-false");
        this.REMARKS_DESCRIPTION = page.locator("//textarea[@id='react-project-remarks']");
        this.SAVE_BUTTON = page.locator("#save-btn");
        faker = new Faker();
        //ProposalPage.applicationInformation = applicationInformation;
    }

    public void clickProposalMenu() {
        PROPOSAL_MENU.click();
    }

    public void fillProposalDetails() {
        page.waitForSelector("//input[@data-testid='text-field' and @id='react-project-title']");
        if (PROJECT_TITLE.isEnabled()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            Calendar currentDate = Calendar.getInstance();
            String startDate = dateFormat.format(currentDate.getTime());
            Calendar futureDate = Calendar.getInstance();
            futureDate.add(Calendar.DAY_OF_MONTH, 30);
            String endDate = dateFormat.format(futureDate.getTime());
            //applicationInformation.setPROJECT_TITLE(faker.lorem().sentence());
            Random rand = new Random();
            PROJECT_TITLE.fill("Test Project-" +  rand.nextInt(1000));
            START_DATE.fill(startDate);
            END_DATE.fill(endDate);
            PROJECT_DESCRIPTION.fill(faker.lorem().paragraph());
            ACTIVITY_DROPDOWN.fill("Identification of Potential Overseas Partners");
            page.keyboard().press("Enter");
            TARGET_MARKET_DROPDOWN.fill("Singapore");
            page.keyboard().press("Enter");
            IS_FIRST_TIME_EXPAND_TRUE_RADIO.click();
            page.setInputFiles("#react-project-attachments-input", Paths.get("src/test/resources/filestoUpload/Upload.jpeg"));
            REMARKS_DESCRIPTION.fill(faker.lorem().paragraph());
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("src/test/resources/filestoUpload/screenshot" + faker.name() + ".png")));
            SAVE_BUTTON.click();
        }
    }

    public void clickSaveButton() {
        SAVE_BUTTON.click();
    }
}
