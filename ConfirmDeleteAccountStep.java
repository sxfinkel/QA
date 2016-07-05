package com.aexp.qa.wsp.steps;

import com.aexp.qa.testframework.BaseStep;
import com.aexp.qa.testframework.actions.ClickOnElement;
import com.aexp.qa.testframework.actions.SyncTime;
import com.aexp.qa.wsp.constants.WspElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Step responsible to add a bank account.
 */
public class ConfirmDeleteAccountStep extends BaseStep {

	/** Logger. **/
	public static final Logger logger = Logger.getLogger(ConfirmDeleteAccountStep.class);

	/**
	 * Step execution.
	 * @param browserSession the browser session.
	 */
	public void doExecute(final WebDriver browserSession) {
		// Delete Bank Confirmation

		//Sync screen for some time
		SyncTime sync = new SyncTime();
		sync.doAction(browserSession);

		// Select Proceed button
		new ClickOnElement(WspElements.WSP_BUTTON_PROCEED_MODIFY).doAction(browserSession);

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
	 * Checks pre conditions.
	 */
	public void checkPreconditions() {

	}
}
