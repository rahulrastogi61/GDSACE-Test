package org.cognizant.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.cognizant.utils.WebActions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactDetailsPage {

    private static Page page;
    private final Faker faker;
    private final Locator GET_NEW_GRANT;
    private final Locator POSTAL_CODE_INPUT;
    private final Locator UNITLEVEL;
    private final Locator UNITNO;
    private final Locator SAME_AS_REGISTERED_ADDRESS_CHECKBOX;
    private final Locator SAVE_BUTTON;
    public final Map<String, String> contactDetailsElements = new HashMap<>();

    public ContactDetailsPage(Page page) {
        ContactDetailsPage.page = page;
        this.GET_NEW_GRANT = page.locator("//span[@class='menu-text' and text()='Contact Details']");
        faker = new Faker();
        contactDetailsElements.put("Name", "#react-contact_info-name");
        contactDetailsElements.put("Job Title", "#react-contact_info-designation");
        contactDetailsElements.put("Contact No", "#react-contact_info-phone");
        contactDetailsElements.put("Email", "#react-contact_info-primary_email");
        contactDetailsElements.put("Alternate Contact Email", "#react-contact_info-secondary_email");
        contactDetailsElements.put("Same as registered address checkbox", "#react-contact_info-correspondence_address-copied");
        contactDetailsElements.put("Mailing Address - Postal Code", "#react-contact_info-correspondence_address-postal");
        contactDetailsElements.put("Mailing Address - Block No", "#react-contact_info-correspondence_address-block");
        contactDetailsElements.put("Mailing Address - Street", "#react-contact_info-correspondence_address-street");
        contactDetailsElements.put("Mailing Address - Level", "#react-contact_info-correspondence_address-level");
        contactDetailsElements.put("Mailing Address - Unit", "#react-contact_info-correspondence_address-unit");
        contactDetailsElements.put("Mailing Address - Building", "#react-contact_info-correspondence_address-building_name");
        this.POSTAL_CODE_INPUT = page.locator("#react-contact_info-correspondence_address-postal");
        this.UNITLEVEL = page.locator("#react-contact_info-correspondence_address-level");
        this.UNITNO = page.locator("#react-contact_info-correspondence_address-unit");
        this.SAME_AS_REGISTERED_ADDRESS_CHECKBOX = page.locator("#react-contact_info-correspondence_address-copied");
        this.SAVE_BUTTON = page.locator("//button[@class='bgp-btn bgp-btn-save' and @id='save-btn']");
    }

    public void clickOnContactDetailsMenu() {
        GET_NEW_GRANT.click();
    }

    public void verifyMainContactPersonFields(List<String> list) {
        page.waitForSelector(contactDetailsElements.get("Name"));
        for (int i = 1; i < list.size(); i++) {
            boolean isElementVisible = page.locator(contactDetailsElements.get(list.get(i))).isVisible();
            Assert.assertTrue(list.get(i) + " field is not displayed.", isElementVisible);
        }
    }

    public void enterPostalCode(String postalCode) {
        page.waitForSelector("#react-contact_info-correspondence_address-postal");
        POSTAL_CODE_INPUT.fill(postalCode);
        Response oneMapApiResponse = RestAssured.get("https://www.onemap.gov.sg/api/common/elastic/search?searchVal=" + postalCode + "&returnGeom=Y&getAddrDetails=Y&pageNum=1");
        Assert.assertEquals("Unexpected status code", 200, oneMapApiResponse.getStatusCode());
        JSONObject jsonResponse = new JSONObject(oneMapApiResponse.getBody().asString());
        Assert.assertTrue("Expected 'found' field in the response", jsonResponse.has("found"));
        JSONArray resultsArray = jsonResponse.getJSONArray("results");
        Assert.assertFalse("Expected at least one result in the 'results' array", resultsArray.isEmpty());
        JSONObject addressDetails = ((JSONArray) resultsArray).getJSONObject(0);
        UNITLEVEL.fill("10");
        UNITNO.fill("104");
        verifyPopulatedHouseNumberField(addressDetails.getString("BLK_NO"));
        verifyPopulatedStreetField(addressDetails.getString("ROAD_NAME"));
    }

    public void verifyPopulatedHouseNumberField(String houseNumber) {
        page.waitForSelector("#react-contact_info-correspondence_address-block");
        String actualHouseNumber = WebActions.getValuefromDisbaledElement("#react-contact_info-correspondence_address-block");
        Assert.assertEquals(houseNumber, actualHouseNumber);
    }

    public void verifyPopulatedStreetField(String street) {
        page.waitForSelector("#react-contact_info-correspondence_address-street");
        String actualStreet = WebActions.getValuefromDisbaledElement("#react-contact_info-correspondence_address-street");
        Assert.assertEquals(street, actualStreet);
    }

    public void selectSameAsRegisteredAddressCheckbox() {
        SAME_AS_REGISTERED_ADDRESS_CHECKBOX.click();
    }

    public void verifyMailingAddressAutoPopulated(List<String> list) {
        page.waitForSelector("#react-contact_info-correspondence_address-street");
        String postalCode = WebActions.getValuefromDisbaledElement("#react-contact_info-correspondence_address-postal");
        String blockNo = WebActions.getValuefromDisbaledElement("#react-contact_info-correspondence_address-block");
        String street = WebActions.getValuefromDisbaledElement("#react-contact_info-correspondence_address-street");
        String level = WebActions.getValuefromDisbaledElement("#react-contact_info-correspondence_address-level");
        String unit = WebActions.getValuefromDisbaledElement("#react-contact_info-correspondence_address-unit");
        Assert.assertEquals(list.get(0), postalCode);
        Assert.assertEquals(list.get(1), blockNo);
        Assert.assertEquals(list.get(2), street);
        Assert.assertEquals(list.get(3), level);
        Assert.assertEquals(list.get(4), unit);
    }

    public void verifyLetterOfOfferAddresseeFieldsEditable() {
        page.waitForSelector("#react-contact_info-offeree_name");
        Assert.assertTrue("Offer Letter Name field is not editable", page.locator("#react-contact_info-offeree_name").isEnabled());
        Assert.assertTrue("Offer Letter Job Title field is not editable", page.locator("#react-contact_info-offeree_designation").isEnabled());
        Assert.assertTrue("Offer Letter Email field is not editable", page.locator("#react-contact_info-offeree_email").isEnabled());
    }

    public void inputMainContactPersonDetails() {
        page.locator(contactDetailsElements.get("Name")).fill(faker.name().fullName());
        page.locator(contactDetailsElements.get("Job Title")).fill(faker.job().title());
        page.locator(contactDetailsElements.get("Contact No")).fill(faker.phoneNumber().subscriberNumber(8));
        page.locator(contactDetailsElements.get("Email")).fill(faker.internet().emailAddress());
        page.locator(contactDetailsElements.get("Alternate Contact Email")).fill(faker.internet().emailAddress());
    }

    public void clickSameAsMainContactCheckbox() {
        page.locator("#react-contact_info-copied").click();
    }

    public void verifyMainContactPersonDetailsPopulated() {
        page.waitForSelector("#react-contact_info-offeree_name");
        String name = WebActions.getValuefromDisbaledElement("#react-contact_info-offeree_name");
        String jobTitle = WebActions.getValuefromDisbaledElement("#react-contact_info-offeree_designation");
        String email = WebActions.getValuefromDisbaledElement("#react-contact_info-offeree_email");
        Assert.assertEquals(name, WebActions.getValuefromDisbaledElement("#react-contact_info-name"));
        Assert.assertEquals(jobTitle, WebActions.getValuefromDisbaledElement("#react-contact_info-designation"));
        Assert.assertEquals(email, WebActions.getValuefromDisbaledElement("#react-contact_info-primary_email"));
    }

    public void clickSaveButton() {
        SAVE_BUTTON.click();
    }

    public void refreshPage() {
        page.reload();
    }

    public void verifyReloadedValues() {
        page.waitForSelector("#react-contact_info-name");
        String name = WebActions.getValuefromDisbaledElement("#react-contact_info-name");
        String jobTitle = WebActions.getValuefromDisbaledElement("#react-contact_info-designation");
        String contactNo = WebActions.getValuefromDisbaledElement("#react-contact_info-phone");
        String email = WebActions.getValuefromDisbaledElement("#react-contact_info-primary_email");
        String alternateEmail = WebActions.getValuefromDisbaledElement("#react-contact_info-secondary_email");
        Assert.assertEquals(name, WebActions.getValuefromDisbaledElement("#react-contact_info-offeree_name"));
        Assert.assertEquals(jobTitle, WebActions.getValuefromDisbaledElement("#react-contact_info-offeree_designation"));
        Assert.assertEquals(contactNo, WebActions.getValuefromDisbaledElement("#react-contact_info-phone"));
        Assert.assertEquals(email, WebActions.getValuefromDisbaledElement("#react-contact_info-primary_email"));
        Assert.assertEquals(alternateEmail, WebActions.getValuefromDisbaledElement("#react-contact_info-secondary_email"));
        Assert.assertEquals(name, WebActions.getValuefromDisbaledElement("#react-contact_info-name"));
        Assert.assertEquals(jobTitle, WebActions.getValuefromDisbaledElement("#react-contact_info-designation"));
        Assert.assertEquals(email, WebActions.getValuefromDisbaledElement("#react-contact_info-primary_email"));
    }
}
