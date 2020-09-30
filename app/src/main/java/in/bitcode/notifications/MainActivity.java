package in.bitcode.notifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnSimpleNotification, mBtnCancelNotification;
    NotificationManager mNotificationManager;

    private static final String NOTIFICATION_CHANNEL_ACCOUNT_ID = "account";
    private static final String NOTIFICATION_CHANNEL_ACCOUNT_TITLE = "Account Updates";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mBtnSimpleNotification = findViewById(R.id.btnSimpleNotification);
        mBtnCancelNotification = findViewById(R.id.btnCancelNotification);

        mBtnSimpleNotification.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                Notification.Builder builder = new Notification.Builder(MainActivity.this);

                builder.setContentTitle("Job Notification");
                builder.setContentText("Your interview has been scheduled on 30 Sep 2020, 9.00am");

                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setColor(Color.RED);
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                Bitmap IndianFlag =
                        BitmapFactory.decodeResource(getResources(), R.drawable.in_flag_icon);
                builder.setLargeIcon(IndianFlag);

                builder.setOngoing(true);
                builder.setAutoCancel(true);

                Intent intent = new Intent(MainActivity.this, JobDetailsActivity.class);
                intent.putExtra("name", "BitCode");
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                1,
                                intent,
                                0);
                builder.setContentIntent(pendingIntent);

                //set notification channel
//                if (mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ACCOUNT_ID) == null) {
//                    createNotificationChannel();
//                }
                createNotificationChannel();
                builder.setChannelId(NOTIFICATION_CHANNEL_ACCOUNT_ID);

                Notification notification = builder.build();
                mNotificationManager.notify(101, notification);


            }
        });

        mBtnCancelNotification.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mNotificationManager.cancel(101);
                    }
                }
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {

        NotificationChannel notificationChannel =
                new NotificationChannel(
                        NOTIFICATION_CHANNEL_ACCOUNT_ID,
                        NOTIFICATION_CHANNEL_ACCOUNT_TITLE,
                        NotificationManager.IMPORTANCE_HIGH
                );
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setVibrationPattern(
                new long[] {300, 400, 300, 400, 400, 400, 500, 300 }
        );
        //notificationChannel.setSound();
        notificationChannel.setShowBadge(true);

        mNotificationManager.createNotificationChannel(notificationChannel);


    }
}