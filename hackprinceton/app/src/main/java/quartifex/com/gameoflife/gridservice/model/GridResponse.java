package quartifex.com.gameoflife.gridservice.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GridResponse {

	@SerializedName("mac")
	@Expose
	private String mac;
	@SerializedName("board")
	@Expose
	private List<List<Boolean>> board = null;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<List<Boolean>> getBoard() {
		return board;
	}

	public void setBoard(List<List<Boolean>> board) {
		this.board = board;
	}

}
