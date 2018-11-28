# Automation Test Document
***
## SELENIUM

### I. Web driver

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Selenium WebDriver** provides a programming interface to create and execute test cases. 
Test cases are written such that, web elements on web pages are identified and then actions are performed on those elements.

#### &nbsp;&nbsp;&nbsp;&nbsp; 1. Most used commands

| Commands                              | Description                                                         | 
|---------------------------------------|---------------------------------------------------------------------|
|driver.get("URL")                      |To navigate to an application.                                       |
|driver.switchTo().window("windowName") |Move the focus from one window to another.                           |
|driver.switchTo().frame("frameName")   |Swing from frame to frame.                                           |
|driver.switchTo().alert()              |Helps in handling alerts.                                            |
|driver.navigate().to("URL")            |Navigate to the URL.                                                 |
|driver.navigate().forward()            |To navigate forward.                                                 |
|driver.navigate().back()               |To navigate back.                                                    |
|driver.close()                         |Closes the current browser associated with the driver.               |
|driver.quit()                          |Quits the driver and closes all the associated window of that driver.|
|driver.refresh()                       |Refreshes the current page.                                          |

### II. Locators

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Locating elements in Selenium WebDriver is performed with the help of **findElement()** and **findElements()** methods provided by WebDriver and WebElement class.

* **findElement()** returns a WebElement object based on a specified search criteria or ends up throwing an exception if it does not find any element matching the search criteria.

* **findElements()** returns a list of WebElements matching the search criteria. If no elements are found, it returns an empty list.

|Priority|Method               |Syntax                                                                                           |Description                                 |
|:------:|---------------------|-------------------------------------------------------------------------------------------------|--------------------------------------------|
|   1    |By id                |`driver.findElement(By.id("id"))`                                                                |Locates an element using the id attribute   |
|   2    |By name              |`driver.findElement(By.name("name"))`                                                            |Locates an element using the name attribute |
|   3    |By class name        |`driver.findElement(By.className("class"))`                                                      |Locates an element using the class attribute|
|   4    |By tag name          |`driver.findElement(By.tagName("tagname"))`                                                      |Locates an element using the html tag       |
|   5    |By link text         |`driver.findElement(By.linkText("linktext"))`                                                    |Locates a link using link text              |
|   6    |By partial link text |`driver.findElement(By.partialLinkText("partialLinkText"))`                                            |Locates a link using the link's partial text|
|   7    |By css               |`Locates an element using the css selector`                                                      |Locates an element using the css attribute  |
|        |Id                   |`driver.findElement(By.cssSelector("<tagName>[id=<id>]"))`<br/><br/> or <br/><br/>`driver.findElement(By.cssSelector("<tagName>#<id>"))`    |              |
|        |Class                |`driver.findElement(By.cssSelector("<tagName>.<value of class attribute>"))`                    |                                            |
|        |Attribute            |`driver.findElement(By.cssSelector("<tagName>[<attribute>=<value of attribute>]"))`             |                                            |
|        |Sub-string           |`driver.findElement(By.cssSelector("<tagName>[<attribute>^=<prefix of the string>]"))`<br/><br/> or <br/><br/>`driver.findElement(By.cssSelector("<tagName>[<attribute>$=<suffix of the string>]"))`<br/><br/> or <br/><br/>`driver.findElement(By.cssSelector("<tagName>[<attribute>*=<sub string>]"))`| |
|   8    |By xpath             |`driver.findElement(By.xpath("xpath"))`                                                          |Locates an element using xpath query        |

***Example:***

* **By id:**

 ```
Element: <div class="gray-bg full-screen" id="wrapper">

Find: driver.findElement(By.id("page-wrapper"));
```

* **By name:**

 ```
Element: <meta name="description" content="AT-Portal version 1.4.0-beta">

Find: driver.findElement(By.name("description"));
```

* **By class name:**

```
Element: <div class="alert alert-danger alert-dismissible" role="alert">

Find: driver.findElement(By.className("alert alert-danger alert-dismissible"));
```

* **By tag name:**

```
Element: <form class="m-t ng-dirty ng-touched ng-valid" novalidate>

Find: driver.findElement(By.tagName("form"));
```

* **By link text:**

