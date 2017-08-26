package es.jcgallardo.colormix.ItemsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import es.jcgallardo.colormix.Adapters.ColorAdapter;
import es.jcgallardo.colormix.MainActivity;
import es.jcgallardo.colormix.R;

public class PaletaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paleta, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.contendor_colores);
        gridview.setAdapter(new ColorAdapter(getActivity()));

        /*
        Creando una nueva escucha para los elementos del Grid
         */
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ((MainActivity) getActivity()).addColor(position);
            }
        });

        return view;
    }
}