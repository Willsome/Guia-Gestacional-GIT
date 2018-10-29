package com.scriptpoin.gestacaosaudavel.web.server;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.scriptpoin.gestacaosaudavel.caderneta.consultas_mensais.ConsultasMensais;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_obstetricos.DadosObstetricos;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_pessoais.DadosPessoais;
import com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultados;
import com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia.Ultrassonografia;
import com.scriptpoin.gestacaosaudavel.dao.DaoCaderneta;
import com.scriptpoin.gestacaosaudavel.web.Constants;
import com.scriptpoin.gestacaosaudavel.web.json.CadernetaJSON;
import com.scriptpoin.gestacaosaudavel.web.login.handler.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DadosServidorSender extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog progressDialog;
    private String resposta;
    private int id;

    public DadosServidorSender(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        progressDialog = progressDialog.show(
                context,
                "Aguarde",
                "Atualizando seus dados no servidor...",
                true,
                true);

    }

    @Override
    protected String doInBackground(Void... voids) {

            DaoCaderneta dao = new DaoCaderneta(context);
            final DadosPessoais dadosPessoais = dao.pegaDadosPessoais();
            DadosObstetricos dadosObstetricos = dao.pegaDadosObstetricos();
            ArrayList<ExamesSolicitadosResultados> examesSolicitadosResultados = dao.pegaExamesSolicitadosResultados(dao);
            ArrayList<String> medicamentos = dao.pegaUsoDeMedicamento();
            ArrayList<Ultrassonografia> ultrassonografias = dao.pegaUltrassonografias();
            ArrayList<ConsultasMensais> consultasMensais = dao.pegaConsultasMensais();

            dao.close();

            CadernetaJSON json = new CadernetaJSON();

            //JSONStringer stringer = new JSONStringer();

            //final String dadosPessoaisString = json.dadosPessoaisToJSON(stringer, dadosPessoais).toString();

            final String dadosDaGestante = json.toJSON(
                    dadosPessoais,
                    dadosObstetricos,
                    examesSolicitadosResultados,
                    ultrassonografias,
                    medicamentos,
                    consultasMensais,
                    id);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_ATUALIZAR_DADOS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);
                            resposta = json.getString("mensagem");

                            Toast.makeText(
                                    context,
                                    resposta,
                                    Toast.LENGTH_LONG
                            ).show();

                        } catch (JSONException e) {
                            Log.d("Erro da Aplicação", e.getMessage());
                            e.printStackTrace();
                            Toast.makeText(
                                    context,
                                    "Ocorreu um erro ao atualizar seus dados.\nVerifique se sua caderneta foi preenchida corretamente...",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Erro da Aplicação", error.getCause() + " | " + error.getMessage() + " | " + error);
                        Toast.makeText(
                                context,
                                "Ocorreu um erro interno durante o login, verifique se seu dispositivo está conectado à internet e tente novamente...",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("dados_da_gestante", dadosDaGestante);
                return params;
            }
        };

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        progressDialog.dismiss();
        progressDialog = null;
    }
}
