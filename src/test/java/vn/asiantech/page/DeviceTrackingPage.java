package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author at-phuongdang
 */
public class DeviceTrackingPage extends BasePage<DeviceTrackingPage> {

    private static final int COLUMNS_HEADER_COUNT = 7;
    private static final String DEVICE_ITEM_BORDER_COLOR = "#676A6C";
    private static final String DEVICE_ITEM_BORDER_COLOR_SELECTED = "#D9EDF7";
    private static final String DEVICE_ITEM_TITLE = "LTA0010";
    private static final int ITEM_DEVICE_TRACKING_MENU = 8;
    private static final int MASK = 0xff;

    @FindBy(id = "side-menu")
    private
    WebElement menuNavigationMain;

    @FindBy(className = "content")
    private
    WebElement tvTitleContent;

    @FindBy(id = "btn-this-week")
    private
    WebElement btnThisWeek;

    @FindBy(id = "btn-submit-device-tracking")
    private
    WebElement btnSubmit;

    @FindBy(id = "btn-prev-week")
    private
    WebElement btnPrevious;

    @FindBy(id = "btn-next-week")
    private
    WebElement btnNext;

    @FindBy(className = "timesheet-body")
    private
    WebElement viewDeviceBody;

    @FindBy(id = "checkbox-select-all")
    private
    WebElement cbxSelectAll;

    @FindBy(className = "timesheet-head")
    private
    WebElement tvDeviceHeader;

    @FindBy(id = "static-dialog-wrapper")
    private
    WebElement dlgConfirm;

    @FindBy(className = "ui-dialog-title")
    private
    WebElement tvTitleDialogConfirm;

    @FindBy(className = "ui-dialog-content")
    private
    WebElement tvMessageDialogConfirm;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel.ng-star-inserted")
    private
    WebElement btnStayDialogConfim;

    @FindBy(css = ".btn.btn-sm.btn-warning.btn-submit.ng-star-inserted")
    private
    WebElement btnLeaveDialogConfirm;

    @FindBy(css = ".fa.fa-fw.fa-close")
    private
    WebElement btnCloseDialogConfirm;

    @FindBy(tagName = "p-dialog")
    private
    WebElement dlgConfirmSubmit;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel.ng-star-inserted")
    private
    WebElement btnCancelDialogConfirm;

    @FindBy(css = ".btn.btn-sm.btn-primary.btn-submit.ng-star-inserted")
    private
    WebElement btnSubmitDialogConfirm;

    @FindBy(css = ".fa.fa-fw.fa-close")
    private
    WebElement btnCloseDialogConfirmSubmit;

    @FindBy(css = ".app-alert.ng-star-inserted")
    private
    WebElement tvMessageConfirm;

    @Override
    public DeviceTrackingPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final void clickItemDevice() {
        WebElement itemHolidaySetting = getItemMenuInPosition();
        itemHolidaySetting.click();
    }

    public final void clickMenuDeviceTracking() {
        WebElement itemHolidaySetting = getItemMenuInPosition();
        WebElement device = itemHolidaySetting.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        device.click();
    }

    public final String getTitleContent(final WebDriver driver) {
        waitForElement(driver, tvTitleContent);
        return tvTitleContent.getText();
    }

    public final boolean checkDeviceMenuDropDown() {
        WebElement itemDevice = getItemMenuInPosition();
        return itemDevice.findElement(By.tagName("ul")).getRect().height == 0;
    }

    private WebElement getItemMenuInPosition() {
        List<WebElement> itemMenus = new ArrayList<>();
        int countChildItem;
        List<WebElement> items = menuNavigationMain.findElements(By.tagName("li"));
        for (int i = 0; i < items.size(); i = i + countChildItem + 1) {
            countChildItem = 0;
            itemMenus.add(items.get(i));
            if (items.get(i).findElements(By.tagName("li")).size() > 0) {
                countChildItem = items.get(i).findElements(By.tagName("li")).size();
            }
        }
        return itemMenus.get(ITEM_DEVICE_TRACKING_MENU);
    }

    public final Boolean isDisplayContentDevice(final WebDriver driver) {
        waitForElement(driver, viewDeviceBody);
        return viewDeviceBody.isEnabled();
    }

    public final Boolean isDisplayButtonControl() {
        return btnPrevious.isEnabled() && btnNext.isEnabled();
    }

    public final Boolean isButtonThisWeekDisable(final WebDriver driver) {
        waitForElement(driver, btnThisWeek);
        return !btnThisWeek.isEnabled();
    }

    public final void clickButtonNext(final WebDriver driver) {
        waitForElement(driver, btnNext);
        btnNext.click();
    }

