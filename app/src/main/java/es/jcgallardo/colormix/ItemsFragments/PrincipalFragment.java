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

import java.util.HashMap;
import java.util.Map;

import es.jcgallardo.colormix.R;

public class PrincipalFragment extends Fragment {
    private TextView textColor;

    private TextView getTextColor(){
        return this.textColor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Map<Integer, ImageView> m_colors= new HashMap<Integer, ImageView>();

        // creamos la vista
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        //Instanciamos objetos
        ImageView painting_draw = (ImageView) view.findViewById(R.id.painting_draw);
        m_colors.put(1,(ImageView) view.findViewById(R.id.iv1));
        m_colors.put(2,(ImageView) view.findViewById(R.id.iv2));
        m_colors.put(3,(ImageView) view.findViewById(R.id.iv3));
        m_colors.put(4,(ImageView) view.findViewById(R.id.iv4));
        m_colors.put(5,(ImageView) view.findViewById(R.id.iv5));

        this.textColor = (TextView) view.findViewById(R.id.textColor);
        Bundle bundle = getArguments();


        //  Necesitamos que exista Bundle y que contenga "color"
        if (bundle != null && bundle.get("color") != null){
            int color = (int) bundle.get("color");

            // animamos el cambio de color
            final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(painting_draw,
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

            // añadimos todos los colores
            String s_colores = (String) bundle.get("allColors");
            String [] colores = s_colores.split("\\|");

            for (int i=0; i<colores.length; i++){
                m_colors.get(i+1).setColorFilter(Integer.parseInt(colores[i]));
            }
        }

        // Devolvemos vista
        return view;
    }
}