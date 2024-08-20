package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.*;

public class FetchTest extends BaseTest {

    private final Random random = new Random();
    private final List<Integer> bowlGrids = Arrays.asList(0,1,2,3,4,5,6,7,8);
    private final List<List<Integer>> listOfGoldBars = Arrays.asList(
            Arrays.asList(0,1,2),
            Arrays.asList(3,4,5),
            Arrays.asList(6,7,8)
    );
    private List<Integer> threeRandomSquares = new ArrayList<>();

    private int findTheFakeBarSet(String latestResult) {
        if (latestResult.equals("=")) {
            return 2;
        } else if (latestResult.equals("<")) {
            return 0;
        } else {
            return 1;
        }
    }

    private List<Integer> getRandomListOfThreeIntegers(){
        Collections.shuffle(bowlGrids);
        return bowlGrids.subList(0,3);
    }

    private int findFakeBarId(){
        threeRandomSquares = getRandomListOfThreeIntegers();

        weighScalePage.enterValuesIntoLeftBowl(threeRandomSquares, listOfGoldBars.get(0));

        threeRandomSquares = getRandomListOfThreeIntegers();

        weighScalePage.enterValuesIntoRightBowl(threeRandomSquares, listOfGoldBars.get(1));

        weighScalePage.clickWeigh();
        weighScalePage.waitForAllResults(1);
        int fakeSetIndex = findTheFakeBarSet(weighScalePage.getResultOfWeigh());
        weighScalePage.clickReset();

        weighScalePage.enterValueIntoLeftBowl(random.nextInt(9), listOfGoldBars.get(fakeSetIndex).get(0).toString());
        weighScalePage.enterValueIntoRightBowl(random.nextInt(9), listOfGoldBars.get(fakeSetIndex).get(1).toString());

        weighScalePage.clickWeigh();
        weighScalePage.waitForAllResults(2);
        int fakeBarIndex = findTheFakeBarSet(weighScalePage.getResultOfWeigh());
        return listOfGoldBars.get(fakeSetIndex).get(fakeBarIndex);
    }

    /**
     * Verify that Success Alert Message is displayed when the correct Fake Bar was clicked.
     */
    @Test
    public void verifyFakeGoldBarIsFound() {
        waitUtils.navigateToUrl(Url);
        String fakeBarId = "coin_"+findFakeBarId();
        weighScalePage.clickGoldBar(fakeBarId);
        Assert.assertEquals("Yay! You find it!", weighScalePage.getAlertText());
        System.out.println("Alert Message is: "+ weighScalePage.getAlertText());
        weighScalePage.acceptAlert();
        List<WebElement> results = weighScalePage.getWeighResults();
        System.out.println("Number of Weighings to find the fake bar is: "+ results.size());
        System.out.println("All the weighing are: ");
        for(WebElement e : results){
            System.out.println(e.getText());
        }
    }

    /**
     * Verify the Invalid Input Alert is displayed when both input values are same on both side of the scale.
     */
    @Test
    public void verifyAlertMessageForInvalidInput(){
        waitUtils.navigateToUrl(Url);

        threeRandomSquares = getRandomListOfThreeIntegers();
        weighScalePage.enterValuesIntoLeftBowl(threeRandomSquares, listOfGoldBars.get(0));

        threeRandomSquares = getRandomListOfThreeIntegers();
        weighScalePage.enterValuesIntoRightBowl(threeRandomSquares, listOfGoldBars.get(0));

        weighScalePage.clickWeigh();
        Assert.assertTrue(weighScalePage.getAlertText().contains("Inputs are invalid: Both sides have coin(s):"));
        weighScalePage.acceptAlert();
    }

    /**
     * Verify that Error alert is displayed when Incorrect Fake Bar is clicked.
     */
    @Test
    public void verifyAlertMessageForIncorrectFakeBar(){
        waitUtils.navigateToUrl(Url);
        int fakeBarId = findFakeBarId();
        if(fakeBarId == 8){
            fakeBarId = fakeBarId -2;
        }
        String fakeBarLocator = "coin_"+(fakeBarId+1);
        weighScalePage.clickGoldBar(fakeBarLocator);
        Assert.assertEquals("Oops! Try Again!", weighScalePage.getAlertText());
        weighScalePage.acceptAlert();
    }
}