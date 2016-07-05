package com.aexp.qa.wsp.steps;

import com.aexp.qa.testframework.actions.ClickOnElement;
import com.aexp.qa.testframework.actions.SendKeysToElement;
import com.aexp.qa.wsp.constants.WspElements;
import com.aexp.qa.testframework.BaseStep;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Implements userName step..
 */
public class LoginStep extends BaseStep {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(LoginStep.class);

    /** User user name. **/
    private String userName;

    /** User password. **/
    private String password;

    /**
     * Checks pre conditions.
     */
    public void checkPreconditions() {
        Preconditions.checkNotNull(userName);
        Preconditions.checkNotNull(password);
    }

    /**
     * Step execution.
     * @param browserSession the browser session.
     */
    public void doExecute(final WebDriver browserSession) {
        logger.info("Login: username:  " + userName + ", password: " + password);

        // Sets username
        new SendKeysToElement(WspElements.WSP_FIELD_LOGIN_PAGE_USERNAME, userName).doAction(browserSession);

        // Sets the password
        new SendKeysToElement(WspElements.WSP_FIELD_LOGIN_PAGE_PASSWORD, password).doAction(browserSession);

        // Click to proceed with login
        new ClickOnElement(WspElements.WSP_BUTTON_LOGIN).doAction(browserSession);
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
//        String bodyContent = browserSession.findElement(By.tagName("body")).getText();
//        Assert.assertTrue(bodyContent.contains("Logout"), "Page should contain logout");
    }

    /**
     * Sets the user credentials.
     * @param userName the user name.
     * @param password the password.
     * @return the builder.
     */
    public LoginStep setCredential(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
        return this;
    }

    /**
     * Sets the user name used in the credential.
     * @param userName the user name.
     * @return the builder.
     */
    public LoginStep setUserName(final String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Sets the password.
     * @param password the password.
     * @return the builder.
     */
    public LoginStep setPassword(final String password) {
        this.password = password;
        return this;
    }

    /**
     * Gets the user name.
     * @return the user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the password.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
}
