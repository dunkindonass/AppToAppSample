package kr.co.apptoapp.aclientapp.apptoapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

/**
 * @value appMessenger : After connection on get Messenger
 */

public class AppToAppManager implements AppToAppConstant {

    final String SERVICE_ACTION = "kr.co.apptoapp.bclientapp.ApptoAPpService";
    final String SERVICE_PACKAGE = "kr.co.apptoapp.bclientapp";

    private static AppToAppManager instance;
    private static Context mContext;

    private Messenger mService = null;
    private boolean mBound;
    private boolean stop;

    private static Messenger appMessenger;

    AppToAppManager() {
    }

    public static AppToAppManager with(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new AppToAppManager();
        }
        if (appMessenger == null) {
            appMessenger = new Messenger(new AppToApphandler(mContext));
        }
        return instance;
    }

    public void startBindService() {
        stop = false;

        Intent intent = new Intent();
        intent.setAction(SERVICE_ACTION);
        intent.setPackage(SERVICE_PACKAGE);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);


    }


    public void stopBindService() {
        stop = true;
        if (mBound) {
            send(DISCONNETCLIENTA);
            mContext.unbindService(mConnection);
            Toast.makeText(mContext,"Service is Unbind",Toast.LENGTH_SHORT).show();
        }

    }

    public void send(int what, Bundle bundle) {
        if (mBound && mService != null) {
            Message msg = Message.obtain(null, what, 0, 0);
            msg.setData(bundle);
            msg.replyTo = appMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    public void send(int what) {
        if (mBound && mService != null) {
            Message msg = Message.obtain(null, what, 0, 0);
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            mService = new Messenger(service);
            mBound = true;


            Bundle bundle = new Bundle();
            bundle.putString("name", "testuser01");
            bundle.putString("room", "CPS00110");
            send(ACLIENT_REQUEST_ADD, bundle);
        }

        public void onServiceDisconnected(ComponentName className) {
            if (!stop) {
                startBindService();
            }
            mService = null;
            mBound = false;


        }
    };
}
