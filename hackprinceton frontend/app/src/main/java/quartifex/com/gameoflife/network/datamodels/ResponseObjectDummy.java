package quartifex.com.gameoflife.network.datamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseObjectDummy {


	@SerializedName("response_msg")
	@Expose
	private String respmsg;


	public String getRespmsg() {
		return respmsg;
	}

	public void setRespmsg(String respmsg) {
		this.respmsg = respmsg;
	}



}
