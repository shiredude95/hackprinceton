package quartifex.com.gameoflife.network.csarch;

import okhttp3.RequestBody;
import quartifex.com.gameoflife.network.datamodels.ResponseObject;
import quartifex.com.gameoflife.network.datamodels.ResponseObjectDummy;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ResponseService {


	@POST("/message")
	Call<ResponseObject> post2Server(@Body RequestBody requestBody);

	@POST("hello")
	Call<ResponseObjectDummy> postDummy2Server(@Body RequestBody requestBody);

}
