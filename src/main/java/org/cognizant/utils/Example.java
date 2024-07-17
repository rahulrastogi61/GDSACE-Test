package org.cognizant.utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;


public class Example {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://ff-mgmt-users.auth.ap-southeast-1.amazoncognito.com/login?client_id=7k2qd7amucdgr7ajsmgb14dmmq&redirect_uri=https%3A%2F%2Fqa-internet.bgp.onl%2Foauth2%2Fidpresponse&response_type=code&scope=openid&state=4bO6sbIQoRvyJns4TXR4GiH9Bmp8q2cp5QuKFfXDtErKVs5zjHkGIZzqbZfRCRK7zmCAQy0v%2BgnMxLgeaiBJoTlykQa%2FoUz9dK%2FohUmpHJjdDEdImTIqn%2Fe5yOJs1S%2FdxzVHSHKwI345qMkBX8P2j%2FRe89aJvKDyPUiEdDZvbTP9jAstw5w1ZrcHHrEYImzM1DaqgLrXFCggD18erGkktw9unlxiTD4kbKr53bIpPKvs03zM32XQvA%3D%3D");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("temp-govtech");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("temp-govtechbgPB3Aw3SomeGvtF@lk!");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).press("Meta+z");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("bgPB3Aw3SomeGvtF@lk!");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("submit")).click();
            page.locator("a").filter(new Locator.FilterOptions().setHasText("LOG IN")).click();
            page.getByPlaceholder("e.g. R90SS0001A").click();
            page.getByPlaceholder("e.g. R90SS0001A").fill("BGPQETECH5");
            page.getByPlaceholder("e.g. S1234567D").click();
            page.getByPlaceholder("e.g. S1234567D").fill("S1234567D");
            page.getByPlaceholder("e.g. Acceptor").click();
            page.getByPlaceholder("e.g. Acceptor").fill("Acceptor");
            page.getByPlaceholder("e.g. John Grisham").click();
            page.getByPlaceholder("e.g. John Grisham").fill("Tan Ah Kow");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In")).click();
            page.locator("//*[@id='dashboard-menubox-app-apply-grant']/section/div/div[2]/h4").click();
//            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get new grant Apply for a")).click();
            page.getByText("IT", new Page.GetByTextOptions().setExact(true)).click();
//            page.getByText("Bring my business overseas or", new Page.GetByTextOptions().setExact(true)).click();
            page.locator("label").filter(new Locator.FilterOptions().setHasText("Bring my business overseas or")).locator("div").first().click();
            page.getByText("Market Readiness AssistanceSet up an overseas market, identify overseas").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
            page.locator(".radiobutton").first().click();
            page.locator("div:nth-child(6) > .form-group > .controls > label > .radiobutton").first().click();
            page.locator("div:nth-child(7) > .form-group > .controls > label > .radiobutton").first().click();
            page.locator("div:nth-child(8) > .form-group > .controls > label > .radiobutton").first().click();
            page.locator("div:nth-child(9) > .form-group > .controls > label > .radiobutton").first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
            page.getByRole(AriaRole.NAVIGATION).click();
        }
    }
}