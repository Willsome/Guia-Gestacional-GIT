package com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

public class ExamesSolicitadosResultadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames_solicitados_resultados);

        DaoCaderneta dao = new DaoCaderneta(ExamesSolicitadosResultadosActivity.this);
        ExamesSolicitadosResultados es = dao.pegaExamesSolicitadosResultados();
        dao.close();

        final ExamesSolicitadosResultadosHelper esh =
                new ExamesSolicitadosResultadosHelper(ExamesSolicitadosResultadosActivity.this, 1, null);

        esh.preencheExamesSolicitadosResultados(es);

        Button esBtSalvar = (Button) findViewById(R.id.esBtSalvar);
        esBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DaoCaderneta dao = new DaoCaderneta(ExamesSolicitadosResultadosActivity.this);
                ExamesSolicitadosResultados es = esh.pegaExamesSolicitadosResultados();
                dao.salvaExamesSolicitadosResultados(es);
                dao.close();

                finish();
            }
        });
    }
}
