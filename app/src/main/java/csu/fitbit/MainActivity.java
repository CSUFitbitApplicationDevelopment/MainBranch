package csu.fitbit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Calendar;

//Class that is shown when users open the app for the first time. This page is reshown to the user after howWell SharedPreference has been reset everyday at 12A.M
//To edit the layout of this class, update activity_main.xml
//Note: Layout of this class is different from HowWellNow.java class
public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        int check = sharedPreferences.getInt("howWell",0);

        //Check if howWell SharedPreference is 0. If 0, set the alarm to reset howWell at 12A.M everyday. If not 0, skip to MainScreen.java
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

    //Method that is called when users choose when of the options from "Excellent", "Good", "All Right" and "Awful"
    //Note: User responses from this class are stored in howWell SharedPreferences where responses from HowWellNow.java are stored in howWellNow SharedPreferences
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

