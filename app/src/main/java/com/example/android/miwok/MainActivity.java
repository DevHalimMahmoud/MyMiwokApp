package com.example.android.miwok;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }


    public void numbers(View view) {
        startActivity(new Intent(this, Numbers.class));
    }

    public void family(View view) {
        startActivity(new Intent(this, family.class));
    }

    public void colors(View view) {
        startActivity(new Intent(this, colors.class));
    }

    public void phrases(View view) {
        startActivity(new Intent(this, phrases.class));
    }
}
