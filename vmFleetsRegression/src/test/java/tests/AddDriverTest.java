package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AddDriverPage;
import pages.LoginPage;
import utils.ConfigReader;

public class AddDriverTest extends BaseTest{
	@Test
    public void testAddDriver() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        AddDriverPage addDriverPage = new AddDriverPage(driver);

        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        Thread.sleep(5000);
 // Assert login success
        By dashboardHeader = By.id("menu-title"); // Replace with actual locator
        Assert.assertTrue(driver.findElement(dashboardHeader).isDisplayed(), "Login failed");
        addDriverPage.openAddDriverForm();
        addDriverPage.fillDriverDetails("TN4820080007043", "18-02-1996", "6789035333");
        addDriverPage.completeForm();
        
  
    }

}
