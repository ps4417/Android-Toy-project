package com.example.ws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class SecondActivity extends AppCompatActivity {
    Fragment1 fragment1; // 홈
    Fragment2 fragment2; // 맛집 리스트
    Fragment3 fragment3; // 내 주변 맛집(지도)
    Fragment4 fragment4;  // 설정
    FragmentManager fragmentManager;

    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;

    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();;
        Bundle bundle = intent.getExtras();
        String login_result = bundle.getString("login","");
        Toast.makeText(this, login_result, Toast.LENGTH_SHORT).show();

        // 푸시서비스
        FirebaseMessaging.getInstance().subscribeToTopic("car").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg = "FCM Complete ...";
                if(!task.isSuccessful()){
                    msg = "FCM Fail";
                }
                Log.d("[TAG]:",msg);
            }
        });
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver,new IntentFilter("notification"));



        //permission
        String [] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions(SecondActivity.this, permission, 101);

        // fragment
        fragment1 = new Fragment1();
        fragment2 = new Fragment2(this);
        fragment3 = new Fragment3(this);
        fragment4 = new Fragment4();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout,fragment1).commit();

        // 상단 메뉴 - 설정
        actionBar = getSupportActionBar();
        actionBar.setTitle("먹방 MUKBANG");

        //하단 메뉴
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                ProgressDialog progressDialog = null;
                if(item.getItemId()==R.id.tab1){
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment1).commit();
                    Toast.makeText(SecondActivity.this, "홈 화면", Toast.LENGTH_SHORT).show();
                }else if(item.getItemId()==R.id.tab2){
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment2).commit();
                    Toast.makeText(SecondActivity.this, "맛집 리스트", Toast.LENGTH_SHORT).show();
                }else if(item.getItemId()==R.id.tab3){
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment3).commit();
                    Toast.makeText(SecondActivity.this, "내 주변 맛집", Toast.LENGTH_SHORT).show();
                }



                return false;
            }
        });


    }// end onCreate
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                String title = intent.getStringExtra("title");
                String control = intent.getStringExtra("control");
                String data = intent.getStringExtra("data");
//                tx.setText(control+" "+data);
                Toast.makeText(context, title+" "+control+" "+data, Toast.LENGTH_SHORT).show();

                // 진동이 울리도록
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if(Build.VERSION.SDK_INT>=26){
                    vibrator.vibrate(VibrationEffect.createOneShot(1000,10));
                }else{
                    vibrator.vibrate(1000);
                }

                // 소리가 울리도록
//                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),uri);
//                ringtone.play();

                // 상단 알림창이 뜨도록
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = null;
                if(Build.VERSION.SDK_INT>=26){
                    if(manager.getNotificationChannel("ch2")==null){
                        manager.createNotificationChannel(new NotificationChannel("ch2","chname2",NotificationManager.IMPORTANCE_DEFAULT));
                    }
                    builder = new NotificationCompat.Builder(SecondActivity.this,"ch2");
                }else{
                    builder = new NotificationCompat.Builder(SecondActivity.this);
                }
                Intent intent2 = new Intent(SecondActivity.this,SecondActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(SecondActivity.this,101,intent2,PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setAutoCancel(true);  // notification 클릭하면 사라지도록
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("먹방 MUKBANG");
                builder.setContentText("치킨 먹고 여행가자! 먹방 이벤트 쿠폰!");
                builder.setSmallIcon(R.drawable.mukbang);
                Notification noti = builder.build();
                manager.notify(1,noti);

            }
        }
    };

}