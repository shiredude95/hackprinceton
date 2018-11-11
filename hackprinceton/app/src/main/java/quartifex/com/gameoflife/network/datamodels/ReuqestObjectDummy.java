package quartifex.com.gameoflife.network.datamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReuqestObjectDummy {

	@SerializedName("reqmsg")
	@Expose
	private String reqmsg;


	public String getReqmsg() {
		return reqmsg;
	}

	public void setReqmsg(String reqmsg) {
		this.reqmsg = reqmsg;
	}
}
