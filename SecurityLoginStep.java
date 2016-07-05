package com.aexp.qa.wsp.steps;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import com.aexp.qa.testframework.BaseStep;
import com.aexp.qa.testframework.TestingContext;
import com.aexp.qa.testframework.actions.SyncTime;
import com.aexp.qa.testframework.actions.ValidateTitle;
import com.aexp.qa.testframework.constants.Constants;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Implements alert security login step.
 */
public class SecurityLoginStep extends BaseStep {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(SecurityLoginStep.class);

    /** User user name. **/
    private String username;

    /** User password. **/
    private String password;

    /** Expected titled in th opened URL. **/
    private String expectedTittle;

    /**
     * Checks pre conditions.
     */
    public void checkPreconditions() {
        Preconditions.checkNotNull(username);
        Preconditions.checkNotNull(password);
    }

    /**
     * Step execution.
     * @param browserSession the browser session.
     */
    public void doExecute(final WebDriver browserSession) {
    	logger.info("Open the URL");
    	browserSession.navigate().to((TestingContext.getEnvironmentValue("wsp_url")));
        logger.info("Login: username:  " + username + ", password: " + password);

        // Sets credentials
        try {
            // Code to Login
            logger.info("Provide inputs to Alert function now");
            WebDriverWait wait = new WebDriverWait(browserSession, Constants.MAXIMUM_WAIT_TIME);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = browserSession.switchTo().alert();
            alert.sendKeys(username);

            // create robot for keyboard operations
            Robot rb = new Robot();
            new SyncTime().doAction(browserSession);
            //tab to password entry field
            rb.keyPress(KeyEvent.VK_TAB);
            rb.keyRelease(KeyEvent.VK_TAB);
            new SyncTime().doAction(browserSession);
            //Enter password by ctrl-v
            StringSelection pwd = new StringSelection(password);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);

            //press enter
            alert.accept();

        } catch (Exception e) {
            logger.error("Not able to log in: " + e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Title of the browser now is: " + browserSession.getTitle());
    }

    /**
     * Validates the step execution.
     * @param browserSession the browser session.
     */
    public void validateStepExecution(final WebDriver browserSession) {
        if (expectedTittle != null) {
            logger.info("Validating the expected tittle: " + expectedTittle);
            ValidateTitle validateTitle = new ValidateTitle();
            validateTitle.setExpectedTitle(expectedTittle);
            validateTitle.doAction(browserSession);
        }
    }

    /**
     * Validates the expected step output.
     * @param browserSession the browser session.
     */
    public void validateOutput(final WebDriver browserSession) {
//        new ValidateBodyContent("Logout").doAction(browserSession);
    }

    /**
     * Sets the user credentials.
     * @param username the user name.
     * @param password the password.
     * @return the builder.
     */
    public SecurityLoginStep setCredential(final String username, final String password) {
        this.username = username;
        this.password = password;
        return this;
    }

    /**
     * Sets the user name used in the credential.
     * @param userName the user name.
     * @return the builder.
     */
    public SecurityLoginStep setUsername(final String userName) {
        this.username = userName;
        return this;
    }

    /**
     * Sets the password.
     * @param password the password.
     * @return the builder.
     */
    public SecurityLoginStep setPassword(final String password) {
        this.password = password;
        return this;
    }

    /**
     * Gets the user name.
     * @return the user name.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the expected title.
     * @return the expected title.
     */
    public String getExpectedTittle() {
        return expectedTittle;
    }

    /**
     * Sets the expected title.
     * @param expectedTittle the expected title.
     * @return the builder.
     */
    public SecurityLoginStep setExpectedTittle(final String expectedTittle) {
        this.expectedTittle = expectedTittle;
        return this;
    }
}
