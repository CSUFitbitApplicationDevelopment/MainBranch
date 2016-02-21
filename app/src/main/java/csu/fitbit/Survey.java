package csu.fitbit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

//Class that is shown when users press "Tell us a bit about today" button
//To edit the layout of this class, update activity_survey.xml
public class Survey extends AppCompatActivity {

    SeekBar seekbar1;
    SeekBar seekbar2;
    SeekBar seekbar3;
    SeekBar seekbar4;
    SeekBar seekbar5;
    SeekBar seekbar6;
    SeekBar seekbar7;
    SeekBar seekbar8;
    SeekBar seekbar9;
    SeekBar seekbar10;

     protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_survey);

         //display back button on activity title bar
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         seekbar1= (SeekBar) findViewById(R.id.seekBar1);
         seekbar2= (SeekBar) findViewById(R.id.seekBar2);
         seekbar3= (SeekBar) findViewById(R.id.seekBar3);
         seekbar4= (SeekBar) findViewById(R.id.seekBar4);
         seekbar5= (SeekBar) findViewById(R.id.seekBar5);
         seekbar6= (SeekBar) findViewById(R.id.seekBar6);
         seekbar7= (SeekBar) findViewById(R.id.seekBar7);
         seekbar8= (SeekBar) findViewById(R.id.seekBar8);
         seekbar9= (SeekBar) findViewById(R.id.seekBar9);
         seekbar10= (SeekBar) findViewById(R.id.seekBar10);
     }

    //Method that is called when user press "Submit" button
    public void submit (View V) {

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int one = seekbar1.getProgress();
        int two = seekbar2.getProgress();
        int three = seekbar3.getProgress();
        int four = seekbar4.getProgress();
        int five = seekbar5.getProgress();
        int six = seekbar6.getProgress();
        int seven = seekbar7.getProgress();
        int eight = seekbar8.getProgress();
        int nine = seekbar9.getProgress();
        int ten = seekbar10.getProgress();
        String surveyData = one + ", " + two + ", " + three + ", " + four+ ", " + five+ ", " + six+ ", " + seven+ ", " + eight+ ", " + nine+ ", " + ten;
        editor.putString("surveyData", surveyData);
        editor.commit();
        Toast.makeText(this, "Data Submitted", Toast.LENGTH_LONG).show();
        finish();
    }

    //Method that is called when user press "back" button
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

