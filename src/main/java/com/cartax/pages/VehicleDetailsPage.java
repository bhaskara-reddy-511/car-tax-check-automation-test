package com.cartax.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;


public class VehicleDetailsPage extends PageObject {

    @FindBy(xpath="//dt[text()='Registration']//following-sibling::dd")
    WebElementFacade registrationNumber;

    @FindBy(xpath="//dt[text()='Make']//following-sibling::dd")
    WebElementFacade make;

    @FindBy(xpath="//dt[text()='Model']//following-sibling::dd")
    WebElementFacade model;

    @FindBy(xpath="//dt[text()='Colour']//following-sibling::dd")
    WebElementFacade colour;

    @FindBy(xpath="//dt[text()='Colour']//following-sibling::dd")
    WebElementFacade color;

    @FindBy(xpath="//dt[text()='Year']//following-sibling::dd")
    WebElementFacade year;

    public String getRegistrationNumber(){
        return getFieldValue(registrationNumber);
    }

    public String getMake(){
        return getFieldValue(make);
    }

    public String getModel(){
        return getFieldValue(model);
    }

    public String getColor(){
        return getFieldValue(color);
    }

    public String getYear(){
        return getFieldValue(year);
    }

    private String getFieldValue(WebElementFacade element){
        element.waitUntilVisible();
        return element.getText();
    }
}
