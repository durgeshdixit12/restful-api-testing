package deck.api.test;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;

public class AssertHelperUtil {

	public static void assertSuccessStatusCode(int statusCode) {
		Assert.assertEquals(DeckApiConstants.SUCCESS_STATUS_CODE, statusCode);
	}

	public static void assertFailureStatusCode(int statusCode) {
		Assert.assertNotSame(DeckApiConstants.SUCCESS_STATUS_CODE, statusCode);
	}

	public static void basicAPIAssertions(JsonPath jsonResponse) {
		Assert.assertEquals(true, jsonResponse.get(DeckApiConstants.SUCCESS));
		Assert.assertNotNull(jsonResponse.get(DeckApiConstants.DECK_ID));
	}

	public static void assertCardsCountInDeck(int expectedCount, JsonPath jsonResponse) {
		Assert.assertEquals(expectedCount, (int) jsonResponse.get(DeckApiConstants.REMAINING));
	}
}
