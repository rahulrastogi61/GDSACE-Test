package org.cognizant.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class CostPage {

    private static Page page;
    private final Locator COST_MENU;
    private final Locator THIRD_PARTY_VENDOR;
    private final Locator OFFICE_SPACE_RENTAL;
    private final Locator SALARY_HEADER;
    private final Locator SAVE_BUTTON;
    private static ThirdPartyVendor thirdPartyVendor;
    private static OfficeSpaceRental officeSpaceRental;
    private static Salary salary;

    public CostPage(Page page) {
        CostPage.page = page;
        this.COST_MENU = page.locator("//span[@class='menu-text' and text()='Cost']");
        this.THIRD_PARTY_VENDOR = page.locator("#react-project_cost-vendors-accordion-header");
        this.OFFICE_SPACE_RENTAL = page.locator("#react-project_cost-office_rentals-accordion-header");
        this.SALARY_HEADER = page.locator("#react-project_cost-salaries-accordion-header");
        this.SAVE_BUTTON = page.locator("//button[@id='save-btn']");
        thirdPartyVendor = new ThirdPartyVendor(page);
        officeSpaceRental = new OfficeSpaceRental(page);
        salary = new Salary(page);
    }

    public void clickCostMenu() {
        COST_MENU.click();
    }

    public void fillCostDetails() {
        THIRD_PARTY_VENDOR.click();
        thirdPartyVendor.fillVendorDetails();
        page.locator("#react-project_cost-vendors-0-title").click();
        OFFICE_SPACE_RENTAL.click();
        officeSpaceRental.fillOfficeSpaceRentalDetails();
        OFFICE_SPACE_RENTAL.click();
        //page.locator("#react-project_cost-office_rentals-0-title").click();
        SALARY_HEADER.click();
        salary.fillSalaryDetails();
        page.locator("#react-project_cost-salaries-0-title").click();
    }

    public void clickSaveButton() {
        SAVE_BUTTON.click();
    }
}
