package com.academy.techcenture.ecommerce;

import com.academy.techcenture.ecommerce.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ApachePoiExperiment {
    @Test(dataProvider = "newUsersData")
    public void experimentApachePoiData( Map<String,String> users  ){

        System.out.println(users);

    }


    @DataProvider(name = "newUsersData")
    public Object[][] getNewUsersData(){
        //                                                  path the the ecommerce excel document               sheet name
        ExcelReader reader = new ExcelReader("src/main/resources/testData/ecommerce.xlsx", "newUsers");
        return reader.getData();
    }

}
