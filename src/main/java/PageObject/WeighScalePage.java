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
    private By getWeighScaleBowlBy(String id){
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
     * Get Left Bowl Grid Elements
     * @return List of all the grid elements for Left Bowl
     */
    public List<WebElement> getLeftBowlGridElements(){
        return waitUtils.waitForElements(getWeighScaleBowlBy("left"));
    }

    /**
     * Get Right Bowl Grid Elements
     * @return List of all the grid elements for Right Bowl
     */
    public List<WebElement> getRightBowlGridElements(){
        return waitUtils.waitForElements(getWeighScaleBowlBy("right"));
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
     * Enter Values into Left Bowl
     * @param indices = List of Indices of the Left Bowl Grids to input values
     * @param values  = List of Values to enter into Grids
     */
    public void enterValuesIntoLeftBowl(List<Integer> indices, List<Integer> values){
        int count = 0;

        for(Integer s: values){
            getLeftBowlGridElements().get(indices.get(count)).sendKeys(s.toString());
            count++;
        }
    }

    /**
     * Enter Values into Right Bowl
     * @param indices = List of Indices of the Right Bowl Grids to input values
     * @param values  = List of Values to enter into Grids
     */
    public void enterValuesIntoRightBowl(List<Integer> indices, List<Integer> values){
        int count = 0;

        for(Integer s: values){
            getRightBowlGridElements().get(indices.get(count)).sendKeys(s.toString());
            count++;
        }
    }

    /**
     * Enter single value into the left bowl
     * @param index = index of left bowl grid to input value
     * @param value = value to input into grid
     */
    public void enterValueIntoLeftBowl(int index, String value){
        getLeftBowlGridElements().get(index).sendKeys(value);
    }

    /**
     * Enter single value into the right bowl
     * @param index = index of right bowl grid to input value
     * @param value = value to input into grid
     */
    public void enterValueIntoRightBowl(int index, String value){
        getRightBowlGridElements().get(index).sendKeys(value);
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