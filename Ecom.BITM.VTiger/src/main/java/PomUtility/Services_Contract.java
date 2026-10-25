package PomUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Services_Contract {

    WebDriver driver;
    WebDriverWait wait;

    public Services_Contract(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[normalize-space()='More']")
    private WebElement moreOption;

    @FindBy(xpath = "//a[normalize-space()='Service Contracts']")
    private WebElement serviceContractsOption;

    @FindBy(xpath = "//img[@title='Create Service Contract...']")
    private WebElement createContractButton;

    @FindBy(name = "subject")
    private WebElement subjectField;
    
    @FindBy(xpath = "//input[@value='  Cancel  ' or @value='Cancel']")
    private WebElement cancelButton;
    
    @FindBy(name = "search_text")
    private WebElement searchField;

    @FindBy(name = "submit")
    private WebElement searchButton;

    
    public void clearSubject() {
        wait.until(
            ExpectedConditions.visibilityOf(subjectField)
        ).clear();
    }

    @FindBy(xpath = "//input[@value='  Save  ' or @value='Save']")
    private WebElement saveButton;

    public void navigateToServiceContracts() {
        wait.until(
            ExpectedConditions.elementToBeClickable(moreOption)
        ).click();

        wait.until(
            ExpectedConditions.elementToBeClickable(serviceContractsOption)
        ).click();
    }

    public void clickCreateContract() {
        wait.until(
            ExpectedConditions.elementToBeClickable(createContractButton)
        ).click();
    }

    public boolean isCreateContractFormDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOf(subjectField)
        ).isDisplayed();
    }

    public void enterSubject(String subject) {
        wait.until(
            ExpectedConditions.visibilityOf(subjectField)
        ).sendKeys(subject);
    }

    public void clickSave() {
        wait.until(
            ExpectedConditions.elementToBeClickable(saveButton)
        ).click();
    }
    
    public void clickCancel() {
        wait.until(
            ExpectedConditions.elementToBeClickable(cancelButton)
        ).click();
    }
    
    public boolean isContractFormClosed() {
        return driver.findElements(
            By.name("subject")
        ).isEmpty();
    }

    public String getSavedSubject() {
        By savedSubject = By.xpath(
            "//*[contains(@id,'dtlview_Subject')]"
        );

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(savedSubject)
        ).getText().trim();
    }
    
    public void searchContract(String subject) {
        wait.until(
            ExpectedConditions.visibilityOf(searchField)
        ).clear();

        searchField.sendKeys(subject);

        wait.until(
            ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }

    public boolean isContractDisplayed(String subject) {
        By contractLink = By.xpath(
            "//a[normalize-space(.)=" + xpathLiteral(subject) + "]"
        );

        for (WebElement element : driver.findElements(contractLink)) {
            if (element.isDisplayed()) {
                return true;
            }
        }

        return false;
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


