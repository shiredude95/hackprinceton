package quartifex.com.gameoflife.network.datamodels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseObject {

	@SerializedName("enabled")
	@Expose
	private List<Integer> enabled = null;
	@SerializedName("disabled")
	@Expose
	private List<Integer> disabled = null;

	public List<Integer> getEnabled() {
		return enabled;
	}

	public void setEnabled(List<Integer> enabled) {
		this.enabled = enabled;
	}

	public List<Integer> getDisabled() {
		return disabled;
	}

	public void setDisabled(List<Integer> disabled) {
		this.disabled = disabled;
	}

}