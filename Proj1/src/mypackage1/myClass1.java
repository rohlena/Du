package mypackage1;

import java.util.List;
import java.util.concurrent.TimeUnit; 
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


public class myClass1 {
	public static void main(String[] args) {
		System.out.println("Test initialize");		
		frmWorkSelenium frmwrkslnm = new frmWorkSelenium();
		objectRepository or = new objectRepository();			
		System.out.println("Test start");

		//or.filePath = "/home/golem/eclipse-workspace/Proj1/ObjectRepository.properties";
		or.filePath = "/home/golem/eclipse-workspace/Proj1/OR.txt";
		
		System.setProperty("webdriver.gecko.driver","/home/golem/eclipse-workspace/Proj1/seleniumWD/geckodriver");
		Integer stepDelay = 10; // # in seconds
		Integer maxLoadingTime = 60; // # in seconds
		
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, maxLoadingTime);
		driver.manage().timeouts().implicitlyWait(stepDelay, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1900,1080));
		
        String actualTitle = "";
        String baseUrl = "http://www.alza.cz";
        String expectedTitle = "Alza.cz";
        
        driver.get(baseUrl);
        try{    
	        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html")));
	    	
			frmwrkslnm.saveScreenShot("/home/golem/eclipse-workspace/Proj1/ssh.png",driver);
		
	        actualTitle = driver.getTitle();   
	        if (actualTitle.startsWith(expectedTitle)){
	            System.out.println("Test Passed!");
	        } else {
	            System.out.println("Test Failed");
	            //return;
	        }
	     
	        WebElement lnkPrihlasit = driver.findElement(By.xpath(or.loadKeyValue("lnkPrihlasit")));
	        lnkPrihlasit.click();
	   
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.id("loginIframe"))));
	        System.out.println("set_iframe");
	        
	        String eMail1 = "xx@xx.cz";
	        String passwrd = "xxxxxxxx";
	        WebElement txbUserName = driver.findElement(By.xpath(or.loadKeyValue("txbUserName")));
	        txbUserName.clear();
	        txbUserName.sendKeys(eMail1);	
	        
	        WebElement txbPass = driver.findElement(By.xpath(or.loadKeyValue("txbPass")));
	        txbPass.clear();
	        txbPass.sendKeys(passwrd);
	        
            WebElement btnPrihlasit = driver.findElement(By.xpath(or.loadKeyValue("btnPrihlasit")));
            //btnPrihlasit.click(); temporary commented
            frmwrkslnm.waitSeconds(10);
 /*  temporary commented         
            try {
            	WebElement errLblPass = driver.findElement(By.xpath(or.loadKeyValue("errLblPass")));
            	if (frmwrkslnm.isElementExists(By.xpath(or.loadKeyValue("errLblPass")),driver)) {
            		System.out.println("Wrong login account");
            		//return;
            	}
            }
            catch (NoSuchElementException nsee){
            	
            	
            }
            
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("loginIframe")));
*/                        
	        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
	        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("")));
	        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.id("iframe"))));
	        
	        frmwrkslnm.waitSeconds(10);
	        
	        //List<WebElement> seq = driver.findElements(By.tagName("iframe"));
	        //System.out.println("set_iframe" + "seq.length()");

	        
	        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html")));
        
        
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframe")));
            
            WebElement lnkMojeAlza = driver.findElement(By.xpath(or.loadKeyValue("lnkMojeAlza")));
            
          
	    }
	    catch (NoSuchElementException nsee){ 
	    	System.out.println(nsee.toString()); 
	    }
	    catch (TimeoutException toe){ 
	    	System.out.println(toe.toString());  
	    	throw(toe);
	    }
	    
        
 
        driver.close();
        
        
        driver.quit();

		
	}
}
