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


public class HowWellNow extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_how_well_now);

    }

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
