package csu.fitbit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
         public void onReceive(Context context, Intent intent) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("howWell", 0);
        editor.commit();

        Toast.makeText(context, "Data Reset", Toast.LENGTH_SHORT).show();

    }
}