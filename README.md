# QA
qa/src/main/java/com/aexp/qa/wsp/constants
elements folder
callertype
cardtype
demoerror
language
nationalid
prefix
suffix
title
usergroup

=============
project setup
 Pre-Requirements:
Install jdk 1.7 and add into your environment variables.
Install maven (recommended 3.1.1). Do not forget to add into your environment variables.
Download the zip file for the project set up: 
Unzip file into your workspace.
Rename the folder to your project name.
Rename the project name in pom file. Replace "project-name".
Rename folder src/main/java/com/aexp/qa/<project-name>.
Rename all the packages name for all steps: src/main/java/com/aexp/qa/<project-name>/steps/.
Rename all the packages name for all constant classes: src/main/java/com/aexp/qa/<project-name>/constants/.
Open the command prompt, access your project folder.
Build your project: "mvn install". 
=======================




import into ide
Make sure you already completed the project set up!
Your IDE** already configured, supporting MAVEN and TestNG. 
** You can use any IDE. In this tutorial we are using Eclipse Juno.
Instructions:
Open eclipse
New -> Import -> Existing Maven Project.
Select the project folder
Finish  
Execute "FrameworkValidation" using testing.
-----------------------
create first test case
create first test case class - GoogleTest.java


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.aexp.qa.nameofyourproject.steps.OpenURLStep;   
import com.aexp.qa.testframework.BaseTest;   
import com.aexp.qa.testframework.StepRunner;   
  
public class GoogleTest extends BaseTest {  
  
    @Test(dataProvider = "driverDataProvider")  
    public void openGoogleUrl(final WebDriver browserSession) throws Throwable {  
        new StepRunner(browserSession)
        .start();
    }
} 

-----------------------
2.  Use OpenUrlStep in  your test case and execute your test

@Test(dataProvider = "driverDataProvider")  
public void openGoogleUrl(final WebDriver browserSession) throws Throwable {  
    new StepRunner(browserSession)
    .add(new OpenURLStep().setURL("http://www.google.com").setExpectedTittle("Google"))
    .start();
}

3.  create a test suite to execute it using maven
google.xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
  
<suite name="Google Suite" parallel="methods" thread-count="5">  
    <parameter name="strategy" value="maven"/>
    <parameter name="browsers" value="internetExplorer"/>
      
    <test name="Google Tests">        
        <classes>          
            <class name="GoogleTest"/>        
        </classes>    
    </test>
</suite>


4.  execute your new suite using maven
5.  $mvn install -Dsuite=google.xml

=============================
configure environment
Make sure you already have created your first test case!
Instructions:
1.Identify which variable will change depending on the environment. Our The goal is to execute GoogleTest in two different environments:
QA environment access google.com
QA_BR environment access google.com.br.

@Test(dataProvider = "driverDataProvider")  
public void openGoogleUrl(final WebDriver browserSession) throws Throwable {  
    new StepRunner(browserSession)
    // we have decided to turn configurable the variable url in this test.
    .add(new OpenURLStep().setURL("http://www.google.com").setExpectedTittle("Google"))
    .start();
} 

2.Let's create a new key/value in qa.properties representing google url.
qa.properties
# Email notification configuration #

# mandatory to send email
email_notification_username=
email_notification_password=
email_notification_from=
email_notification_to=
# optional
email_notification_subject=

# Test Configuration #
google_url=http://www.google.com.br

this is found in the resource config folder - src | Test | config | qa_br.properties and qa.properties

3.  Create a new environment for qa_br based on qa environment.
qa_br.properties
# Test Configuration
# google_url=https://www.google.com.br   

