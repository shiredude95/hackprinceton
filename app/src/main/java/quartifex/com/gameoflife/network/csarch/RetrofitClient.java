package quartifex.com.gameoflife.network.csarch;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

	private static Retrofit retrofit = null;

	public static Retrofit getClient(String baseURL) {


		if (retrofit == null) {

			OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
					.connectTimeout(120, TimeUnit.SECONDS)
					.readTimeout(120, TimeUnit.SECONDS)
					.writeTimeout(120, TimeUnit.SECONDS)
					.build();

			retrofit = new Retrofit.Builder()
					.baseUrl(baseURL)
					.client(okHttpClient)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
