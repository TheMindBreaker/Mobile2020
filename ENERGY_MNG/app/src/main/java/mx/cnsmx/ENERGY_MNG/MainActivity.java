package mx.cnsmx.ENERGY_MNG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView powerStatus;

    private BroadcastReceiver plugInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

            if (plugged == BatteryManager.BATTERY_PLUGGED_AC)
                powerStatus.setText("Conectado a corriente");
            else if (plugged == BatteryManager.BATTERY_PLUGGED_USB)
                powerStatus.setText("Conectar USB");
            else if (plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS)
                powerStatus.setText("Conectado a magnetico");
            else
                powerStatus.setText("No conectado");

        }
    };

    private TextView batteryTxt;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            batteryTxt.setText("Carga actual: " + String.valueOf(level) + "%");


        }
    };


    private TextView connStatusTxt;

    private void netWorkStatus() {

        String networkType = "";

        Context myContext = getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        try{
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork.isConnectedOrConnecting()) {

                if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    networkType = "WiFi";
                }
                else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                    networkType = "Red 3g4g";
                }
                else {
                    networkType = "Otro";
                }

            }
            connStatusTxt.setText("Tipo de red: " + networkType);
        }
        catch (Exception e){
            connStatusTxt.setText("El equipo no esta conectado a ninguna red");
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            setContentView(R.layout.activity_main);

            batteryTxt = findViewById(R.id.batteryLevelTxt);
            powerStatus = findViewById(R.id.powerStatusTxt);
            connStatusTxt = findViewById(R.id.connStatusTxt);

            this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            this.registerReceiver(this.plugInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            this.netWorkStatus();

    }
}