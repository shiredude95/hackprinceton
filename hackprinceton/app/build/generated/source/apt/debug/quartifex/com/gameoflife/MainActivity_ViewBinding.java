// Generated code from Butter Knife. Do not modify!
package quartifex.com.gameoflife;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f070056;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.messageInput = Utils.findRequiredViewAsType(source, R.id.message_input, "field 'messageInput'", EditText.class);
    view = Utils.findRequiredView(source, R.id.message_send, "field 'messageSend' and method 'sendMessageToServer'");
    target.messageSend = Utils.castView(view, R.id.message_send, "field 'messageSend'", Button.class);
    view7f070056 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendMessageToServer(Utils.castParam(p0, "doClick", 0, "sendMessageToServer", 0, Button.class));
      }
    });
    target.messageResponse = Utils.findRequiredViewAsType(source, R.id.message_response, "field 'messageResponse'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.messageInput = null;
    target.messageSend = null;
    target.messageResponse = null;

    view7f070056.setOnClickListener(null);
    view7f070056 = null;
  }
}
