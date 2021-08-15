package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{

	@Test(dataProvider="LoginData")
	public void loginDDT(String un, String pw) throws InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(un);
		
		logger.info("username is provided");
		
		
		try {
			captureScreen(driver, "loginDDT");
			logger.info("checking for screen shot after username");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		lp.setPassword(pw);
		logger.info("password is provided");
		lp.clickSubmit();
		
		
		Thread.sleep(3000);
		
		
		if(isAlertPresent()) {
			
			try {
				captureScreen(driver, "loginDDT");
				logger.info("checking for screen shot after result");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
			
			driver.switchTo().alert().accept();		
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("login failed");
			
			
			
		}else {
			Assert.assertTrue(true);
			logger.info("Login Passed");
			lp.clickLogout();
			Thread.sleep(3000);
			
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		
		
	}
	
	public boolean isAlertPresent() {
		
		
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			// TODO: handle exception
			
			return false;
		}
		
		
		
		
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		
		
		String path = System.getProperty("user.dir") +"/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "sheet1");
		int colcount = XLUtils.getCellCount(path, "sheet1", 1);
		
		String logindata[][] = new String[rownum][colcount];
		
		for(int i = 1; i<= rownum; i++) {
			
			for(int j = 0; j < colcount; j++) {
				
				
				logindata[i-1][j] = XLUtils.getCellData(path, "sheet1", i, j);
				
			}
		}
		
		
				
		return logindata;
		
		
	}
	
}
