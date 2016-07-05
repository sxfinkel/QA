package com.aexp.qa.wsp.testcases;
import com.aexp.wsgcat.seleniumframework.BaseTest;
import com.aexp.wsgcat.seleniumframework.TestingContext;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Basic validations for the framework.
 */
public class FrameworkValidation extends BaseTest{

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(FrameworkValidation.class);

    @Test(dataProvider = "driverDataProvider")
    public void driverTest(final WebDriver browserSession) throws Throwable {
        logger.info("Validating web driver...");
        Assert.assertNotNull(browserSession, "null browserSession");
        browserSession.quit();
    }

    @Test
    public void testingContextTest() throws Throwable {
        logger.info("Validating testing context...");
        TestingContext.getMarketCode();
    }

    @Test
    public void testingFetchEnvironmentConfigurationTest() throws Throwable {
        logger.info("Validating fetch environment variable");
        TestingContext.getEnvironmentValue("test");
    }
}
