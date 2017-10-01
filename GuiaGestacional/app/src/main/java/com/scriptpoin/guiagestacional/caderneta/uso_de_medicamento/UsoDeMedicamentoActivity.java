package com.scriptpoin.guiagestacional.caderneta.uso_de_medicamento;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UsoDeMedicamentoActivity extends AppCompatActivity {

    ArrayList<String> medicamentos;

    private LinearLayout umLlMedicamento;

    // DO MEDICAMENTO DO FORMULÁRIO
    private EditText umEtMedicamentoAlfa;
    private Button umBtAdicionar;

    // DOS MEDICAMENTOS DA LISTA DO FORMULÁRIO
    private EditText umEtMedicamentoBeta;
    private HashMap<View, String> medicamentosTmp = new HashMap<>();

    private Button umBtSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso_de_medicamento);

        setTitle("Editar Uso de Medicamento");

        Intent intent = getIntent();
        medicamentos = intent.getStringArrayListExtra("medicamentos");


        umLlMedicamento = (LinearLayout) findViewById(R.id.umLlFormMedicamento);

        final LayoutInflater inflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // CARREGA MEDICAMENTOS DO FORMULÁRIO
        if (medicamentos.size() > 0) {

            final HashMap<Integer, String> medicamentoX = new HashMap<>();

            for (int i = 0; i < medicamentos.size(); i++) {

                medicamentoX.put(i, medicamentos.get(i));

                final View view = inflater.inflate(R.layout.layout_formulario_uso_de_medicamento, null);

                umEtMedicamentoBeta = (EditText) view.findViewById(R.id.umEtMedicamentoBeta);
                umEtMedicamentoBeta.setText(medicamentos.get(i));

                Button umBtRemover = (Button) view.findViewById(R.id.umBtRemover);

                final int finalI = i;
                final View.OnClickListener thisListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        medicamentoX.remove(finalI);
                        ((LinearLayout) view.getParent()).removeView(view);
                        medicamentos = new ArrayList<>();
                        for (int i = 0; i < medicamentoX.size(); i++) {
                            medicamentos.add(medicamentoX.get(i));
                        }
                    }
                };

                umBtRemover.setOnClickListener(thisListener);

                umLlMedicamento.addView(view);
            }

        }


        // ADICIONA O MEDICAMENTO À LISTA
        umEtMedicamentoAlfa = (EditText) findViewById(R.id.umEtMedicamentoAlfa);

        umBtAdicionar = (Button) findViewById(R.id.umBtAdicionar);
        umBtAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (umEtMedicamentoAlfa.getText().toString().equals("")) {

                    Toast.makeText(UsoDeMedicamentoActivity.this, "Não foi informado o medicamento...", Toast.LENGTH_LONG).show();

                } else {

                    final View view = inflater.inflate(R.layout.layout_formulario_uso_de_medicamento, null);

                    umEtMedicamentoBeta = (EditText) view.findViewById(R.id.umEtMedicamentoBeta);
                    umEtMedicamentoBeta.setText(umEtMedicamentoAlfa.getText().toString());

                    Button umBtRemover = (Button) view.findViewById(R.id.umBtRemover);

                    medicamentosTmp.put(view, umEtMedicamentoAlfa.getText().toString());

                    final View.OnClickListener thisListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            medicamentosTmp.remove(view);
                            ((LinearLayout) view.getParent()).removeView(view);
                        }
                    };

                    umBtRemover.setOnClickListener(thisListener);

                    umLlMedicamento.addView(view);
                    umEtMedicamentoAlfa.setText("");
                }
            }
        });


        // SALVA OS MEDICAMENTOS DA LISTA
        umBtSalvar = (Button) findViewById(R.id.umBtSalvar);
        umBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> medicamentos = new ArrayList<>();

                for (int i = 0; i < umLlMedicamento.getChildCount(); i++) {
                    medicamentos.add(((EditText) ((RelativeLayout) umLlMedicamento.getChildAt(i)).getChildAt(1)).getText().toString());
                }

                Collections.sort(medicamentos);

                DaoCaderneta dao = new DaoCaderneta(UsoDeMedicamentoActivity.this);
                dao.salvaUsoDeMedicamento(medicamentos);
                dao.close();

                Toast.makeText(UsoDeMedicamentoActivity.this, "Medicamentos atualizados !", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }

}