package es.jcgallardo.colormix;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

import es.jcgallardo.colormix.ItemsFragments.PaletaFragment;
import es.jcgallardo.colormix.ItemsFragments.PrincipalFragment;
import es.jcgallardo.colormix.models.MiColor;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    ArrayList<Integer> lista_colores;
    HashMap<Integer,MiColor> colores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        lista_colores = new ArrayList<>();
        colores = new HashMap<Integer, MiColor>();

        // Seleccionamos el principal para que se pulse por defecto
        navigation.setSelectedItemId(R.id.navigation_principal);

        this.addFragment();
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

                // nos creamos el color y lo añadimos tanto a la lista como al Bundle de PrincipalFragment
                int color = Color.rgb(255,255,0);
                lista_colores.add(color);
                System.out.println(lista_colores.toString());
                Bundle bundle = new Bundle();
                bundle.putInt("COLOR",color);
                fragment.setArguments(bundle);

                break;
        }

        // Reemplazamos el fragment
        if (fragment != null)
            replaceFragment(fragment);

        return true;
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
