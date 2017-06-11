package kr.connotation.fiermemory;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InputForm extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Boolean isGPSEnabled;
    Boolean isNetworkEnabled;
    LocationManager locationManager;
    FloatingActionButton fab;
    double mlat;        //위도
    double mlng;        //경도
    Spinner spn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // GPS 프로바이더 사용가능여부
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 네트워크 프로바이더 사용가능여부
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Log.d("Main", "isGPSEnabled=" + isGPSEnabled);
        Log.d("Main", "isNetworkEnabled=" + isNetworkEnabled);


        spn = (Spinner) findViewById(R.id.input_group);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mlat = location.getLatitude();
                mlng = location.getLongitude();

//                logView.setText("latitude: " + lat + ", longitude: " + lng);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
//                logView.setText("onStatusChanged");
            }

            public void onProviderEnabled(String provider) {
//                logView.setText("onProviderEnabled");
            }

            public void onProviderDisabled(String provider) {
//                logView.setText("onProviderDisabled");
            }
        };

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
        if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

        setDefault();
        listen();
    }

    private void listen() {
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title;
                String place;
                String mgroup;
                String memo;
                try {
                    title = ((EditText) findViewById(R.id.input_top)).getText().toString();
                    place = ((EditText) findViewById(R.id.input_place)).getText().toString();
                    mgroup = spn.getSelectedItem().toString();
                    memo = ((EditText) findViewById(R.id.input_memo)).getText().toString();
                    CreateDB(title, place, mgroup, memo, mlat, mlng);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (mlng + mlat == 0) {
                    Toast.makeText(InputForm.this, "위치 저장 실패", Toast.LENGTH_SHORT).show();
                }
                finish();
                startActivity(new Intent(InputForm.this, MainActivity.class));
                // longitude 경도 latitude 위도
            }
        });
    }

    private void setDefault() {
    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(InputForm.this, MainActivity.class);
        switch (v.getId()) {
            case R.id.input_back:
                startActivity(i);
                finish();
                break;
        }
    }


    public void CreateDB(String title, String place, String mgroup, String memo, double lng, double laf) {
        Panic panic = new Panic(title, place, mgroup, memo, lng, laf);
        panic.save();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.rgb(0xFF,0x40,0x81));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
