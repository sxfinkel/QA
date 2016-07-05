package com.aexp.qa.wsp.testcases;

import com.aexp.qa.wsp.constants.CallerType;
import com.aexp.qa.wsp.constants.DemographicError;
import com.aexp.qa.wsp.constants.UserGroup;
import com.aexp.qa.wsp.steps.CardmemberAuthenticationStep;
import com.aexp.qa.wsp.steps.InitiateNewCallStep;
import com.aexp.qa.wsp.steps.LoginStep;
import com.aexp.qa.wsp.steps.OpenURLStep;
import com.aexp.qa.wsp.steps.SelectCallerType;
import com.aexp.qa.wsp.steps.SelectTabStep;
import com.aexp.qa.wsp.steps.demographics.EditCustomerInfoStep;
import com.aexp.qa.wsp.steps.demographics.SelectDemographicsOptionStep;
import com.aexp.qa.wsp.steps.demographics.SubmitCustomerInfoChangesStep;
import com.aexp.qa.wsp.steps.demographics.ValidateCustomerInfoStep;
import com.aexp.qa.wsp.steps.demographics.ValidateEditCustomerInfoStep;
import com.aexp.wsgcat.seleniumframework.BaseTest;
import com.aexp.wsgcat.seleniumframework.StepRunner;
import com.aexp.wsgcat.seleniumframework.TestingContext;
import com.beust.jcommander.internal.Lists;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Testing authorization notes feature in WSP.
 */
public class DemographicsTest extends BaseTest {

    @Test(groups = {"US", "demographics", "menu", "open"},
            dataProvider = "driverDataProvider")
    public void verifyMenu_FullEditAccess_OpenAccount(final WebDriver browserSession) throws Throwable {
        verifyMenu(browserSession, "login_full_access", "open1_account");
    }

    @Test(groups = {"US", "demographics", "menu", "consumer"},
            dataProvider = "driverDataProvider")
    public void verifyMenu_FullEditAccess_ConsumerAccount(final WebDriver browserSession) throws Throwable {
        verifyMenu(browserSession, "login_full_access", "consumer1_account");
    }

    @Test(groups = {"US", "demographics", "menu", "open"},
            dataProvider = "driverDataProvider")
    public void verifyMenu_NoEditAccess_OpenAccount(final WebDriver browserSession) throws Throwable {
        verifyMenu(browserSession, "login_only_view_access", "open1_account");
    }

    @Test(groups = {"US", "demographics", "menu", "corporate"},
            dataProvider = "driverDataProvider")
    public void verifyMenu_NoEditAccess_CorporateAccount(final WebDriver browserSession) throws Throwable {
        verifyMenu(browserSession, "login_only_view_access", "corporate_account");
    }

