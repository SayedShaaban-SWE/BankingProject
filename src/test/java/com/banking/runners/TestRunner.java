package com.banking.runners;

import com.banking.utils.LoggerUtil;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.banking.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        tags = ""
)
public class TestRunner extends AbstractTestNGCucumberTests {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        LoggerUtil.logMethodEntry();
        Object[][] scenarios = super.scenarios();
        logger.info("Running {} scenarios", scenarios.length);
        LoggerUtil.logMethodExit(null);
        return scenarios;
    }
}