package com.aexp.qa.wsp.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Unit test for CustomerUtil class..
 */
public class CustomerUtilTest {

    // VALIDATES DOB GENERATION

    @Test
    public void validateDateOfBirthGeneration() {
        String dob = CustomerUtils.generateDateOfBirth();
        CustomerUtils.validateDate("dd/mm/yyyy", dob);
    }

    @Test
    public void validateDateOfBirthIsRandom() {
        String dob1 = CustomerUtils.generateDateOfBirth();
        CustomerUtils.validateDate("dd/mm/yyyy", dob1);

        String dob2 = CustomerUtils.generateDateOfBirth();
        CustomerUtils.validateDate("dd/mm/yyyy", dob2);

        Assert.assertNotEquals(dob1, dob2, "Should not generate two same dates");
    }

    // VALIDATES CARD REPLACEMENT DATE VALIDATION

    @Test
    public void validateCardReplacementValidDate() {
        CustomerUtils.validateCardReplacementDate("01 Mar 2016");
    }

    @Test
    public void validateCardReplacementOneDigitDay() {
        CustomerUtils.validateCardReplacementDate("1 May 2000");
    }

    @Test
    public void validateCardReplacementTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        Date date = new Date();

        String ourFormat = formatter.format(date.getTime());
        CustomerUtils.validateCardReplacementDate(ourFormat);
    }

    @Test(expectedExceptions = {AssertionError.class},
            expectedExceptionsMessageRegExp = ".*Date 32 Mar 2015 NOT valid!.*")
    public void validateCardReplacementInvalidDay() {
        CustomerUtils.validateCardReplacementDate("32 Mar 2015");
    }

    @Test(expectedExceptions = {AssertionError.class},
            expectedExceptionsMessageRegExp = ".*Day in date 005 Dec 2015 not in a valid format!.*")
    public void validateCardReplacement3DigitDay() {
        CustomerUtils.validateCardReplacementDate("005 Dec 2015");
    }

    @Test(expectedExceptions = {AssertionError.class},
            expectedExceptionsMessageRegExp = ".*Date 29 Feb 2015 NOT valid!.*")
    public void validateCardReplacementInvalidFabDate() {
        CustomerUtils.validateCardReplacementDate("29 Feb 2015");
    }

    @Test(expectedExceptions = {AssertionError.class},
            expectedExceptionsMessageRegExp = ".*Date 31 Apr 2014 NOT valid!.*")
    public void validateCardReplacementInvalidDayOfTheMonth() {
        CustomerUtils.validateCardReplacementDate("31 Apr 2014");
    }

    @Test(expectedExceptions = {AssertionError.class},
            expectedExceptionsMessageRegExp = ".*should be in the past!.*")
    public void validateCardReplacementFutureDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        Date date = new Date();

        //Add one day
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();

        String ourFormat = formatter.format(date.getTime());
        CustomerUtils.validateCardReplacementDate(ourFormat);
    }

    @Test(expectedExceptions = {AssertionError.class},
            expectedExceptionsMessageRegExp = ".*Date 31-Apr-2014 NOT valid!.*")
    public void validateCardReplacementInvalidFormat() {
        CustomerUtils.validateCardReplacementDate("31-Apr-2014");
    }
}
