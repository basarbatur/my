package com.qatools.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features"  //without file name it executes all tests
			,glue="com.qatools.steps"
			//,dryRun=false
			,monochrome=true
			,tags= {"@deneme"}   //groups we want to run
			,plugin= {"pretty",
					"html:target/cucumber-default-reports",
					"json:target/cucumber.json",
					"rerun:target/rerun.txt"}
			)
		
		
		
		
public class TestRunner {

}
//change file name in POM.xml build/plugin
/*open git bash, navigate to your project directory (using cd "folder_name" command), 
 * and then just run commands: 1. mvn clean - wait until finished, 2. mvn verify - also wait. 
 * Then go back to eclipse, refresh the project and go to target folder
 */