package kr.co.apptoapp.aclientapp.apptoapp;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Handler receiving the message transmitted from Messsenger
 */

public class AppToApphandler extends Handler implements AppToAppConstant {
    private static final int MSG_SAY_HELLO = 1;

    Context mContext;

    AppToApphandler(Context context) {
        this.mContext = context;
    }

    @Override
    public void handleMessage(Message msg) {
        Bundle bundle=msg.getData();
        switch (msg.what) {
            case ACLIENT_RESPONSE_ADD:
                Toast.makeText(mContext, "Request add Sucess", Toast.LENGTH_SHORT).show();
                break;
            case BCLIENT_SEND_TIMESTAMP:
                if(bundle!=null){
                    long timeStamp=bundle.getLong("timestamp");
                    Toast.makeText(mContext,"AClient Response "+timeStamp,Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                super.handleMessage(msg);
        }
    }
}
