package com.example.notifku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import com.example.notifku.databinding.ActivityMainJavaBinding;

public class MainActivityJava extends AppCompatActivity {

    private ActivityMainJavaBinding binding;
    private final String channelId = "TEST_NOTIF";
    private final int notifId = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        binding.btnNotif.setOnClickListener(v -> {

            int flag = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_UPDATE_CURRENT : 0;

            Intent intent = new Intent(this, MainActivityJava.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    flag
            );

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_bell)
                    .setContentTitle("Notifku")
                    .setContentText("Hello World!")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notifChannel = new NotificationChannel(
                        channelId,
                        "Notifku",
                        NotificationManager.IMPORTANCE_DEFAULT
                );
                notifManager.createNotificationChannel(notifChannel);
                notifManager.notify(notifId, builder.build());
            } else {
                notifManager.notify(notifId, builder.build());
            }
        });

        binding.btnUpdate.setOnClickListener(v -> {
            Bitmap notifImage = BitmapFactory.decodeResource(getResources(), R.drawable.img);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_bell)
                    .setContentTitle("Notifku")
                    .setContentText("Ini update notifikasi")
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(notifImage))
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            notifManager.notify(notifId, builder.build());
        });
    }
}