    public final Boolean isButtonThisWeekClickable(final WebDriver driver) {
        waitForElement(driver, btnThisWeek);
        return btnThisWeek.isEnabled() && btnThisWeek.isDisplayed();
    }

    public final void clickButtonPrevious(final WebDriver driver) {
        waitForElement(driver, btnPrevious);
        btnPrevious.click();
    }

    public final Boolean isDefaultCheckboxSelectAll(final WebDriver driver) {
        waitForElement(driver, cbxSelectAll);
        return cbxSelectAll.isSelected();
    }

    public final Boolean isDisplayFullDeviceHeader(final WebDriver driver) {
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

    public final Boolean isDisplayFullDeviceContent(final WebDriver driver) {
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

    public final void moveToItemDevice(final WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            for (int i = 1; i < bodyItems.size(); i++) {
                action.moveToElement(bodyItems.get(i)).build().perform();
            }
        }
    }

    public final Boolean isDisplayBorderItemDevice(final WebDriver driver) {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            String rgb[] = bodyItems.get(0).getCssValue("border-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
            String borderColor = String.format("#%s%s%s", getBrowserHexValue(Integer.parseInt(rgb[0])), getBrowserHexValue(Integer.parseInt(rgb[1])), getBrowserHexValue(Integer.parseInt(rgb[2])));
            return bodyItems.get(0).getText().equals(DEVICE_ITEM_TITLE) && borderColor.equals(DEVICE_ITEM_BORDER_COLOR);
        }
        return true;
    }

    private String getBrowserHexValue(final int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & MASK));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }

    public final Boolean isButtonSubmitDisable(final WebDriver driver) {
        waitForElement(driver, btnSubmit);
        return !btnSubmit.isEnabled();
    }

    public final void clickItemOnListItemDevice(final WebDriver driver) {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            WebElement itemDevice = viewDeviceBody.findElements(By.className("timesheet-cell")).get(1);
            if (itemDevice != null && itemDevice.isEnabled()) {
                itemDevice.click();
            }
        }
    }

    public final Boolean isDisplayColorDeviceItem(final WebDriver driver) {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            WebElement itemDevice = viewDeviceBody.findElements(By.className("timesheet-cell")).get(1);
            WebElement content = itemDevice.findElement(By.cssSelector(".create-inspection.selected"));
            if (content != null && content.isEnabled()) {
                String rgb[] = content.getCssValue("border-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
                String borderColor = String.format("#%s%s%s", getBrowserHexValue(Integer.parseInt(rgb[0])), getBrowserHexValue(Integer.parseInt(rgb[1])), getBrowserHexValue(Integer.parseInt(rgb[2])));
                return borderColor.equals(DEVICE_ITEM_BORDER_COLOR_SELECTED);
            }
        }
        return false;
    }

    public final Boolean isDisplayButtonSubmit(final WebDriver driver) {
        waitForElement(driver, btnSubmit);
        return btnSubmit.isDisplayed();
    }

    public final void clickCheckboxSelectAll(final WebDriver driver) {
        waitForElement(driver, cbxSelectAll);
        cbxSelectAll.click();
    }

    public final Boolean isDisplayAllItemSelected(final WebDriver driver) {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            for (int i = 1; i < bodyItems.size(); i++) {
                WebElement content = bodyItems.get(i).findElement(By.cssSelector(".create-inspection.selected"));
                String rgb[] = content.getCssValue("border-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
                String borderColor = String.format("#%s%s%s", getBrowserHexValue(Integer.parseInt(rgb[0])), getBrowserHexValue(Integer.parseInt(rgb[1])), getBrowserHexValue(Integer.parseInt(rgb[2])));
                if (i == COLUMNS_HEADER_COUNT && borderColor.equals(DEVICE_ITEM_BORDER_COLOR_SELECTED)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean isDisplayAllItemStateSubmit(final WebDriver driver) {
        waitForElement(driver, viewDeviceBody);
        if (viewDeviceBody != null && viewDeviceBody.isEnabled()) {
            List<WebElement> bodyItems = viewDeviceBody.findElements(By.className("timesheet-cell"));
            for (int i = 1; i < bodyItems.size(); i++) {
                WebElement content = bodyItems.get(i).findElement(By.cssSelector(".create-inspection.selected"));
                String rgb[] = content.getCssValue("border-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
                String borderColor = String.format("#%s%s%s", getBrowserHexValue(Integer.parseInt(rgb[0])), getBrowserHexValue(Integer.parseInt(rgb[1])), getBrowserHexValue(Integer.parseInt(rgb[2])));
                if (i == COLUMNS_HEADER_COUNT && borderColor.equals(DEVICE_ITEM_BORDER_COLOR)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final Boolean isDisplayDialogConfirm(final WebDriver driver) {
        waitForElement(driver, dlgConfirm);
        return dlgConfirm.isDisplayed();
    }

    public final Boolean isDisplayTitleDialogConfirm(final WebDriver driver, final String title) {
        waitForElement(driver, tvTitleDialogConfirm);
        return tvTitleDialogConfirm.getText().equals(title);
    }

    public final Boolean isDisplayMessageDialogConfirm(final WebDriver driver, final String message) {
        waitForElement(driver, tvMessageDialogConfirm);
        return tvMessageDialogConfirm.getText().equals(message);
    }

    public final Boolean isDisplayButtonDialogControl(final WebDriver driver) {
        waitForElement(driver, dlgConfirm);
        return btnStayDialogConfim.isDisplayed() && btnCloseDialogConfirm.isDisplayed();
    }

    public final void clickButtonCloseDialogConfirm(final WebDriver driver) {
        waitForElement(driver, btnCloseDialogConfirm);
        btnCloseDialogConfirm.click();
    }

    public final Boolean isDismissDialogConfirm(final WebDriver driver) {
        waitForElement(driver, dlgConfirm);
        return !dlgConfirm.isDisplayed();
    }

    public final void clickButtonStayDialogConfirm(final WebDriver driver) {
        clickButtonNext(driver);
        waitForElement(driver, btnStayDialogConfim);
        btnStayDialogConfim.click();
    }

    public final Boolean isDismissDialogConfirmSaveState(final WebDriver driver) {
        waitForElement(driver, dlgConfirm);
        return !dlgConfirm.isDisplayed() && isDisplayColorDeviceItem(driver);
    }

    public final void clickButtonLeaveDialogConfirm(final WebDriver driver) {
        clickButtonNext(driver);
        waitForElement(driver, btnLeaveDialogConfirm);
        btnLeaveDialogConfirm.click();
    }

    public final Boolean isDismissDialogConfirmNotSaveState(final WebDriver driver) {
        waitForElement(driver, dlgConfirm);
        return !dlgConfirm.isDisplayed() && !isDisplayColorDeviceItem(driver);
    }

    public final void clickButtonSubmit(final WebDriver driver) {
        waitForElement(driver, btnSubmit);
        btnSubmit.click();
    }

    public final Boolean isDisplayDialogConfirmSubmit(final WebDriver driver) {
        waitForElement(driver, dlgConfirmSubmit);
        return dlgConfirmSubmit.isDisplayed();
    }

    public final Boolean isDisplayTitleDialogConfirmSubmit(final WebDriver driver, final String title) {
        waitForElement(driver, dlgConfirmSubmit);
        return tvTitleDialogConfirm.getText().equals(title);
    }

    public final String getMessageDialogConfirmSubmit(final WebDriver driver) {
        waitForElement(driver, tvMessageDialogConfirm);
        return tvMessageDialogConfirm.getText();
    }

    public final Boolean isDisplayButtonDialogSubmitControl(final WebDriver driver) {
        waitForElement(driver, dlgConfirmSubmit);
        return btnCancelDialogConfirm.isDisplayed() && btnSubmitDialogConfirm.isDisplayed();
    }

    public final void clickButtonCloseDialogConfirmSubmit(final WebDriver driver) {
        waitForElement(driver, btnCloseDialogConfirmSubmit);
        btnCloseDialogConfirmSubmit.click();
    }

    public final Boolean isDismissDialogConfirmSubmit(final WebDriver driver) {
        waitForElement(driver, dlgConfirmSubmit);
        return !dlgConfirmSubmit.isDisplayed();
    }

    public final void clickButtonCancelDialogConfirmSubmit(final WebDriver driver) {
        clickButtonSubmit(driver);
        waitForElement(driver, btnCancelDialogConfirm);
        btnCancelDialogConfirm.click();
    }

    public final Boolean isDismissDialogConfirmSubmitSaveState(final WebDriver driver) {
        waitForElement(driver, dlgConfirmSubmit);
        return !dlgConfirmSubmit.isDisplayed() && isDisplayAllItemSelected(driver);
    }

    public final void clickButtonSubmitDialogConfirmSubmit(final WebDriver driver) {
        clickButtonSubmit(driver);
        waitForElement(driver, btnSubmitDialogConfirm);
        btnSubmitDialogConfirm.click();
    }

    public final Boolean isDismissDialogConfirmSubmitChangeStateSubmit(final WebDriver driver) {
        waitForElement(driver, dlgConfirmSubmit);
        return !dlgConfirmSubmit.isDisplayed() && isDisplayAllItemStateSubmit(driver);
    }

    public final Boolean isMessageConfirmShowing(final WebDriver driver) {
        waitForElement(driver, tvMessageConfirm);
        return tvMessageConfirm.isDisplayed();
    }
}
