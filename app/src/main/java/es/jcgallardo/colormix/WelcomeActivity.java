package es.jcgallardo.colormix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    ImageView paint1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // instanciación
        //paint1 = (ImageView) findViewById(R.id.paint1);
        //paint1.setColorFilter(Color.rgb(200, 0, 0));


    }
}
