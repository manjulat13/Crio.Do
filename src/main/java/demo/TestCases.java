package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        String urlText=driver.getCurrentUrl();
        if(urlText.contains("calendar.")){
            System.out.println("URL Contains calender");
        }else{
            System.out.println("URL doesn't Contains calender");

        }
    

        System.out.println("end Test case: testCase01");
    }
    public void testCase02() throws InterruptedException{
        driver.get("https://calendar.google.com");
        WebElement drop=driver.findElement(By.xpath("//button[@jsname='jnPWCc']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", drop);

        Thread.sleep(1000);
        WebElement month=driver.findElement(By.xpath("//li[@data-viewkey='month']"));
               js.executeScript("arguments[0].click();", month);
               WebElement selected=driver.findElement(By.xpath("//button[@jsname='jnPWCc']/span"));
            String textdrop=selected.getText();
            if(textdrop.contains("Month")){
                System.out.println("Switched to the month view");
            }else{
                System.out.println("Not Switched to the month view");

            }

        Thread.sleep(1000);
        // driver.findElement(By.xpath("//div[@class='Gw6Zhc']")).click(); 
        // driver.findElement(By.xpath("//div[@data-key='task']")).click();
        WebElement date=driver.findElement(By.xpath("//*[@id='YPCqFe']/div/div/div/div[2]/div[2]/div[1]/div[5]"));
        js.executeScript("arguments[0].click();", date);
        Thread.sleep(2000);
        WebElement task=driver.findElement(By.xpath("//button[@data-tab-button='YjoMNe']"));
        js.executeScript("arguments[0].click();", task);

        String title = "Crio INTV Task Automation";
        String description = "Crio INTV Calendar Task Automation automate automation code";
        driver.findElement(By.xpath("//input[@aria-label='Add title and time']")).sendKeys(title);
    

         Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@aria-label='Add description']")).sendKeys(description);
        WebElement save=driver.findElement(By.xpath("//span[text()='Save']"));
        js.executeScript("arguments[0].click();", save);
       Thread.sleep(2000);
       System.out.println("The Calendar switched to month view and a task was created.");


    }
    public void testCase03() throws InterruptedException{
        
        driver.get("https://calendar.google.com");
        Thread.sleep(5000);
       
        WebElement event=driver.findElement(By.xpath("//span[text()='Crio INTV Task Automation']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        event.click();
        
        Thread.sleep(1000);
        WebElement edit=driver.findElement(By.xpath("//button[@aria-label='Edit task']"));
        js.executeScript("arguments[0].click();", edit);
        
        WebElement clear=driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        clear.clear();
        
        String description="Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application";
        clear.sendKeys(description);
        Thread.sleep(1000);
        WebElement save=driver.findElement(By.xpath("//span[text()='Save']"));
                save.click();
                Thread.sleep(1000);
                js.executeScript("arguments[0].click();", event);
        Thread.sleep(2000);
        WebElement updatedTExt=driver.findElement(By.xpath("//div[@class='Mz3isd']/div[3]/div[2]"));
        String updatedDescription=updatedTExt.getText();
        if(updatedDescription.contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")){
            System.out.println("The task description is successfully updated and displayed");

        }else{
            System.out.println("The task description is not updated successfully");
        }
        
    }
    public void testCase04() throws InterruptedException{
        driver.get("https://calendar.google.com");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //  Click on an existing task & Open the task details..
        Thread.sleep(5000);
        WebElement event=driver.findElement(By.xpath("//span[text()='Crio INTV Task Automation']"));
        js.executeScript("arguments[0].click();", event);
        Thread.sleep(1000);
     
        // Verify the title of the task.
        WebElement titleText= driver.findElement(By.xpath("//div[@class='toUqff ']/span"));
        String title=titleText.getText();
        if(title.contains("Crio INTV Task Automation")){
            System.out.println("Task contains Title");
        }else{
            System.out.println("Task doesn't contains Title");

        }

        // Delete the task.
        WebElement deleteButton=driver.findElement(By.xpath("//button[@aria-label='Delete task']"));
        deleteButton.click();

        // Confirm the task deletion, by verifying "Task deleted" is displayed.
        WebElement taskDelMsg=driver.findElement(By.xpath("//div[@class='VYTiVb']"));
        String status=taskDelMsg.getText();
        if(status.contains("Task deleted")){
        System.out.println("The task is successfully deleted, and the confirmation message indicates Task deleted");
        
    }
    else{
        System.out.println("Task not deleted");

    }
    }


}
