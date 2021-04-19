package apptest;

import static org.junit.Assert.*;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName; 
import io.appium.java_client.remote.IOSMobileCapabilityType; 
import io.appium.java_client.remote.MobileCapabilityType; 

public class AppiumTest {
	private IOSDriver driver;

	@Before
	public void setUp() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION , "14.4");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME , "iPhone 11");
		
		//Framework de testing. El de iOS es XCUI_TEST
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME , AutomationName.IOS_XCUI_TEST);
		capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT , 500000);
		capabilities.setCapability("commandTimeouts" , "12000");
		
		capabilities.setCapability(MobileCapabilityType.APP  , 
				"/Users/martafernandez/Library/Developer/Xcode/DerivedData/UICatalog-dkpkzrrkxnpkfdbitvrpmgkbegyq/Build/Products/Debug-iphonesimulator/UICatalog.app");

		
		driver = new IOSDriver<>(new URL ("http://localhost:4723/wd/hub"), capabilities);
	}
	
	@Test
	public void appiumClickTest() {
		boolean isText = false;
		driver.findElementByAccessibilityId("Alert Views").click();
		driver.findElementByName("Text Entry").click();
		
		driver.findElementByXPath("//XCUIElementTypeTextField").sendKeys("Texto a introducir");
		driver.findElementByAccessibilityId("OK").click();
		//driver.findElementByAccessibilityId("Confirm / Cancel").click(); // MAL
		driver.findElementByAccessibilityId("Okay / Cancel").click(); // BIEN
		isText = driver.findElementByName("A message should be a short, complete sentence.").getText().contains("message");
		driver.findElementByAccessibilityId("OK").click();
		
		assertTrue(isText);
	}

}
