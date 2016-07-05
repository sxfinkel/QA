package com.aexp.qa.wsp.steps;

import com.aexp.qa.testframework.BaseStep;
import com.aexp.qa.testframework.actions.AlertAction;
import com.aexp.qa.testframework.actions.ClickOnElement;
import com.aexp.qa.testframework.actions.SendKeysToElement;
import com.aexp.qa.testframework.actions.SwitchToFrame;
import com.aexp.qa.testframework.actions.SyncTime;
import com.aexp.qa.testframework.actions.WaitForElement;
import com.aexp.qa.wsp.constants.WspElements;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Step responsible to initiate a new call.
 */
public class InitiateNewCallStep extends BaseStep {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(InitiateNewCallStep.class);

    /** Account number. **/
    private String accountNumber;

    /**
     * Checks pre conditions.
     */
    public void checkPreconditions() {
        Preconditions.checkNotNull(accountNumber, "null accountNumber");
    }

    /**
     * Step execution.
     * @param browserSession the browser session.
     */
    public void doExecute(final WebDriver browserSession) {
        //Sync screen for some time
        new SyncTime().doAction(browserSession);

        // Select OK if alert is present
        new AlertAction().setOption(AlertAction.Option.Accept).doAction(browserSession);
        // Navigates to Manage Payments tab
        browserSession.switchTo().defaultContent();

        // Select OK if alert is present
        new SyncTime().doAction(browserSession);
        new WaitForElement(WspElements.WSP_LINK_NEW_PHONE_CALL).doAction(browserSession);

        // New Phone Call
        new ClickOnElement(WspElements.WSP_LINK_NEW_PHONE_CALL).doAction(browserSession);

        // Select OK if alert is present
        new SyncTime().doAction(browserSession);
        new WaitForElement(WspElements.WSP_LINK_PHONE_CALL).doAction(browserSession);

        // Phone call link
        new ClickOnElement(WspElements.WSP_LINK_PHONE_CALL).doAction(browserSession);

        // Select OK if alert is present
        new SyncTime().doAction(browserSession);
        new AlertAction().setOption(AlertAction.Option.Accept).doAction(browserSession);
        browserSession.switchTo().defaultContent();

        // Switch to search frame
        new SwitchToFrame(WspElements.WSP_FRAME_SEARCH_ACCOUNT_NUMBER).doAction(browserSession);

        // Navigates to Manage Payments tab
        new WaitForElement(WspElements.WSP_FIELD_SEARCH_ACCOUNT_NUMBER).doAction(browserSession);

        // Fill the account number
        new SendKeysToElement(WspElements.WSP_FIELD_SEARCH_ACCOUNT_NUMBER, accountNumber).doAction(browserSession);

        new SyncTime().doAction(browserSession);
        new WaitForElement(WspElements.WSP_BUTTON_SEARCH_ACCOUNT_NUMBER).doAction(browserSession);

        // Search account number button
        new ClickOnElement(WspElements.WSP_BUTTON_SEARCH_ACCOUNT_NUMBER).doAction(browserSession);

        // Select OK if alert is present
        new AlertAction().setOption(AlertAction.Option.Accept).doAction(browserSession);
        // Navigates to Manage Payments tab
        browserSession.switchTo().defaultContent();
    }

    /**
     * Validates the step execution.
     * @param browserSession the browser session.
     */
    public void validateStepExecution(final WebDriver browserSession) {

    }

    /**
     * Validates the expected step output.
     * @param browserSession the browser session.
     */
    public void validateOutput(final WebDriver browserSession) {

    }

    /**
     * Sets the account number.
     * @param accountNumber the account number.
     * @return this.
     */
    public InitiateNewCallStep setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    /**
     * Gets the account number.
     * @return the account number.
     */
    public String getAccountNumber() {
        return accountNumber;
    }
}
