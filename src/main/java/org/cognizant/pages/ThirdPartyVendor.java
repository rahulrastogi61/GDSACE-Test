package org.cognizant.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class ThirdPartyVendor {

    private static Page page;
    private final Locator LOCAL_VENDOR;
    private final Locator OVERSEAS_VENDOR;
    private final Locator NAME_OF_VENDOR;
    private final Locator VENDOR_FILE_UPLOAD;
    private final Locator ESTIMATED_COST;
    private final Locator VENDOR_REMARKS;
    private final Locator ADD_VENDOR_BUTTON;
    private final Faker faker;
    public ThirdPartyVendor(Page page) {
        ThirdPartyVendor.page = page;
        this.LOCAL_VENDOR = page.locator("#react-project_cost-vendors-0-local_vendor-true");
        this.OVERSEAS_VENDOR = page.locator("#react-project_cost-vendors-0-local_vendor-false");
        this.NAME_OF_VENDOR = page.locator("#react-project_cost-vendors-0-vendor_name");
        this.VENDOR_FILE_UPLOAD = page.locator("#react-project_cost-vendors-0-attachments-input");
        this.ESTIMATED_COST = page.locator("#react-project_cost-vendors-0-amount_in_billing_currency");
        this.VENDOR_REMARKS = page.locator("//textarea[@id='react-project_cost-vendors-0-remarks']");
        this.ADD_VENDOR_BUTTON = page.locator("#react-project_cost-vendors-add-item");
        this.faker = new Faker();
    }

    public void fillVendorDetails() {
        ADD_VENDOR_BUTTON.click();
        LOCAL_VENDOR.click();
        NAME_OF_VENDOR.fill("AUTHENTIC TECH");
        page.keyboard().press("Enter");
        page.waitForSelector("//span[@id=\"vendor-row-sub\"]");
        page.locator("//span[@id=\"vendor-row-sub\"]").nth(0).click();
        VENDOR_FILE_UPLOAD.setInputFiles(Paths.get("src/test/resources/filestoUpload/vendors.xlsx"));
        ESTIMATED_COST.fill(String.valueOf(faker.number().randomDouble(2, 100, 1000)));
        VENDOR_REMARKS.fill(faker.lorem().sentence());
    }
}
