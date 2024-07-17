package org.cognizant.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class Salary {

    private static Page page;
    private final Locator ADD_SALARY_ITEM;
    private final Locator EMPLOYEE_NAME;
    private final Locator EMPLOYEE_DESIGNATION;
    private final Locator FIN_NUMBER;
    private final Locator NATIONALITY_TYPE;
    private final Locator ROLE_IN_PROJECT;
    private final Locator PROJECT_INVOLMENT;
    private final Locator MONTHLY_SALARY;
    private final Locator SALARY_UPLOAD;
    private final Locator SALARY_REMARKS;
    private final Faker faker;

    public Salary(Page page) {
        Salary.page = page;
        this.ADD_SALARY_ITEM = page.locator("#react-project_cost-salaries-add-item");
        this.EMPLOYEE_NAME = page.locator("#react-project_cost-salaries-0-name");
        this.EMPLOYEE_DESIGNATION = page.locator("#react-project_cost-salaries-0-designation");
        this.FIN_NUMBER = page.locator("#react-project_cost-salaries-0-nric");
        this.NATIONALITY_TYPE = page.locator("#react-project_cost-salaries-0-nationality");
        this.ROLE_IN_PROJECT = page.locator("//textarea[@id='react-project_cost-salaries-0-project_role']");
        this.PROJECT_INVOLMENT = page.locator("#react-project_cost-salaries-0-involvement_months");
        this.MONTHLY_SALARY = page.locator("#react-project_cost-salaries-0-salary_in_billing_currency");
        this.SALARY_UPLOAD = page.locator("#react-project_cost-salaries-0-attachments-input");
        this.SALARY_REMARKS = page.locator("//textarea[@id='react-project_cost-salaries-0-remarks']");
        this.faker = new Faker();
    }

    public void fillSalaryDetails() {
        ADD_SALARY_ITEM.click();
        EMPLOYEE_NAME.fill(faker.name().fullName());
        EMPLOYEE_DESIGNATION.fill(faker.job().position());
        FIN_NUMBER.fill(faker.idNumber().valid());
        NATIONALITY_TYPE.fill("Singaporean");
        page.keyboard().press("Enter");
        ROLE_IN_PROJECT.fill(faker.lorem().sentence());
        PROJECT_INVOLMENT.fill("1");
        MONTHLY_SALARY.fill(String.valueOf(faker.number().randomDouble(2, 100, 1000)));
        SALARY_UPLOAD.setInputFiles(Paths.get("src/test/resources/filestoUpload/Vendors.xlsx"));
        SALARY_REMARKS.fill(faker.lorem().sentence());
    }
}