```
Element: <a id="link-to-timesheet-submission" href="/timesheet/submission">My Timesheet</a>

Find: driver.findElement(By.linkText("My Timesheet"));
```

* **By partial link text:**

```
Element: <a id="link-to-timesheet-submission" href="/timesheet/submission">My Timesheet</a>

Find: driver.findElement(By.partialLinkText("Timesheet"));
```

* **By css:**

```
Element: 
<input formcontrolname="gender" type="radio" id="gender-male" class="ng-untouched ng-pristine ng-invalid">
```

- **With id:**

 ```
  Find: 
        driver.findElement(By.cssSelector("input[id=gender-male]"));
   
        or
   
        driver.findElement(By.cssSelector("input#gender-male"));
  ```

 - **With css selector:**

```
  Find: 
        driver.findElement(By.cssSelector("input.ng-untouched.ng-prinstine.ng-invalid"));

        or 

        driver.findElement(By.cssSelector(".ng-untouched.ng-prinstine"));
```

- **With attribute:**

```
  Find: 
        driver.findElement(By.cssSelector("input[type=radio]"));

        or 

        driver.findElement(By.cssSelector("*[type=radio]"));

        or

        driver.findElement(By.cssSelector("input[formcontrolname=gender]"));
```

- **With sub-string:**

```
  Find: 
         driver.findElement(By.cssSelector("input[type^=ra]"));

         or

          driver.findElement(By.cssSelector("input[type$=dio]"));

         or 

         driver.findElement(By.cssSelector("input[type*=ad]"));
```
  
* **By xpath:**

```
  Element:
    <div class="form-group">
      <label class="col-md-4 control-label required">"First name"</label>
      <input class="form-control" formcontrolname="first_name" name="first_name" type="text">
      <span class="help-block">Please enter value</span>
    </div>
       
  Find:     
    driver.findElement(By.xpath("//div/input"));
    
    or
    
    driver.findElement(By.xpath("//label[contains(text(),'First name')]/../input""));        
```

### III. User interactions

#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. Text box

```
 Element: 
 <input class="form-control" formcontrolname="first_name" name="first_name">
```

- We can put values into a text box using the **sendKeys()** method.

```
 Find: driver.findElement(By.name("first_name")).sendKeys("abc");
```

- We can also retrieve text from a text box using the **getAttribute("value")** command.

```
 Find:  driver.findElement(By.name("first_name")).getAttribute("value);
```

#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2. Radio button

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;We can select a radio button option using the **click()** method and unselect using the same **click()** method.

```
Element: 
<input formcontrolname="gender" type="radio" id="gender-male" class="ng-untouched ng-pristine ng-invalid">

Find: driver.findElement(By.name("gender-male")).click();
```

#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3. Check box
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Similar use **Radio button**

```
Element: <input class="form-control" name="check_all" type="checkbox">

Find:
 driver.findElement(By.name("check_all")).click(); // click
 driver.findElement(By.name("check_all")).isSelected()); // get select status
```

#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 4. Drop down
```
Element:
 <select class="nationlity" multiple="">
    <option value="vi">Viet Nam</option>        
    <option value="en">English</option>  
 </select>         

Find:
 dropdown.selectByVisibleText("Viet Nam");
 dropdown.selectByIndex(0)
 dropdown.selectByValue("vi")
```

### III. Synchronization

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To synchronize between script execution and application, we need to wait after performing appropriate actions.

#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. Thread.Sleep

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Thread.Sleep** is a static wait and it is not a good way to use in scripts as it is sleep without condition.

```
Thread.Sleep(1000); //Will wait for 1 second.
```

#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2. Implicit waits

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The implicit wait will tell to the web driver to wait for certain amount of time before it throws a **"No Such Element Exception"**.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The default setting is 0. Once we set the time, web driver will wait for that time before throwing an exception.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Implicit wait will accept 2 parameters, the first parameter will accept the time as an integer value and the second parameter will accept the time measurement in terms of SECONDS, MINUTES, MILISECOND, MICROSECONDS, NANOSECONDS, DAYS, HOURS, etc.

```
driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
```

#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. Explicit waits

  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The explicit wait is used to tell the Web Driver to wait for certain conditions (Expected Conditions) or the maximum time exceeded before throwing an **"ElementNotVisibleException"** exception.
   It will wait for dynamically loaded **Ajax elements**.
   
