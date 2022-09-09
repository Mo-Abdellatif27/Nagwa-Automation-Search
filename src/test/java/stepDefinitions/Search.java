package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Search {
    public static WebDriver driver = null;

    //Open Nagwa Website in Arabic
    @Given("User navigates to Nagwa website")
    public void OpenBrowser() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.nagwa.com/ar/");
        Thread.sleep(1000);
    }

    //Change Language to English
    @When("Display language is English")
    public void ChooseLanguage() throws InterruptedException{
        driver.findElement(By.cssSelector("i[class=\"f-icon arrow-head-down-icon\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[href=\"https://www.nagwa.com/en/\"]")).click();
        Thread.sleep(1000);
        String Expected = "https://www.nagwa.com/en/";
        String Actual = driver.getCurrentUrl();
        Assert.assertTrue(Actual.equals(Expected), "Error language displayed!");
        Thread.sleep(1000);
    }

    //Start Search Process
    @And("User click on search icon")
    public void ClickSearch() throws InterruptedException{
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div[class=\"search\"]")).click();
        Thread.sleep(1000);
    }

    //Search about "additional" keyword
    @When("User enter search keyword")
    public void EnterKeyword() throws InterruptedException{
        driver.findElement(By.id("txt_search_query")).sendKeys("addition");
        driver.findElement(By.id("txt_search_query")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }

    //Choose the second result after results check
    @And("User can browse any subject")
    public void DisplayedSuccess() throws InterruptedException{
        String Expected = "https://www.nagwa.com/en/search/?q=addition";
        String Actual = driver.getCurrentUrl();
        Assert.assertTrue(Actual.equals(Expected), "Not Found!");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a[href=\"https://www.nagwa.com/en/lessons/167131671315/\"]")).click();
        Thread.sleep(1000);
    }

    //Preview Worksheet
    @When("User open subject worksheet")
    public void OpenWorksheet() throws InterruptedException{
        driver.findElement(By.cssSelector("a[href=\"../../worksheets/805156363640\"]")).click();
        Thread.sleep(1000);
    }

    //Count number of questions
    @Then("User can see all questions in the worksheet")
    public void CountQuestions() throws InterruptedException{
        System.out.println("Number of questions: " + driver.findElements(By.xpath("//*[@class='instances']/div")).size());
        Thread.sleep(3000);
    }

    @After
    public void QuitTest() {
        driver.quit();
    }
}
