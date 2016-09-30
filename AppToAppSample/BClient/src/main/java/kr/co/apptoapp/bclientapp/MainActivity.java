package kr.co.apptoapp.bclientapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.co.apptoapp.bclientapp.apptoapp.AppToAppConstant;
import kr.co.apptoapp.bclientapp.apptoapp.AppToAppManager;

public class MainActivity extends AppCompatActivity implements AppToAppConstant {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.sendtimestamp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("timestamp", System.currentTimeMillis());
                if(!AppToAppManager.with(MainActivity.this).send(BCLIENT_SEND_TIMESTAMP, bundle)){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();     //닫기
                        }
                    });
                    alert.setMessage("Message send fail :: ACient is offline");
                    alert.show();
                }

            }
        });
    }
}
