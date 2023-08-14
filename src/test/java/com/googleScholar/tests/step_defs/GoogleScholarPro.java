package com.googleScholar.tests.step_defs;

import com.googleScholar.tests.pages.HomePage;
import com.googleScholar.tests.utilities.BrowserUtils;
import com.googleScholar.tests.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleScholarPro {

    HomePage homePage = new HomePage();

    @Then("user is on main page of Google Scholar")
    public void user_is_on_main_page_of_google_scholar() {

        BrowserUtils.waitFor(3);

        WebElement actual = Driver.get().findElement(By.xpath("//*[@id='gs_hdr_hp_lgow']/img"));

        Assert.assertTrue(actual.isDisplayed());
    }

    @Then("user should be able to enter some valid keywords in the search bar")
    public void user_should_be_able_to_enter_some_valid_keywords_in_the_search_bar() {

        homePage.searchBox.sendKeys("phone");

        BrowserUtils.waitFor(4);
    }


    @Then("user should click on the search button")
    public void user_should_click_on_the_search_button() {
        homePage.searchButton.click();

        BrowserUtils.waitFor(4);

    }


    @Then("user verifies that relevant search results are displayed")
    public void user_verifies_that_relevant_search_results_are_displayed() {

        List<WebElement> words=Driver.get().findElements(By.xpath("//h3/a/b"));

        for (int i=0;i< words.size();i++){
            
            Assert.assertEquals(true, words.get(i).getText().equals("phone"));
        }

        BrowserUtils.waitFor(4);
    }
}