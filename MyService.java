package kr.connotation.fiermemory;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    Context context = this;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //서비스 클래스의 onStartCommand 함수 등에서-
        NotiControl cl = new NotiControl(context);
        startForeground(1, cl.getNoti());
        return 0;
    }
}