```
   WebDriverWait wait = new WebDriverWait(WebDriverRefrence,TimeOut);
   wait.until(<Condition>);
```
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**The expected conditions that can be used in Explicit Wait:**

```
- alertIsPresent()
- elementSelectionStateToBe()
- elementToBeClickable()
- elementToBeSelected()
- frameToBeAvailableAndSwitchToIt()
- invisibilityOfTheElementLocated()
- invisibilityOfElementWithText()
- presenceOfAllElementsLocatedBy()
- presenceOfElementLocated()
- textToBePresentInElement()
- textToBePresentInElementLocated()
- textToBePresentInElementValue()
- titleIs()
- titleContains()
- visibilityOf()
- visibilityOfAllElements()
- visibilityOfAllElementsLocatedBy()
- visibilityOfElementLocated()
```

#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3. Fluent waits

 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The fluent wait is used to tell the web driver to wait for a condition, as well as the frequency with which we want to check the condition before throwing an **"ElementNotVisibleException"** exception.
 
 ```
    Wait wait = new FluentWait(WebDriver reference).withTimeout(timeout, SECONDS)
                                                   .pollingEvery(frequentTimeout, SECONDS)
                                                   .ignoring(Exception.class);
    wait.until((Function<WebDriver, WebElement>) driver -> { 
       // TODO
    });
  ```    
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In the above example, we are declaring a fluent wait with the **timeout** seconds and the frequency is set to **frequentTimeout** seconds by ignoring **Exception**
   
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Difference between Implicit Wait and Explicit Wait**
  
   Implicit Wait                                                                                       | Explicit Wait                                                                                                            
  -----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------- 
  Time is applied to all the elements in the script                                                    |Time is applied only to those elements which are intended by us     
  We need not specify "ExpectedConditions" on the element to be located                                |We need to specify "ExpectedConditions" on the element to be located
  It is recommended to use when the elements are located with the time frame specified in implicit wait|It is recommended to use when the elements are taking long time to load and also for verifying the property of the element like(visibilityOfElementLocated, elementToBeClickable,elementToBeSelected)

### IV. Keyboard action

**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. keyDown(modifier_key)**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Performs a modifier key press. Does not release the modifier key - subsequent interactions may assume it's kept pressed.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Parameters:**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**modifier_key** - Any of the modifier keys (Keys.ALT, Keys.SHIFT, or Keys.CONTROL)

```
new Actions(driver).keyDown(<Key>).build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**2. keyUp(modifier _key)**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Performs a key release.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Parameters:**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**modifier_key** - Any of the modifier keys (Keys.ALT, Keys.SHIFT, or Keys.CONTROL)

```
new Actions(driver).keyUp(<Key>).build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**3. sendKeys(onElement, charSequence)**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sends a series of keystrokes onto the element. 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Parameters:**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**onElement** - Element that will receive the keystrokes, usually a text field

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**charSequence** - Any string value representing the sequence of keystrokes to be sent

```
new Actions(driver).sendKeys(element, charSequence).build().perform();
```

### V. Mouse events

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**1. click**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Perform a click based on coordinates.

```
element.click();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**1. clickAndHold**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Clicks (without releasing) at the current mouse location.

```
new Actions(driver).clickAndHold().build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**2. contextClick**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Performs a context-click at the current mouse location.

```
new Actions(driver).contextClick().build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**3. doubleClick**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Performs a double-click at the current mouse location.

```
new Actions(driver).doubleClick().build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**4. dragAndDrop(source, target)**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Performs click-and-hold at the location of the source element, moves to the location of the target element, then releases the mouse.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Parameters:**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**source**- Element to emulate button down at.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**target**- Element to move to and release the mouse at.

```
new Actions(driver).dragAndDrop(elementSource, elementTarget).build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**5. dragAndDropBy(source, x-offset, y-offset)**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Performs click-and-hold at the location of the source element, moves by a given offset, then releases the mouse.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Parameters:**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**source**- Element to emulate button down at.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**xOffset**- Horizontal move offset.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**yOffset**- Vertical move offset.

```
new Actions(driver).dragAndDrop(element, x-offset, y-offset).build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**6. moveByOffset(x-offset, y-offset)**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Moves the mouse from its current position by the given offset.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Parameters:**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**x-offset** - Horizontal offset. A negative value means moving the mouse left.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**y-offset** - Vertical offset. A negative value means moving the mouse down.