    private void verifyMenu(final WebDriver browserSession, final String username, final String account) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue(username), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(account)))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .start();
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_FullEditAccess_OpenAccount1(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_full_access", UserGroup.FULL_EDIT_ACCESS, "open1");
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_FullEditAccess_OpenAccount2(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_full_access", UserGroup.FULL_EDIT_ACCESS, "open2");
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_NoEditAccess_OpenAccount3(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_only_view_access", UserGroup.ONLY_VIEW_ACCESS, "open3");
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_LimitedAccess_OpenAccount4(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_limited_access", UserGroup.LIMITED_EDIT_ACCESS, "open4");
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_NoEditAccess_OpenAccount5(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_only_view_access", UserGroup.ONLY_VIEW_ACCESS, "open5");
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_LimitedAccess_OpenAccount6(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_limited_access", UserGroup.LIMITED_EDIT_ACCESS, "open6");
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_NoEditAccess_OpenAccount7(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_only_view_access", UserGroup.ONLY_VIEW_ACCESS, "open7");
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_LimitedAccess_OpenAccount8(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_limited_access", UserGroup.LIMITED_EDIT_ACCESS, "open8");
    }

    @Test(groups = {"US", "demographics", "content", "consumer"},
            dataProvider = "driverDataProvider")
    public void verifyContent_FullEditAccess_ConsumerAccount1(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_full_access", UserGroup.FULL_EDIT_ACCESS, "consumer1");
    }

    @Test(groups = {"US", "demographics", "content", "consumer"},
            dataProvider = "driverDataProvider")
    public void verifyContent_NoEditAccess_ConsumerAccount2(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_only_view_access", UserGroup.ONLY_VIEW_ACCESS, "consumer2");
    }

    @Test(groups = {"US", "demographics", "content", "consumer"},
            dataProvider = "driverDataProvider")
    public void verifyContent_LimitedAccess_ConsumerAccount3(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_limited_access", UserGroup.LIMITED_EDIT_ACCESS, "consumer3");
    }

    @Test(groups = {"US", "demographics", "content", "consumer"},
            dataProvider = "driverDataProvider")
    public void verifyContent_FullEditAccess_ConsumerAccount4(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_full_access", UserGroup.FULL_EDIT_ACCESS, "consumer4");
    }

    @Test(groups = {"US", "demographics", "content", "consumer"},
            dataProvider = "driverDataProvider")
    public void verifyContent_NoEditAccess_ConsumerAccount5(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_only_view_access", UserGroup.ONLY_VIEW_ACCESS, "consumer5");
    }

    @Test(groups = {"US", "demographics", "content", "consumer"},
            dataProvider = "driverDataProvider")
    public void verifyContent_LimitedAccess_ConsumerAccount6(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_limited_access", UserGroup.LIMITED_EDIT_ACCESS, "consumer6");
    }

    @Test(groups = {"US", "demographics", "content", "corporate"},
            dataProvider = "driverDataProvider")
    public void verifyContent_FullEditAccess_CorporateAccount(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_full_access", UserGroup.FULL_EDIT_ACCESS, "corporate");
    }

    @Test(groups = {"US", "demographics", "content", "open"},
            dataProvider = "driverDataProvider")
    public void verifyContent_NoAccessDefined_OpenAccount1(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_access_not_defined", UserGroup.NOT_DEFINED, "open1");
    }

    @Test(groups = {"US", "demographics", "content", "open", "fraud"},
            dataProvider = "driverDataProvider")
    public void verifyContent_Fraud(final WebDriver browserSession) throws Throwable {
        verifyContent(browserSession, "login_full_access", UserGroup.FRAUD, "openFraud");
    }

    private void verifyContent(final WebDriver browserSession, final String username, final UserGroup userGroup, final String testID) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue(username), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateCustomerInfoStep().setUserGroup(userGroup).setExpectedDataByTestID(testID))
                .start();
    }

    @Test(groups = {"US", "demographics", "content", "callertype", "consumer"},
            dataProvider = "driverDataProvider")
    public void verifyCallerAccess_FullEditAccess_ConsumerAccount1_Spouse(final WebDriver browserSession) throws Throwable {
        verifyCallerAccess(browserSession, "login_full_access", UserGroup.FULL_EDIT_ACCESS, CallerType.SPOUSE, "consumer1");
    }

    private void verifyCallerAccess(final WebDriver browserSession, final String username, final UserGroup userGroup, final CallerType
            callerType, final String testID) throws Throwable {
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue(username), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new SelectCallerType().setCallerType(callerType))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateCustomerInfoStep().setUserGroup(userGroup).setExpectedDataByTestID(testID).setCallerType(callerType))
                .start();
    }

    ///////////////
    // EDIT TEST //
    ///////////////

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalNameAndCancelUCID_openAccount1(final WebDriver browserSession) throws Throwable {
        String testID = "open1";
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("login_full_access"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setCloseAuthentication(true).setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateCustomerInfoStep().setExpectedDataByTestID(testID))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateEditCustomerInfoStep().setExpectedDataByTestID(testID).setLegalNameToEditMode())
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalName_openAccount1(final WebDriver browserSession) throws Throwable {
        String testID = "open1";
        String username = "login_full_access";

        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue(username), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateEditCustomerInfoStep().setExpectedDataByTestID(testID).setLegalNameToEditMode())
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editCancelLegalName_openAccount1(final WebDriver browserSession) throws Throwable {
        String testID = "open1";
        String username = "login_full_access";

        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue(username), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.CANCEL_EDIT_LEGAL_NAME))
                .add(new ValidateCustomerInfoStep().setExpectedDataByTestID(testID))
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editNameOnCardAndCloseUCID_openAccount1(final WebDriver browserSession) throws Throwable {
        String testID = "open1";
        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("login_full_access"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_NAME_ON_CARD))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setCloseAuthentication(true).setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateCustomerInfoStep().setExpectedDataByTestID(testID))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_NAME_ON_CARD))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateEditCustomerInfoStep().setExpectedDataByTestID(testID).setNameOnCardToEditMode())
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editCancelNameOnCard_openAccount1(final WebDriver browserSession) throws Throwable {
        String testID = "open1";
        String username = "login_full_access";

        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue(username), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_NAME_ON_CARD))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.CANCEL_EDIT_NAME_ON_CARD))
                .add(new ValidateCustomerInfoStep().setExpectedDataByTestID(testID))
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editNameOnCard_openAccount1(final WebDriver browserSession) throws Throwable {
        String testID = "open1";
        String username = "login_full_access";

        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue(username), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_NAME_ON_CARD))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateEditCustomerInfoStep().setExpectedDataByTestID(testID).setNameOnCardToEditMode())
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalAndNameOnCard_openAccount2(final WebDriver browserSession) throws Throwable {
        editLegalAndNameOnCard(browserSession, "login_full_access", "open2");
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalAndNameOnCard_openAccount3(final WebDriver browserSession) throws Throwable {
        editLegalAndNameOnCard(browserSession, "login_full_access", "open3");
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalAndNameOnCard_openAccount4(final WebDriver browserSession) throws Throwable {
        editLegalAndNameOnCard(browserSession, "login_full_access", "open4");
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalAndNameOnCard_openAccount5(final WebDriver browserSession) throws Throwable {
        editLegalAndNameOnCard(browserSession, "login_full_access", "open5");
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalAndNameOnCard_openAccount6(final WebDriver browserSession) throws Throwable {
        editLegalAndNameOnCard(browserSession, "login_full_access", "open6");
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalAndNameOnCard_consumerAccount2(final WebDriver browserSession) throws Throwable {
        editLegalAndNameOnCard(browserSession, "login_full_access", "consumer2");
    }

    @Test(groups = {"US", "demographics", "edit", "open"},
            dataProvider = "driverDataProvider")
    public void editLegalAndNameOnCard_consumerAccount3(final WebDriver browserSession) throws Throwable {
        editLegalAndNameOnCard(browserSession, "login_full_access", "consumer3");
    }

    public void editLegalAndNameOnCard(final WebDriver webDriver, final String username, final String testID) throws Exception {
        new StepRunner(webDriver)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue(username), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_NAME_ON_CARD))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new ValidateEditCustomerInfoStep().setExpectedDataByTestID(testID).setLegalNameToEditMode().setNameOnCardToEditMode())
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
     public void editLegalName_verifyBlankFirstName_openAccount1(final WebDriver browserSession) throws Throwable {
        firstNameVerification(browserSession, "open1", " ", Lists.newArrayList(DemographicError.BLANK));
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
    public void editLegalName_verifyNumericFirstName_openAccount1(final WebDriver browserSession) throws Throwable {
        firstNameVerification(browserSession, "open1", "abcd3e", Lists.newArrayList(DemographicError.INVALID_CHARACTER));
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
    public void editLegalName_verifyDotSpaceFirstName_openAccount1(final WebDriver browserSession) throws Throwable {
        firstNameVerification(browserSession, "open1", "One. Name", Lists.newArrayList(DemographicError.DOT_SPACE));
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
    public void editLegalName_verifyNotAppliedFirstName_openAccount1(final WebDriver browserSession) throws Throwable {
        firstNameVerification(browserSession, "open1", "N/A", Lists.newArrayList(DemographicError.INVALID_CHARACTER));
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
    public void editLegalName_verifyInvalidFirstName_openAccount1(final WebDriver browserSession) throws Throwable {
        firstNameVerification(browserSession, "open1", "Mona|i", Lists.newArrayList(DemographicError.INVALID_CHARACTER));
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
    public void editLegalName_verifyErrorFirstName_openAccount1(final WebDriver browserSession) throws Throwable {
        firstNameVerification(browserSession, "open1", "3. N/A", Lists.newArrayList(DemographicError.INVALID_CHARACTER, DemographicError.DOT_SPACE));
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
    public void editLegalName_verifyLegalNameSize1_openAccount1(final WebDriver browserSession) throws Throwable {
        legalNameVerification(browserSession, "open1", "A", "", "CD", Lists.newArrayList(DemographicError.INVALID_INPUT),
                Lists.newArrayList(DemographicError.INVALID_INPUT), null);
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
    public void editLegalName_verifyLegalNameSize2_openAccount1(final WebDriver browserSession) throws Throwable {
        legalNameVerification(browserSession, "open1", "|", "", "CD",
                Lists.newArrayList(DemographicError.INVALID_INPUT, DemographicError.INVALID_CHARACTER),
                Lists.newArrayList(DemographicError.INVALID_INPUT), null);
    }

    @Test(groups = {"US", "demographics", "edit", "open", "verifyError"},
            dataProvider = "driverDataProvider")
    public void editLegalName_verifyInvalidLegalName_openAccount1(final WebDriver browserSession) throws Throwable {
        legalNameVerification(browserSession, "open1", "Mo1. a|", "Cris. |a", "N/A",
                Lists.newArrayList(DemographicError.INVALID_CHARACTER, DemographicError.DOT_SPACE),
                Lists.newArrayList(DemographicError.INVALID_CHARACTER, DemographicError.DOT_SPACE),
                Lists.newArrayList(DemographicError.INVALID_CHARACTER));
    }

    public void firstNameVerification(final WebDriver webDriver, final String testID, final String newFirstName, final List<DemographicError> errors) throws
            Throwable{
        legalNameVerification(webDriver, testID, newFirstName, null, null, errors, null, null);
    }

    public void legalNameVerification(final WebDriver webDriver, final String testID, final String newFirstName, final String newMiddleName,
                                      final String newLastName, final List<DemographicError> errorsFirstName, final List<DemographicError> errorsMiddleName,
                                      final List<DemographicError> errorLastName) throws Throwable{
        new StepRunner(webDriver)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("login_full_access"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new EditCustomerInfoStep()
                        .setNewFirstName(newFirstName).setNewMiddleName(newMiddleName).setNewLastName(newLastName)
                        .setToSkipVerifyValidation())
                .add(new ValidateEditCustomerInfoStep().setExpectedDataByTestID(testID).setLegalNameToEditMode()
                        .setExpectedFirstNameValue(newFirstName).setExpectedMiddleNameValue(newMiddleName).setExpectedLastNameValue(newLastName)
                        .setExpectedFirstNameError(errorsFirstName).setExpectedMiddleNameError(errorsMiddleName).setExpectedLastNameError(errorLastName))
                .start();
    }

    // TODO test no changes for Legal Name

    @Test(groups = {"US", "demographics", "edit", "open", "submitChange"},
            dataProvider = "driverDataProvider")
    public void submitPrefixChange_openAccount8(final WebDriver browserSession) throws Throwable {
        String testID = "open8";

        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("login_full_access"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new EditCustomerInfoStep().setToGenerateNewPrefix())
                .add(new SubmitCustomerInfoChangesStep())
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "consumer", "submitChange"},
            dataProvider = "driverDataProvider")
    public void submitFirstNameChange_consumerAccount4(final WebDriver browserSession) throws Throwable {
        String testID = "consumer4";

        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("login_full_access"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new EditCustomerInfoStep().setToGenerateNewFirstName())
                .add(new SubmitCustomerInfoChangesStep())
                .start();
    }

    @Test(groups = {"US", "demographics", "edit", "open", "submitChange"},
            dataProvider = "driverDataProvider")
    public void submitCustomerInformationFullChange_openAccount8(final WebDriver browserSession) throws Throwable {
        String testID = "open8";

        new StepRunner(browserSession)
                .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("wsp_url")).setExpectedTittle("American Express"))
                .add(new LoginStep().setCredential(TestingContext.getEnvironmentValue("login_full_access"), TestingContext.getEnvironmentValue("wsp_general_password")))
                .add(new InitiateNewCallStep().setAccountNumber(TestingContext.getEnvironmentValue(testID + "_account")))
                .add(new CardmemberAuthenticationStep())
                .add(new SelectTabStep().seLinkSubLink(SelectTabStep.Link.CustomerDetails, SelectTabStep.Link.CustomerInformation))
                .add(new CardmemberAuthenticationStep().setFrameToAuthenticateInActiveTab().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_LEGAL_NAME))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new SelectDemographicsOptionStep().setOption(SelectDemographicsOptionStep.Option.EDIT_NAME_ON_CARD))
                .add(new CardmemberAuthenticationStep().notSwitchFrame().setExpectedTabAfterAuthenticate("Customer Information"))
                .add(new EditCustomerInfoStep().setToGenerateNewLegalName().setToGenerateNameOnCard())
                .add(new SubmitCustomerInfoChangesStep().setNameOnCardAsUpdated())
                .start();
    }

    //    @DataProvider(name = "tc01DataProvider", parallel = true)
//    public Object[][] tc01DataProvider(Method testMethod) throws MalformedURLException {
//        Object[][] testData = new Object[][] {
//                {"wsp_general_login_1", "open_account"},
//                {"wsp_general_login_1", "consumer_account"},
//                {"wsp_general_login_2", "open_account"},
//                {"wsp_general_login_2", "corporate_account"}
//        };
//        return generateDataProvider(testMethod, testData);
//    }
}
