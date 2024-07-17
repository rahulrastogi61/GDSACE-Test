package org.cognizant.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.Assert;
import org.cognizant.Factory.DriverFactory;

import java.util.List;
import java.util.Objects;

public class EligibilityPage {
    private static Page page;
    private final Locator GET_NEW_GRANT;
    private final Locator ELIGIBILITY_QUESTIONNAIRE;
    private final Locator APPLY_GRANT_BUTTON;
    private final Locator PROCEED_GRANT_BUTTON;
    private final Locator SAVE_BUTTON;

    public EligibilityPage(Page page) {
        EligibilityPage.page = page;
        this.GET_NEW_GRANT = page.locator("//*[@id='dashboard-menubox-app-apply-grant']/section/div/div[2]/h4");
        this.ELIGIBILITY_QUESTIONNAIRE = page.locator("//div[@data-testid='label']/label");
        this.APPLY_GRANT_BUTTON = page.locator("#go-to-grant");
        this.PROCEED_GRANT_BUTTON = page.locator("#keyPage-form-button");
        this.SAVE_BUTTON = page.locator("//button[@class='bgp-btn bgp-btn-save' and @id='save-btn']");
    }

    public void applyNewGrant() {
        GET_NEW_GRANT.click();
    }

    public void countEligibilityQuestionnaire(int noOfQuestions) throws InterruptedException {
        page.waitForSelector("//div[@data-testid='label']/label");
        int actualCount = ELIGIBILITY_QUESTIONNAIRE.count();
        Assert.assertEquals(noOfQuestions, actualCount);
    }


    public void verifyEligibilityQuestions(List<String> list) {
        for (int i = 0; i < ELIGIBILITY_QUESTIONNAIRE.count(); i++) {
            String actualQuestions = ELIGIBILITY_QUESTIONNAIRE.allInnerTexts().get(i);
            Assert.assertEquals(list.get(i), actualQuestions.replaceAll("[\\n\\u00A0]", "").trim());
        }
    }

    public void selectSectorType(String sectorType) {
        page.getByText(sectorType, new Page.GetByTextOptions().setExact(true)).click();
    }

    public void selectGrantType(String typeOfGrant) {
        page.locator("label").filter(new Locator.FilterOptions().setHasText(typeOfGrant)).locator("div").first().click();
    }

    public void selectAssistance(String typeOfAssistance) {
        page.getByText(typeOfAssistance).click();
    }

    public void verifySectionType() {
//        if (CHECK_ELIGIBITY_HEADING.isVisible()) {
//            System.out.println("User is on Eligibility section");
//        } else {
//            System.out.println("User is not on Eligibility section");
//        }
    }

    public void clickProceedButton() {
        APPLY_GRANT_BUTTON.click();
        PROCEED_GRANT_BUTTON.click();
    }

    public void verifyYesAndNoRadioButtons(List<String> ids) {
        for (String id : ids) {
            boolean areButtonsPresent = verifyYesNoRadioButton(id);
            Assert.assertTrue(String.format("'Yes' and 'No' radio buttons for ID '%s' should exist", id), areButtonsPresent);
        }
    }

    private static boolean verifyYesNoRadioButton(String id) {
        boolean areYesButtonsPresent = page.locator(String.format("//input[@name='%s' and @id='%s-true']", id, id)).isVisible();
        boolean areNoButtonsPresent = page.locator(String.format("//input[@name='%s' and @id='%s-false']", id, id)).isVisible();
        return areYesButtonsPresent && areNoButtonsPresent;
    }

    public void verifyYesAndNoRadioButtonsCount(int expectedYesCount, int expectedNoCount) {
        page.waitForSelector("//span[@class='bgp-label' and text()='Yes']");
        int actualYesCount = page.locator("//span[@class='bgp-label' and text()='Yes']").count();
        int actualNoCount = page.locator("//span[@class='bgp-label' and text()='No']").count();
        Assert.assertEquals("Number of 'Yes' radio buttons does not match", expectedYesCount, actualYesCount);
        Assert.assertEquals("Number of 'No' radio buttons does not match", expectedNoCount, actualNoCount);
    }

    public void clickYesorNoForAllQuestions(String options) {
        if (Objects.equals(options, "No")) {
            clickYesorNoBasedOnOption(options, "false");
        } else {
            clickYesorNoBasedOnOption(options, "true");
        }
    }

    private static void clickYesorNoBasedOnOption(String option, String value) {
        page.waitForSelector("//input[@type='radio' and @value='" + value + "']");
        int optionCount = page.locator("//input[@type='radio' and @value='" + value + "']").count();
        for (int i = 0; i < optionCount; i++) {
            page.locator("//span[@class='bgp-label' and text()='" + option + "']").nth(i).click();
        }
    }

    public void checkWarningMessages(int expectedWarningCount, String expectedWarningMessage) {
        page.waitForSelector("//div[@class='field-warning-text']");
        int actualWarningCount = page.locator("//div[@class='field-warning-text']").count();
        Assert.assertEquals("Number of warning messages does not match", expectedWarningCount, actualWarningCount);
        for (int i = 0; i < actualWarningCount; i++) {
            String actualWarningMessage = page.locator("//div[@class='field-warning-text']/span").nth(i).innerText();
            Assert.assertEquals("Warning message does not match", expectedWarningMessage, actualWarningMessage);
        }
    }

    public void clickTheLinkInWarningMessage() {
        page.locator("//div[@class='field-warning-text']//a").nth(1).click();
    }

    public void verifyURLandSwitchToNewTab(String url) throws InterruptedException {
        BrowserContext context = DriverFactory.getContext();
        context.onPage(newPage -> {
            newPage.waitForLoadState();
            System.out.println("newPage: " + newPage.url());
            Assert.assertEquals(url, newPage.url());
        });

    }

    public void clickSaveButton() {
        SAVE_BUTTON.click();
    }

    public void refreshAndCheckSavedValues() {
        page.reload();
        page.waitForSelector("//span[@class='bgp-label' and text()='Yes']");
        int actualYesCount = page.locator("//span[@class='bgp-label' and text()='Yes']").count();
        for (int i = 0; i < actualYesCount; i++) {
            Assert.assertTrue("Yes radio button is not visible", page.locator("//span[@class='bgp-label' and text()='Yes']").nth(i).isVisible());
        }
    }
}
