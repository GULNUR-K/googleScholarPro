package com.prettyLittleThingBDD.tests.step_defs;

import com.prettyLittleThingBDD.tests.pages.BasePage;
import com.prettyLittleThingBDD.tests.utilities.BrowserUtils;
import com.prettyLittleThingBDD.tests.utilities.ConfigurationReader;
import com.prettyLittleThingBDD.tests.utilities.Driver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.prettyLittleThingBDD.tests.utilities.BrowserUtils.*;
import static com.prettyLittleThingBDD.tests.utilities.Driver.*;

public class PurchaseAnItem {

    BasePage basePage = new BasePage();

    @When("user is on main page")
    public void userIsOnMainPage() {

        assert get().getCurrentUrl().equals("https://www.prettylittlething.com/");
    }

    @Then("user should able to accept cookies")
    public void userShouldAbleToAcceptCookies() {

        basePage.acceptCookies.click();
    }

    @And("user can choose {string} category from the nav menu")
    public void userCanChooseCategoryFromTheNavMenu(String categoryFromNav) {

        WebElement category= get().findElement(By.xpath("//nav/a[contains(@href,'" + categoryFromNav + "')]"));

        waitForClickablility(category, 10);

        category.click();

        waitForPageToLoad(10);
    }

    @Then("user can select random {int} item")
    public void userCanSelectRandomItem(int numberOfItem) {

        List<WebElement> items = get().findElements(By.xpath("//div[@class='product-image-block']"));

        waitForClickablility(items.get(numberOfItem), 10);

        items.get(numberOfItem).click();
    }

    String itemNameHeader;

    @And("store the product name as a string")
    public void storeTheProductNameAsAString() {

        waitForPageToLoad(20);

//      the required statement because a 504 error is received a few times
        if(get().findElement(By.xpath("//body//h1")).getText().equals("504 Gateway Time-out")){

            Driver.get().navigate().refresh();

            waitForPageToLoad(30);
        }

        WebElement selectedItem = get().findElement(By.xpath("//section[@id='product-right']//h1"));

        waitForVisibility(selectedItem, 10);

        itemNameHeader = selectedItem.getText();
    }

    @Then("user can select random {int} product size")
    public void userCanSelectRandomProductSize(int size) {

        verifyElementDisplayed(By.xpath("//button[@id='add-to-bag' and @disabled='disabled']"));

        List<WebElement> sizes = get().findElements(By.xpath("//div[@id='js-product-size']//div[@data-value]"));

        sizes.get(size).click();

        waitForInvisibility(By.xpath("//button[@id='add-to-bag' and @disabled='disabled']"), 5);

        waitForVisibility(By.xpath("//div[@id='js-product-size']//div[@data-value and contains(@class,'selected')]"), 2);
    }

    @And("user selects add to bag button")
    public void userSelectsAddToBagButton() {

        basePage.addToBag.click();

        waitForInvisibility(By.xpath("//button[@id='add-to-bag' and @disabled='disabled']"), 20);
    }

    @Then("selected product name appears in top cart bag on the top right")
    public void selectedProductNameAppearsInTopCartBagOnTheTopRight() {

        waitForVisibility(get().findElement(By.xpath("//div[@class='block-cart top-cart bag_hover active' and @id='topcart']")), 20);

        assert get()
                .findElement(By.xpath("//div[@class='block-cart top-cart bag_hover active']//p[@class='product-name']"))
                .getText().toLowerCase().equals(itemNameHeader.toLowerCase());
    }

    @Then("select the bag icon on the top right")
    public void selectTheBagIconOnTheTopRight() {

        basePage.bagIcon.click();
    }

    @Then("user is on your bag page")
    public void userIsOnYourBagPage() {

        waitForPageToLoad(5);

        assert get().findElement(By.xpath("//div/h3")).getText().equals("Your bag");
    }

    @And("user should be able to see the selected product on your bag page")
    public void userShouldBeAbleToSeeTheSelectedProductOnYourBagPage() {

        List<WebElement> itemsInYourBagPage = get().findElements(By.xpath("//div[@class='product-info']//p[@class='product-name']"));

        for (WebElement webElement : itemsInYourBagPage) {
            if (webElement.getText().equals(itemNameHeader)) {
                System.out.println("The product selected by the user is on your bag page");
                break;
            }
        }
    }

    String subTotal;
    @Then("stored the subtotal value in a string")
    public void storedTheSubtotalValueInAString() {

        subTotal = get().findElement(By.xpath("//div[@class='cart-summary-total']/p/span")).getText();
    }

    @And("user can select proceed to checkout button")
    public void userCanSelectProceedToCheckoutButton() {

        basePage.ProceedToCheckout.click();

        waitForPresenceOfElement(By.xpath("//body[@class='checkout--desktop']"), 100);
    }

    @Then("user should able to input {string}")
    public void userShouldAbleToInputUsername(String input) {

        if (input.equals("userName")) {

            get().findElement(By.name("email")).sendKeys(ConfigurationReader.get("userName"));

        } else if (input.equals("password")) {

            get().findElement(By.name(input)).sendKeys(ConfigurationReader.get("password"));
        }
    }

    @And("click continue button")
    public void clickContinueButton() {

        get().findElement(By.xpath("//button[@type='submit']/span[text()='Continue']")).click();

        waitForInvisibility(By.xpath("//button[@type='submit' and contains(@class,'loading')]"), 50);
    }

    @Then("user bag subtotal should be equal the checkout subtotal")
    public void userBagSubtotalShouldBeEqualTheCheckoutSubtotal() {

        assert get()
                .findElement(By.xpath("//li[@class='bag__totals-item']/span[text()='Subtotal']/../span[2]"))
                .getText()
                .equals(subTotal);
    }

    @And("user scrolls to the payment method section")
    public void userScrollsToThePaymentMethodSection() {

        BrowserUtils.scrollToElement(get().findElement(By.id("klarna-container")));

        waitFor(3);
    }

    @Then("user can select a payment method {string}")
    public void userCanSelectAPaymentMethod(String paymentMethod) {

        if (paymentMethod.equals("paypal") || paymentMethod.equals("worldpay") || paymentMethod.equals("klarna")) {

            get().findElement(By.xpath("(//div[contains(@id,'" + paymentMethod + "')])[1]")).click();

        } else {

            get().findElement(By.xpath("//div/img[contains(@alt,'" + paymentMethod + "')]")).click();
        }

        waitFor(15);
    }
}
