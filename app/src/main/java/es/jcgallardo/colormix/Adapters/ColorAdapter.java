package es.jcgallardo.colormix.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import es.jcgallardo.colormix.MainActivity;
import es.jcgallardo.colormix.R;
import es.jcgallardo.colormix.models.MiColor;

/**
 * Created by jcgallardo on 26/08/2017.
 */

public class ColorAdapter extends BaseAdapter {
    // Contexto de la aplicación
    private Context context;
    private MiColor[] colores;


    public ColorAdapter(Context c){
        this.context = c;
        colores = ((MainActivity)context).getColores();
    }

    @Override
    public int getCount() {
        return colores.length;
    }

    @Override
    public Object getItem(int i) {
        return colores[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ImageView a retornar
        ImageView imageView;

        if (convertView == null) {
            /*
            Crear un nuevo Image View de 150x150
             */
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(150,150));
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(R.drawable.paint);
        imageView.setColorFilter(colores[position].getColor());

        return imageView;
    }
}
