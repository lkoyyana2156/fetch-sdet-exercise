package tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class FetchTest extends BaseTest {

    private final Random random = new Random();
    private final List<Integer> matrixBlocks = Arrays.asList(0,1,2,3,4,5,6,7,8);
    private final List<List<Integer>> list = Arrays.asList(
            Arrays.asList(0,1,2),
            Arrays.asList(3,4,5),
            Arrays.asList(6,7,8)
    );
    private List<Integer> threeRandomSquares = new ArrayList<>();

    private int findTheFakeBarSet(String latestResult) {
        if (latestResult.contains("=")) {
            return 2;
        } else if (latestResult.contains("<")) {
            return 0;
        } else {
            return 1;
        }
    }

    private List<Integer> getRandomListOfThreeIntegers(){
        Collections.shuffle(matrixBlocks);
        return matrixBlocks.subList(0,3);
    }

    private int findFakeBarId(){
        threeRandomSquares = getRandomListOfThreeIntegers();

        weighScalePage.enterValuesIntoLeftScale(threeRandomSquares, list.get(0));

        threeRandomSquares = getRandomListOfThreeIntegers();

        weighScalePage.enterValuesIntoRightScale(threeRandomSquares, list.get(1));

        weighScalePage.clickWeigh();
        int fakeSetIndex = findTheFakeBarSet(weighScalePage.getLatestWeighResult());

        weighScalePage.clickReset();

        weighScalePage.enterValueIntoLeftScale(random.nextInt(9),list.get(fakeSetIndex).get(0).toString());
        weighScalePage.enterValueIntoRightScale(random.nextInt(9),list.get(fakeSetIndex).get(1).toString());

        weighScalePage.clickWeigh();
        weighScalePage.waitForAllResults(2);
        int fakeBarIndex = findTheFakeBarSet(weighScalePage.getLatestWeighResult());
        return list.get(fakeSetIndex).get(fakeBarIndex);
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
        weighScalePage.acceptAlert();
    }

    /**
     * Verify the Invalid Input Alert is displayed when both input values are same on both side of the scale.
     */
    @Test
    public void verifyAlertMessageForInvalidInput(){
        waitUtils.navigateToUrl(Url);

        threeRandomSquares = getRandomListOfThreeIntegers();
        weighScalePage.enterValuesIntoLeftScale(threeRandomSquares, list.get(0));

        threeRandomSquares = getRandomListOfThreeIntegers();
        weighScalePage.enterValuesIntoRightScale(threeRandomSquares, list.get(0));

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