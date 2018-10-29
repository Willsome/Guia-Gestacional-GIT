package com.scriptpoin.gestacaosaudavel.web.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.scriptpoin.gestacaosaudavel.MenuLateralPrincipalActivity;
import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.filter.FiltraInput;
import com.scriptpoin.gestacaosaudavel.web.Constants;
import com.scriptpoin.gestacaosaudavel.web.login.handler.RequestHandler;
import com.scriptpoin.gestacaosaudavel.web.login.shared_preferences.LoginSharedPrefManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etLoginUsuario, etLoginSenha;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // VERIFICA SE O USUÁRIO ESTÁ LOGADO
        if (LoginSharedPrefManager.getInstance(this).isUsuarioLogado()) {
            finish();
            startActivity(new Intent(getApplicationContext(), MenuLateralPrincipalActivity.class));
        }

        etLoginUsuario = findViewById(R.id.etLoginUsuario);
        etLoginSenha = findViewById(R.id.etLoginSenha);

        etLoginUsuario.setFilters(new InputFilter[]{FiltraInput.filtroDoInput()});
        etLoginSenha.setFilters(new InputFilter[]{FiltraInput.filtroDoInput()});

        Button btLogar = findViewById(R.id.btLogar);
        btLogar.setOnClickListener(this);

        TextView tvLoginRegistrar = (findViewById(R.id.tvLoginRegistrar));
        tvLoginRegistrar.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Realizando login...");
    }

    public void login() {

        final String usuario = etLoginUsuario.getText().toString().trim();
        final String senha = etLoginSenha.getText().toString().trim();

        boolean erro = false;
        View viewFoco = null;

        if (isEmpty(usuario)) {
            etLoginUsuario.setError(getString(R.string.erro_campo_obrigatorio_login_usuario));
            viewFoco = etLoginUsuario;
            erro = true;
        } else if (validaCampo(usuario)) {
            etLoginUsuario.setError(getString(R.string.erro_usuario_invalido_login));
            viewFoco = etLoginUsuario;
            erro = true;
        } else if (isEmpty(senha)) {
            etLoginSenha.setError(getString(R.string.erro_campo_obrigatorio_login_senha));
            viewFoco = etLoginSenha;
            erro = true;
        }

        if (erro) {
            viewFoco.requestFocus();
        } else {

            progressDialog.show();

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_FAZER_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            try {

                                JSONObject json = new JSONObject(response);

                                if (!json.getBoolean("erro")) {

                                    LoginSharedPrefManager.getInstance(getApplicationContext())
                                            .login(
                                                    json.getInt("id"),
                                                    json.getString("usuario")
                                            );

                                    startActivity(new Intent(getApplicationContext(), MenuLateralPrincipalActivity.class));
                                    finish();

                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            json.getString("mensagem"),
                                            Toast.LENGTH_LONG
                                    ).show();
                                }


                            } catch (Exception e) {
                                Log.d("Erro da Aplicação", e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(
                                        LoginActivity.this,
                                        "Ocorreu um erro durante o login, tente novamente...",
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Log.d("Erro da Aplicação", error.getCause() + " | " + error.getMessage() + " | " + error);
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Ocorreu um erro interno durante o login, verifique se seu dispositivo está conectado à internet e tente novamente...",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("usuario", usuario);
                    params.put("senha", senha);
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogar:
                login();
                break;
            case R.id.tvLoginRegistrar:
                etLoginUsuario.setText("");
                etLoginSenha.setText("");
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
                break;
        }
    }

    public boolean validaCampo(String campo) {
        return campo.length() <= 3;
    }

}

