package pages;

import utils.bo.MailBO;
import utils.data.WaiterType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.elementdecorator.CustomWebDriver;
import utils.waiterfactory.WaiterFactory;

/**
 * Created by Katsiaryna_Skarzhyns on 1/3/2018.
 */
public class MailCreationPage extends BaseMailPage {

	private static final String LETTER_IS_CREATED_INDICATOR = "//span[text() = 'Сохранено']";
	WaiterFactory waiterFactory = new WaiterFactory();

	@FindBy(xpath = "//*[@name = 'subjectbox']")
	private WebElement subjectField;
	@FindBy(xpath = "//div[@role = 'textbox']")
	private WebElement bodyField;
	@FindBy(xpath = "//textarea[@name = 'to']")
	private WebElement addressField;
	@FindBy(xpath = "//div[contains(text(), 'Отправить')]")
	private WebElement sendBtn;
	@FindBy(xpath = "//div[@command = 'Files']")
	private WebElement attachFile;

	MailCreationPage(CustomWebDriver driver) {
		super(driver);
	}

	public void createMail(MailBO mailBO) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name = 'to']")));
		addressField.sendKeys(mailBO.getAddress());
		subjectField.sendKeys(mailBO.getSubject());
		bodyField.sendKeys(mailBO.getDescription());
		waiterFactory.wait(WaiterType.APPEARED).waitForPageIsLoaded(driver, By.xpath(LETTER_IS_CREATED_INDICATOR));
	}

	void send() {
		sendBtn.click();
	}
}