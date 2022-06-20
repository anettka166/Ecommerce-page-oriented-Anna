package com.academy.techcenture.ecommerce.pages;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class UserRegistrationPage {

    private WebDriver driver;
    private CommonUtils commonUtils;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    public UserRegistrationPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.commonUtils = new CommonUtils();
        this.softAssert = softAssert;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);

    }


    @FindBy(className = "page-heading")
    private WebElement createAnAccountHeader;

    @FindBy(xpath = "//h3[text()='Your personal information']")
    private WebElement yourPersonalInfoLabel;

    @FindBy(xpath = "//label[contains(text(),'Title')]")
    private WebElement titlePersonalLabel;

    @FindBy(xpath = "//div[@class='radio-inline']//span/input")
    private List<WebElement> genderPersonalInput;


    @FindBy(xpath = "//label[@for='customer_firstname']")
    private WebElement firstNamePersonalLabel;

    @FindBy(id = "customer_firstname")
    private WebElement inputFirstNamePersonal;

    @FindBy(xpath = "//label[@for='customer_lastname']")
    private WebElement lastNameLabelPersonal;

    @FindBy(id = "customer_lastname")
    private WebElement inputLastNamePersonal;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailLabelPersonal;

    @FindBy(id = "email")
    private WebElement inputEmailPersonal;

    @FindBy(xpath = "//label[@for='passwd']")
    private WebElement passwdLabelPersonal;

    @FindBy(id = "passwd")
    private WebElement inputPasswdPersonal;

    @FindBy(xpath = "//span[contains(text(), 'Five characters')]")
    private WebElement formInfoPersonalLabel;

    @FindBy(xpath = "//label[text()='Date of Birth']")
    private WebElement dateOfBirthPersonalLabel;

    @FindBy(id = "days")
    private WebElement daysDroppedDownPersonal;

    @FindBy(id = "months")
    private WebElement monthsDroppedDownPersonal;

    @FindBy(id = "years")
    private WebElement yearsDroppedDownPersonal;

    @FindBy(id = "newsletter")
    private WebElement inputNewsLettersPersonal;

    @FindBy(id = "optin")
    private WebElement specialOffersInputPersonal;

    @FindBy(xpath = "//h3[contains(text(),'Your address')]")
    private WebElement yourAddress;

    @FindBy(xpath = "//label[@for='firstname']")
    private WebElement fistNameAddressLabel;

    @FindBy(id = "firstname")
    private WebElement inputFirstNameAddress;

    @FindBy(xpath = "//label[@for='lastname']")
    private WebElement lastNameAddressLabel;

    @FindBy(id = "lastname")
    private WebElement inputLastNameAddress;

    @FindBy(xpath = "//label[@for='company']")
    private WebElement companyAddressLabel;

    @FindBy(id = "company")
    private WebElement inputCompanyAddress;

    @FindBy(xpath = "//label[@for='address1']")
    private WebElement address1Label;

    @FindBy(id = "address1")
    private WebElement input1Address;

    @FindBy(xpath = "//span[contains(text(),'Street address, P.O. Box')]")
    private WebElement address1Txt;

    @FindBy(xpath = "//label[@for='address2']")
    private WebElement address2Label;

    @FindBy(id = "address2")
    private WebElement input2Address;

    @FindBy(xpath = "//span[contains(text(),'Apartment, suite')]")
    private WebElement address2Txt;

    @FindBy(xpath = "//label[@for='city']")
    private WebElement cityAddressLabel;

    @FindBy(id = "city")
    private WebElement inputCityAddress;

    @FindBy(xpath = "//label[@for='id_state']")
    private WebElement stateLabelAddress;

    @FindBy(id = "id_state")
    private WebElement inputStateAddress;

    @FindBy(xpath = "//label[@for='postcode']")
    private WebElement postalCodeLabelAddress;

    @FindBy(id = "postcode")
    private WebElement inputPostalCodeAddress;

    @FindBy(xpath = "//label[@for='id_country']")
    private WebElement countryLabelAddress;

    @FindBy(id = "id_country")
    private WebElement inputCountryAddress;

    @FindBy(xpath = "//label[@for='other']")
    private WebElement additionalInfoAddressLabel;

    @FindBy(id = "other")
    private WebElement inputAdditionalInfoAddress;


    @FindBy(xpath = "//label[@for='phone_mobile']")
    private WebElement mobilePhoneLabel;

    @FindBy(id = "phone_mobile")
    private WebElement inputMobilePhone;

    @FindBy(xpath = "//label[@for='alias']")
    private WebElement myAddressLabel;

    @FindBy(id = "alias")
    private WebElement inputMyAddress;

    @FindBy(id = "submitAccount")
    private WebElement registerAddressBtn;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    private WebElement alertLabel;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]/p")
    private WebElement alertTxt;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]//li")
    private List<WebElement> alertMsgLists;

    public void registerUser(Map<String,String> users) {
        fillOutPersonalDetailsSection(users);
        fillOutAddressSection(users);


    }

    private void fillOutPersonalDetailsSection(Map<String,String> users) {

        softAssert.assertTrue(createAnAccountHeader.isDisplayed());
        softAssert.assertEquals(yourPersonalInfoLabel.getText(), "YOUR PERSONAL INFORMATION");

        if (users.get("title").equals("Mr")){
            genderPersonalInput.get(0).click();
        }else{
            genderPersonalInput.get(1).click();
        }

        softAssert.assertTrue(firstNamePersonalLabel.isDisplayed());
        softAssert.assertTrue(inputFirstNamePersonal.isDisplayed());

        String newEmail = users.get("email");

        softAssert.assertEquals(inputEmailPersonal.getAttribute("value"), newEmail, "Email Addresses do not match");

        String firstName = users.get("firstName");
        String lastName = users.get("lastName");
        String password = users.get("password");

        inputFirstNamePersonal.sendKeys(firstName);
        softAssert.assertTrue(lastNameLabelPersonal.isDisplayed());
        inputLastNamePersonal.sendKeys(lastName);

        softAssert.assertTrue(emailLabelPersonal.isDisplayed());
        inputEmailPersonal.getAttribute("value");

        softAssert.assertTrue(passwdLabelPersonal.isDisplayed());
        inputPasswdPersonal.sendKeys(password);

        softAssert.assertEquals(formInfoPersonalLabel.getText(), "(Five characters minimum)");

        softAssert.assertTrue(dateOfBirthPersonalLabel.isDisplayed());

        String dob = users.get("dob"); // 12-May-1950

        String[] splitDob = dob.split("-");
        int dobYear = Integer.parseInt(splitDob[2]);
        String dobMonth = splitDob[1] + " "; //05
        int dobDay = Integer.parseInt(splitDob[0]);

        Select select = new Select(yearsDroppedDownPersonal);
        select.selectByValue(String.valueOf(dobYear));

        select = new Select(monthsDroppedDownPersonal);
        select.selectByVisibleText(dobMonth);

        select = new Select(daysDroppedDownPersonal);
        select.selectByValue(String.valueOf(dobDay));


        if (users.get("newsLetter").equals("yes")){
            inputNewsLettersPersonal.click();
        }

        if (users.get("specialOffers").equals("yes")){
            specialOffersInputPersonal.click();
        }
    }


    private void fillOutAddressSection(Map<String,String> users) {

        softAssert.assertEquals(yourAddress.getText(), "YOUR ADDRESS");
        softAssert.assertTrue(fistNameAddressLabel.isDisplayed());
        inputFirstNameAddress.getAttribute("value");

        softAssert.assertTrue(lastNameAddressLabel.isDisplayed());
        inputLastNameAddress.getAttribute("value");

        softAssert.assertTrue(companyAddressLabel.isDisplayed());
        inputCompanyAddress.sendKeys(users.get("company"));

        softAssert.assertTrue(address1Label.isDisplayed());
        input1Address.sendKeys(users.get("address"));
        softAssert.assertEquals(address1Txt.getText(), users.get("address1Desc"));

        softAssert.assertTrue(address2Label.isDisplayed());
        input2Address.sendKeys(users.get("address2"));
        softAssert.assertEquals(address2Txt.getText(), "address2Desc");

        softAssert.assertTrue(cityAddressLabel.isDisplayed());
        inputCityAddress.sendKeys(users.get("city"));

        softAssert.assertTrue(stateLabelAddress.isDisplayed());
        Select select = new Select(inputStateAddress);
        select.selectByVisibleText(users.get("state"));

        softAssert.assertTrue(postalCodeLabelAddress.isDisplayed());

        inputPostalCodeAddress.sendKeys(  users.get("zipcode").substring(0, 5) );

        softAssert.assertTrue(countryLabelAddress.isDisplayed());
        select = new Select(inputCountryAddress);
        String text = select.getFirstSelectedOption().getText();
        softAssert.assertEquals(text, users.get("country"));

        softAssert.assertTrue(additionalInfoAddressLabel.isDisplayed());
        inputAdditionalInfoAddress.sendKeys(users.get("additionalInfo"));

        softAssert.assertTrue(mobilePhoneLabel.isDisplayed());
        inputMobilePhone.sendKeys(users.get("mobileNumber"));

        softAssert.assertTrue(myAddressLabel.isDisplayed());
        inputMyAddress.sendKeys(users.get("addressAlias"));

        softAssert.assertTrue(registerAddressBtn.isEnabled());

        System.out.println("email" + inputEmailPersonal.getAttribute("value"));
        System.out.println("password" + inputPasswdPersonal.getAttribute("value"));

        registerAddressBtn.click();
    }


    public void verifyErrorsOnUserRegisterPage(Map<String,String> users) {

        Actions actions = new Actions(driver);
        actions.moveToElement(registerAddressBtn).build().perform();
        registerAddressBtn.click();

        softAssert.assertTrue(alertLabel.isDisplayed());
        softAssert.assertEquals(alertTxt.getText().trim(), "There are 8 errors");

        String errorMessages = users.get("errorMessages");
        String[] split = errorMessages.split("-");
        for (int i = 0; i < alertMsgLists.size(); i++) {
            softAssert.assertEquals(alertMsgLists.get(i).getText().trim(), split[i].replace("\n","").trim(), "error msg did not match");

        }

    }
}
