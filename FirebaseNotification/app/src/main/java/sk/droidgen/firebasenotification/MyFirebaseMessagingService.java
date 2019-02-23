package sk.droidgen.firebasenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("CustomFilter", "onMessageReceived Executed");

        if (remoteMessage.getData().size()>0){
            sendNotification(remoteMessage.getData().get("text"));
        }
    }

    private void sendNotification(String text) {
        int NOTIFICATON_ID;
        Random random=new Random();
        NOTIFICATON_ID=random.nextInt();
        Log.d("CustomFilter", "Notification ID: "+NOTIFICATON_ID);

        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,NOTIFICATON_ID,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"default")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round))
                .setContentTitle("Firebase Notification")
                .setContentText(text)
                .setSound(defaultSoundUri)
                .setDefaults(Notification.DEFAULT_LIGHTS);

        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATON_ID,builder.build());
    }
}
