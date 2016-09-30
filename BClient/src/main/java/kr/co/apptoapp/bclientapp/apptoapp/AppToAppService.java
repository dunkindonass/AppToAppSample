package kr.co.apptoapp.bclientapp.apptoapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

/*
* When a service is created, define Messenger class
* onBind() is Return the Binder of Messenger
* */
public class AppToAppService extends Service {
    Messenger mMessenger = null;

    public AppToAppService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMessenger = new Messenger(new AppToApphandler(getApplicationContext()));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
