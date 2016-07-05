package com.aexp.qa.wsp.constants;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * List of available titles in demographics.
 * Uses "Title.valueOf(name)" to get the title value for the name you have.
 

 */
public enum Title {

    NOT_DEFINED("", "Select One"),
    DR("DR", "Doctor"),
    PROF("PROF", "Professor"),
    REV("REV", "Reverend"),
    CAPT("CAPT", "Captain"),

    // Invalid
    Dr("Dr", "Select One");

    private String viewValue;
    private String dropdownValue;

    private static final int TOTAL_OPTIONS = 5;

    Title(final String viewValue,final String dropdownValue) {
        this.viewValue = viewValue;
        this.dropdownValue=dropdownValue;
    }

    /**
     * Compares the expected name list against the given one.
     * @param   options the title list.
     * @return  Returns TRUE if content is the same, otherwise FALSE.
     */
    public static boolean compare(final List<WebElement> options) {
        Title[] expectedTitleList = values();
        if (TOTAL_OPTIONS != options.size()) {
            return false;
        }
        for (int i = 0; i < TOTAL_OPTIONS; i++) {
            if (!expectedTitleList[i].dropdownValue.equals(options.get(i).getText())) {
                return false;
            }
        }
        return true;
    }

    public static Title valueByDropdown(final String dropdownValue) {
        for (Title title :  values()) {
            if (title.dropdownValue.toString().equals(dropdownValue)) {
                return title;
            }
        }
        return null;
    }

    /**
     * Gets a different title from this.
     * @return the title.
     */
    public Title getDifferentTitle() {
        Title[] titles = values();
        int index;
        do {
            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            index = ThreadLocalRandom.current().nextInt(0, TOTAL_OPTIONS);
        } while (titles[index] == this);

        return titles[index];
    }

    /**
     * Gets the value for the view.
     * @return
     */
    public String getViewValue() {
        return viewValue;
    }

    /**
     * Gets the value for the dropDown.
     * @return
     */
    public String getDropDownValue() {
        return dropdownValue;
    }

}
