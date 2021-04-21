package admin;

import framework.Helper;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Katarina
 */
public class CategoriesTest {
    
    private static WebDriver driver;
    
    public CategoriesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       driver = new ChromeDriver();
       driver.manage().window().fullscreen();
       driver.get("http://bvtest.school.cubes.rs/login");
    }
    
    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }
    
    @Before
    public void setUp() {
        login();
    }
    
    @After
    public void tearDown() {
        logout();
    }
    
    private void login() {
       WebElement emailField = driver.findElement(By.name("email"));
       // Enter email into email field
       emailField.sendKeys("qa@cubes.rs");
       
       WebElement passwordField = driver.findElement(By.name("password"));
       passwordField.sendKeys("cubesqa");
       
       WebElement loginButton = driver.findElement(By.className("btn-primary"));
       loginButton.click();
    }
    
    private void logout() {
        WebElement cubesQaButton = driver.findElement(By.className("dropdown-toggle"));
        cubesQaButton.click();
        WebElement logoutButton = driver.findElement(By.className("fa-sign-out"));
        logoutButton.click();
        driver.get("http://bvtest.school.cubes.rs/login");
    }

     @Test
     public void testAddNewCategory() {

       WebElement navCategory = driver.findElement(By.linkText("Categories"));
       navCategory.click();
       
       WebElement addNewCategoryButton = driver.findElement(By.className("pull-right"));
       addNewCategoryButton.click();
       
       WebElement titleField = driver.findElement(By.id("title"));
       String newCategoryTitle = Helper.generateTitle();
       titleField.sendKeys(newCategoryTitle);
       
       WebElement saveButton = driver.findElement(By.id("save-category-button"));
       saveButton.click();  
       
       String expectedAlertResult = "Category \"" + newCategoryTitle + "\" has been successfully saved!";
       String actualAlertResult = driver.findElement(By.className("alert-success")).getText();
       
       assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
       String expectedResult = "http://bvtest.school.cubes.rs/admin/categories";
       String actualResult = driver.getCurrentUrl();
       
       assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
       
       WebDriverWait wait = new WebDriverWait(driver,15);
       wait.until(ExpectedConditions.elementToBeClickable(By.className("dropdown-toggle")));
     }
     
     @Test
     public void testCancelAddingNewCategory() {
       
       WebElement navCategory = driver.findElement(By.linkText("Categories"));
       navCategory.click();
       
       WebElement addNewCategoryButton = driver.findElement(By.className("pull-right"));
       addNewCategoryButton.click();
       
       WebElement titleField = driver.findElement(By.id("title"));
       String newCategoryTitle = Helper.generateTitle();
       titleField.sendKeys(newCategoryTitle);
       
       WebElement backButton = driver.findElement(By.id("back-button"));
       backButton.click();  
       
       String expectedURL = "http://bvtest.school.cubes.rs/admin/categories";
       String actualResURL = driver.getCurrentUrl();
       
       assertTrue("URLs doesn't match", expectedURL.equals(actualResURL));
    }
     
     @Test
     public void testDeleteLastCategory() {
        WebElement navCategory = driver.findElement(By.linkText("Categories"));
        navCategory.click();
        
        List<WebElement> deleteIconButtons = driver.findElements(By.cssSelector("table#categoriesTable button.btn-default[title=\"Delete\"]"));
        String lastCategoryTitle = "";
        String expectedCategoryTitle = "";
        
        // deleteIconButtons.get(100).click();
//        for (int i = 0; i < deleteIconButtons.size();i++){
//           if (i + 1 == deleteIconButtons.size()) {
//              lastCategoryTitle = deleteIconButtons.get(i).getAttribute("data-category_title");
//              expectedCategoryTitle = deleteIconButtons.get(i - 1).getAttribute("data-category_title");
//              
//              deleteIconButtons.get(i).click();
//           }
//        }
        expectedCategoryTitle = deleteIconButtons.get(deleteIconButtons.size() - 2).getAttribute("data-category_title");
        lastCategoryTitle = deleteIconButtons.get(deleteIconButtons.size() - 1).getAttribute("data-category_title");
        deleteIconButtons.get(deleteIconButtons.size() - 1).click();
        
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#categoryDeleteDialog button.btn-primary")));
        
        WebElement deleteButton = driver.findElement(By.cssSelector("div#categoryDeleteDialog button.btn-primary"));
        deleteButton.click();
        
        String expectedAlertResult = "Category \"" + lastCategoryTitle + "\" has been successfully deleted!";
        String actualAlertResult = driver.findElement(By.className("alert-success")).getText();
        assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
        
        String expectedResult = "http://bvtest.school.cubes.rs/admin/categories";
        String actualResult = driver.getCurrentUrl();
        assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
        
       
        WebDriverWait wait3 = new WebDriverWait(driver,15);
        wait3.until(ExpectedConditions.elementToBeClickable(By.cssSelector("table#categoriesTable button.btn-default[title=\"Delete\"]")));
        
        deleteIconButtons = driver.findElements(By.cssSelector("table#categoriesTable button.btn-default[title=\"Delete\"]"));
        
        String expectedLastResult = expectedCategoryTitle;
        // System.out.println("expectedLastResult " + expectedLastResult);
        String actualLastResult = deleteIconButtons.get(deleteIconButtons.size() - 1).getAttribute("data-category_title");
        System.out.println("actualLastResult " + actualLastResult);
        assertTrue("Alert message is not correct", expectedLastResult.equals(actualLastResult));
        
        WebDriverWait wait2 = new WebDriverWait(driver,15);
        wait2.until(ExpectedConditions.elementToBeClickable(By.className("dropdown-toggle")));
     }
     
     @Test
     public void testCloseLastDeleteCategory() {
        WebElement navCategory = driver.findElement(By.linkText("Categories"));
        navCategory.click();
        
        List<WebElement> deleteIconButtons = driver.findElements(By.cssSelector("table#categoriesTable button.btn-default[title=\"Delete\"]"));
        
        // deleteIconButtons.get(100).click();
        for (int i=0; i<deleteIconButtons.size();i++){
           if (i + 1 == deleteIconButtons.size()) {
              deleteIconButtons.get(i).click();
           }
        }
        
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#categoryDeleteDialog button.btn-default")));
        
        WebElement closeButton = driver.findElement(By.cssSelector("div#categoryDeleteDialog button.btn-default"));
        closeButton.click();
        
        String expectedResult = "http://bvtest.school.cubes.rs/admin/categories";
        String actualResult = driver.getCurrentUrl();
        assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
        
        WebDriverWait wait2 = new WebDriverWait(driver,15);
        wait2.until(ExpectedConditions.elementToBeClickable(By.className("dropdown-toggle")));
     }
     
     @Test 
     public void testEditNextToLastCategory() {
        WebElement navCategory = driver.findElement(By.linkText("Categories"));
        navCategory.click();
         
        List<WebElement> editButtons = driver.findElements(By.cssSelector("table#categoriesTable a.btn-default[title=\"Edit\"]"));
        
        for (int i = 0; i < editButtons.size(); i++){
           if (i + 1 == editButtons.size()) {
              editButtons.get(i-1).click();
           }
        }
        
        WebElement titleField = driver.findElement(By.id("title"));
        titleField.clear();
        String editCategoryTitle = Helper.generateTitle();
        titleField.sendKeys(editCategoryTitle);
       
        WebElement saveButton = driver.findElement(By.id("save-category-button"));
        saveButton.click();
        
        String expectedAlertResult = "Category \"" + editCategoryTitle + "\" has been successfully saved!";
        String actualAlertResult = driver.findElement(By.className("alert-success")).getText();
       
        assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
        String expectedResult = "http://bvtest.school.cubes.rs/admin/categories";
        String actualResult = driver.getCurrentUrl();
       
        assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
        
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("dropdown-toggle")));
     }
     
     @Test
     public void testCancelLastEditCategory() {
        WebElement navCategory = driver.findElement(By.linkText("Categories"));
        navCategory.click();
         
        List<WebElement> editButtons = driver.findElements(By.cssSelector("table#categoriesTable a.btn-default[title=\"Edit\"]"));
        
        for (int i = 0; i < editButtons.size(); i++){
           if (i + 1 == editButtons.size()) {
              editButtons.get(i).click();
           }
        }
        
        WebElement titleField = driver.findElement(By.id("title"));
        titleField.clear();
        String editCategoryTitle = Helper.generateTitle();
        titleField.sendKeys(editCategoryTitle);
        
        WebElement backButton = driver.findElement(By.id("back-button"));
        backButton.click();  
       
        String expectedURL = "http://bvtest.school.cubes.rs/admin/categories";
        String actualResURL = driver.getCurrentUrl();
        assertTrue("URLs doesn't match", expectedURL.equals(actualResURL));
        
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("dropdown-toggle")));
     }


}



