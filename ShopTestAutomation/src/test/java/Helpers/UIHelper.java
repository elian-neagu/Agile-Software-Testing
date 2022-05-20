package Helpers;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class UIHelper {

    WebDriver driver;
    Actions actionsObject;
    WebDriverWait elem;
    String pageUrl = "";
    String productID = "";

    private static UIHelper shareIntance = null;

    public static UIHelper getInstance(){
        if (shareIntance == null)
            shareIntance = new UIHelper();

        return shareIntance;
    }
    public void InitialSetup (){

        if (driver == null) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get("https://www.evomag.ro/");
            CheckForPopUpandCloseIt();
        }
    }


    void CheckForPopUpandCloseIt(){
        WaitForSeconds(1000);
        List<WebElement> l =driver.findElements(By.xpath("//a[@class=\"pushinstruments_button_deny\"]"));
        if (l.size() > 0)
            PointAndClick("pushinstruments_button_deny");

    }


    public WebDriver  getDriver(){
        return driver;
    }
    public void WaitForSeconds(long delay){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void HoverOverMenu(String menuName){
        WebElement element = null;
        switch (menuName){
            case "Category":
                element = WaitAndFindElement("//div[@class=\"lbhead\"]");
                assertEquals(element.isDisplayed(), true);
               if(element.isDisplayed())
                   GetActionObject().moveToElement(element).perform();
                break;
            case "MobileAndAccessory":
                element = WaitAndFindElement("(//a[@href=\"/solutii-mobile/\"])[1]");
                assertEquals(element.isDisplayed(), true);
                if(element.isDisplayed())
                    GetActionObject().moveToElement(element).perform();

                break;
            case "MobilePhones":
                element = WaitAndFindElement("//div[@class=\"right_container_category\"]/div[1]/ul/li[1]/a");
                assertTrue(element.isDisplayed());
                if(element.isDisplayed()) {
                    GetActionObject().moveToElement(element).perform();
                    driver.findElement(By.xpath("//div[@class=\"right_container_category\"]/div[1]/ul/li[1]/a")).click();
                }
                break;
        }
    }

    public void SetWebURL(String url){
        pageUrl = url;
    }

    public String GetWebURL(){
        return pageUrl;
    }

    public void CheckCartForElement(int elementId){
        try {
            SetWebURL(driver.getCurrentUrl());
            productID = driver.findElement(By.xpath("(//input[@class=\"nice_add_to_cart\"])["+elementId+"]/parent::form/parent::div/parent::div/parent::div/div[@class=\"npi_name\"]/h2/a")).getAttribute("text");
            String cartXpath= "//div[contains(@class, 'fa-shopping-basket')]";
            driver.navigate().to("https://www.evomag.ro/cart");
            Thread.sleep(1500);
            assertTrue(driver.findElement(By.xpath("//td[@class=\"cart_product_name_cell\"]/a[contains(text(),'"+productID+"')]")).isDisplayed());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void UserIsOnSearchPage(){
        if (!GetWebURL().equals(driver.getCurrentUrl()))
            driver.navigate().to(GetWebURL());
    }

    public void ScrollElementInView (String elementXpath){
        WebElement element = driver.findElement(By.xpath(elementXpath));
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void PointAndClick (String elementName) {
        WebElement element = null;
        switch (elementName) {
            case "Sort":

                element = WaitAndFindElement("(//select[@class=\"sortWidget\"])[1]");
                assertTrue(element.isDisplayed());
                if(element.isDisplayed()) {
                    element.click();
                }
                break;
            case "HighestPrice":
                element = WaitAndFindElement("(//select[@class=\"sortWidget\"])[1]/option[contains(text(),'Pret descrescator')]");
                assertTrue(element.isDisplayed());
                if(element.isDisplayed()) {
                    element.click();
                }
                break;
            case "FirstProductAddToCart":
                element = WaitAndFindElement("(//input[@class=\"nice_add_to_cart\"])[1]");
                assertTrue(element.isDisplayed());
                if(element.isDisplayed()) {
                    element.click();
                }
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                break;
            case "SecondProductAddToCart":
                element = WaitAndFindElement("(//input[@class=\"nice_add_to_cart\"])[2]");
                assertTrue(element.isDisplayed());
                if(element.isDisplayed()) {
                    element.click();
                }
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                break;
            case "pushinstruments_button_deny":
                element = WaitAndFindElement("//a[@class=\"pushinstruments_button_deny\"]");
                assertTrue(element.isDisplayed());
                if(element.isDisplayed()) {
                    element.click();
                }
                break;

        }
    }
    Actions GetActionObject (){
        if (actionsObject == null)
            actionsObject = new Actions((driver));
        return  actionsObject;
    }
    WebElement WaitAndFindElement(String xPathOfElement){
        WebDriverWait wait=new WebDriverWait(driver,  Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathOfElement)));
        List<WebElement> l =driver.findElements(By.xpath(xPathOfElement));
        if (l.size() > 0)
            return driver.findElement(By.xpath(xPathOfElement));
        return null;
    }

    public void CloseDriver() {

        if (driver != null)
            driver.quit();
    }

}
