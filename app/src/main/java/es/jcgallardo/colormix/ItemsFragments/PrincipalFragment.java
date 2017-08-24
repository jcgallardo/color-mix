package es.jcgallardo.colormix.ItemsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import es.jcgallardo.colormix.R;

public class PrincipalFragment extends Fragment {
    private ImageView painting_draw;
    //private ArrayList<Integer> colores_mezclados = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // creamos la vista
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        //Instanciamos objetos
        this.painting_draw = (ImageView) view.findViewById(R.id.painting_draw);
        Bundle bundle = getArguments();

        if (bundle != null && bundle.get("COLOR") != null){
            int color = (int) bundle.get("COLOR");
            //colores_mezclados.add(color);
            this.painting_draw.setColorFilter(color);
        }

        // Devolvemos vista
        return view;
    }
}