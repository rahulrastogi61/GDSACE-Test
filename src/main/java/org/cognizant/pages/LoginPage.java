package org.cognizant.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.cognizant.utils.WebActions;


public class LoginPage {
    private Page page;
    private final Locator USERNAME_EDITBOX;
    private final Locator PASSWORD_EDITBOX;
    private final Locator SUBMIT_BUTTON;
    private final Locator LOGIN_BUTTON;
    private final Locator ENTITY_ID;
    private final Locator USER_ID;
    private final Locator USER_ROLE;
    private final Locator USER_FULLNAME;
    private final Locator MANUAL_LOGIN_BUTTON;


    public LoginPage(Page page) {
        this.page = page;
        this.USERNAME_EDITBOX = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username"));
        this.PASSWORD_EDITBOX = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        this.SUBMIT_BUTTON = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("submit"));
        this.LOGIN_BUTTON = page.locator("#login-button");
        this.ENTITY_ID = page.locator("#entityId");
        this.USER_ID = page.locator("#userId");
        this.USER_ROLE = page.locator("#userRole");
        this.USER_FULLNAME = page.locator("#userFullName");
        this.MANUAL_LOGIN_BUTTON = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In"));
    }

    public void navigateToUrl(String url) {
        this.page.navigate(WebActions.getProperty("url"));
    }

    public void performLogin() {
        USERNAME_EDITBOX.fill(WebActions.getProperty("adminUsername"));
        PASSWORD_EDITBOX.fill(WebActions.decrypt("adminPassword"));
        SUBMIT_BUTTON.click();
        LOGIN_BUTTON.click();
        ENTITY_ID.fill(WebActions.getProperty("entityId"));
        USER_ID.fill(WebActions.getProperty("userId"));
        USER_ROLE.fill(WebActions.getProperty("userRole"));
        USER_FULLNAME.fill(WebActions.getProperty("userFullName"));
        MANUAL_LOGIN_BUTTON.click();
    }
}
