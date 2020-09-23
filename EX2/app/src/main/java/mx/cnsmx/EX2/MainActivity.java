package mx.cnsmx.EX2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        final Toast myToast = new Toast(context);

        myToast.makeText(context, "Not tipo Toast", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder dialogConf = new AlertDialog.Builder(this);
        dialogConf.setTitle("Dialogo");
        dialogConf.setMessage("Confirmacion ?");

        dialogConf.setNeutralButton("Cerrar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myToast.makeText(context, "Mensaje de cancelacion", Toast.LENGTH_SHORT).show();
                    }
                });

        dialogConf.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myToast.makeText(context, "Confirmacion", Toast.LENGTH_SHORT).show();
                        // Do whatever you want here
                    }
                });

        dialogConf.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myToast.makeText(context, "Negacion", Toast.LENGTH_SHORT).show();
                        // Do whatever you want here
                    }
                });

        AlertDialog myDialog = dialogConf.create();
        myDialog.show();

        int NOTIF_ID = 1234;
        Notification.Builder NotifBuilder = new Notification.Builder(this);
        NotifBuilder.setSmallIcon(R.mipmap.ic_launcher);
        NotifBuilder.setContentTitle("Push Notification");
        NotifBuilder.setContentText("Datos de notificacion o descripcion");

        Intent notificationIntent = new Intent(context, ChildActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        NotifBuilder.setContentIntent(contentIntent);

        NotificationManager MyNotification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        MyNotification.notify(NOTIF_ID, NotifBuilder.build());

     }
}