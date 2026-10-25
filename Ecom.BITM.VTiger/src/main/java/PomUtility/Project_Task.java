package PomUtility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Project_Task {

    WebDriver driver;
    WebDriverWait wait;

    public Project_Task(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[normalize-space()='More']")
    private WebElement moreOption;

    @FindBy(xpath = "//a[normalize-space()='Project Tasks']")
    private WebElement projectTasksOption;

    @FindBy(xpath = "//img[contains(@title,'Create') and contains(@title,'Project Task')]")
    private WebElement createTaskButton;

    @FindBy(name = "projecttaskname")
    private WebElement taskNameField;
    
    @FindBy(xpath = "//img[@title='Select']")
    private WebElement relatedToLookup;
    
    @FindBy(xpath = "//input[@value='  Delete  ' or @value='Delete']")
    private WebElement deleteButton;

//    @FindBy(name = "parent_type")
//    private WebElement relatedToType;

    @FindBy(xpath = "(//input[@value='  Save  '])[2]")
    private WebElement saveButton;
    
    @FindBy(name = "search_text")
    private WebElement searchField;

    @FindBy(name = "submit")
    private WebElement searchButton;

    public void navigateToProjectTasks() {
        wait.until(
            ExpectedConditions.elementToBeClickable(moreOption)
        ).click();

        wait.until(
            ExpectedConditions.elementToBeClickable(projectTasksOption)
        ).click();
    }

    public void clickCreateTask() {
        wait.until(
            ExpectedConditions.elementToBeClickable(createTaskButton)
        ).click();
    }

    public boolean isCreateTaskFormDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOf(taskNameField)
        ).isDisplayed();
    }

    public void enterTaskName(String taskName) {
        wait.until(
            ExpectedConditions.visibilityOf(taskNameField)
        ).sendKeys(taskName);
    }

    public void clickSave() {
        wait.until(
            ExpectedConditions.elementToBeClickable(saveButton)
        ).click();
    }
    
    public void searchProjectTask(String taskName) {
        wait.until(
            ExpectedConditions.visibilityOf(searchField)
        ).clear();

        searchField.sendKeys(taskName);

        wait.until(
            ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }
    
    public void selectTask(String taskName) {
        By taskLink = By.xpath("(//a[@title='Project Tasks'])[3]");

        wait.until(
            ExpectedConditions.elementToBeClickable(taskLink)
        ).click();
    }

    public void clickDelete() {
        wait.until(
            ExpectedConditions.elementToBeClickable(deleteButton)
        ).click();
    }

    
    public boolean isTaskDisplayed(String taskName) {
        By taskElement = By.xpath(
            "(//a[@title='Project Tasks'])[3]");
        

        for (WebElement element : driver.findElements(taskElement)) {
            if (element.isDisplayed()) {
                return true;
            }
        }

        return false;
    }
    
  public void clearTaskName() {
    wait.until(
            ExpectedConditions.visibilityOf(taskNameField)
        ).clear();
    }
    public String clickRelatedToLookup() {
        String parentWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();

        wait.until(
            ExpectedConditions.elementToBeClickable(relatedToLookup)
        ).click();

        wait.until(
            d -> d.getWindowHandles().size() > oldWindows.size()
        );

        for (String window : driver.getWindowHandles()) {
            if (!oldWindows.contains(window)) {
                driver.switchTo().window(window);
                break;
            }
        }

        return parentWindow;
    }

    public void selectRelatedRecord(String recordName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By recordLink = By.xpath(
            "//a[text()='QA_Project_Module4']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.tagName("body")
        ));

        if (driver.findElements(recordLink).isEmpty()) {
            throw new AssertionError(
                "Project not found in Related To lookup: " + recordName
            );
        }

        wait.until(ExpectedConditions.elementToBeClickable(recordLink)).click();
    }


public void returnToTaskForm(String parentWindow) {
    if (!driver.getWindowHandles().contains(parentWindow)) {
        throw new AssertionError("Main Project Task window is already closed");
    }

    driver.switchTo().window(parentWindow);

    wait.until(ExpectedConditions.visibilityOf(taskNameField));
}

public void checkbox() throws InterruptedException {
	driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();
}
    private String xpathLiteral(String value) {
        if (!value.contains("'")) {
            return "'" + value + "'";
        }

        if (!value.contains("\"")) {
            return "\"" + value + "\"";
        }

        return "concat('" + value.replace("'", "',\"'\",'") + "')";
    }
}

