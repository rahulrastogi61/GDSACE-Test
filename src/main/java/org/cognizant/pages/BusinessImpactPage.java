package org.cognizant.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BusinessImpactPage {
    private static Page page;
    private final Locator BUSINESS_IMPACT_MENU;
    private final Faker faker;
    private final Locator FY_END_DATE;
    private final Locator OVERSEAS_SALES;
    private final Locator OVERSEAS_SALES1;
    private final Locator OVERSEAS_SALES2;
    private final Locator OVERSEAS_SALES3;
    private final Locator OVERSEAS_INVESTMENTS;
    private final Locator OVERSEAS_INVESTMENTS1;
    private final Locator OVERSEAS_INVESTMENTS2;
    private final Locator OVERSEAS_INVESTMENTS3;
    private final Locator RATIONALE_REMARKS;
    private final Locator BENEFITS_REMARKS;
    private final Locator SAVE_BUTTON;

    public BusinessImpactPage(Page page) {
        BusinessImpactPage.page = page;
        this.BUSINESS_IMPACT_MENU = page.locator("//span[@class='menu-text' and text()='Business Impact']");
        this.faker = new Faker();
        this.FY_END_DATE = page.locator("//input[@id='react-project_impact-fy_end_date_0']");
        this.OVERSEAS_SALES = page.locator("//input[@id='react-project_impact-overseas_sales_0']");
        this.OVERSEAS_SALES1 = page.locator("//input[@id='react-project_impact-overseas_sales_1']");
        this.OVERSEAS_SALES2 = page.locator("//input[@id='react-project_impact-overseas_sales_2']");
        this.OVERSEAS_SALES3 = page.locator("//input[@id='react-project_impact-overseas_sales_3']");
        this.OVERSEAS_INVESTMENTS = page.locator("//input[@id='react-project_impact-overseas_investments_0']");
        this.OVERSEAS_INVESTMENTS1 = page.locator("//input[@id='react-project_impact-overseas_investments_1']");
        this.OVERSEAS_INVESTMENTS2 = page.locator("//input[@id='react-project_impact-overseas_investments_2']");
        this.OVERSEAS_INVESTMENTS3 = page.locator("//input[@id='react-project_impact-overseas_investments_3']");
        this.RATIONALE_REMARKS = page.locator("//textarea[@id='react-project_impact-rationale_remarks']");
        this.BENEFITS_REMARKS = page.locator("//textarea[@id='react-project_impact-benefits_remarks']");
        this.SAVE_BUTTON = page.locator("//button[@id='save-btn']");
    }

    public void clickBusinessImpactMenu() {
        BUSINESS_IMPACT_MENU.click();
    }

    public void fillBusinessImpactDetails() {
        page.waitForSelector("//input[@id='react-project_impact-fy_end_date_0']");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Calendar futureDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();
        maxDate.set(2023, Calendar.DECEMBER, 31);
        futureDate.add(Calendar.DAY_OF_MONTH, (int) (Math.random() * 20));
        if (futureDate.after(maxDate)) {
            futureDate = maxDate;
        }
        String fyEndDate = dateFormat.format(futureDate.getTime());
        FY_END_DATE.fill(fyEndDate);
        OVERSEAS_SALES.fill(faker.number().digits(5));
        OVERSEAS_SALES1.fill(faker.number().digits(2));
        OVERSEAS_SALES2.fill(faker.number().digits(2));
        OVERSEAS_SALES3.fill(faker.number().digits(2));
        OVERSEAS_INVESTMENTS.fill(faker.number().digits(5));
        OVERSEAS_INVESTMENTS1.fill(faker.number().digits(2));
        OVERSEAS_INVESTMENTS2.fill(faker.number().digits(2));
        OVERSEAS_INVESTMENTS3.fill(faker.number().digits(2));
        RATIONALE_REMARKS.fill(faker.lorem().sentence());
        BENEFITS_REMARKS.fill(faker.lorem().sentence());
    }

    public void clickSaveButton() {
        SAVE_BUTTON.click();
    }
}
