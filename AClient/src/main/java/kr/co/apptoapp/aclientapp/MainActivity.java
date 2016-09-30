package kr.co.apptoapp.aclientapp;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kr.co.apptoapp.aclientapp.apptoapp.AppToAppConstant;
import kr.co.apptoapp.aclientapp.apptoapp.AppToAppManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AppToAppConstant {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    public void init() {
        ((Button) findViewById(R.id.sendtime)).setOnClickListener(this);
        ((Button) findViewById(R.id.force_exit_btn)).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppToAppManager.with(getApplicationContext()).startBindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppToAppManager.with(getApplicationContext()).stopBindService();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendtime:
                Bundle bundle = new Bundle();
                bundle.putLong("timestamp", System.currentTimeMillis());
                AppToAppManager.with(this).send(ACLIENT_SENAD_TIMESTAMP, bundle);
                break;
            case R.id.force_exit_btn:
                AppToAppManager.with(this).send(ACLIENT_REQUEST_FORCEEXIT);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //    AppToAppManager.with(getApplicationContext()).stopBindService();
    }
}
