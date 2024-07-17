package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cognizant.Factory.DriverFactory;
import org.cognizant.pages.ContactDetailsPage;

import java.util.List;

public class ContactDetails {

    ContactDetailsPage contactDetailsPage = new ContactDetailsPage(DriverFactory.getPage());

    @When("The user clicks on the Contact Details menu")
    public void theUserClicksOnTheContactDetailsMenu() {
        contactDetailsPage.clickOnContactDetailsMenu();
    }

    @Then("The user should see the fields in the Main Contact Person section")
    public void theUserShouldSeeTheFieldsInTheMainContactPersonSection(DataTable dataTable) {
        contactDetailsPage.verifyMainContactPersonFields(dataTable.asList());
    }

    @When("User fills in all mandatory details on the Contact Details page")
    public void user_fills_in_all_mandatory_details_on_the_contact_details_page() {
        // to be implemented
    }

    @Then("The user enters the postal code {string} in the Postal Code field")
    public void theUserEntersThePostalCodeInThePostalCodeField(String postalCode) {
        contactDetailsPage.enterPostalCode(postalCode);
    }

    @And("The House Number field should be auto populated with {string}")
    public void theHouseNumberFieldShouldBeAutoPopulatedWith(String houseNumber) {
        contactDetailsPage.verifyPopulatedHouseNumberField(houseNumber);
    }

    @And("the Street field should be auto populated with {string}")
    public void theStreetFieldShouldBeAutoPopulatedWith(String street) {
        contactDetailsPage.verifyPopulatedStreetField(street);
    }

    @Then("The user selects Same as registered address in Company Profile checkbox")
    public void theUserSelectsSameAsRegisteredAddressInCompanyProfileCheckbox() {
        contactDetailsPage.selectSameAsRegisteredAddressCheckbox();
    }

    @And("The Mailing Address details should be auto-populated from the Applicant’s Company Profile")
    public void theMailingAddressDetailsShouldBeAutoPopulatedFromTheApplicantSCompanyProfile(DataTable dataTable) {
        contactDetailsPage.verifyMailingAddressAutoPopulated(dataTable.asList());
    }

    @And("The Mailing Address details should be auto-populated from the Applicant’s Company Profile With following {string}, {string}, {string}, {string}, {string}")
    public void theMailingAddressDetailsShouldBeAutoPopulatedFromTheApplicantSCompanyProfileWithFollowing(String postalCode, String houseNo, String street, String Level, String Unit) {
        List<String> data = List.of(postalCode, houseNo, street, Level, Unit);
        contactDetailsPage.verifyMailingAddressAutoPopulated(data);
    }

    @Then("Verify the Letter of Offer Addressee fields are visible and editable")
    public void verifyTheLetterOfOfferAddresseeFieldsAreVisibleAndEditable() {
        contactDetailsPage.verifyLetterOfOfferAddresseeFieldsEditable();
    }

    @Then("The user inputs main contact person details")
    public void theUserInputsMainContactPersonDetails() {
        contactDetailsPage.inputMainContactPersonDetails();
    }

    @And("The user checks the Same as Main Contact checkbox")
    public void theUserChecksTheSameAsMainContactCheckbox() {
        contactDetailsPage.clickSameAsMainContactCheckbox();
    }

    @And("The user Verifies the Letter of Offer Addressee fields are populated with details from Main Contact Person")
    public void theUserVerifiesTheLetterOfOfferAddresseeFieldsArePopulatedWithDetailsFromMainContactPerson() {
        contactDetailsPage.verifyMainContactPersonDetailsPopulated();
    }

    @And("The user clicks the Save button on Contact Details Page")
    public void theUserClicksTheSaveButtonOnContactDetailsPage() {
        contactDetailsPage.clickSaveButton();
    }

    @And("The user refreshes the page")
    public void theUserRefreshesThePage() {
        contactDetailsPage.refreshPage();
    }

    @And("The user verifies the saved values after reloading the page")
    public void theUserVerifiesTheSavedValuesAfterReloadingThePage() {
        contactDetailsPage.verifyReloadedValues();
    }
}
