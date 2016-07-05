package com.aexp.qa.wsp.testcases;

import com.aexp.qa.wsp.steps.InitiateNewCallStep;
import com.aexp.qa.wsp.steps.OpenURLStep;
import com.aexp.qa.wsp.steps.SecurityLoginStep;
import com.aexp.qa.wsp.steps.SelectTabStep;
import com.aexp.wsgcat.seleniumframework.BaseTest;
import com.aexp.wsgcat.seleniumframework.StepRunner;
import com.aexp.wsgcat.seleniumframework.TestingContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Test for Retrieve Account details WSP.
 */
public class RetrieveAccountTest extends BaseTest {

    @Test(groups = "US",
    dataProvider = "driverDataProvider")
    public void loadAccountTest(final WebDriver browserSession) throws Throwable {
        new StepRunner(browserSession)
		        .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")))
		        .add(new SecurityLoginStep().setCredential(TestingContext.getEnvironmentValue("wsp_general_login"), TestingContext.getEnvironmentValue
                        ("wsp_general_password")))
		        .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue("wsp_account_number")))
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.Payments, SelectTabStep.Link.ManagePaymentMethods))
                .start();
    }
}