```
new Actions(driver).moveByOffset(x-offset, y-offset).build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**7. moveToElement(toElement)**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Moves the mouse to the middle of the element. 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Parameters:**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**toElement** - Element to move to.

```
new Actions(driver).moveToElement(element).build().perform();
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**8.release()**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Releases the depressed left mouse button at the current mouse location

```
new Actions(driver).release().build().perform();
```

### VI. Exception handling

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**1. Most common Exceptions:**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- **NoSuchElementException:** FindBy method can’t find the element.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- **StaleElementReferenceException:** This tells that element is no longer appearing on the DOM page.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- **TimeoutException:** This tells that the execution is failed because the command did not complete in enough time.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- **ElementNotVisibleException:** Thrown to indicate that although an element is present on the DOM, it is not visible, and so is not able to be interacted with

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- **ElementNotSelectableException:** Thrown to indicate that may be the element is disabled, and so is not able to select.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**2. Try/ catch**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Try/Catch:** A method catches an exception using a combination of the try and catch keywords.

```
try {
 element = driver.findElement(By.xpath(".//*[@id='menu']/div[4]/div[3]/a")); 
    } catch (Exception exception) {
      // Add a message to your Log File to capture the error
      Logger.error("Link is not found.");
    }
```

## CUCUMBER

### I. Operation

 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cucumber is one such open source tool, which supports behavior driven development.
 
 **Describe behaviour -> Write step definition -> Run and fail ->Write code to make step pass -> Run and pass**

### II. Annotation

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Annotation is a predefined text, which holds a specific meaning. It lets the compiler/interpreter know, what should be done upon execution. Cucumber has got the following few annotations:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Given**: It describes the pre-requisite for the test to be executed.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**When**: It defines the trigger point for any test scenario execution.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Then**: It holds the expected result for the test to be executed.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**And**: It provides the logical **And** condition between any two statements. And can be used in conjunction with **Given**, **When** and **Then** statement.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**But**: It signifies logical **Or** condition between any two statements. Or can be used in conjunction with **Given**, **When** and **Then** statement.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Background**: It generally has the instruction on what to setup before each scenario runs. So this is ideal to be used for code when we want to set up the web-browser or we want to establish the database connectivity.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example:**

```
Background: Go to Portal login page

Scenario: Given user can not navigate to AT-Portal.
Given I am a AT employee
But My account is blocked
When I enter my "<username>"
And I enter my "<password>"
Then Login should be unsuccessful
```

## III. Scenario

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Every scenario starts with the keyword **Scenario:** and is followed by an optional scenario title which describe a behaviour.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example**

```
Scenario: Given user can navigate to AT-Portal.
Given I am a AT employee
But My account is blocked
When I enter my "hangtran"
And I enter my "abc123"
Then Login should be successful
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Scenario Outline**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Scenario outline is similar to scenario structure. The only difference is the provision of multiple inputs. At the bottom we have provided multiple input values. While running the actual test, Cucumber will replace the variable with input values provided and it will execute the test.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example:**

```
Background: Go to AT-Portal login page

Scenario Outline: Given user can not navigate to AT-Portal.
Given I am a AT employee
But My account is blocked
When I enter my "<username>"
And I enter my "<password>"
Then Login should be unsuccessful
Examples:
 | username | password  |
 | user1    | password1 |
 | user2    | password2 |
 | user3    | password3 |
```

## IV. Feature

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The keyword to represent a feature under test in Gherkins is **Feature:**. A feature can be defined as a standalone unit or functionality of a project.
 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A feature usually contains a list of scenarios to be tested for that feature.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example:**

```
Feature: Login functionality for a AT-Portal site.
```

*The user should be able to login into AT-Portal site if the username and the password are correct -> A Scenario*

*The user should be shown the error message if the username and the password are incorrect. -> A Scenario*

*The user should be navigated to home page, if the username and password are correct.-> A Scenario*

* **Feature file**

 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; The file, in which Cucumber tests are written, is known as feature files. It is advisable that there should be a separate feature file, for each feature under test. 
      
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The extension of the feature file needs to be **".feature"**. One can create as many feature files as needed. To have an organized structure, each feature should have one feature file.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example:**

```
   UserLogin.feature
   CreateAccount.feature
