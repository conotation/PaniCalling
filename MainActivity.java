package kr.connotation.fiermemory;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Panic> pList;
    private PanicAdapter arrayAdapter;
    ListView listView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setDefault();
        listen();
        Intent i = new Intent(MainActivity.this, MyService.class);
        startService(i);

    }





    private void setDefault() {
        listView = (ListView) findViewById(R.id.mlist);
        View header = getLayoutInflater().inflate(R.layout.header_form, null, false);
        listView.addHeaderView(header);
        arrayAdapter = new PanicAdapter(getApplicationContext(), pList);

        if (LoadDB() != null) {
            setListView();
        }
    }

    private void setListView() {
        pList = LoadDB();
        arrayAdapter = new PanicAdapter(getApplicationContext(), pList);

        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ShowData.class);
                i.putExtra("key", pList.get(position - 1).getId());
                finish();
                startActivity(i);
//                DeleteDB(pList.get(position-1).getId());
//                setListView();
            }
        });

    }

    private void listen() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InputForm.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn:
//                break;
        }
    }

    private List<Panic> LoadDB() {
        List<Panic> panics = new ArrayList<>();
        try {
            panics = Panic.listAll(Panic.class);
        } catch (Exception e) {
            e.printStackTrace();
            panics = null;
        }
        return panics;
    }

    public void CreateDB(String title, String place, String group, String memo, double lng, double laf) {
        Panic panic = new Panic(title, place, group, memo, lng, laf);
//        Panic.save();
    }

    private void DeleteDB(long id) {
        Panic panic = Panic.findById(Panic.class, id);
        panic.delete();
    }
}
