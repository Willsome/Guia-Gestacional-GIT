package com.scriptpoin.guiagestacional;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.dao.DaoDuvidas;

public class MenuLateralPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            DaoDuvidas daoDuvidas = new DaoDuvidas(this);
            if (daoDuvidas.pegaDuvidas(1).size() == 0) {
                daoDuvidas.insere();
            }
            daoDuvidas.close();

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao ler dados do Banco...", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lateral_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menuDuvidas) {

            DuvidasFragment duvidasFragment = new DuvidasFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, duvidasFragment).commit();

        } else if (id == R.id.menuViasDeParto) {

            ViasDePartoFragment viasDePartoFragment = new ViasDePartoFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, viasDePartoFragment).commit();

        } else if (id == R.id.menuAleitamento) {

            AleitamentoMaternoFragment aleitamentoMaternoFragment = new AleitamentoMaternoFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, aleitamentoMaternoFragment).commit();

        } else if (id == R.id.menuCaderneta) {

            CadernetaFragment cadernetaFragment = new CadernetaFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, cadernetaFragment).commit();

        } else if (id == R.id.menuSobre) {

            Intent sobreIntent = new Intent(this, SobreActivity.class);
            startActivity(sobreIntent);

        } else if (id == R.id.menuFeedback) {

            Intent emailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + "Feedback Guia Gestacional" + "&to=" + "williamucep@hotmail.com");
            emailIntent.setData(data);

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar feedback com..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MenuLateralPrincipalActivity.this, "Sem programa de e-mail instalado", Toast.LENGTH_SHORT).show();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
