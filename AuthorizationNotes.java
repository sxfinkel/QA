package com.aexp.qa.wsp.testcases;

import com.aexp.qa.wsp.steps.AddTravelStatusStep;
import com.aexp.qa.wsp.steps.CardmemberAuthenticationStep;
import com.aexp.qa.wsp.steps.EndCallStep;
import com.aexp.qa.wsp.steps.InitiateNewCallStep;
import com.aexp.qa.wsp.steps.LogOutStep;
import com.aexp.qa.wsp.steps.LoginStep;
import com.aexp.qa.wsp.steps.OpenURLStep;
import com.aexp.qa.wsp.steps.RemoveTravelStatusStep;
import com.aexp.qa.wsp.steps.SelectTabStep;
import com.aexp.qa.wsp.steps.ValidateAuthNotesStep;
import com.aexp.qa.wsp.steps.ValidateTravelStatusStep;
import com.aexp.wsgcat.seleniumframework.BaseTest;
import com.aexp.wsgcat.seleniumframework.StepRunner;
import com.aexp.wsgcat.seleniumframework.TestingContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Testing authorization notes feature in WSP.
 */
public class AuthorizationNotes extends BaseTest {

    @Test(groups = "US",
            dataProvider = "driverDataProvider")
    public void tc01_NavigationToAuthNotes(final WebDriver browserSession) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("wsp_general_login"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue("tc01_account_number")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().setSubLink(SelectTabStep.Link.AuthNotes))
                .add(new EndCallStep())
                .add(new LogOutStep())
                .start();
    }

    @Test(groups = "US",
            dataProvider = "driverDataProvider")
    public void tc02_AuthNotesPageLoads(final WebDriver browserSession) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("wsp_general_login"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue("tc02_account_number")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().setSubLink(SelectTabStep.Link.AuthNotes))
                .add(new ValidateAuthNotesStep().setExpectedCardLastFiveDigits(TestingContext.getEnvironmentValue("tc02_card_last_5_digits"))
                        .setExpectedCardProductDescription(TestingContext.getEnvironmentValue("tc02_card_product_description")))
                .add(new EndCallStep())
                .add(new LogOutStep())
                .start();
    }

    @Test(groups = {"US", "inprogress"},
            dataProvider = "driverDataProvider")
    public void tc03_AddTravelStatus(final WebDriver browserSession) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("wsp_general_login"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue("tc03_account_number")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().setSubLink(SelectTabStep.Link.AuthNotes))
                .add(new AddTravelStatusStep())
                .add(new EndCallStep())
                .add(new LogOutStep())
                .start();
    }

    @Test(groups = {"US", "inprogress"},
            dataProvider = "driverDataProvider", dependsOnMethods = "tc05_ValidateAddedTravelStatus")
    public void tc04_RemoveTravelStatus(final WebDriver browserSession) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("wsp_general_login"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue("tc03_account_number")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().setSubLink(SelectTabStep.Link.AuthNotes))
                .add(new RemoveTravelStatusStep())
                .add(new EndCallStep())
                .add(new LogOutStep())
                .start();
    }

    @Test(groups = "US",
            dataProvider = "driverDataProvider", dependsOnMethods = "tc03_AddTravelStatus")
    public void tc05_ValidateAddedTravelStatus(final WebDriver browserSession) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("wsp_general_login"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue("tc03_account_number")))
                .add(new CardmemberAuthenticationStep())
                .add(new ValidateTravelStatusStep().setIsTravelStatusExpected(true))
                .add(new EndCallStep())
                .add(new LogOutStep())
                .start();
    }

    @Test(groups = "US",
            dataProvider = "driverDataProvider", dependsOnMethods = "tc04_RemoveTravelStatus")
    public void tc06_ValidateRemovedTravelStatus(final WebDriver browserSession) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("wsp_general_login"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue("tc03_account_number")))
                .add(new CardmemberAuthenticationStep())
                .add(new ValidateTravelStatusStep().setIsTravelStatusExpected(false))
                .add(new EndCallStep())
                .add(new LogOutStep())
                .start();
    }
}
