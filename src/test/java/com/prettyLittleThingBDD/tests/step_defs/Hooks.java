package com.prettyLittleThingBDD.tests.step_defs;

import com.prettyLittleThingBDD.tests.utilities.ConfigurationReader;
import com.prettyLittleThingBDD.tests.utilities.Driver;
import io.cucumber.java.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

import static com.prettyLittleThingBDD.tests.utilities.BrowserUtils.*;

public class Hooks {

    @Before
    public void setUp() {
        String browser =ConfigurationReader.get("browser");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        if(!browser.contains(("mobile"))) {
            Driver.get().manage().window().maximize();
        }
//        Driver.get().manage().window().setSize(new Dimension(1440, 900));

        Driver.get().get(ConfigurationReader.get("mainPageUrl"));

        waitForPresenceOfElement(By.xpath("//html[@class]"), 10);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        Driver.closeDriver();
    }
}