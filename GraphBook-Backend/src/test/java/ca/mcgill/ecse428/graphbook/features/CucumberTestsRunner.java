package ca.mcgill.ecse428.graphbook.features;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = "pretty", 
		features = "src/test/resources",
		glue = "ca.mcgill.ecse428.graphbook.features")
public class CucumberTestsRunner {
	
	
	
}