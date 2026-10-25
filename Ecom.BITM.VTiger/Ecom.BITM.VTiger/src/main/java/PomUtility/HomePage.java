package PomUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtility.webDriverUtility;

public class HomePage {
	WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// More menu
	@FindBy(xpath = "//*[normalize-space()='More']")
	private WebElement moreOption;

	// Project Milestone menu option
	@FindBy(xpath = "//a[@name='Project Milestones']")
	private WebElement projectMilestoneOption;

	// Projects menu option
	@FindBy(xpath = "//a[@name='Projects']")
	private WebElement projectOption;

	@FindBy(xpath = "//a[normalize-space()='Service Contracts']")
	private WebElement serviceContractsOption;
	
	@FindBy(xpath="//a[text()='Organizations']")
	private WebElement Organizations;
	
	@FindBy(xpath="//a[text()='Email']")
	private WebElement Email;
	
	@FindBy(xpath="//a[text()='Products']")
	private WebElement Products;
	
	@FindBy(xpath="//a[text()='Contacts']")
	private WebElement Contacts;
	
	@FindBy(xpath = "//img[contains(@src,'user.PNG')]")
	private WebElement userIcon;

	@FindBy(linkText = "Sign Out")
	private WebElement logout;

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogout() {
		return logout;
	}

	public void navigateToProjectMilestone() {

		wait.until(ExpectedConditions.elementToBeClickable(moreOption)).click();

		wait.until(ExpectedConditions.elementToBeClickable(projectMilestoneOption)).click();
	}

	public void navigateToProject() {
		wait.until(ExpectedConditions.elementToBeClickable(moreOption)).click();
		wait.until(ExpectedConditions.elementToBeClickable(projectOption)).click();
	}
	
	public void clickOrganization() {

		wait.until(ExpectedConditions.elementToBeClickable(Organizations)).click();
	}
	
	public void clickContacts() {

		wait.until(ExpectedConditions.elementToBeClickable(Contacts)).click();
	}
	
	public void clickproducts() {

		wait.until(ExpectedConditions.elementToBeClickable(Products)).click();
	}
	
	public void clickEmail() {

		wait.until(ExpectedConditions.elementToBeClickable(Email)).click();
	}
	
	

	public void navigateToServiceContracts() {
		wait.until(ExpectedConditions.elementToBeClickable(moreOption)).click();

		wait.until(ExpectedConditions.elementToBeClickable(serviceContractsOption)).click();
	}

	public void userLogout(WebDriver driver) {
		webDriverUtility wu = new webDriverUtility();
		wu.moveToAnElement(driver, userIcon);
		logout.click();
	}

}
