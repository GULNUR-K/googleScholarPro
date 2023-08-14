package com.googleScholar.tests.pages;

import com.googleScholar.tests.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.get(), this);
    }

     @FindBy(xpath = "//head/title")
     public WebElement pageTitle;

    @FindBy(xpath="//input[@type='text' and @name='q']")
    public WebElement searchBox;

    @FindBy(css = "button[id='gs_hdr_tsb'] span[class='gs_ico']")
    public WebElement searchButton;

    @FindBy(css = "#gs_res_ccl")
    public List<WebElement> page;
}