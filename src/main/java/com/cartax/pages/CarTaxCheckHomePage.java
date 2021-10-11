package com.cartax.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class CarTaxCheckHomePage extends PageObject {

    @FindBy(id="vrm-input")
    WebElementFacade registrationNumber;

    @FindBy(xpath="//button[contains(text(),'Free Car Check')]")
    WebElementFacade freeCarCheckButton;

    public void enterRegistrationNumber(String number){
        registrationNumber.waitUntilVisible();
        registrationNumber.sendKeys(number);
    }

    public void clickFreeCarCheckButton(){
        freeCarCheckButton.waitUntilClickable();
        freeCarCheckButton.click();
    }

}
