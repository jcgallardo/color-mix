package es.jcgallardo.colormix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // Paramos la ejecución 5s
        //SystemClock.sleep(5000);

        //Una vez despierto cambiamos a la pantalla de bienvenida
        Intent Welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
        //startActivity(Welcome);

    }
}
