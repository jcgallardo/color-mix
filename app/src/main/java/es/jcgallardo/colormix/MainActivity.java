package es.jcgallardo.colormix;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

import es.jcgallardo.colormix.ItemsFragments.PaletaFragment;
import es.jcgallardo.colormix.ItemsFragments.PrincipalFragment;
import es.jcgallardo.colormix.models.MiColor;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    ArrayList<MiColor> colores_seleccionados = new ArrayList<>();
    private MiColor[] colores;
    private Bundle miBundle;

    public MiColor[] getColores(){
        return colores;
    }

    public void addColor(int position){
        // si no existe lo añadimos, si existe mostramos un TOAST de error.
        if (colores_seleccionados.indexOf(colores[position]) < 0) {
            int seleccionados = colores_seleccionados.size();
            colores_seleccionados.add(colores[position]);
            int seleccionados_new = colores_seleccionados.size();

            if (seleccionados_new > seleccionados) {
                // nos creamos el color y lo añadimos tanto a la lista como al Bundle de PrincipalFragment
                miBundle.putBoolean("modificado", true);
            }


            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setSelectedItemId(R.id.navigation_principal);
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("¡COLOR REPETIDO! \nPor favor selecciona otro de la paleta");
            builder1.setIcon(R.drawable.paint);
            builder1.setCancelable(true);
            AlertDialog alert = builder1.create();
            alert.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        this.miBundle = new Bundle();
        navigation.setOnNavigationItemSelectedListener(this);
        colores = inflateColors();

        // Seleccionamos el principal para que se pulse por defecto
        navigation.setSelectedItemId(R.id.navigation_principal);

        this.addFragment();
    }

    /**
     * Método que añade todos los colores que se manejarán en la aplicación
     * @return colores como Array
     */
    private MiColor[] inflateColors() {
        return new MiColor[]{
           new MiColor(255, 0, 0, R.color.black, "rojo"),
           new MiColor(255,255,0,R.color.black, "amarillo"),
           new MiColor(0,255,0, R.color.black, "verde"),
           new MiColor(0,0,255,R.color.black, "azul"),
           new MiColor(255, 164, 32, R.color.white, "naranja")
       };
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Inicializamos el framento a null
        Fragment fragment = null;


        // Inicializamos el fragmento en función del item seleccionado
        switch (item.getItemId()) {
            case R.id.navigation_borrar:
                // restaurar ocolor
                break;
            case R.id.navigation_paleta:
                fragment = new PaletaFragment();
                break;
            case R.id.navigation_principal:
                fragment = new PrincipalFragment();

                if (miBundle != null && miBundle.get("modificado") != null && (Boolean)miBundle.get("modificado") == true ) {
                    miBundle.putInt("color", mix());
                    fragment.setArguments(miBundle);
                }

                break;
        }

        // Reemplazamos el fragment
        if (fragment != null)
            replaceFragment(fragment);

        return true;
    }

    private int mix(){
        int r = 0, g = 0, b = 0, alpha = 0;

        for (MiColor c : colores_seleccionados){
            r += c.getR();
            g += c.getG();
            b += c.getB();
            alpha += c.getAlpha();
        }

        r       /= colores_seleccionados.size();
        g       /= colores_seleccionados.size();
        b       /= colores_seleccionados.size();
        alpha   /= colores_seleccionados.size();

        return Color.argb(alpha,r, g, b);
    }

    private void addFragment() {
        FragmentTransaction transation_fragment = getSupportFragmentManager().beginTransaction();
        transation_fragment.add(R.id.placeholder_fragment, new PrincipalFragment());
        transation_fragment.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transation_fragment = getSupportFragmentManager().beginTransaction();
        transation_fragment.replace(R.id.placeholder_fragment, fragment);
        transation_fragment.commit();
    }
}
