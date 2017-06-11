package kr.connotation.fiermemory;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

/**
 * Created by Connotation on 2016-03-27.
 */
public class NotiControl {
    private Context context;
    private RemoteViews rView;
    private NotificationCompat.Builder nBuilder;

    public NotiControl (Context parent)
    {
        this.context= parent;
        nBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentTitle("위험!")
                .setContentText("두려움이 주변에 있습니다")
//                .setSmallIcon(R.drawable.ic_launcher)
                .setPriority(Notification.PRIORITY_MIN) //요 부분이 핵심입니다. MAX가 아닌 MIN을 줘야 합니다.
                .setOngoing(true);

        rView = new RemoteViews(parent.getPackageName(), R.layout.mynoticontrolview); //노티바를 내렸을때 보여지는 화면입니다.

        //set the listener
        nBuilder.setContent(rView);
    }

    public Notification getNoti()
    {
        return nBuilder.build();
    }

}
