package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//provide access to logger instances
import org.apache.logging.log4j.Logger;   //represents the logger object used for logging messages
 
public class BaseClass {

	public static WebDriver driver;
	public  Logger logger;
	public Properties p;
	
	@BeforeClass(alwaysRun=true)  //(groups= {"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	
	public void setup(String os, String br) throws IOException
	{
		//loading propertyfile 
		
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		
		//Loading log4j file.
		logger = LogManager.getLogger(this.getClass()); //this.getclass used for all classes.
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WINDOWS);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		

		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
      {
		switch(br.toLowerCase())
		{
		case "chrome": driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		default: System.out.println("No matching browser...");
		return;
		}
      }
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(alwaysRun=true)    //(groups= {"Sanity","Regression","Master"})
	
	public void tearDown()
	{
		if(driver!=null)
		{                                           //driver.close();
			driver.quit();
		}
	}
	
	
	
	public String randomString()
	{
		String generatedString =RandomStringUtils.insecure().nextAlphabetic(5);
		return generatedString ;
		
	}
	
	public String randomNumber()
	{
		String generatedString=RandomStringUtils.insecure().nextNumeric(10);
		return generatedString;
		
	}
	
	public String randomAlphaNumeric()
	{
		
		String str= RandomStringUtils.insecure().nextAlphabetic(5);
		String num =RandomStringUtils.insecure().nextNumeric(3);
		return(str+"@"+num);

		
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

	
	
}
