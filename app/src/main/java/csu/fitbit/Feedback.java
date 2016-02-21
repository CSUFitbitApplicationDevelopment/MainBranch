package csu.fitbit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


//Class that is shown when users press "My Health Feedback" button
//To edit the layout of this class, update activity_feedback.xml
public class Feedback extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        //display back button on activity title bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
