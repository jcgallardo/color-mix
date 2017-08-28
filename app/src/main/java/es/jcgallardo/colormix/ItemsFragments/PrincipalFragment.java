package es.jcgallardo.colormix.ItemsFragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.jcgallardo.colormix.R;

public class PrincipalFragment extends Fragment {
    private ImageView painting_draw;
    private TextView textColor;

    private TextView getTextColor(){
        return this.textColor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // creamos la vista
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        //Instanciamos objetos
        this.painting_draw = (ImageView) view.findViewById(R.id.painting_draw);
        this.textColor = (TextView) view.findViewById(R.id.textColor);
        Bundle bundle = getArguments();


        //  Necesitamos que exista Bundle y que contenga "color"
        if (bundle != null && bundle.get("color") != null){
            int color = (int) bundle.get("color");

            // animamos el cambio de color
            final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(this.painting_draw,
                    "colorFilter",
                    new ArgbEvaluator(),
                    (int) bundle.get("oldColor"),
                    color);
            backgroundColorAnimator.setDuration(1000);
            backgroundColorAnimator.start();

            // cambiamos el texto del color seleccionado
            this.textColor.setText("Último seleccionado: " + bundle.get("lastColorLabel"));
            this.textColor.setTextColor(((int)bundle.get("lastColorText")));
            this.textColor.setBackgroundColor((int) bundle.get("lastColorColor"));
        }

        // Devolvemos vista
        return view;
    }
}