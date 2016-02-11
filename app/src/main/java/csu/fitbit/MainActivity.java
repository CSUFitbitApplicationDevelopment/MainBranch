package csu.fitbit;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int check = sharedPreferences.getInt("howWell",0);

        if (check != 0)
        {
            Intent intent = new Intent(this,MainScreen.class);
            startActivity(intent);
        }
        else if (check ==0) {

            Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);



            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , AlarmManager.INTERVAL_DAY, pendingIntent);
            setContentView(R.layout.activity_main);
        }




    }

    public void loadMainScreen(View V)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (V.getId() == R.id.answer1)
        {
            editor.putInt("howWell", 1);
        }
        else if (V.getId() == R.id.answer2)
        {
            editor.putInt("howWell", 2);
        }
        else if (V.getId() == R.id.answer3)
        {
            editor.putInt("howWell", 3);
        }
        else if (V.getId() == R.id.answer4)
        {
            editor.putInt("howWell", 4);
        }

        editor.commit();


        Intent intent = new Intent(this,MainScreen.class);
        startActivity(intent);

    }
}

