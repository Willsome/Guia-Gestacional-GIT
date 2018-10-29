package com.scriptpoin.gestacaosaudavel.web.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.filter.FiltraInput;
import com.scriptpoin.gestacaosaudavel.web.Constants;
import com.scriptpoin.gestacaosaudavel.web.login.handler.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.text.TextUtils.isEmpty;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCadastroNome, etCadastroEmail, etCadastroUsuario, etCadastroSenha, etCadastroConfirmacaoSenha;
    private Button btCadastrar;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        progressDialog = new ProgressDialog(this);

        // REFERÊNCIA DOS COMPONENTES - CADASTRO
        etCadastroNome = findViewById(R.id.etCadastroNome);
        etCadastroEmail = findViewById(R.id.etCadastroEmail);
        etCadastroUsuario = findViewById(R.id.etCadastroUsuario);
        etCadastroSenha = findViewById(R.id.etCadastroSenha);
        etCadastroConfirmacaoSenha = findViewById(R.id.etCadastroConfirmacaoSenha);

        etCadastroEmail.setFilters(new InputFilter[]{FiltraInput.filtroDoInput()});
        etCadastroUsuario.setFilters(new InputFilter[]{FiltraInput.filtroDoInput()});
        etCadastroSenha.setFilters(new InputFilter[]{FiltraInput.filtroDoInput()});
        etCadastroSenha.setFilters(new InputFilter[]{FiltraInput.filtroDoInput()});
        etCadastroConfirmacaoSenha.setFilters(new InputFilter[]{FiltraInput.filtroDoInput()});

        btCadastrar = findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(this);
    }

    public void registrarUsuario() {

        final String nome = etCadastroNome.getText().toString().trim();
        final String email = etCadastroEmail.getText().toString().trim();
        final String usuario = etCadastroUsuario.getText().toString().trim();
        final String senha = etCadastroSenha.getText().toString().trim();
        final String confirmacaoSenha = etCadastroConfirmacaoSenha.getText().toString().trim();

        boolean erro = false;
        View viewFoco = null;

        etCadastroNome.setError(null);
        etCadastroEmail.setError(null);
        etCadastroUsuario.setError(null);
        etCadastroSenha.setError(null);

        if (isEmpty(nome)) {
            etCadastroNome.setError(getString(R.string.erro_campo_obrigatorio));
            viewFoco = etCadastroNome;
            erro = true;
        } else if (validaCampo(nome, "nome")) {
            etCadastroNome.setError(getString(R.string.erro_nome_invalido));
            viewFoco = etCadastroNome;
            erro = true;
        } else if (isEmpty(email)) {
            etCadastroEmail.setError(getString(R.string.erro_campo_obrigatorio));
            viewFoco = etCadastroEmail;
            erro = true;
        } else if (validaCampo(email, "email")) {
            etCadastroEmail.setError(getString(R.string.erro_email_invalido));
            viewFoco = etCadastroEmail;
            erro = true;
        } else if (isEmpty(usuario)) {
            etCadastroUsuario.setError(getString(R.string.erro_campo_obrigatorio));
            viewFoco = etCadastroUsuario;
            erro = true;
        } else if (validaCampo(usuario, "usuario")) {
            etCadastroUsuario.setError(getString(R.string.erro_usuario_invalido_cadastro));
            viewFoco = etCadastroUsuario;
            erro = true;
        } else if (isEmpty(senha)) {
            etCadastroSenha.setError(getString(R.string.erro_campo_obrigatorio));
            viewFoco = etCadastroSenha;
            erro = true;
        } else if (validaCampo(senha, "senha")) {
            etCadastroSenha.setError(getString(R.string.erro_senha_invalida));
            viewFoco = etCadastroSenha;
            erro = true;
        } else if (!senha.equals(confirmacaoSenha)) {
            etCadastroConfirmacaoSenha.setError(getString(R.string.erro_confirmacao_senha));
            viewFoco = etCadastroConfirmacaoSenha;
            erro = true;
        }

        if (erro) {
            viewFoco.requestFocus();
        } else {

            progressDialog.setMessage("Cadastrando usuário...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_CADASTRO,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            try {
                                JSONObject json = new JSONObject(response);
                                etCadastroNome.setText("");
                                etCadastroUsuario.setText("");
                                etCadastroEmail.setText("");
                                etCadastroSenha.setText("");
                                finish();
                                Toast.makeText(
                                        CadastroActivity.this,
                                        json.getString("mensagem"),
                                        Toast.LENGTH_SHORT
                                ).show();

                            } catch (JSONException e) {
                                Log.d("Erro da Aplicação", e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(
                                        CadastroActivity.this,
                                        "Ocorreu um erro durante o cadastro, tente novamente...",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Log.d("Erro da Aplicação", error.getCause() + " | " + error.getMessage() + " | " + error);
                            Toast.makeText(
                                    CadastroActivity.this,
                                    "Ocorreu um erro interno durante o cadastro, verifique se seu dispositivo está conectado à internet e tente novamente...",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<>();
                    params.put("nome", nome);
                    params.put("usuario", usuario);
                    params.put("email", email);
                    params.put("senha", senha);
                    return params;
                }
            };

            /*RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);*/
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btCadastrar) {
            registrarUsuario();
        }
    }

    public boolean validaCampo(String campo, String tipo) {

        switch (tipo) {
            case "nome":
                return campo.length() < 3;
            case "email":
                return !campo.contains("@");
            case "senha":
                return campo.length() <= 4;
            default:
                return campo.length() <= 3;
        }
    }
}