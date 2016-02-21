package csu.fitbit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//Class that is shown when users press "Tell us how you are feeling right now" button
//To edit the layout of this class, update activity_how_well_now.xml
//Note: Layout of this class is different from MainActivity.java class
public class HowWellNow extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_how_well_now);

    }

    //Method that is called when users choose when of the options from "Excellent", "Good", "All Right" and "Awful"
    //Note: User responses from this class are stored in howWellNow SharedPreferences where responses from MainActivity.java are stored in howWell SharedPreferences
    public void loadMainScreen(View V)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (V.getId() == R.id.answer1)
        {
            editor.putInt("howWellNow", 1);
        }
        else if (V.getId() == R.id.answer2)
        {
            editor.putInt("howWellNow", 2);
        }
        else if (V.getId() == R.id.answer3)
        {
            editor.putInt("howWellNow", 3);
        }
        else if (V.getId() == R.id.answer4)
        {
            editor.putInt("howWellNow", 4);
        }

        editor.commit();
        Intent intent = new Intent(this,MainScreen.class);
        startActivity(intent);

    }
}
