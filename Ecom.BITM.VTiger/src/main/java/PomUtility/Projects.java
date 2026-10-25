package PomUtility;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Projects {

    WebDriver driver;
    WebDriverWait wait;

    public Projects(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Create Project button
    @FindBy(xpath = "//img[@title='Create Projects...']")
    private WebElement createProjectButton;

    // Project Name field
    @FindBy(name = "projectname")
    private WebElement projectName;

    // Related To lookup button
    @FindBy(xpath = "//img[@title='Select']")
    private WebElement relatedTo;

    // Project Status dropdown
    @FindBy(name = "projectstatus")
    private WebElement projectStatus;

    // Project Type dropdown
    @FindBy(name = "projecttype")
    private WebElement projectType;

    // Description field
    @FindBy(name = "description")
    private WebElement description;

    // Save button
    @FindBy(xpath = "//input[@value='  Save  ' or @value='Save']")
    private List<WebElement> saveButtons;
    
    @FindBy(xpath="//input[@id='project_no']")
    private WebElement projectNumber;
    
    @FindBy(xpath = "//input[@value='  Cancel  ' or @value='Cancel']")
    private WebElement cancelButton;
    
    @FindBy(name = "search_text")
    private WebElement searchText;
    
    @FindBy(name = "submit")
    private WebElement searchButton;
    
    @FindBy(xpath = "//input[@name='Edit']")
    private WebElement editButton;

    public String getProjectNumber() {
        return wait.until(
            ExpectedConditions.visibilityOf(projectNumber)
        ).getAttribute("value");
    }

    // Project Name field used to verify form
    public void clickCreateProject() {
        wait.until(
            ExpectedConditions.elementToBeClickable(createProjectButton)
        ).click();
    }

    public void enterProjectName(String name) {
        wait.until(
            ExpectedConditions.visibilityOf(projectName)
        ).clear();

        projectName.sendKeys(name);
    }

    public boolean isCreateProjectFormDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOf(projectName)
            ).isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    // Open Organization lookup popup and switch to it
    public String clickRelatedToLookup() {

        String parentWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();

        wait.until(
            ExpectedConditions.elementToBeClickable(relatedTo)
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

    // Select an Organization from the lookup popup
    public void selectOrganization(String organizationName) {

        By organizationLink = By.xpath(
            "//a[normalize-space(.)="
            + xpathLiteral(organizationName) + "]"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(organizationLink)
        ).click();
    }

    // Return to the Create Project form after selecting Organization
    public void returnToProjectForm(String parentWindow) {

        wait.until(
            d -> d.getWindowHandles().size() == 1
        );

        driver.switchTo().window(parentWindow);

        wait.until(
            ExpectedConditions.visibilityOf(projectName)
        );
    }

    public String getRelatedToValue() {
        // Locate the Related To input using its current page structure
        WebElement relatedToField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='parent_name']")
            )
        );

        return relatedToField.getAttribute("value");
    }

    public void selectProjectStatus(String status) {
        new Select(projectStatus).selectByVisibleText(status);
    }

    public void selectProjectType(String type) {
        new Select(projectType).selectByVisibleText(type);
    }

    public void enterDescription(String text) {
        description.sendKeys(text);
    }
    

public boolean isProjectNameValidationDisplayed() {
    try {
        org.openqa.selenium.Alert alert =
            new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.alertIsPresent());

        String message = alert.getText();

        alert.accept();

        return message != null && !message.trim().isEmpty();

    } catch (org.openqa.selenium.TimeoutException e) {
        return false;
    }
}

public void searchProject(String name) throws InterruptedException {
    wait.until(ExpectedConditions.visibilityOf(searchText));
    searchText.clear();
    searchText.sendKeys(name);
    searchButton.click();
    Thread.sleep(2000);
}

public boolean isProjectDisplayed(String name) {
    By projectLink = By.xpath("(//a[@title='Projects'])[1]");
    return wait.until(ExpectedConditions.visibilityOfElementLocated(projectLink)).isDisplayed();
}

    public void clickSave() {
        WebElement saveButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath(
                    "(//input[@value='  Save  ' or @value='Save'])[1]"
                )
            )
        );

        saveButton.click();
    }
    public void clickCancel() {
        wait.until(
            ExpectedConditions.elementToBeClickable(cancelButton)
        ).click();
    }
    
    public boolean isProjectFormClosed() {
        try {
            return driver.findElements(By.name("projectname")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProjectName() {
        return projectName.getAttribute("value");
    }
    
    public void clickEdit() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }
    public void clickProjectName(String name) {
    	    By projectLink = By.xpath(
    	        "(//a[@title='Projects'])[1]"
    	    );
    	   // driver.findElement(By.xpath("(//a[@title='Projects'])[1]")).click();
            
        wait.until(
            ExpectedConditions.elementToBeClickable(projectLink)
        ).click();
    }
    
    public void updateProjectName(String name) {
        wait.until(ExpectedConditions.visibilityOf(projectName));
        projectName.clear();
        projectName.sendKeys(name);
    }
    public String getDisplayedProjectName() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@id='dtlview_Project Name']")
            )
        ).getText();
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