```

 A simple feature file consists of the following keywords/parts:
- **Feature** − Name of the feature under test.
- **Description** (optional) − Describe about feature under test.
- **Scenario** − What is the test scenario.
- **Given** − Prerequisite before the test steps get executed.
- **When** − Specific condition which should match in order to execute the next step.
- **Then** − What should happen if the condition mentioned in When is satisfied.

## V.Tag

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cucumber has already provided a way to organize your scenario execution by using tags in feature file. We can define each scenario with a useful tag. Tag starts with **@**. After **@** you can have any relevant text to define your tag.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Then to target these tagged scenarios just specify the tags names in the CucumberOptions as **tags = {"@SmokeTests"}**.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example:**

```
   @Login
   Feature: Login functionality for AT-Portal site.
   
   Scenario: Given user navigates to AT-Portal.
   Background: Go to Portal login page
   Given I am a AT employee
   When I enter my "<username>"
   And I enter my "<password>"
   Then Login should be successful
   
   @Ignore
   Scenario Outline: Given user can not navigate to AT-Portal.
   Background: Go to AT-Portal login page
   Given I am a AT employee
   But My account is blocked
   When I enter my "<username>"
   And I enter my "<password>"
   Then Login should be unsuccessful
   | username | password  | 
   | user1    | password1 | 
   | user2    | password2 |
   | user3    | password3 |
```

## VI. Data table

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Data table is a set of input to be provided for a single tag. This tag can be **Given**, **When**, or **Then**. It is there any better way to manage such chunk of inputs.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example:**

```
   Feature: Data table
   Verify that the new user registration is unsuccessful after passing incorrect inputs
   Scenario:
   Given I am on the new user registration page
   When I enter invalid data on the page
   | First Name             | Hang                   |
   | Last Name              | Tran                   |
   | Email Address          | hang.tran@asiantech.vn |
   | Re-enter Email Address |hang.tran@asiantech.vn  |
   | Password               | abc                    |
   | Birthdate              | 09-10-1995             |
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Implement steps:

``` 
When("^I enter invalid data on the page$", (DataTable dataTable) -> {
          dataTable.get(<row>).get(<column>)
          // TODO
       });
```


## VII. Comments

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Comment is basically a piece of code meant for documentation purpose and not for execution. Be it a step definition file or a feature file, to make it more readable and understandable. To put comments, we just need to start the statement with **#** sign.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example:**

```
#This is how background can be used to eliminate duplicate steps 
Background: 
User navigates to AT-Portal 
Given I am on AT-Portal  login page
```

## VIII. Implement steps

 | Expression reference | Description                                   |
 |:--------------------:|-----------------------------------------------|
 |[abc]                 |A single character of: a, b, or c              |
 |[^abc]                |Any single character except: a, b, or c        |
 |[a-z]                 |Any single character in the range a-z          |
 |[a-zA-Z]              |Any single character in the range a-z or A-Z   |
 |^                     |Start of line                                  |
 |$                     |End of line                                    |
 |.                     |Any single character                           |
 |\s                    |Any whitespace character                       |
 |\S                    |Any non-whitespace character                   |
 |\d                    |Any digit                                      |
 |\D                    |Any non-digit                                  |
 |\w                    |Any word character (letter, number, underscore)|
 |\W                    |Any non-word character                         |
 |\b                    |Any word boundary                              |
 |(...)                 |Capture any three characters                   |
 |(a&#124;b)            |a or b                                         |
 |a?                    |Zero or one of a                               |
 |a*                    |Zero or more of a                              |
 |a+                    |One or more of a                               |
 |a{3}                  |Exactly 3 of a                                 |
 |a{3,}                 |3 or more of a                                 |
 |a{3,6}                |Between 3 and 6 of a                           |
 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Example:**
 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Expression:
 
 ```
 [a-zA-Z0-9]{1,}[.][a-zA-Z0-9]{1,}@asiantech.vn
 ```
 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Capture:

 ```
 hang.tran@asiantech.vn
 tien.hoang@asiantech.vn
 hoa1.vo@asiantech.vn
 etc
 ```
 