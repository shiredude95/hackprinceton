package quartifex.com.gameoflife;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.Socket;

public class MessagingTaskBg extends AsyncTask<String,Void,String> {


	private WeakReference<TextView> messageResponse;

	MessagingTaskBg(TextView textView){messageResponse=new WeakReference<>(textView); }

	@Override
	protected String doInBackground(String... strings) {
		try {
			try{
				Socket socket = new Socket("http://3d537792.ngrok.io",4040);
				PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				outToServer.print(strings[0]);
				outToServer.flush();
			}catch (IOException e){
				return e.getMessage();
			}

		}catch (Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		return  null;
	}

	@Override
	protected void onPostExecute(String s) {

		if(messageResponse.get()!=null){
			messageResponse.get().setText(s);
		}
		super.onPostExecute(s);
	}
}
