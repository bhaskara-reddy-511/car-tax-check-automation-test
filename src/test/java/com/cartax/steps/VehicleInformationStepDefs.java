package com.cartax.steps;

import com.cartax.model.Vehicle;
import com.cartax.pages.CarTaxCheckHomePage;
import com.cartax.pages.VehicleDetailsPage;
import com.cartax.utilities.CsvHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class VehicleInformationStepDefs {
    private static final Logger logger = LoggerFactory.getLogger(VehicleInformationStepDefs.class);

    @Managed
    WebDriver driver;

    private VehicleDetailsPage vehicleDetailsPage;
    private CarTaxCheckHomePage carTaxCheckHomePage;
    private List<String> registrationNumbers;
    private final Map<String, Vehicle> actualVehicleDetailsMap = new HashMap<>();
    private Map<String, Vehicle> expectedVehicleDetailsMap = new HashMap<>();
    private EnvironmentVariables environmentVariables;


    @Given("I read input file {string} to extract vehicle registration numbers")
    public void iReadInputFileToExtractVehiclRegistrationNumbers(String inputFile) {
        registrationNumbers = CsvHelper.getVehicleRegistrationNumbersFromInputFile(inputFile);
        logger.info("Registration Numbers from input file:"+registrationNumbers);
    }

    @When("I Navigate to homepage")
    public void iNavigateToWebsite() {
        String carTaxHomePageUrl =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webdriver.base.url");
        driver.get(carTaxHomePageUrl);
    }

    @And("perform free car check to get the details")
    public void performFreeCarCheckToGetTheDetails() {
        registrationNumbers.forEach(registrationNumber ->{
            carTaxCheckHomePage.enterRegistrationNumber(registrationNumber);
            carTaxCheckHomePage.clickFreeCarCheckButton();
            Vehicle vehicle = Vehicle.builder()
                    .registration(vehicleDetailsPage.getRegistrationNumber())
                    .make(vehicleDetailsPage.getMake())
                    .model(vehicleDetailsPage.getModel())
                    .color(vehicleDetailsPage.getColor())
                    .year(vehicleDetailsPage.getYear())
                    .build();
            actualVehicleDetailsMap.put(registrationNumber,vehicle);
            driver.navigate().back();
        });
    }

    @Then("compare the vehicle details returned from website with output file {string}")
    public void compareTheVehicleDetailsReturnedFromWebsiteWithOutputFile(String outputFile) {
        expectedVehicleDetailsMap = CsvHelper.getVehicleListFromOutputFile(outputFile);
        expectedVehicleDetailsMap.keySet().forEach(registrationNumber ->{
            Vehicle actualVehicle = actualVehicleDetailsMap.get(registrationNumber);
            if(actualVehicle == null){
                logger.info("Vehicle Not found with Registration Number: "+registrationNumber);
            }else {
                Vehicle expectedVehicle = expectedVehicleDetailsMap.get(registrationNumber);
                assertThat("Vehicle Registration Number is coming wrong", actualVehicle.getRegistration(), equalTo(expectedVehicle.getRegistration()));
                assertThat("Vehicle Make is coming wrong", actualVehicle.getMake(), equalTo(expectedVehicle.getMake()));
                assertThat("Vehicle Model is coming wrong", actualVehicle.getModel(), equalTo(expectedVehicle.getModel()));
                assertThat("Vehicle Color is coming wrong", actualVehicle.getColor(), equalTo(expectedVehicle.getColor()));
                assertThat("Vehicle Year is coming wrong", actualVehicle.getYear(), equalTo(expectedVehicle.getYear()));
            }
        });
    }

}
