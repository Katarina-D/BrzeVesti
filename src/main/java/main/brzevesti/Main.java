package main.brzevesti;

import static java.lang.System.console;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main {

    public static void main(String[] args) {
        
//       // initialize driver 
//       WebDriver driver = new ChromeDriver();
//       driver.manage().window().fullscreen();
//       // go to URL
//       driver.get("http://bvtest.school.cubes.rs/login");
//       
//       // find email field by Name locator
//       WebElement emailField = driver.findElement(By.name("email"));
//       // Enter email into email field
//       emailField.sendKeys("qa@cubes.rs");
//       
//       WebElement passwordField = driver.findElement(By.name("password"));
//       passwordField.sendKeys("cubesqa");
//       
//       WebElement loginButton = driver.findElement(By.className("btn-primary"));
//       loginButton.click();
//       
//       WebElement navCategory = driver.findElement(By.linkText("Categories"));
//       navCategory.click();
//       
//       // Create new category
//       // precondition: user is logged in and on page for Category
//       // 1. Click on button "Add Category"
//       // 2. Enter unique title in Title field
//       // 3. Click on save button
//       // expected result>
//       // - user is redirected to categories page
//       // - confirmation massage is shown "Category "title" has been successfully saved!"
//       // - new category is listed as the last element in table
//       
//      
//       
//       WebElement addNewCategoryButton = driver.findElement(By.className("pull-right"));
//       addNewCategoryButton.click();
//       
//       WebElement titleField = driver.findElement(By.id("title"));
//       String newCategoryTitle = "Katarina123";
//       titleField.sendKeys(newCategoryTitle);
//       
//       WebElement saveButton = driver.findElement(By.id("save-category-button"));
//       saveButton.click();  
//       
//       String expectedResult = "Category \"" + newCategoryTitle + "\" has been successfully saved!";
//       String actualResult = driver.findElement(By.className("alert-success")).getText();
//        
//        if (expectedResult.equals(actualResult)) {
//            System.out.println("Test je prosao");
//        } else {
//            System.out.println("Test nije prosao");
//        }

    }
    
}