4 Set url in Google Test using enviroment configuration
GoogleTest.java
    @Test(dataProvider = "driverDataProvider")  
    public void openGoogleUrl(final WebDriver browserSession) throws Throwable {  
        new StepRunner(browserSession) 
            .add(new OpenURLStep().setURL(TestingContext.getEnvironmentValue("google_url")).setExpectedTittle("Google")  
        .start();
}

5 Add environment configuration in the test suite. 
google.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">    
  
<suite name="Google Suite" parallel="methods" thread-count="5">   
    <parameter name="strategy" value="maven"/>   
    <parameter name="browsers" value="internetExplorer"/>   
    <parameter name="environment" value="src//test//config//qa_br.properties"/>   
    <test name="Google Tests">   
        <classes>   
            <class name="GoogleTest"/>   
        </classes>  
    </test>   
</suite>

6 execute suite with both configs - qa and qa_br

==============================================
create first step
1 create your step call it SubmitGoogleSearchStep.java
package com.aexp.qa.nameofyourproject.steps;         
  
import org.openqa.selenium.By;   
import org.openqa.selenium.WebDriver;   
import org.testng.log4testng.Logger;   
import com.aexp.qa.testframework.BaseStep;   
import com.aexp.qa.testframework.actions.ClickOnElement;   
import com.aexp.qa.testframework.actions.SendKeysToElement;   
import com.google.common.base.Preconditions;   
  
public class SubmitGoogleSearchStep extends BaseStep{  
  
    /** Logger. **/   
    public static final Logger logger = Logger.getLogger(SubmitGoogleSearch.class);  
  
    /** String to search. **/   
    private String searchString;  
  
    @Override   
    public void checkPreconditions() { 
        Preconditions.checkNotNull(searchString, "null searchString"); 
    }    
  
    @Override   
    public void doExecute(WebDriver browserSession) {  
        // Fill the string to search   
        new SendKeysToElement(By.xpath("//div[@id='gs_lc0']/input"), searchString).doAction(browserSession);  
  
        // Confirm search   
        new ClickOnElement(By.xpath("//div[@id='sblsbb']/button")).doAction(browserSession); 
    }    
  
    @Override   
    public void validateOutput(WebDriver arg0) {  
        // TODO Auto-generated method stub    
    }    
  
    @Override   
    public void validateStepExecution(WebDriver arg0) {  
        // TODO Auto-generated method stub    
    }    
  
    /** 
    * Gets the string. 
    * @return the string to be searched. 
    */   
    public String getSearchString() {  
        return searchString; 
    }
     
    /** 
    * Sets the string to be searched. 
    * @param searchString the string. 
    * @return this class. 
    */   
    public SubmitGoogleSearch setSearchString(String searchString) {  
        this.searchString = searchString;  return this; 
    }       
} 

2 Add the new step into your test - found in src/test/java = package com.aexp.qa.google.testcases | GoogleTest.java
package com.aexp.qa.google.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


import com.aexp.qa.google.steps.OpenURLStep;
import com.aexp.qa.testframework.BaseTest;
import com.aexp.qa.testframework.StepRunner;


public class GoogleTest extends BaseTest{
	
	@Test(dataProvider = "driverDataProvider")
	public void openGoogleUrl(final WebDriver browserSession) throws Throwable {
		new StepRunner(browserSession)
		.add(new OpenURLStep().setURL("http://www.google.com").setExpectedTittle("Google"))
		.start();
		
	}
	

}


3 execute your new suite using maven
$mvn install -Dsuite=google.xml

or run as a suite by right click  on the xml file and run as TestNgSuite
=====================


centralize web elements
 Pre-Requirements:
Make sure you already have created your first test case!
Make sure you completed your first step creation!
1 Identify the web elements in your step SubmitGoogleSearchStep and create constant variable in WebElements class representing these elements.
Webelements.java
package com.aexp.qa.nameofyourproject.constants;
  
import org.openqa.selenium.By;
  
/** 
* Elements used to identify elements in Steps. 
*/
public final class WebElements {
        /********************     
    * LIST ALL Elements. *     
    ********************/        
  
    /**     
    * BUTTONS     
    */
         
    /** Proceed button on google search. **/    
    public static final By BUTTON_GOOGLE_SEARCH = By.xpath("//div[@id='sblsbb']/button");
     
    /**     
    * FIELDS to fill       
    */
         
    /** Google search field in the home page. **/    
    public static final By FIELD_GOOGLE_SEARCH = By.xpath("//div[@id='gs_lc0']/input");
     
    /**     
    * Prevents from constructing objects of this class, by declaring this private constructor.     
    */    
    private WebElements() {    
    }
}

2 Uses the constants in you step SubmitGoogleSearchStep, method doExecute .
SubmitGoogleSearchStep.java
@Override  public void doExecute(WebDriver browserSession) {  
    // Fill the string to search  
    new SendKeysToElement(WebElements.FIELD_GOOGLE_SEARCH, searchString).doAction(browserSession);     
  
    // Confirm search  
    new ClickOnElement(WebElements.BUTTON_GOOGLE_SEARCH).doAction(browserSession); 
}

3 Execute your new suite using maven.
$mvn install -Dsuite=google.xml
====================

email configuration
qa.properties file found in config folder
# Email notification configuration #

# mandatory to send email
email_notification_username=cteix4
email_notification_password=
email_notification_from=cristina.teixeiradeoliveira@aexp.com
email_notification_to=cristina.teixeiradeoliveira@aexp.com,venkata.ponnada@aexp.com

# Metrics related
subproject_id=PRJ000TEST

# optional
email_notification_subject=WSP Test Report

# Test Configuration #
wsp_url=https://dcentral820.intra.aexp.com/wsp71/PRServlet
#wsp_url=https://dcentral820.intra.aexp.com/wsp/PRServlet
wsp_general_login=gsures2
#wsp_general_login=ads-sso-1\\gsures2
wsp_general_password=P@$$W0rd

# Test Accounts
#wsp_account_number=376741097903004
#wsp_card_authorization_code=1111
#wsp_card_product_description=Business Gold Rewards Card
#wsp_card_last_5_digits=03004

wsp_account_number=372719283851009
wsp_card_authorization_code=1111
wsp_card_product_description=Business Centurion® Card
wsp_card_last_5_digits=51009


execute new suite using maven
$mvn install -Dsuite=google.xml
verify email box
