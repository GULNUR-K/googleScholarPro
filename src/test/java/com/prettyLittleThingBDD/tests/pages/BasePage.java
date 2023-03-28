package com.prettyLittleThingBDD.tests.pages;

import com.prettyLittleThingBDD.tests.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage() {
    PageFactory.initElements(Driver.get(), this);
}

    @FindBy(xpath = "//button[@type='button' and text()='Accept All']")
    public WebElement acceptCookies;

    @FindBy(id = "add-to-bag")
    public WebElement addToBag;

    @FindBy(className = "bag-icon")
    public WebElement bagIcon;

    @FindBy(xpath = "(//button/span[@data-event-action='Proceed To Checkout'])[2]")
    public WebElement ProceedToCheckout;
}
