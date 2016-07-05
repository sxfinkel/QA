package com.aexp.qa.wsp.steps;

import com.aexp.qa.testframework.BaseStep;
import com.aexp.qa.testframework.actions.AlertAction;
import com.aexp.qa.testframework.actions.ClickOnElement;
import com.aexp.qa.testframework.actions.ValidateTitle;
import com.aexp.qa.testframework.actions.WaitForElement;
import com.aexp.qa.wsp.constants.WspElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Step responsible to log out from WSP.
 */
public class LogOutStep extends BaseStep {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(LogOutStep.class);

    /**
     * Checks pre conditions.
     */
    public void checkPreconditions() {
    }

    /**
     * Step execution.
     * @param browserSession the browser session.
     */
    public void doExecute(final WebDriver browserSession) {
    	new WaitForElement(WspElements.WSP_BUTTON_LOGOUT).doAction(browserSession);
        logger.info("Logging out...");
        browserSession.switchTo().defaultContent();
        String title = browserSession.getTitle();
        logger.info("Title of browser after login is..." + title);
        if (title != null) {
            logger.info("Validating the expected tittle: " + title);
            ValidateTitle validateTitle = new ValidateTitle();
            validateTitle.setExpectedTitle(title);
            validateTitle.doAction(browserSession);
        }

        // Click to proceed with logout
        ClickOnElement clickOnElement = new ClickOnElement();
        clickOnElement.setElement(WspElements.WSP_BUTTON_LOGOUT);
        clickOnElement.doAction(browserSession);

        // Select OK option in alert
        AlertAction alert = new AlertAction();
        alert.setOption(AlertAction.Option.Accept);
        alert.doAction(browserSession);
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
}
