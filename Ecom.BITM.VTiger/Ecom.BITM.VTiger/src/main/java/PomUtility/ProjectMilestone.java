package PomUtility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ProjectMilestone {

    WebDriver driver;
    WebDriverWait wait;

    public ProjectMilestone(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Create Project Milestone button
    @FindBy(xpath = "//img[contains(@title,'Create Project Milestone')]"
            + " | //img[contains(@alt,'Create Project Milestone')]")
    private WebElement createMilestoneButton;

    // Milestone Name
    @FindBy(name = "projectmilestonename")
    private WebElement milestoneName;

    // Related To lookup icon
    @FindBy(xpath = "//img[@title='Select' or @alt='Select']")
    private WebElement relatedTo;

    // Milestone Date
    @FindBy(name = "projectmilestonedate")
    private WebElement milestoneDate;

    // Type dropdown
    @FindBy(name = "projectmilestonetype")
    private WebElement milestoneType;

    // Description
    @FindBy(name = "description")
    private WebElement description;

    // Assigned User dropdown
    @FindBy(name = "assigned_user_id")
    private WebElement assignedUser;

    // Save button
    @FindBy(xpath = "(//input[@value='  Save  '])[2]")
    private WebElement saveButton;

    // Search text field locator
    private By searchButton = By.xpath("//input[@class='txtBox']");

    // Search Now button locator
    private By searchNow = By.xpath(
            "(//input[@class='crmbutton small create'])[1]");

    // Click Create Milestone
    public void clickCreateMilestone() {
        wait.until(ExpectedConditions.elementToBeClickable(
                createMilestoneButton)).click();
    }

    // Enter milestone name
    public void enterMilestoneName(String name) {
        wait.until(ExpectedConditions.visibilityOf(
                milestoneName));
        milestoneName.clear();
        milestoneName.sendKeys(name);
    }

    // Select related project using multiple-window handling
    public void selectRelatedProject(String projectName) {

        String parentWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();

        wait.until(ExpectedConditions.elementToBeClickable(
                relatedTo)).click();

        // Wait for the lookup popup
        wait.until(d ->
                d.getWindowHandles().size() > oldWindows.size());

        // Switch to the newly opened popup
        for (String window : driver.getWindowHandles()) {
            if (!oldWindows.contains(window)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Select project using its visible name
        By projectLink = By.xpath(
                "//a[contains(@onclick,'projectid') and normalize-space(.)="
                + xpathLiteral(projectName) + "]");

        wait.until(ExpectedConditions.elementToBeClickable(
                projectLink)).click();

        // Wait until popup closes
        wait.until(d ->
                d.getWindowHandles().size() == oldWindows.size());

        // Return to milestone form
        driver.switchTo().window(parentWindow);

        wait.until(ExpectedConditions.visibilityOf(milestoneName));
    }

    // Select assigned user
    public void selectAssignedUser(String userName) {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOf(
                        assignedUser)));

        select.selectByVisibleText(userName);
    }

    // Enter milestone date
    public void enterMilestoneDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(
                milestoneDate));
        milestoneDate.clear();
        milestoneDate.sendKeys(date);
    }

    // Select milestone type
    public void selectMilestoneType(String type) {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOf(
                        milestoneType)));

        select.selectByVisibleText(type);
    }

    // Enter description
    public void enterDescription(String text) {
        wait.until(ExpectedConditions.visibilityOf(
                description));
        description.clear();
        description.sendKeys(text);
    }

    // Click Save and handle validation alert
    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(
                saveButton)).click();

        try {
            Alert alert = new WebDriverWait(
                    driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.alertIsPresent());

            String alertMessage = alert.getText();
            alert.accept();

            System.out.println("Validation alert: " + alertMessage);

        } catch (TimeoutException e) {
            // No alert appeared; continue normally.
        }
    }

    // Search for an existing milestone
    public void searchMilestone(String name) {

        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        searchButton));

        searchField.clear();
        searchField.sendKeys(name);

        wait.until(ExpectedConditions.elementToBeClickable(
                searchNow)).click();
    }

    // Verify that the milestone is present
    public boolean isMilestonePresent(String name) {

        By milestoneLink = By.xpath("(//a[@title='Project Milestones'])[3]");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    milestoneLink));
            return true;

        } catch (TimeoutException e) {
            return false;
        }
    }
    



public void clickCreatedMilestone(String name) {

    By milestoneLink = By.xpath("(//a[@title='Project Milestones'])[3]");

    WebElement link = wait.until(
        ExpectedConditions.visibilityOfElementLocated(milestoneLink)
    );

    link.click();

    Reporter.log("Clicked on Project Milestone: " + name, true);
}




public boolean isMilestoneDetailsPageOpened(String name) {

    try {
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("projectmilestone"),
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(.,'Milestone Name')]")
            ),
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space(text())="
                    + xpathLiteral(name) + "]")
            )
        ));

        Reporter.log("Project Milestone details page opened successfully", true);
        return true;

    } catch (TimeoutException e) {
        Reporter.log("Project Milestone details page verification failed", true);
        return false;
    }
}



    // Return milestone name entered in the form
    public String getMilestoneName() {
        return milestoneName.getAttribute("value");
    }

    // Safely build XPath for project names containing quotes
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
