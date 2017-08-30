package es.jcgallardo.colormix;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import es.jcgallardo.colormix.ItemsFragments.PaletaFragment;
import es.jcgallardo.colormix.ItemsFragments.PrincipalFragment;
import es.jcgallardo.colormix.models.MiColor;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    ArrayList<MiColor> colores_seleccionados = new ArrayList<>();
    private MiColor[] colores;
    private Bundle miBundle;
    private NameFragment fr_old;
    private NameFragment fr_new;

    public MiColor[] getColores(){
        return colores;
    }

    public void addColor(int position){
        // si no existe lo añadimos, si existe mostramos un TOAST de error.
        if (colores_seleccionados.indexOf(colores[position]) < 0) {
            int seleccionados = colores_seleccionados.size();

            // Guardamos el viejo valor del color o ponemos por defecto blanco para hacer la animación de colores
            if (!colores_seleccionados.isEmpty())
                miBundle.putInt("oldColor",colores_seleccionados.get(colores_seleccionados.size()-1).getColor());
            else
                miBundle.putInt("oldColor", Color.rgb(255,255,255));

            colores_seleccionados.add(colores[position]);
            int seleccionados_new = colores_seleccionados.size();

            if (seleccionados_new > seleccionados) {
                // nos creamos el color y lo añadimos tanto a la lista como al Bundle de PrincipalFragment
                miBundle.putBoolean("modificado", true);
                miBundle.putString("lastColorLabel",colores[position].getLabelColor());
                miBundle.putInt("lastColorColor",colores[position].getColor());
                miBundle.putInt("lastColorText",colores[position].getColorTextColor());
                // mandamos todos los colores
                miBundle.putString("allColors",getStringSelectedColors());
            }


            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setSelectedItemId(R.id.navigation_principal);
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            LayoutInflater factory = LayoutInflater.from(this);
            final View view = factory.inflate(R.layout.alert, null);
            ((TextView)view.findViewById(R.id.textoAlert)).setText("El color que has seleccionado ya está repetido");
            builder1.setView(view);
            builder1.setPositiveButton("¡Entendido!", null);

            builder1.show();
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
        this.fr_old = null;
        this.fr_new = NameFragment.PRINCIPAL;

        this.addFragment();
    }

    private void changeAnimation(NameFragment frag){
        this.fr_old = this.fr_new;
        this.fr_new = frag;
    }

    /**
     * Método que añade todos los colores que se manejarán en la aplicación
     * @return colores como Array
     */
    private MiColor[] inflateColors() {
        int grey = Color.rgb(50,50,50);
        int white = Color.rgb(255,255,255);

        return new MiColor[]{
                new MiColor(255,0,0,white, "rojo"),
                new MiColor(255,255,0,grey, "amarillo"),
                new MiColor(0,255,0,grey, "verde"),
                new MiColor(0,0,255,white, "azul cyan"),
                new MiColor(255,164,32,white, "naranja"),
                new MiColor(141,73,37,white, "marrrón"),
                new MiColor(163,73,164,white, "morado"),
                new MiColor(231,161,255,grey, "lila"),
                new MiColor(0,0,0,white, "negro"),
                new MiColor(96,201,246,grey, "azul claro"),
                new MiColor(230,214,64,grey, "dorado"),
                new MiColor(130,130,130,white, "gris"),
                new MiColor(0,128,128,white, "teal"),
                new MiColor(255,255,255, grey, "blanco"),
                new MiColor(252,255,144,grey, "amarillo claro"),
                new MiColor(53,209,95,grey, "verde claro"),
                new MiColor(185,25,20,white, "rojo oscuro")

       };
    }

    private String getStringSelectedColors(){
        String cadena_parseada = "";
        for (MiColor c : colores_seleccionados){
            cadena_parseada += c.getColor() + "|";
        }

        cadena_parseada = cadena_parseada.substring(0,cadena_parseada.length()-1);

        return cadena_parseada;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Inicializamos el framento a null
        Fragment fragment = null;


        // Inicializamos el fragmento en función del item seleccionado
        switch (item.getItemId()) {
            case R.id.navigation_borrar:
                // restaurar ocolor
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Comenzar de nuevo")
                        .setMessage("¿Estás seguro de que deseas eliminar todos los colores seleccionados y comenzar la mezcla desde el principio?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                miBundle.clear();
                                colores_seleccionados.clear();

                                Fragment fragment = new PrincipalFragment();
                                fragment.setArguments(miBundle);
                                replaceFragment(fragment);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                        return false;
            case R.id.navigation_principal:
                fragment = new PrincipalFragment();

                if (miBundle != null && miBundle.get("modificado") != null && (Boolean)miBundle.get("modificado") == true ) {
                    miBundle.putInt("color", mix());
                    fragment.setArguments(miBundle);
                }

                // actualizamos el nombre del fragmento seleccionado
                changeAnimation(NameFragment.PRINCIPAL);

                break;
            case R.id.navigation_paleta:
                if (colores_seleccionados.size() < 5) {
                    fragment = new PaletaFragment();

                    // actualizamos el nombre del fragmento a Paleta
                    changeAnimation(NameFragment.PALETA);
                    break;
                }else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    LayoutInflater factory = LayoutInflater.from(this);
                    final View view = factory.inflate(R.layout.alert, null);
                    ((TextView)view.findViewById(R.id.textoAlert)).setText("Has alcanzado el máximo número de colores, borra para volver a comenzar");
                    builder1.setView(view);
                    builder1.setPositiveButton("¡Entendido!", null);

                    builder1.show();

                    return false;
                }
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

    private int[] getAnimations(){

        if(fr_old == NameFragment.PRINCIPAL && fr_new == NameFragment.PALETA)
            return new int[]{android.R.anim.fade_in, android.R.anim.fade_out};
        else if(fr_old == NameFragment.PALETA && fr_new == NameFragment.PRINCIPAL)
            return new int[]{android.R.anim.fade_in, android.R.anim.fade_out};

        return null;

    }

    private void addFragment() {
        FragmentTransaction transation_fragment = getSupportFragmentManager().beginTransaction();
        transation_fragment.add(R.id.placeholder_fragment, new PrincipalFragment());
        transation_fragment.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transation_fragment = getSupportFragmentManager().beginTransaction();

        int animations[] = getAnimations();
        if (animations != null) {
            transation_fragment.setCustomAnimations(animations[0], animations[1]);
        }
        transation_fragment.replace(R.id.placeholder_fragment, fragment);
        transation_fragment.commit();
    }
}
