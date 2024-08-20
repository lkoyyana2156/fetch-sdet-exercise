package PageObject;

import Utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class WeighScalePage {

    //region Locators
    private final By weighButtonBy = By.id("weigh");
    private final By resultsBy = By.xpath(".//li");
    private final By resetButtonBy = By.xpath(".//button[@id='reset' and not(@disabled)]");
    private final By resultButtonBy = By.xpath(".//button[@id='reset' and @disabled]");
    private By getWeighScaleMatrixBy(String id){
        return By.xpath(".//input[contains(@id,'"+id+"')]");
    }
    private By getGoldBarBy(String id){
        return By.id((id));
    }
    //endregion

    private final WaitUtils waitUtils;
    public WeighScalePage(WaitUtils waitUtils){
        this.waitUtils = waitUtils;
    }

    //region Page Actions
    /**
     * Get Left Scale Matrix Squares
     * @return List of all the matrix squares for left scale
     */
    public List<WebElement> getLeftScaleMatrix(){
        return waitUtils.waitForElements(getWeighScaleMatrixBy("left"));
    }

    /**
     * Get Right Scale Matrix Squares
     * @return List of all the matrix squares for right scale
     */
    public List<WebElement> getRightScaleMatrix(){
        return waitUtils.waitForElements(getWeighScaleMatrixBy("right"));
    }

    /**
     * Click Weigh Button
     */
    public void clickWeigh(){
        waitUtils.waitForElement(weighButtonBy, 30).click();
    }

    /**
     * Get all Weight Results Web Elements
     * @return List<WebElement> for weigh results
     */
    public List<WebElement> getWeighResults(){
        return waitUtils.waitForElements(resultsBy);
    }

    /**
     * Click Reset button
     */
    public void clickReset(){
        waitUtils.waitForElement(resetButtonBy, 30).click();
    }

    /**
     * Wait for all the results to display
     * @param expectedResultsCount = Expected number of results
     */
    public void waitForAllResults(int expectedResultsCount){
        waitUtils.waitForElementsCountGreaterThanExpectedCount(resultsBy, expectedResultsCount, 30);
    }

    /**
     * Click the Gold bar
     * @param index = Index of the gold bar to click
     */
    public void clickGoldBar(String index){
        waitUtils.waitForElement(getGoldBarBy(index)).click();
    }

    /**
     * Get Alert text
     * @return Alert text as String
     */
    public String getAlertText(){
        return waitUtils.waitForAlert(30).getText();
    }

    /**
     * Accept Alert
     */
    public void acceptAlert(){
        waitUtils.waitForAlert(30).accept();
    }

    /**
     * Enter Values into Left weigh scale
     * @param indices = List of Indices of the matrix to input values
     * @param values  = List of Values to enter into matrix
     */
    public void enterValuesIntoLeftScale(List<Integer> indices, List<Integer> values){
        int square = 0;

        for(Integer s: values){
            getLeftScaleMatrix().get(indices.get(square)).sendKeys(s.toString());
            square++;
        }
    }

    /**
     * Enter Values into Right weigh scale
     * @param indices = List of Indices of the matrix to input values
     * @param values  = List of Values to enter into matrix
     */
    public void enterValuesIntoRightScale(List<Integer> indices, List<Integer> values){
        int square = 0;

        for(Integer s: values){
            getRightScaleMatrix().get(indices.get(square)).sendKeys(s.toString());
            square++;
        }
    }

    /**
     * Enter single value into the left weigh scale
     * @param index = index of matrix to input value
     * @param value = value to input into matrix
     */
    public void enterValueIntoLeftScale(int index, String value){
        getLeftScaleMatrix().get(index).sendKeys(value);
    }

    /**
     * Enter single value into the right weigh scale
     * @param index = index of matrix to input value
     * @param value = value to input into matrix
     */
    public void enterValueIntoRightScale(int index, String value){
        getRightScaleMatrix().get(index).sendKeys(value);
    }

    /**
     * Get result of the weigh
     * @return Result as String
     */
    public String getResultOfWeigh(){
        return waitUtils.waitForElement(resultButtonBy).getText();
    }
    //endregion
}