package kr.connotation.fiermemory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import kr.connotation.fiermemory.R;

public class ShowData extends AppCompatActivity {
    int key;
    private String IMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String s = i.getExtras().get("key").toString();
        key = Integer.parseInt(s);
        Panic thisPanic = Panic.findById(Panic.class, key);
        String stitle = thisPanic.getTitle().toString();
        String sgroup = thisPanic.getmGroup().toString();
        String splace = thisPanic.getPlace().toString();
        String smemo = thisPanic.getMemo().toString();

        double slng = thisPanic.getLng();
        double slat = thisPanic.getLat();

        ((TextView)findViewById(R.id.show_top)).setText(""+stitle);
        ((TextView)findViewById(R.id.show_group)).setText(""+sgroup);
        ((TextView)findViewById(R.id.show_place)).setText(""+splace);
        ((TextView)findViewById(R.id.show_memo)).setText(""+smemo);
        setIMG(sgroup);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShowData.this, InputForm.class);
                startActivity(i);
            }
        });
    }

    public void setIMG(String IMG) {
        switch (IMG){
            case "사람":
                (findViewById(R.id.show_type)).setBackgroundResource(R.drawable.human_60);
                break;
            case "사물":
                (findViewById(R.id.show_type)).setBackgroundResource(R.drawable.object_60);
                break;
            case "동물":
                (findViewById(R.id.show_type)).setBackgroundResource(R.drawable.animals_60);
                break;
            case "장소":
                (findViewById(R.id.show_type)).setBackgroundResource(R.drawable.place_60);
                break;
            default :
                (findViewById(R.id.show_type)).setBackgroundResource(R.drawable.guitar_60);
                break;
        }
        (findViewById(R.id.show_type)).setMinimumWidth(60);
        (findViewById(R.id.show_type)).setMinimumHeight(60);

    }
}
