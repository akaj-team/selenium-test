package vn.asiantech.page.device;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-phuongdang
 */
public class DeviceTrackingPage extends BasePage<DeviceTrackingPage> {

    private static final int COLUMNS_HEADER_COUNT = 7;
    private static final String DEVICE_ITEM_BORDER_COLOR = "#676A6C";
    private static final String DEVICE_ITEM_BORDER_COLOR_SELECTED = "#D9EDF7";
    private static final String DEVICE_ITEM_TITLE = "LTA0010";
    private static final int MASK = 0xff;

    @FindBy(className = "content")
    @CacheLookup
    private WebElement tvTitleContent;

    @FindBy(id = "btn-this-week")
    private WebElement btnThisWeek;

    @FindBy(id = "btn-submit-device-tracking")
    private WebElement btnSubmit;

    @FindBy(id = "btn-prev-week")
    private WebElement btnPrevious;

    @FindBy(id = "btn-next-week")
    private WebElement btnNext;

    @FindBy(className = "timesheet-body")
    private WebElement viewDeviceBody;

    @FindBy(id = "checkbox-select-all")
    private WebElement cbxSelectAll;

    @FindBy(className = "timesheet-head")
    private WebElement tvDeviceHeader;

    @FindBy(id = "static-dialog-wrapper")
    private WebElement dlgConfirm;

    @FindBy(className = "ui-dialog-title")
    private WebElement tvTitleDialogConfirm;

    @FindBy(className = "ui-dialog-content")
    private WebElement tvMessageDialogConfirm;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel.ng-star-inserted")
    private WebElement btnStayDialogConfim;

    @FindBy(css = ".btn.btn-sm.btn-warning.btn-submit.ng-star-inserted")
    private WebElement btnLeaveDialogConfirm;

    @FindBy(css = ".fa.fa-fw.fa-close")
    private WebElement btnCloseDialogConfirm;

    @FindBy(tagName = "p-dialog")
    private WebElement dlgConfirmSubmit;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel.ng-star-inserted")
    private WebElement btnCancelDialogConfirm;

    @FindBy(css = ".btn.btn-sm.btn-primary.btn-submit.ng-star-inserted")
    private WebElement btnSubmitDialogConfirm;

    @FindBy(css = ".fa.fa-fw.fa-close")
    private WebElement btnCloseDialogConfirmSubmit;

    private WebDriver driver;

    public DeviceTrackingPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public DeviceTrackingPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final Boolean isDisplayTitleContent() {
        waitForElement(driver, tvTitleContent);
        return tvTitleContent.isDisplayed();
    }

    public final Boolean isDisplayContentDevice() {
        waitForElement(driver, viewDeviceBody);
        return viewDeviceBody.isEnabled();
    }

    public final Boolean isDisplayButtonControl() {
        return btnPrevious.isEnabled() && btnNext.isEnabled();
    }

    public final Boolean isButtonThisWeekDisable() {
        waitForElement(driver, btnThisWeek);
        return !btnThisWeek.isEnabled();
    }

    public final void clickButtonNext() {
        waitForElement(driver, btnNext);
        if (!btnNext.isEnabled()) {
            btnNext.click();
        } else {
            btnPrevious.click();
        }
    }

    public final Boolean isButtonThisWeekClickable() {
        waitForElement(driver, btnThisWeek);
        return btnThisWeek.isEnabled() && btnThisWeek.isDisplayed();
    }

    public final void clickButtonPrevious() {
        waitForElement(driver, btnPrevious);
        btnPrevious.click();
    }

    public final Boolean isDefaultCheckboxSelectAll() {
        waitForElement(driver, cbxSelectAll);
        return cbxSelectAll.isSelected();
    }

