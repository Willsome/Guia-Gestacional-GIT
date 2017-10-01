package com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.io.Serializable;

public class ExamesSolicitadosResultadosActivity extends AppCompatActivity {

    private ExamesSolicitadosResultadosHelper examesSolicitadosResultadosHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames_solicitados_resultados);

        setTitle("Editar Exames Solicitados/Resultados");

        Intent intent = getIntent();
        ExamesSolicitadosResultados examesSolicitadosResultados
                = (ExamesSolicitadosResultados) intent.getSerializableExtra("examesSolicitadosResultados");

        examesSolicitadosResultadosHelper =
                new ExamesSolicitadosResultadosHelper(this, 1, null);

        if (examesSolicitadosResultados != null) {
            examesSolicitadosResultadosHelper.preencheExamesSolicitadosResultados(examesSolicitadosResultados);
        }

        Button esBtSalvar = (Button) findViewById(R.id.esBtSalvar);
        esBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ExamesSolicitadosResultados examesSolicitadosResultados =
                        examesSolicitadosResultadosHelper.pegaExamesSolicitadosResultados();

                DaoCaderneta dao = new DaoCaderneta(ExamesSolicitadosResultadosActivity.this);

                if (examesSolicitadosResultados.getId() != null) {
                    dao.alteraExamesSolicitadosResultados(examesSolicitadosResultados);
                    Toast.makeText(ExamesSolicitadosResultadosActivity.this, "Dados Pessoais atualizados !", Toast.LENGTH_SHORT).show();
                } else {
                    dao.salvaExamesSolicitadosResultados(examesSolicitadosResultados);
                }
                dao.close();

                finish();
            }
        });
    }
}
