package com.example.canvasdemo;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private static final int MSG_START = 0;
    private static final int MSG_SUCCESS = 1;
    private static final int MSG_FAILURE = 2;

    private TextView tx;
    private Button btn;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START:
                    btn.setText("开始上传。。");
                    tx.setText("开始上传了。。。。");
                    break;
                case MSG_SUCCESS:
                    btn.setText("上传成功。。");
                    tx.setText("上传成功了。。。。");
                    break;
                case MSG_FAILURE:
                    btn.setText("上传失败。。");
                    tx.setText("上传失败了。。。。");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        tx = (TextView) findViewById(R.id.textView);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = MSG_START;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_CONTACTS})
    void asas() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_CONTACTS})
    void sas(final PermissionRequest request) {
    }
}
