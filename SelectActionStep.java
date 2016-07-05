package com.aexp.qa.wsp.steps;

import com.aexp.qa.testframework.BaseStep;
import com.aexp.qa.testframework.actions.AlertAction;
import com.aexp.qa.testframework.actions.ClickOnElement;
import com.aexp.qa.testframework.actions.DoubleClickElement;
import com.aexp.qa.testframework.actions.SwitchToFrame;
import com.aexp.qa.wsp.constants.WspElements;
import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by cteix4 on 10/28/2015.
 */
public class SelectActionStep extends BaseStep {

    /**
     * Actions.
     */
    public enum Link {

        /** General. **/
        General(null),
        /** Payments. **/
        Payments(WspElements.WSP_TAB_PAYMENT),
        /** Statements. **/
        Statements(null),

        /** Under General Option. **/
        Notes(null),
        /** Under General Option. **/
        Transactions(null),
        /** Under Payments Option. **/
        ManagePaymentMethods(WspElements.WSP_SUB_TAB_MANAGE_PAYMENT_METHOD),
        /** Under Statements Option. **/
        ViewStatement(null);

        /** Xpath. **/
        private By element;

        /**
         * Link constructor.
         * @param element the element.
         */
        Link(final By element) {
            this.element = element;
        }

        /**
         * Gets the element.
         * @return the element.
         */
        public By getElement() {
            return element;
        }
    }

    /** name of the option in the left menu. **/
    private Link link;

    /** Sub-action in the lefct menu. **/
    private Link subLink;


    /**
     * Validates pre conditions.
     */
    public void checkPreconditions() {
        Preconditions.checkNotNull(link);
        Preconditions.checkNotNull(subLink);
    }

    /**
     * Executes step.
     * @param driver the web driver session to be used by the step.
     */
    public void doExecute(final WebDriver driver) {

        // Select OK if alert is present
        AlertAction checkAlertAndAct = new AlertAction();
        checkAlertAndAct.setOption(AlertAction.Option.Accept);
        checkAlertAndAct.doAction(driver);

        // Navigates to Manage Payments tab
        driver.switchTo().defaultContent();

        // Switch to search frame
        new SwitchToFrame(WspElements.WSP_FRAME_SEARCH_ACCOUNT_NUMBER).doAction(driver);

        // Payment tab Click
        new ClickOnElement(WspElements.WSP_TAB_PAYMENT).doAction(driver);

        // Double-click on Manage Payments tab
        new DoubleClickElement(WspElements.WSP_SUB_TAB_MANAGE_PAYMENT_METHOD).doAction(driver);

        // Wait and Switch to Management frame
        new SwitchToFrame(WspElements.WSP_FRAME_PAYMENT_MGMT).doAction(driver);
    }

    /**
     * Validates the step executions.
     * @param driver the web driver session to be used by the step.
     */
    public void validateStepExecution(final WebDriver driver) {

    }

    /**
     * Validates output.
     * @param driver the web driver session to be used by the step.
     */
    public void validateOutput(final WebDriver driver) {

    }

    /**
     * Sets the link.
     * @param link the link.
     * @return this step.
     */
    public SelectActionStep setLink(final Link link) {
        this.link = link;
        return this;
    }

    /**
     * Sets the sub-link.
     * @param subLink the link.
     * @return this step.
     */
    public SelectActionStep setSubLink(final Link subLink) {
        this.subLink = subLink;
        return this;
    }

    /**
     * Sets the sub-link.
     * @param link the link.
     * @param subLink the link.
     * @return this step.
     */
    public SelectActionStep seLinkSubLink(final Link link, final Link subLink) {
        this.link = link;
        this.subLink = subLink;
        return this;
    }

    /**
     * Gets the link.
     * @return the link.
     */
    public Link getLink() {
        return link;
    }

    /**
     * Gets the sub-link.
     * @return the sub-link.
     */
    public Link getSubLink() {
        return subLink;
    }
}
