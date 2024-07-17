package org.cognizant.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class OfficeSpaceRental {

    private static Page page;
    private final Locator ADD_NEW_OFFICE_RENTAL_ITEM;
    private final Locator OFFICE_RENTAL_DESCRIPTION;
    private final Locator OFFICE_RENTAL_DURATION;
    private final Locator OFFICE_MONTHLY_RENTAL_COST;
    private final Locator OFFICE_RENTAL_DOCUMENT_UPLOAD;
    private final Locator OFFICE_RENTAL_REMARKS;
    private final Faker faker;

    public OfficeSpaceRental(Page page) {
        OfficeSpaceRental.page = page;
        this.ADD_NEW_OFFICE_RENTAL_ITEM = page.locator("#react-project_cost-office_rentals-add-item");
        this.OFFICE_RENTAL_DESCRIPTION = page.locator("//textarea[@id='react-project_cost-office_rentals-0-description']");
        this.OFFICE_RENTAL_DURATION = page.locator("#react-project_cost-office_rentals-0-rental_duration");
        this.OFFICE_MONTHLY_RENTAL_COST = page.locator("#react-project_cost-office_rentals-0-amount_in_billing_currency");
        this.OFFICE_RENTAL_DOCUMENT_UPLOAD = page.locator("#react-project_cost-office_rentals-0-attachments-input");
        this.OFFICE_RENTAL_REMARKS = page.locator("//textarea[@id='react-project_cost-office_rentals-0-remarks']");
        this.faker = new Faker();
    }

    public void fillOfficeSpaceRentalDetails() {
        ADD_NEW_OFFICE_RENTAL_ITEM.click();
        OFFICE_RENTAL_DESCRIPTION.fill(faker.lorem().sentence());
        OFFICE_RENTAL_DURATION.fill("1");
        OFFICE_MONTHLY_RENTAL_COST.fill(String.valueOf(faker.number().randomDouble(2, 100, 1000)));
        OFFICE_RENTAL_DOCUMENT_UPLOAD.setInputFiles(Paths.get("src/test/resources/filestoUpload/Vendors.xlsx"));
        OFFICE_RENTAL_REMARKS.fill(faker.lorem().sentence());
    }
}
