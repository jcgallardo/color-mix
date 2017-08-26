package es.jcgallardo.colormix.ItemsFragments;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // creamos la vista
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        //Instanciamos objetos
        this.painting_draw = (ImageView) view.findViewById(R.id.painting_draw);
        this.textColor = (TextView) view.findViewById(R.id.textColor);
        Bundle bundle = getArguments();

        if (bundle != null && bundle.get("color") != null){
            int color = (int) bundle.get("color");
            this.painting_draw.setColorFilter(color);
            this.textColor.setBackgroundColor(color);
        }

        // Devolvemos vista
        return view;
    }
}