package org.cognizant.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.Assert;
import org.cognizant.utils.ApplicationInformation;

import java.util.List;
import java.util.Objects;


public class DeclarationPage {

    private static Page page;
    private final Locator DECLARATION_MENU;
    private final Locator ACKNOWLEDGEMENT_CHECKBOX;
    private final Locator SAVE_BUTTON;
    private final Locator REVIEW_BUTTON;
    private final Locator CONSENT_ACKNOWLEDGEMENT_CHECKBOX;
    private final Locator FORM_SUBMIT_BUTTON;
    private final Locator SUBMISSION_STATUS;
    private final Locator AGENCY_DETAILS;
    private final Locator REF_ID;
    private final Locator BACK_TO_GRANT_ACTIONS;
    private final Locator BACK_TO_MY_GRANT;
    private final Locator PROCESSING_TAB;
    private static ApplicationInformation applicationInformation;

    public DeclarationPage(Page page, ApplicationInformation applicationInformation) {
        DeclarationPage.page = page;
        this.DECLARATION_MENU = page.locator("//span[@class='menu-text' and starts-with(text(), 'Declare')]");
        this.ACKNOWLEDGEMENT_CHECKBOX = page.locator("//input[@id='react-declaration-consent_acknowledgement_check']");
        this.SAVE_BUTTON = page.locator("//button[@id='save-btn']");
        this.REVIEW_BUTTON = page.locator("//button[@id='review-btn']");
        this.CONSENT_ACKNOWLEDGEMENT_CHECKBOX = page.locator("#react-declaration-info_truthfulness_check");
        this.FORM_SUBMIT_BUTTON = page.locator("#submit-btn");
        this.SUBMISSION_STATUS = page.locator("//td[contains(text(),'Status')]/following-sibling::td");
        this.AGENCY_DETAILS = page.locator("//td[contains(text(),'Agency Details:')]/following-sibling::td");
        this.REF_ID = page.locator("//td[contains(text(),'Ref ID:')]/following-sibling::td");
        this.BACK_TO_GRANT_ACTIONS = page.locator("//span[@class='back-text']");
        this.BACK_TO_MY_GRANT = page.locator("//span[@class='back-text']");
        this.PROCESSING_TAB = page.locator("//a[@href='#processing']");
        DeclarationPage.applicationInformation = applicationInformation;
    }

    public void clickDeclareReviewMenu() {
        DECLARATION_MENU.click();
    }

    public void clickYesorNoForAllQuestions(String options) {
        if (Objects.equals(options, "No")) {
            clickYesorNoBasedOnOption(options, "false");
        } else {
            clickYesorNoBasedOnOption(options, "true");
        }
    }

    private static void clickYesorNoBasedOnOption(String option, String value) {
        try {
            page.waitForSelector("//div[@class='subsection-title']");
            int optionCount = page.locator("//input[@type='radio' and @value='" + value + "']").count();
            System.out.println("Option count: " + optionCount);
            for (int i = 0; i < optionCount; i++) {
                page.locator("//span[@class='bgp-label' and text()='" + option + "']").nth(i).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAcknowledgement() {
        ACKNOWLEDGEMENT_CHECKBOX.check();
    }

    public void clickSaveButton() {
        SAVE_BUTTON.click();
    }

    public void clickReviewButton() {
        REVIEW_BUTTON.click();
    }

    public void verifyReadOnlySummaryPage() {
        Assert.assertTrue("Could not find the expected text on the read only summary page ",
                page.locator("//span[@class='menu-text' and starts-with(text(), 'Company Profile')]").isEnabled());

    }

    public void countErrors(List<String> list) {
        for (int i = 1; i < list.size(); i++) {
            Assert.assertEquals(list.get(i), page.locator("//span[@class=\"label label-error\"]").nth(i - 1).innerText());
        }
    }

    public void verifyConsentAcknowledgementCheckbox() {
        page.waitForSelector("#react-declaration-info_truthfulness_check");
        Assert.assertTrue("Consent and Acknowledgement checkbox is not present on Review and Summary page",
                CONSENT_ACKNOWLEDGEMENT_CHECKBOX.isEnabled());
        System.out.println("Consent and Acknowledgement checkbox is present on Review and Summary page");
    }

    public void submitApplication() {
        FORM_SUBMIT_BUTTON.click();
    }

    public void verifySuccessMessage() {
        page.waitForSelector("//h3[text()='Your application has been submitted.']");
        Assert.assertTrue("Success message box is not displayed",
                page.locator("//h3[text()='Your application has been submitted.']").isEnabled());
        Assert.assertEquals("Submitted", SUBMISSION_STATUS.innerText());
        applicationInformation.setAPP_STATUS(SUBMISSION_STATUS.innerText());
    }

    public void verifyAgencyDetails() {
        Assert.assertTrue("Agency details is not displayed", AGENCY_DETAILS.isEnabled());
        applicationInformation.setREF_ID(REF_ID.innerText().trim());
        Assert.assertTrue("Agency details should contain 'Enterprise Singapore'", AGENCY_DETAILS.innerText().trim().contains("Enterprise Singapore"));
    }

    public void acceptAcknowledgement() {
        CONSENT_ACKNOWLEDGEMENT_CHECKBOX.check();
    }

    public void navigateToProcessingTable() {
        BACK_TO_GRANT_ACTIONS.click();
        BACK_TO_MY_GRANT.click();
        PROCESSING_TAB.waitFor();
        PROCESSING_TAB.click();
        page.waitForSelector("//div[contains(text(), '" + applicationInformation.getPROJECT_TITLE() + "')]");
        page.locator("//div[contains(text(), '" + applicationInformation.getPROJECT_TITLE() + "')]").click();
    }

    public void verifyApplicationSubmitted() {
        Assert.assertEquals(REF_ID.innerText().trim(), applicationInformation.getREF_ID());
        Assert.assertEquals(SUBMISSION_STATUS.innerText(), applicationInformation.getAPP_STATUS());
    }
}
