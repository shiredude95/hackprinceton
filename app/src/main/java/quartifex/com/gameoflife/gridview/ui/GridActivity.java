package quartifex.com.gameoflife.gridview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import quartifex.com.gameoflife.R;
import quartifex.com.gameoflife.gridservice.model.Cell;
import quartifex.com.gameoflife.gridview.ui.GridFragment;

public class GridActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_activity);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, GridFragment.newInstance())
					.commitNow();
		}
	}

	void sendEvent2Server(Cell cell){

	}
}
