package com.example.platform;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@FoliumTeste", glue = "steps",
        monochrome = false, dryRun = false, plugin = {
        "pretty",
        "html:target/cucumber-report.html"
})
public class FoliumTeste {

}
