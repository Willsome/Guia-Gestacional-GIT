package com.scriptpoin.gestacaosaudavel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.gestacaosaudavel.dao.DaoDuvidas;
import com.scriptpoin.gestacaosaudavel.web.login.LoginActivity;
import com.scriptpoin.gestacaosaudavel.web.login.shared_preferences.LoginSharedPrefManager;
import com.scriptpoin.gestacaosaudavel.web.server.DadosServidorSender;

public class MenuLateralPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral_principal);

//        if (!LoginSharedPrefManager.getInstance(this).isUsuarioLogado()) {
//            finish();
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//        }

        View menuView = ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0);
//        TextView tvNomeUsuario = menuView.findViewById(R.id.tvNomeUsuario);
//        tvNomeUsuario.setText(LoginSharedPrefManager.getInstance(this).getUsuario());

        try {
            DaoDuvidas daoDuvidas = new DaoDuvidas(this);
            if (daoDuvidas.pegaDuvidas(1).size() == 0) {
                daoDuvidas.insere();
            }
            daoDuvidas.close();

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao ler dados do Banco...", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            Log.d("LOKOLO", e.getMessage());
//            Log.d("LOKOLO", e.getCause().toString());
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {

                        Fragment fragmentAtual =
                                (MenuLateralPrincipalActivity.this)
                                        .getSupportFragmentManager()
                                        .findFragmentById(R.id.mainLayout);

                        if (fragmentAtual instanceof DuvidasDoPreNatalFragment) {
                            navigationView.getMenu().getItem(0).setChecked(true);
                        } else if (fragmentAtual instanceof OrientacoesSobreOPartoFragment) {
                            navigationView.getMenu().getItem(1).setChecked(true);
                        } else if (fragmentAtual instanceof AleitamentoMaternoFragment) {
                            navigationView.getMenu().getItem(2).setChecked(true);
                        } else if (fragmentAtual instanceof ResguardoFragment) {
                            navigationView.getMenu().getItem(3).setChecked(true);
                        } else if (fragmentAtual instanceof DataDasConsultasFragment) {
                            navigationView.getMenu().getItem(4).setChecked(true);
                        } else if (fragmentAtual instanceof CadernetaFragment) {
                            navigationView.getMenu().getItem(5).setChecked(true);
                        } else {
                            for (int i = 0; i < navigationView.getMenu().size(); i++) {
                                navigationView.getMenu().getItem(i).setChecked(false);
                            }
                        }

                    }
                });

        Fragment frag = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
//                .addToBackStack(null)
                .replace(R.id.mainLayout, frag)
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();
            setTitle("Gestação Saudável");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_lateral_principal, menu);
//        getMenuInflater().inflate(R.menu.menu_enviar_dados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.logout:
                LoginSharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(
                        getApplicationContext(),
                        LoginActivity.class
                ));
                break;
            case R.id.mnEnviarDados:
                int usuario_id = LoginSharedPrefManager.getInstance(this).getId();
                new DadosServidorSender(this, usuario_id).execute();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragmentAtual =
                (MenuLateralPrincipalActivity.this)
                        .getSupportFragmentManager()
                        .findFragmentById(R.id.mainLayout);

        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        int id = item.getItemId();

        if (id == R.id.menuDuvidasDoPreNatal) {

            DuvidasDoPreNatalFragment duvidasDoPreNatalFragment = new DuvidasDoPreNatalFragment();
            FragmentManager manager = getSupportFragmentManager();

            if (!(fragmentAtual instanceof DuvidasDoPreNatalFragment)) {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, duvidasDoPreNatalFragment)
                        .commit();
            } else {
                manager.beginTransaction()
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, duvidasDoPreNatalFragment)
                        .commit();
            }

        } else if (id == R.id.menuOrientacoesSobreOParto) {

            OrientacoesSobreOPartoFragment orientacoesSobreOPartoFragment = new OrientacoesSobreOPartoFragment();
            FragmentManager manager = getSupportFragmentManager();

            if (!(fragmentAtual instanceof OrientacoesSobreOPartoFragment)) {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, orientacoesSobreOPartoFragment)
                        .commit();
            } else {
                manager.beginTransaction()
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, orientacoesSobreOPartoFragment)
                        .commit();
            }

        } else if (id == R.id.menuAleitamento) {

            AleitamentoMaternoFragment aleitamentoMaternoFragment = new AleitamentoMaternoFragment();
            FragmentManager manager = getSupportFragmentManager();

            if (!(fragmentAtual instanceof AleitamentoMaternoFragment)) {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, aleitamentoMaternoFragment)
                        .commit();
            } else {
                manager.beginTransaction()
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, aleitamentoMaternoFragment)
                        .commit();
            }

        } else if (id == R.id.menuPuerperio) {

            ResguardoFragment resguardoFragment = new ResguardoFragment();
            FragmentManager manager = getSupportFragmentManager();

            if (!(fragmentAtual instanceof ResguardoFragment)) {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, resguardoFragment)
                        .commit();
            } else {
                manager.beginTransaction()
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, resguardoFragment)
                        .commit();
            }

        } else if (id == R.id.menuDataConsultas) {

            DataDasConsultasFragment dataDasConsultasFragment = new DataDasConsultasFragment();
            FragmentManager manager = getSupportFragmentManager();

            if (!(fragmentAtual instanceof DataDasConsultasFragment)) {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, dataDasConsultasFragment)
                        .commit();
            } else {
                manager.beginTransaction()
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, dataDasConsultasFragment)
                        .commit();
            }

        } else if (id == R.id.menuCaderneta) {

            CadernetaFragment cadernetaFragment = new CadernetaFragment();
            FragmentManager manager = getSupportFragmentManager();

            if (!(fragmentAtual instanceof CadernetaFragment)) {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, cadernetaFragment)
                        .commit();
            } else {
                manager.beginTransaction()
                        .add(new HomeFragment(), "HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.mainLayout, cadernetaFragment)
                        .commit();
            }

        } else if (id == R.id.menuSobre) {

            Intent sobreIntent = new Intent(this, SobreActivity.class);
            startActivity(sobreIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        } else if (id == R.id.menuFeedback) {

            Intent emailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse(
                    "mailto:" +
                    "?subject=Feedback Gestação Saudável" +
                    "&to=enfermarta2001@yahoo.com.br");
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
