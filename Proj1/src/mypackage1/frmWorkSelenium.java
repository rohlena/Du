package mypackage1;

import java.util.concurrent.TimeUnit;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.FileOutputStream;			
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;




public class frmWorkSelenium {
	
	private String browser;
	private String machine;
	private WebDriver driver;
	private String environment;
	private String data;	

/*
	public Funkce (WebDriver driver, String environment, String machine, String browser) {
		this.driver = driver;
		this.environment = environment;
		this.machine = machine;
		this.browser = browser;
	   //PageFactory.initElements(driver, this);
	  
	}
*/            
	
	public void saveScreenShot(String fileName, WebDriver driver ) {
/*		String fullFileName = null;
		this.environment ="Lnx";
		this.data = "/home/golem/eclipse-workspace/Proj1/";
	    String timeStamp1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date().getTime());
	    if (this.environment.equals("Lnx") || this.environment.equals("Win") || this.environment.equals("Docker")) {
	    	fullFileName = this.data + timeStamp1 + "_" + fileName + ".png";
        }
	    else {
	    	fullFileName = this.data + browser + "_" + timeStamp1 + "_" + fileName + ".png";
        } 	  
*/	    
	    File trgFile = new File(fileName);
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try	{
			//FileUtils.copyFile(scrFile, new File(fullFileName));
    		com.google.common.io.Files.copy(scrFile, trgFile);    		
		} 
    	catch (Exception e1) {
			System.out.println("IO Exception: " + e1.getMessage());
			
			e1.printStackTrace();
		}
    }
	
	public boolean isElementExists(By by,WebDriver driver) {
	    boolean isExists = true;
	    try {
	        driver.findElement(by);
	    } 
	    catch (NoSuchElementException e) {
	        isExists = false;
	    }
	    return isExists;
	}
	
	public void waitSeconds(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(Integer.toUnsignedLong(seconds));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
         
	}
	
	public void waitForObject(WebElement element, int seconds, String endtest, WebDriver driver) throws NoSuchElementException {
        System.out.println("Waiting for displaying element - " + element.toString());
        // cyklus cekani na object
		for(int i=1; i<=seconds; i++) { 
				
			try {
				element.isDisplayed();
				break;	
			} catch (Exception e) {
				//System.out.println("waiting for displaying object: " + element.toString());					
				waitSeconds(1);	
			}
		 	
		    if (i==seconds) {
			   System.out.println("Object: " + element.toString() +" is not displayed.");
			   
			   if (endtest =="Y") {
				// printscreen 
				   saveScreenShot("object is not displayed", driver);
					  throw new AssertionError("Error webElement : " + element.toString() + " was not displayed.");		  
				}
		    }
		} 
	}
	
	public String findText(String inputStr,String before,String after) {
    	try	{
			int a=inputStr.indexOf(before);
			String s=inputStr.substring(a+before.length());
			int b=s.indexOf(after);
		    return(s.substring(0,b));
		}catch (Exception e){
			System.out.println("Exception in function findText()");
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
    	}
		return("");
    }
	
}


