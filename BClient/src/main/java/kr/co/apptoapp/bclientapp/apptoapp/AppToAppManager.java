package kr.co.apptoapp.bclientapp.apptoapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * @value mService : Passed in Client Messenger
 */
public class AppToAppManager {
    private static AppToAppManager instance;
    private static Context mContext;
    private Messenger mService = null;

    AppToAppManager() {
    }


    public static AppToAppManager with(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new AppToAppManager();
        }
        return instance;
    }


    /**
     * @return mService ==null(Client is Craceful Shutdown) or RemoteException (Client is Receive no state response)
     * @Params what : Request code sent to Client( define AppToAppConstant)
     * @Params bundle : data sent to client (object must implemented parcelable)
     */
    public boolean send(int what, Bundle bundle) {

        if (mService != null) {
            Message msg = Message.obtain(null, what, 0, 0);
            msg.setData(bundle);
            try {
                mService.send(msg);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean send(int what) {

        if (mService != null) {
            Message msg = Message.obtain(null, what, 0, 0);
            try {
                mService.send(msg);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }


    }


    public void setMessenger(Messenger messenger) {
        mService = messenger;
    }
}
