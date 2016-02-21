package csu.fitbit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;


//Class which handles most of the functions of the app
//To edit the layout of this class, update activity_mainscreen.xml
public class MainScreen extends AppCompatActivity implements GestureDetector.OnGestureListener {

    Timer timer;
    TimerTask timerTask;

    TextView value;
    TextView category;

    ImageView firstImage;
    ImageView secondImage;

    Button Button1;
    Button Button2;
    Button Button3;
    Button left;
    Button right;
    LinearLayout background;

    int bpm = 0;
    int steps = 0;
    int calories = 0;

    //To add more category (e.g. Sleep) change max value to needed
    int current = 1;
    int max = 3;

    private GestureDetector gestureScanner;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestureScanner = new GestureDetector(this);

        setContentView(R.layout.activity_mainscreen);
        category = (TextView) findViewById(R.id.category);
        value = (TextView) findViewById(R.id.value);


        firstImage = (ImageView) findViewById(R.id.firstImage);
        secondImage = (ImageView) findViewById(R.id.secondImage);

        background = (LinearLayout) findViewById(R.id.background);

        Button1 = (Button) findViewById(R.id.Button1);
        Button2 = (Button) findViewById(R.id.Button2);
        Button3 = (Button) findViewById(R.id.Button3);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
    }

    //Set the activity title bar to the layout of item.xml (can be found under .../res/menu/item.xml)
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
        return true;

    }

    //Method that is called when user press "settings" button
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this,"Settings Button Pressed", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //onResume, we start our timer so it can start when the app comes from the background
    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    //onPause, we pause our timer so it won't take up the resources when the app is in the background
    protected void onPause() {
        super.onPause();
        stoptimertask();
    }

    //start/restart Timer
    public void startTimer() {

        timer = new Timer();

        initializeTimerTask();
        //schedule the timer, after the first 0ms (0sec) the TimerTask will run every 1000ms (1 sec)
        timer.schedule(timerTask, 0, 1000);
    }

    //stop/pause Timer
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    //initialize the Timer's job
    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                handler.post(new Runnable() {
                    public void run() {

                        bpm += 1;
                        steps+=2;
                        calories+=3;

                        if (current == 1)
                        {
                            value.setText(bpm + " Pulses");
                        }
                        else if (current == 2)
                        {
                            value.setText(steps + " Steps");
                        }
                        else if (current == 3)
                        {
                            value.setText(calories + " Calories");
                        }
                    }
                });
            }
        };
    }

    //Method that is called when user press "Tell us how you are feeling right now" button
    public void loadMainActivity(View V)
    {
        Intent intent = new Intent(this,HowWellNow.class);
        startActivity(intent);
    }

    //Method that is called when user press "Tell us a bit about today" button
    public void loadSurvey (View V)
    {

        Intent intent = new Intent(this,Survey.class);
        startActivity(intent);

    }

    //Method that is called when user press ">" (Right) button
    public void rightExecute (View V)
    {
        if(current == max)
        {
            current = 1;
        }
        else
        {
            current++;
        }

        if(current == 1)
        {
            setHeartRate();
        }
        else if (current == 2)
        {
            setSteps();

        }
        else if (current == 3)
        {
            setCalories();
        }
    }
    //Method that is called when user swipe from right to left (equals to right button)
    public void rightExecuteTouch ()
    {
        if(current == max)
        {
            current = 1;
        }
        else
        {
            current++;
        }

        if(current == 1)
        {
            setHeartRate();
        }
        else if (current == 2)
        {
            setSteps();

        }
        else if (current == 3)
        {
            setCalories();
        }
    }

    //Method that is called when user press "<" (Left) button
    public void leftExecute (View V)
    {
        if(current == 1)
        {
            current = max;
        }
        else
        {
            current--;
        }

        if(current == 1)
        {
            setHeartRate();
        }
        else if (current == 2)
        {
            setSteps();
        }
        else if (current == 3)
        {
            setCalories();
        }
    }
    //Method that is called when user swipe from left to right (equals to left button)
    public void leftExecuteTouch ()
    {
        if(current == 1)
        {
            current = max;
        }
        else
        {
            current--;
        }

        if(current == 1)
        {
            setHeartRate();
        }
        else if (current == 2)
        {
            setSteps();
        }
        else if (current == 3)
        {
            setCalories();
        }
    }

    //This method is currently not defined in xml. Cut and Paste it where syncData function is gonna be called from
    public void syncData (View V)
    {
        //It is implemented in a way that howWell data would be reset after every manual sync. To remove this feature, delete all the SharedPreferences code from below (4 lines)
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("howWell", 0);
        editor.commit();
        Toast.makeText(this,"Syncing Data. Please Wait", Toast.LENGTH_LONG).show();
    }

    //Method that is called when user press "My Health Feedback" button
    public void loadFeedback (View V)
    {
        Intent intent = new Intent(this,Feedback.class);
        startActivity(intent);
    }


    //Method which sets the category, images and unit to Heart Rate data
    public void setHeartRate () {
        category.setText("Heart Rate");
        firstImage.setImageResource(R.drawable.heart);
        secondImage.setImageResource(R.drawable.heartrate);
        value.setText(bpm + " Pulses");
        value.setTextColor(Color.parseColor("#ff64bc"));
        category.setTextColor(Color.parseColor("#ff64bc"));

        //uncomment below lines to change button color with category change

        //background.setBackgroundColor(Color.parseColor("#ffa9da"));

        //Button1.setBackgroundResource(R.drawable.mybuttone);
        //Button2.setBackgroundResource(R.drawable.mybuttone);
        //Button3.setBackgroundResource(R.drawable.mybuttone);
        //left.setBackgroundResource(R.drawable.mybuttone);
        //right.setBackgroundResource(R.drawable.mybuttone);

        //Button1.setTextColor(Color.parseColor("#000000"));
        //Button2.setTextColor(Color.parseColor("#000000"));
        //Button3.setTextColor(Color.parseColor("#000000"));
        //left.setTextColor(Color.parseColor("#000000"));
        //right.setTextColor(Color.parseColor("#000000"));
    }

    //Method which sets the category, images and unit to Step Count data
    public void setSteps () {
        category.setText("Step Count");
        firstImage.setImageResource(R.drawable.run);
        secondImage.setImageResource(R.drawable.stepcount);
        value.setText(steps + " Steps");
        value.setTextColor(Color.parseColor("#ffffff"));
        category.setTextColor(Color.parseColor("#ffffff"));

        //uncomment below lines to change button color with category change

        //background.setBackgroundColor(Color.parseColor("#95ffb3"));

        //Button1.setBackgroundResource(R.drawable.mybuttong);
        //Button2.setBackgroundResource(R.drawable.mybuttong);
        //Button3.setBackgroundResource(R.drawable.mybuttong);
        //left.setBackgroundResource(R.drawable.mybuttong);
        //right.setBackgroundResource(R.drawable.mybuttong);

        //Button1.setTextColor(Color.parseColor("#ffffff"));
        //Button2.setTextColor(Color.parseColor("#ffffff"));
        //Button3.setTextColor(Color.parseColor("#ffffff"));
        //left.setTextColor(Color.parseColor("#ffffff"));
        //right.setTextColor(Color.parseColor("#ffffff"));
    }

    //Method which sets the category, images and unit to Calories Burned data
    public void setCalories () {
        category.setText("Calories Burned");
        firstImage.setImageResource(R.drawable.food);
        secondImage.setImageResource(R.drawable.calories);
        value.setText(calories + " Calories");
        value.setTextColor(Color.parseColor("#ff5703"));
        category.setTextColor(Color.parseColor("#ff5703"));

        //uncomment below lines to change button color with category change

        //background.setBackgroundColor(Color.parseColor("#f4ff90"));

        //Button1.setBackgroundResource(R.drawable.mybuttonh);
        //Button2.setBackgroundResource(R.drawable.mybuttonh);
        //Button3.setBackgroundResource(R.drawable.mybuttonh);
        //left.setBackgroundResource(R.drawable.mybuttonh);
        //right.setBackgroundResource(R.drawable.mybuttonh);

        //Button1.setTextColor(Color.parseColor("#000000"));
        //Button2.setTextColor(Color.parseColor("#000000"));
        //Button3.setTextColor(Color.parseColor("#000000"));
        //left.setTextColor(Color.parseColor("#000000"));
        //right.setTextColor(Color.parseColor("#000000"));
    }

    //Method which is called when user touches the screen
    @Override
    public boolean onTouchEvent(MotionEvent me)

    {
        return gestureScanner.onTouchEvent(me);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    //Method which is called when user swipes screen
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        //When user swipes from left to right
        if (velocityX > 0) {

            leftExecuteTouch();
        }
        //When user swipes from right to left
        else if (velocityX <0)
        {
            rightExecuteTouch();
        }
        return true;
    }
}



