package com.aexp.qa.wsp.testcases;

import com.aexp.qa.wsp.steps.LogOutStep;
import com.aexp.qa.wsp.steps.LoginStep;
import com.aexp.qa.wsp.steps.OpenURLStep;
import com.aexp.wsgcat.seleniumframework.BaseTest;
import com.aexp.wsgcat.seleniumframework.StepRunner;
import com.aexp.wsgcat.seleniumframework.TestingContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;import java.lang.Throwable;

/**
 * Sanity tests for login WSP.
 */
public class SanityTest extends BaseTest {

    @Test(groups = "US",
            dataProvider = "driverDataProvider")
    public void logoutTest(final WebDriver browserSession) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("wsp_general_login"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new LogOutStep())
                
                .start();
    }
}
