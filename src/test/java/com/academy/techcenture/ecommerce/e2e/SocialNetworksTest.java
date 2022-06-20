package com.academy.techcenture.ecommerce.e2e;

import com.academy.techcenture.ecommerce.base.BaseTest;
import com.academy.techcenture.ecommerce.pages.HomePage;
import org.testng.annotations.Test;

public class SocialNetworksTest extends BaseTest {



    @Test
    public void verifyAutomationPracticeSocialNetworksTest() throws InterruptedException {

        HomePage homePage = new HomePage(driver, softAssert);
        homePage.verifySocialNetworks();
        softAssert.assertAll();

    }





}
