package Runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/addToCart.feature",
        glue = {"StepDefinitions"}

)
public class CucumberRunners extends  AbstractTestNGCucumberTests{
}
