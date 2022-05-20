package StepDefinitions;

import Helpers.UIHelper;
import Runners.CucumberRunners;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;


import java.time.Duration;


public class addToCartStep extends CucumberRunners {

    UIHelper uiHelper = UIHelper.getInstance();

    @Before
    public void setUP(){
        uiHelper.InitialSetup();
    }

    @After
    public void tearDown(){
        //uiHelper.CloseDriver();
    }
    @Given("Bob Navigates to Category Menu")
    public void bobNavigatesToCategoryMenu() {
        uiHelper.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        uiHelper.WaitForSeconds(1000);
        uiHelper.HoverOverMenu("Category");
    }

    @When("Bob click on the {string} category")
    public void bob_click_on_the_category(String string) {

        uiHelper.WaitForSeconds(1000);
        uiHelper.HoverOverMenu("MobileAndAccessory");
    }
    @When("Bob clicks on the {string} subcategory")
    public void bob_clicks_on_the_subcategory(String string) {
        uiHelper.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        uiHelper.WaitForSeconds(1000);
        uiHelper.HoverOverMenu("MobilePhones");
    }
    @When("Bob filters for {string} filter")
    public void bob_filters_for_filter(String string) {
        uiHelper.WaitForSeconds(1000);
        uiHelper.PointAndClick("Sort");
        uiHelper.WaitForSeconds(1000);
        uiHelper.PointAndClick("HighestPrice");

    }

    @And("Bob scrolls page to Add to Cart button")
    public void bobScrollsPageToAddToCartButton() {
        uiHelper.WaitForSeconds(1500);
        uiHelper.ScrollElementInView("(//input[@class=\"nice_add_to_cart\"])[1]");
    }
    @And("Bob clicks on the first available {string} button")
    public void bobClicksOnTheFirstAvailableButton(String buttonName) {
        uiHelper.WaitForSeconds(1000);
        uiHelper.PointAndClick("FirstProductAddToCart");
    }

    @Then("Item should be added to the cart")
    public void itemShouldBeAddedToTheCart() {
        uiHelper.WaitForSeconds(5500);
        uiHelper.CheckCartForElement(1);
    }

    @Given("Bob is still in the search page with filters")
    public void bobIsStillInTheSearchPageWithFilters() {
        uiHelper.WaitForSeconds(1000);
        uiHelper.UserIsOnSearchPage();
    }

    @When("Bob clicks on the second available {string} button")
    public void bobClicksOnTheSecondAvailableButton(String buttonName) {
        uiHelper.WaitForSeconds(1000);
        uiHelper.PointAndClick("SecondProductAddToCart");

    }

    @Then("The Item Should be added to the cart")
    public void theItemShouldBeAddedToTheCart() {
        uiHelper.WaitForSeconds(5500);
        uiHelper.CheckCartForElement(2);

    }



}
