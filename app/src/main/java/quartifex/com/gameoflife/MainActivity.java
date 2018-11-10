package quartifex.com.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import quartifex.com.gameoflife.network.csarch.ApiUtils;
import quartifex.com.gameoflife.network.csarch.ResponseService;
import quartifex.com.gameoflife.network.datamodels.ResponseObjectDummy;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


	private ResponseService mResponseService;

	private static final String LOG_TAG="MainActivityLogger";

	@BindView(R.id.message_input)
	EditText messageInput;

	@BindView(R.id.message_send)
	Button messageSend;

	@BindView(R.id.message_response)
	TextView messageResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		mResponseService= ApiUtils.getResponseService();


	}

	@OnClick(R.id.message_send)
	void sendMessageToServer(Button button){

		String messageString=messageInput.getText().toString();
		if (messageString.length()!=0){
			RequestBody reqBody = new FormBody.Builder()
					.add("request_message",messageString)
					.build();
			Call<ResponseObjectDummy> call = mResponseService.postDummy2Server(reqBody);
			call.enqueue(new Callback<ResponseObjectDummy>() {
				@Override
				public void onResponse(Call<ResponseObjectDummy> call, Response<ResponseObjectDummy> response) {

					ResponseObjectDummy responseObjectDummy=response.body();
					Log.d(LOG_TAG,responseObjectDummy.getRespmsg());
					messageResponse.setText(responseObjectDummy.getRespmsg());
				}

				@Override
				public void onFailure(Call<ResponseObjectDummy> call, Throwable t) {
					Log.d(LOG_TAG,t.getMessage());
				}
			});


			messageInput.getText().clear();
		}
	}

}
