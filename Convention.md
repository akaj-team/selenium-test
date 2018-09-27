# Automation Test Conventions

> **INFOR**
 - `GOOD` --> Recommended
  
 - `BAD` --> Not recommended
 
## Structure

### Resource structure

| Name       | Path                      | Description                                |
| -----------|---------------------------|--------------------------------------------|
| stepdef    | src/test/java/stepdef     | This is where we put our step define files.|
| object     | src/test/java/object      | This is where we put objects.              |
| page       | src/test/java/page        | This is where we put pages.                |
| feature    | src/test/resources/feature| This is where we put feature.              |

### File structure

**Feature file of the following:**
- A page should be divided to many features.
- A feature file should have a minimum of a tag.
- A feature file consists of a single feature.
- A feature file contains a scenario or a list of scenarios.
- Every scenario consists of a list of steps, which must start with one of the keywords `Given`, `When`, `Then`, `But`, `And` or `*`

**Order of functions in a class**
- Override 
- Public
- Protected
- Private

### Name
**1. Package names**
- All letters should be in lower case.
- Meaningful directory name shall be used to indicate the content within the package.
  
  **GOOD**
  
 ```
   com.pragmatic.selenium.page
   com.pragmatic.selenium.test 
   com.pragmatic.selenium.util 
  ```
   **BAD**
    
   ```
     com.pragmatic.selenium.Pages
     com.pragmatic.selenium.Tests
     com.pragmatic.selenium.Utils
   ```
  
  **2. Feature names**
 - Feature names should be described a general action.
 
  **GOOD**
   
  ``` 
    EmployeeLogin.feature 
   ```
   
   **BAD**
     
   ```
     LoginPage.feature 
   ```
    
  **3. Tag names**
  - Tag names should be clear and link to page.  
  
   **GOOD**
     
   ``` 
      @LoginPage
   ```
   **BAD**
       
   ```
      @Login
   ```
   
   **4. Step names**
   
   - Step names should be special and not general to avoid conflict, call the wrong location.  
     
   **GOOD**
        
   ``` 
     When Click submit button at leave planner page
   ```
   **BAD**
          
   ```
     When Click submit button
   ```
  
  **5. Class names**
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
   **6. Method names**
 
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
   **7. Variable names**   
  - Lower camel case should be used for the variable names. 
  - Intent of the variable shall be indicated with the given name.
  - The variable name should be a noun.
  - **Use following prefix for the web elements to indicate the type of the element**
  
  | Name         | Prefix       |  Example             |
  | -------------|:------------:| --------------------:|
  | Button       | btn          | btnSubmit            |
  | Checkbox     | chb          | chbStatus            |
  | Combo box    | cbx          | cbxAnswer            |
  | Input        | input        | inputPassword        |
  | Common dialog| dlg          | dlgNotice            |
  | Date picker  | dtp          | dtpFrom              |
  | Form         | form         | formLogin            |
  | Frame        | fr           | frLanguage           |
  | TabStrip     | tab          | tabOption            |
  | Text Area    | txa          | txaDescription       |
  | Text box     | txt          | txtName              |
  | Image        | img          | imgLogo              |
  | Label        | lb           | lbUserName           |
  | List box     | list         | listCountry          |
  | Menu         | menu         | menuEmployee         |
  | Message      | msg          | msgError             |
  | Radio        | rd           | rdGender             |
  | Table        | tb           | tbEmployee           |
  
 **8. Constants**
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
   
   **9. Comments**
   - Comments shall be used to explain the code to make the test scripts more readable for others.
   - Single line 
   `// This is comment`
   - Multiple line
   `/* This is comment */`
   
   **Comments in feature files (Cucumber)**
   - You can add your comments in the lines followed by the keywords Feature, Scenario, Scenario Outline or Examples.
   - Also you can use #  for line comments.
   `# This is comment`
  
## Locator

- Priority of find element: `id` ->`name` -> `className` -> `tagName` -> `cssSelector` -> `linkText` -> `partialLinkText` -> `xpath`
  
## Cucumber

### Feature
- Feature definitions are typically given a name and an optional, short description.

### Scenario
- Cucumber does not distinguish between keywords Given, When, Then, But, And and `*` but should use this keywords to be more readable.

*Scenario Outline help to avoid repetitive code*

## References
 1.Java Naming Conventions – Javatpoint. Available at: https://www.javatpoint.com/java-naming-conventions.
 2.Code Conventions for the Java Programming Language: 9. Naming Conventions. Available at: [http://www.oracle.com/technetwork/java/codeconventions-135099.html]().
 3.Java Naming Conventions – GeeksforGeeks. Available at: [https://www.geeksforgeeks.org/java-naming-conventions]().
 4.Camel case – En.wikipedia.org. Available at: [https://en.wikipedia.org/wiki/Camel_case]().
 5.Object Naming Conventions – Msdn.microsoft.com Available at:[https://msdn.microsoft.com/en-us/library/aa263493(v=vs.60).aspx]().
 6.Cucumber Limited.Reference·Cucumber. Available [at:https://cucumber.io/docs/reference](). 
 7.Conselenium java. Available at [https://pragmatictestlabs.com/2018/03/05/coding-convention-selenium-java/]().
