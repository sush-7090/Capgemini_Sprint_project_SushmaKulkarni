package PomUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FAQ {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[normalize-space()='More']")
    private WebElement moreOption;

    @FindBy(xpath = "//a[normalize-space()='FAQ']")
    private WebElement faqOption;

    @FindBy(xpath = "(//a[@title='FAQ'])[2]")
    private WebElement faqPageTitle;
    
    @FindBy(xpath = "//img[contains(@title,'Create') and contains(@title,'FAQ')]")
    private WebElement createFAQButton;

    @FindBy(name = "question")
    private WebElement questionField;

    @FindBy(xpath="//textarea[@name='faq_answer']")
    private WebElement answerField;

    @FindBy(name = "status")
    private WebElement statusDropdown;

    @FindBy(xpath = "(//input[contains(@value,'Save')])[1]")
    private WebElement saveButton;
    
    @FindBy(name = "search_text")
    private WebElement searchField;
    
    @FindBy(name = "search_field")
    private WebElement searchByDropdown;

    @FindBy(name = "submit")
    private WebElement searchButton;


    public FAQ(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToFAQ() {
        wait.until(ExpectedConditions.elementToBeClickable(moreOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(faqOption)).click();
    }

    public boolean isFAQPageDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOf(faqPageTitle)
        ).isDisplayed();
    }
        public void clickCreateFAQ() {
            wait.until(ExpectedConditions.elementToBeClickable(createFAQButton)).click();
        }

        public void enterQuestion(String question) {
            wait.until(ExpectedConditions.visibilityOf(questionField)).sendKeys(question);
        }

        public void enterAnswer(String answer) {
            wait.until(ExpectedConditions.visibilityOf(answerField)).sendKeys(answer);
        }

        public void selectStatus(String status) {
            wait.until(ExpectedConditions.visibilityOf(statusDropdown));
            new Select(statusDropdown).selectByVisibleText(status);
        }

        public void clickSave() {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        }
        public void searchFAQ(String question) {
            wait.until(ExpectedConditions.visibilityOf(searchField)).clear();
            searchField.sendKeys(question);

            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        }
        
        public void searchFAQByQuestion(String question) {
            wait.until(ExpectedConditions.visibilityOf(searchField)).clear();
            searchField.sendKeys(question);

            new Select(searchByDropdown).selectByVisibleText("Question");

            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        }

        public boolean isFAQDisplayed(String question) {
            By faqRecord = By.xpath(
                "//*[normalize-space(.)=" + xpathLiteral(question) + "]"
            );

            return !driver.findElements(faqRecord).isEmpty();
        }
        

	 // Safely construct an XPath string literal
    private String xpathLiteral(String value) {

        if (!value.contains("'")) {
            return "'" + value + "'";
        }

        if (!value.contains("\"")) {
            return "\"" + value + "\"";
        }

        String[] parts = value.split("'", -1);
        StringBuilder result = new StringBuilder("concat(");

        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                result.append(", \"'\", ");
            }

            result.append("'").append(parts[i]).append("'");
        }

        result.append(")");
        return result.toString();
    }
    }


