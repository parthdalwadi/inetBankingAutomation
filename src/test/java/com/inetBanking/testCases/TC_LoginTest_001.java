package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	
	
	@Test
	public void loginTest() {
		
	
		
		
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("username is entered");
		
		lp.setPassword(password);
		logger.info("password entered");
		
		lp.clickSubmit();
		logger.info("login button is clicked");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			
			
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
			
		}else {
			
			try {
				captureScreen(driver, "loginTest");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue(false);
			logger.info("Login Test Failed");
			
		}
		
		
	}

}
