# Automation Test Conventions

> **INFOR**
 - `GOOD` --> Recommended
  
 - `BAD` --> Not recommended
 
## Structure

### Resource structure

| Name       | Path                      | Description                                |
| -----------|:-------------------------:| ------------------------------------------:|
| stepdef | src/test/java/stepdef  | This is where we put our step define files.|
| object     | src/test/java/object      | This is where we put objects.              |
| page       | src/test/java/page        | This is where we put pages.                |
| feature    | src/test/resources/feature| This is where we put feature.              |

### File structure

**A .feature file of the following:**

-`A feature file consists of a single featurea.`
-`A feature file contains a scenario or a list of scenarios.`

**A definitions.java of the following, in order:**
-`Give`
-`When`
-`Then`
-`And`
-`But`

**A page.java of the following:**
- Priority of find element: `id` ->`name` -> `className` -> `tagName` -> `cssSelector` -> `linkText` -> `partialLinkText` -> `xpath`

### Name
1. Pakage names
- All letters should be in lower case.
- Meaningful directory name shall be used to indicate the content within the package.
  
  **GOOD**

```
  com.pragmatic.selenium.pages
  com.pragmatic.selenium.tests 
  com.pragmatic.selenium.utils 
 ```
 
  **BAD**
  
 ```
   com.pragmatic.selenium.Page
   com.pragmatic.selenium.Test 
   com.pragmatic.selenium.Util 
  ```
  
  2. Class names
  - Upper camel case should be used for the class names.
  - Class name should be a noun.
  - Try to make the name simple and descriptive.
  
  **GOOD**
 
 ```
   LoginTest.java
   LoginPage.java 
   TestBase.java 
  ```
  
   **BAD**
   
  ```
   Logintest.java
   Loginpage.java 
   Testbase.java  
   ```
   3. Method names
   
   - Lower camel case should be used for the method names.
   - Method name should be a verb.
   - Try to make the name simple and descriptive. 
   - Use whole word instead of abbreviations.
   
     **GOOD**
     
     ```
     initialiseBrowsers()
     waitForTextToBePresent(WebElement webElement, String textToBeVerified)
     ```
     
      **BAD**
      
     ```
     initbrowsers()
     wait_for_text_to_be_present(WebElement webElement, String textToBeVerified)
      ```
   4. Variable names   
  - Lower camel case should be used for the variable names. 
  - Intent of the variable shall be indicated with the given name.
  - The variable name should be a noun.
  - **Use following prefix for the web elements to indicate the type of the element**
  
  | Name         | Prefix       |  Example             |
  | -------------|:------------:| --------------------:|
  | Button       | btn          | btnSubmit            |
  | Checkbox     | chk          | chkStatus            |
  | Combo box    | cbo          | cboAnswer            |
  | Input        | input        | ipPassword           |
  | Common dialog| dlg          | dlgNotice            |
  | Date picker  | dtp          | dtpFrom              |
  | Form         | form         | dtpFrom              |
  | Frame        | fr           | frLanguage           |
  | TabStrip     | tab          | tabOption            |
  | Text Area    | txa          | txaDescription       |
  | Text box     | txt          | txtName              |
  | Image        | img          | imgLogo              |
  | Label        | lbl          | tblUserName          |
  | List box     | lst          | lstCountry           |
  | Menu         | menu         | menuEmployee         |
  | Message      | msg          | msgError             |
  | Radio        | rdo          | droGender            |
  | Table        | tbl          | tblEmployee          |
  
  4. Constants
  - All letters should be in uppercase.
  - Words shall be separated with underscore `_`.
  - Constants shall be defined as public static in the Constant class.
  
   **GOOD**
        
   ```
   public static String BASE_URL;
   ```
        
   **BAD**
         
   ```
    String BASEURL;
   ```
   5. Comments
   - Comments shall be used to explain the code to make the test scripts more readable for others.
   - Single line 
   `// This is comment`
   - Multiple line
   `/* This is comment */`
   
   **Comments in feature files (Cucumber)**
   - You can add your comments in the lines followed by the keywords Feature, Scenario, Scenario Outline or Examples.
   - Also you can use #  for line comments.
   `# This is comment`
