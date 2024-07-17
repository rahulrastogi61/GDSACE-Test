package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cognizant.Factory.DriverFactory;
import org.cognizant.pages.EligibilityPage;
import org.cognizant.pages.LoginPage;
import org.cognizant.utils.WebActions;

public class Eligibility {
    LoginPage loginPage = new LoginPage(DriverFactory.getPage());
    EligibilityPage eligibilityPage = new EligibilityPage(DriverFactory.getPage());

    @Given("User Navigates to My Grants Page")
    public void userNavigatesToMyGrantsPage() {
        loginPage.navigateToUrl(WebActions.getProperty("url"));
        loginPage.performLogin();
    }

    @Then("User applies for new grant for IT sector and Market Readiness Assistance")
    public void userAppliesForNewGrantForITSectorAndMarketReadinessAssistance() {
        eligibilityPage.applyNewGrant();
    }

    @Then("there should be {int} eligibility questions")
    public void thereShouldBeEligibilityQuestions(int noOfQuestions) throws InterruptedException {
        eligibilityPage.countEligibilityQuestionnaire(noOfQuestions);
    }

    @And("the five eligibility questions will be as follows")
    public void theFiveEligibilityQuestionsWillBeAsFollows(DataTable dataTable) {
        eligibilityPage.verifyEligibilityQuestions(dataTable.asList());
    }

    @Given("User fills in all mandatory details on the Eligibility page")
    public void user_fills_in_all_mandatory_details_on_the_eligibility_page() {
        // to do
    }

    @And("User selects sector {string}")
    public void userSelectsSector(String sectorType) {
        eligibilityPage.selectSectorType(sectorType);
    }

    @And("User selects I need this grant to {string}")
    public void userSelectsINeedThisGrantTo(String typeOfGrant) {
        eligibilityPage.selectGrantType(typeOfGrant);
    }

    @And("User selects assistance {string}")
    public void userSelectsAssistance(String typeOfAssistance) {
        eligibilityPage.selectAssistance(typeOfAssistance);
    }


    @Given("User is on Eligibility section")
    public void userIsOnEligibilitySection() {
        eligibilityPage.verifySectionType();
    }

    @And("User proceeds and Applies for grant")
    public void userProceedsAndAppliesForGrant() {
        eligibilityPage.clickProceedButton();
    }

    @Then("User applies for New Grant")
    public void userAppliesForNewGrant() {
        eligibilityPage.applyNewGrant();
    }

    @Then("there should be {int} Yes and {int} No radio buttons")
    public void verifyYesAndNoRadioButtonsCount(int yesRadioBtns, int noRadioBtns) {
        eligibilityPage.verifyYesAndNoRadioButtonsCount(yesRadioBtns, noRadioBtns);
    }

    @And("there should be Yes and No radio buttons for the eligibility options:")
    public void thereShouldBeYesAndNoRadioButtonsForTheEligibilityOptions(DataTable dataTable) {
        eligibilityPage.verifyYesAndNoRadioButtons(dataTable.asList());
    }

    @Then("the user should see {int} warning messages {string} for all questions")
    public void theUserShouldSeeWarningMessagesForAllQuestions(int expectedWarningCount, String expectedWarningMessage) {
        eligibilityPage.checkWarningMessages(expectedWarningCount, expectedWarningMessage);
    }

    @When("The user sees a warning message")
    public void theUserSeesAWarningMessage() {
        eligibilityPage.checkWarningMessages(5, "The applicant may not meet the eligibility criteria for this grant. Visit FAQ page for more information on other government grants.");
    }

    @Then("Clicks the link in the warning message")
    public void clicksTheLinkInTheWarningMessage() {
        eligibilityPage.clickTheLinkInWarningMessage();
    }

    @And("Switch to the new tab and verify URL {string}")
    public void switchToTheNewTabAndVerifyURLHttpsWwwGobusinessGovSgBusinessGrantsPortalFaqGetAGrant(String expectedURL) throws InterruptedException {
        eligibilityPage.verifyURLandSwitchToNewTab(expectedURL);
    }

    @Then("User clicks Save button on Eligibility page")
    public void userClicksSaveButtonOnEligibilityPage() {
        eligibilityPage.clickSaveButton();
    }

    @And("Refreshing the page should reload the saved values")
    public void refreshingThePageShouldReloadTheSavedValues() {
        eligibilityPage.refreshAndCheckSavedValues();
    }

    @When("the user clicks {string} for all eligibility questions")
    public void theUserClicksForAllEligibilityQuestions(String options) {
        eligibilityPage.clickYesorNoForAllQuestions(options);
    }
}

     

