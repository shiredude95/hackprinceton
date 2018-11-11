package quartifex.com.gameoflife.network.csarch;

public class ApiUtils {

	public static final String BASE_URL="https://7b0abca6.ngrok.io/";
	public static ResponseService getResponseService() {

		return RetrofitClient.getClient(BASE_URL).create(ResponseService.class);
	}

}
