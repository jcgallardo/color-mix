package es.jcgallardo.colormix;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    TextView tv_color;
    Button b_comenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // instanciación
        tv_color = (TextView) findViewById(R.id.texto_color);
        b_comenzar = (Button) findViewById(R.id.b_comenzar);

        // crear color gradiente

        Shader myShader = new LinearGradient(
                0, 0, 800, 800,
                Color.rgb(237,88,56),
                Color.rgb(247,228,168),
                Shader.TileMode.CLAMP );

        tv_color.getPaint().setShader(myShader);
        tv_color.setGravity(Gravity.CENTER);

        b_comenzar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });

    }
}
