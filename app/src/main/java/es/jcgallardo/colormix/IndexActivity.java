package es.jcgallardo.colormix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IndexActivity extends Activity {
    private static int TIME_OUT = 1000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // Creamos una hebra la cual ejecutará el método run después del TIMEOUT estblecido
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(Welcome);
                finish();
            }
        }, TIME_OUT);
    }
}
