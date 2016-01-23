package csu.fitbit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