    public final Boolean isDisplayFullDeviceHeader() {
        waitForElement(driver, tvDeviceHeader);
        if (tvDeviceHeader != null && tvDeviceHeader.isEnabled()) {
            List<WebElement> headerItems = tvDeviceHeader.findElements(By.className("timesheet-cell"));
            for (int i = 1; i < headerItems.size(); i++) {
                if (i == COLUMNS_HEADER_COUNT && headerItems.get(i).isEnabled()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final Boolean isDisplayFullDeviceContent() {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            for (int i = 1; i < bodyItems.size(); i++) {
                if (i == COLUMNS_HEADER_COUNT && bodyItems.get(i).isEnabled()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void moveToItemDevice() {
        Actions action = new Actions(driver);
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            for (int i = 1; i < bodyItems.size(); i++) {
                action.moveToElement(bodyItems.get(i)).build().perform();
            }
        }
    }

    public final Boolean isDisplayBorderItemDevice() {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            String[] rgb = bodyItems.get(0).getCssValue("border-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
            String borderColor = String.format("#%s%s%s", getBrowserHexValue(Integer.parseInt(rgb[0])), getBrowserHexValue(Integer.parseInt(rgb[1])), getBrowserHexValue(Integer.parseInt(rgb[2])));
            return bodyItems.get(0).getText().equals(DEVICE_ITEM_TITLE) && borderColor.equals(DEVICE_ITEM_BORDER_COLOR);
        }
        return true;
    }

    public final Boolean isButtonSubmitDisable() {
        waitForElement(driver, btnSubmit);
        return !btnSubmit.isEnabled();
    }

    public final void clickItemOnListItemDevice() {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> itemDevices = viewDeviceBody.findElements(By.xpath("//div[contains(@class,'create-inspection')][not(contains(@class,'actived'))][not(contains(@class,'submitted'))]"));
            if (itemDevices.size() == 0) {
                if (btnNext.isEnabled()) {
                    btnNext.click();
                } else {
                    btnPrevious.click();
                }
                clickItemOnListItemDevice();
            } else {
                itemDevices.get(0).click();
            }
        }
    }

    public final Boolean isDisplayColorDeviceItem() {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            for (int i = 1; i < bodyItems.size(); i++) {
                List<WebElement> contents = bodyItems.get(i).findElements(By.cssSelector(".create-inspection.selected"));
                if (contents.size() != 0) {
                    String[] rgb = contents.get(0).getCssValue("border-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
                    String borderColor = String.format("#%s%s%s", getBrowserHexValue(Integer.parseInt(rgb[0])), getBrowserHexValue(Integer.parseInt(rgb[1])), getBrowserHexValue(Integer.parseInt(rgb[2])));
                    return borderColor.equals(DEVICE_ITEM_BORDER_COLOR_SELECTED);
                }
            }
        }
        return false;
    }

    public final Boolean isDisplayButtonSubmit() {
        waitForElement(driver, btnSubmit);
        return btnSubmit.isDisplayed();
    }

    public final void clickCheckboxSelectAll() {
        waitForElement(driver, cbxSelectAll);
        if (cbxSelectAll.isEnabled()) {
            cbxSelectAll.click();
        } else {
            if (btnNext.isEnabled()) {
                btnNext.click();
            } else {
                btnPrevious.click();
            }
            clickCheckboxSelectAll();
        }
    }

    public final Boolean isDisplayAllItemSelected() {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            for (int i = 1; i < bodyItems.size(); i++) {
                List<WebElement> contents = bodyItems.get(i).findElements(By.cssSelector(".create-inspection.selected"));
                if (contents.size() != 0) {
                    String[] rgb = contents.get(0).getCssValue("border-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
                    String borderColor = String.format("#%s%s%s", getBrowserHexValue(Integer.parseInt(rgb[0])), getBrowserHexValue(Integer.parseInt(rgb[1])), getBrowserHexValue(Integer.parseInt(rgb[2])));
                    if (i == COLUMNS_HEADER_COUNT && borderColor.equals(DEVICE_ITEM_BORDER_COLOR_SELECTED)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final Boolean isDisplayDialogConfirm() {
        waitForElement(driver, dlgConfirm);
        return dlgConfirm.isDisplayed();
    }

    public final Boolean isDisplayTitleDialogConfirm(final String title) {
        waitForElement(driver, tvTitleDialogConfirm);
        return tvTitleDialogConfirm.getText().equals(title);
    }

    public final Boolean isDisplayMessageDialogConfirm(final String message) {
        waitForElement(driver, tvMessageDialogConfirm);
        return tvMessageDialogConfirm.getText().equals(message);
    }

    public final Boolean isDisplayButtonDialogControl() {
        waitForElement(driver, dlgConfirm);
        return btnStayDialogConfim.isDisplayed() && btnCloseDialogConfirm.isDisplayed();
    }

    public final void clickButtonCloseDialogConfirm() {
        waitForElement(driver, btnCloseDialogConfirm);
        btnCloseDialogConfirm.click();
    }

    public final Boolean isDismissDialogConfirm() {
        waitForElement(driver, dlgConfirm);
        return !dlgConfirm.isDisplayed();
    }

    public final void clickButtonStayDialogConfirm() {
        clickButtonNext();
        waitForElement(driver, btnStayDialogConfim);
        btnStayDialogConfim.click();
    }

    public final Boolean isDismissDialogConfirmSaveState() {
        waitForElement(driver, dlgConfirm);
        return !dlgConfirm.isDisplayed() && isDisplayColorDeviceItem();
    }

    public final void clickButtonLeaveDialogConfirm() {
        clickButtonNext();
        waitForElement(driver, btnLeaveDialogConfirm);
        btnLeaveDialogConfirm.click();
    }

    public final Boolean isDismissDialogConfirmNotSaveState() {
        waitForElement(driver, dlgConfirm);
        return !dlgConfirm.isDisplayed() && !isDisplayColorDeviceItem();
    }

    public final void clickButtonSubmit() {
        waitForElement(driver, btnSubmit);
        btnSubmit.click();
    }

    public final Boolean isDisplayDialogConfirmSubmit() {
        waitForElement(driver, dlgConfirmSubmit);
        return dlgConfirmSubmit.isDisplayed();
    }

    public final Boolean isDisplayTitleDialogConfirmSubmit(final String title) {
        waitForElement(driver, dlgConfirmSubmit);
        return tvTitleDialogConfirm.getText().equals(title);
    }

    public final String getMessageDialogConfirmSubmit() {
        waitForElement(driver, tvMessageDialogConfirm);
        return tvMessageDialogConfirm.getText();
    }

    public final Boolean isDisplayButtonDialogSubmitControl() {
        waitForElement(driver, dlgConfirmSubmit);
        return btnCancelDialogConfirm.isDisplayed() && btnSubmitDialogConfirm.isDisplayed();
    }

    public final void clickButtonCloseDialogConfirmSubmit() {
        waitForElement(driver, btnCloseDialogConfirmSubmit);
        btnCloseDialogConfirmSubmit.click();
    }

    public final Boolean isDismissDialogConfirmSubmit() {
        waitForElement(driver, dlgConfirmSubmit);
        return !dlgConfirmSubmit.isDisplayed();
    }

    public final void clickButtonCancelDialogConfirmSubmit() {
        clickButtonSubmit();
        waitForElement(driver, btnCancelDialogConfirm);
        btnCancelDialogConfirm.click();
    }

    public final Boolean isDismissDialogConfirmSubmitSaveState() {
        waitForElement(driver, dlgConfirmSubmit);
        return !dlgConfirmSubmit.isDisplayed() && isDisplayAllItemSelected();
    }

    private String getBrowserHexValue(final int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & MASK));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }
}
