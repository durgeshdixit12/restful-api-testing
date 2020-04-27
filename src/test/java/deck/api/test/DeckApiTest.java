package deck.api.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class DeckApiTest {

	private static final String JOKERS_ENABLED = "jokers_enabled";
	private static final String COUNT = "count";
	static RestAssuredClient client = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		client = new RestAssuredClient();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		client = null;
	}

	@Test
	public void getNewDeckAPITest() {
		Response response = client.execute(DeckApiConstants.DECK_API_URI + DeckApiConstants.GET_NEW_DECK_API_PATH,
				Method.GET, null, null, null);
		AssertHelperUtil.assertSuccessStatusCode(response.getStatusCode());
		AssertHelperUtil.basicAPIAssertions(response.jsonPath());
		AssertHelperUtil.assertCardsCountInDeck(52, response.jsonPath());
	}

	@Test
	public void addJokerAPITest() {
		Map<String, Object> params = new HashMap<>();
		params.put(JOKERS_ENABLED, true);
		Response response = client.execute(DeckApiConstants.DECK_API_URI + DeckApiConstants.GET_NEW_DECK_API_PATH,
				Method.GET, null, null, params);
		AssertHelperUtil.assertSuccessStatusCode(response.getStatusCode());
		AssertHelperUtil.basicAPIAssertions(response.jsonPath());
		AssertHelperUtil.assertCardsCountInDeck(54, response.jsonPath());
	}

	@Test
	public void drawCardFromDeckAPITest() {
		Response response = client.execute(DeckApiConstants.DECK_API_URI + DeckApiConstants.GET_NEW_DECK_API_PATH,
				Method.GET, null, null, null);
		AssertHelperUtil.assertSuccessStatusCode(response.getStatusCode());
		AssertHelperUtil.basicAPIAssertions(response.jsonPath());
		AssertHelperUtil.assertCardsCountInDeck(52, response.jsonPath());
		Map<String, Object> params = new HashMap<>();
		params.put(COUNT, 2);
		AssertHelperUtil.assertSuccessStatusCode(response.getStatusCode());
	}

}
