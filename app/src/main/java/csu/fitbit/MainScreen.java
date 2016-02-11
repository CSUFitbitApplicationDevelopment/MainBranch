package csu.fitbit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainScreen extends AppCompatActivity {
    Timer timer;
    TimerTask timerTask;
    //TextView heartRate;
    int bpm = 0;
    ViewPager viewPager = null;

    int test = 0;


    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        //heartRate = (TextView) findViewById(R.id.textView3);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(100, 0, 100, 0);
        viewPager.setPageMargin(20);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(500);
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();

        //onResume we start our timer so it can start when the app comes from the background
        startTimer();
    }

    protected void onPause() {
        super.onPause();
        stoptimertask();
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 1000ms (1sec) the TimerTask will run every 10000ms (10 sec)
        timer.schedule(timerTask, 0, 1000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp

                        bpm += 1;
                        respond(bpm+"");
                        //respond(bpm+"");
                        //heartRate.setText(bpm + "");

                        //Calendar calendar = Calendar.getInstance();
                        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                        //final String strDate = simpleDateFormat.format(calendar.getTime());

                        //show the toast
                        //int duration = Toast.LENGTH_SHORT;
                        //Toast toast = Toast.makeText(getApplicationContext(), strDate, duration);
                        //toast.show();
                    }
                });
            }
        };
    }

    public void loadMainActivity(View V)
    {
        Intent intent = new Intent(this,HowWellNow.class);
        startActivity(intent);
    }

    public void loadSurvey (View V)
    {

        Intent intent = new Intent(this,Survey.class);
        startActivity(intent);
    }

    public void syncData (View V)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("howWell", 0);
        editor.commit();
        Toast.makeText(this,"Syncing Data. Please Wait", Toast.LENGTH_LONG).show();
    }

    public void loadFeedback (View V)
    {
        Intent intent = new Intent(this,Feedback.class);
        startActivity(intent);
    }

    public void respond(String data) {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TextView view = (TextView) viewPager.findViewById(R.id.oxygenlevel);
        view.setText(""+data);
    }
}


class MyAdapter extends FragmentStatePagerAdapter {
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        if (i%3==0)
        {
            fragment = new OxygenLevelFragment();
        }

        if (i%3 ==1)
        {
            fragment = new HeartRateFragment();
        }

        if (i%3 ==2)
        {
            fragment = new StepsCountFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {

        return 1000;
    }
}

