package kr.co.apptoapp.bclientapp.apptoapp;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

/**
 * Handler receiving the message transmitted from Messsenger
 */

public class AppToApphandler extends Handler implements AppToAppConstant {

    Context mContext;
    Messenger messenger = null;

    AppToApphandler(Context context) {
        this.mContext = context;
    }

    @Override
    public void handleMessage(Message msg) {

        if (messenger == null && msg.replyTo != null) {
            messenger = msg.replyTo;
            AppToAppManager.with(mContext).setMessenger(messenger);
        }
        Bundle bundle = msg.getData();
        switch (msg.what) {
            case ACLIENT_REQUEST_ADD:
                AppToAppManager.with(mContext).send(ACLIENT_RESPONSE_ADD);
                break;
            case ACLIENT_SEND_TIMESTAMP:
                if (bundle != null) {
                    long timeStamp = bundle.getLong("timestamp");
                    Toast.makeText(mContext, "BClient Response " + timeStamp, Toast.LENGTH_SHORT).show();
                }
                break;

            case ACLIENT_REQUEST_FORCEEXIT:
                String exceptionString = null;
                exceptionString.charAt(0);
                break;
            case DISCONNETCLIENTA:
                AppToAppManager.with(mContext).setMessenger(null);
                break;

            default:
                super.handleMessage(msg);
        }
    }
}
