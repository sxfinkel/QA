package com.aexp.qa.wsp.steps;

import com.aexp.qa.testframework.actions.ClickOnElement;
import com.aexp.qa.testframework.actions.SendKeysToElement;
import com.aexp.qa.testframework.actions.SyncTime;
import com.aexp.qa.testframework.actions.WaitForElement;
import com.aexp.qa.testframework.BaseStep;
import com.aexp.qa.wsp.constants.WspElements;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Step responsible to add a bank account.
 */
public class AddBankAccountStep extends BaseStep {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(AddBankAccountStep.class);

    /** Bank routing number. **/
    private String routingNumber;

    /** Bank Account number. **/
    private String acctNumber;

    /**
     * Checks pre conditions.
     */
    public void checkPreconditions() {
        Preconditions.checkNotNull(routingNumber, "null routingNumber");
        Preconditions.checkNotNull(acctNumber, "null acctNumber");
    }

    /**
     * Step execution.
     * @param browserSession the browser session.
     */
    public void doExecute(final WebDriver browserSession) {
        // Add Bank Details

        //Scroll down Bar
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Error creating robot", e);
        }
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);

        // Click Add Bank link
        new ClickOnElement(WspElements.WSP_LINK_ADD_BANK).doAction(browserSession);

        //Sync screen for some time
        SyncTime sync = new SyncTime();
        sync.doAction(browserSession);

        // Click Add Bank link
        new ClickOnElement(WspElements.WSP_RADIO_ADD_BANK_ACCT).doAction(browserSession);
        sync.doAction(browserSession);

        new WaitForElement(WspElements.WSP_FIELD_BANK_ROUTING_NUMBER).doAction(browserSession);

        new SendKeysToElement(WspElements.WSP_FIELD_BANK_ROUTING_NUMBER, routingNumber).doAction(browserSession);
        sync.doAction(browserSession);

        new SendKeysToElement(WspElements.WSP_FIELD_BANK_ACCT_NUMBER, acctNumber).doAction(browserSession);
        sync.doAction(browserSession);

        new SendKeysToElement(WspElements.WSP_FIELD_BANK_ACCT_NO_CONF, acctNumber).doAction(browserSession);
        sync.doAction(browserSession);

        // Click Bank Type
        new ClickOnElement(WspElements.WSP_RADIO_ACCT_TYPE_CHECKINGS).doAction(browserSession);
        sync.doAction(browserSession);

        new ClickOnElement(WspElements.WSP_RADIO_ACCT_OWNER_BUSINESS).doAction(browserSession);
        sync.doAction(browserSession);

        // Click on Process Button
        new ClickOnElement(WspElements.WSP_BUTTON_BANK_ACCT_PROCEED).doAction(browserSession);
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
     * Sets the bank account details.
     * @param routingNumber the routing number.
     * @param acctNumber the account number
     * @return this step.
     */
    public AddBankAccountStep setBankAccountDetails(final String routingNumber, final String acctNumber) {
        this.routingNumber = routingNumber;
        this.acctNumber = acctNumber;
        return this;
    }
}
