package quartifex.com.gameoflife.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {

	public static int calculateNoOfColumns(Context context) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
		return (int) (dpWidth / 36);
	}
}
