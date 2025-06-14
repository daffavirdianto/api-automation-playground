package runners;

import org.testng.annotations.AfterSuite;

import helper.GenerateReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {"pretty", "json:target/cucumber.json"},
        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @AfterSuite
    public void after_suite() {
        GenerateReport.generateReport();
        System.out.println("Cucumber runner start...");
    }
}