package AppiumProject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Task_Fitpeo {

	WebDriver driver;

	@BeforeTest
	public void Setup() {
		
		WebDriverManager.chromedriver().setup(); //initializing chrome driver 
		driver = new ChromeDriver(); //opening chrome browser
		driver.manage().window().maximize(); //maximizing browser
		driver.get("https://www.fitpeo.com/"); //navigating to url
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //providing implicit wait until element to find before throwing
		//no such element exception
		
		
		
	}

	@Test
	public void slider() {
		//click on revenue calculator
		driver.findElement(By.xpath("(//*[@class='satoshi MuiBox-root css-1aspamu' and text()='Revenue Calculator'])")).click();
		WebElement slider = driver.findElement(By.xpath("(//*[@class='MuiTypography-root MuiTypography-h4 crimsonPro css-12siehf'])//following::*[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-sy3s50']"));

		 //scroll down to input field
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", slider);

       
        
        // Initialize the Actions class
        Actions actions = new Actions(driver);
        
        // moved slider
        actions.clickAndHold(slider)
               .moveByOffset(94,0) 
               .release()
               .build()
               .perform();
		
        //clear the input data and  entered 560
        WebElement slider_input = driver.findElement(By.xpath("(//*[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-sizeSmall css-1kkflqu'])//following::*[@class='MuiOutlinedInput-notchedOutline css-igs3ac']"));
        js.executeScript("arguments[0].value='';", slider_input);
        js.executeScript("arguments[0].value='560';", slider_input);
        
       
        
		//getting style amd comparing with equal condition
        WebElement style = driver.findElement(By.xpath("//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-sy3s50']"));
     
        String StyleAttribute = style.getAttribute("style");

       
        String splitval = StyleAttribute.split("left: ")[1].split(";")[0];

        // Print the extracted split value
        System.out.println("split value is: " + splitval);
        
       
		if(splitval.equals("41.5%")) {
			System.out.println("slider dragged  when enter 820 in text field");
		} else {
			System.out.println("slider not dragged when enter 820 in text field");
		}
		//using list collection and using for loop clicked on 3 checkboxes
		js.executeScript("window.scrollTo(0,200)");
		List<WebElement> checkbox = driver.findElements(By.xpath("//*[@type='checkbox']"));
		
		for(int i=1;i<=checkbox.size()-9;i++) {
			driver.findElement(By.xpath("(//*[@type='checkbox'])["+i+"]")).click();
		}
		//Getting the recurr value and comparing with expected string data
		String TotalRecurrValue = driver.findElement(By.xpath("(//*[text()='Total Recurring Reimbursement for all Patients Per Month'])//following::*[@class='MuiTypography-root MuiTypography-body1 inter css-12bch19']")).getText();
		System.out.println(TotalRecurrValue);
		String splitvalue = TotalRecurrValue.replaceAll("$", "");
		
		if(splitvalue.contains("98760")) {
			System.out.println("Total recurring value is  displaying correctly");
		} else {
			System.out.println("Total recurring value is not displaying correctly");
		}
	}
}
