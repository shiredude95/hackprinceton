package quartifex.com.gameoflife.network.datamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestObject {

	@SerializedName("x")
	@Expose
	private Integer x;
	@SerializedName("y")
	@Expose
	private Integer y;
	@SerializedName("localid")
	@Expose
	private Integer localid;
	@SerializedName("globalid")
	@Expose
	private Integer globalid;
	@SerializedName("state")
	@Expose
	private Boolean state;

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getLocalid() {
		return localid;
	}

	public void setLocalid(Integer localid) {
		this.localid = localid;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Integer getGlobalid() {
		return globalid;
	}

	public void setGlobalid(Integer globalid) {
		this.globalid = globalid;
	}

}