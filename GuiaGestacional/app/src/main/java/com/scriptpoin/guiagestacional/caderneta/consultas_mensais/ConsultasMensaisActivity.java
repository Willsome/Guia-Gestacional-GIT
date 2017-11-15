package com.scriptpoin.guiagestacional.caderneta.consultas_mensais;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsultasMensaisActivity extends AppCompatActivity {

    // VARIÁVEIS DO MÉTODO "pegaDataNascimento()"
    private Calendar dataConsulta;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private TextView cmTvFormDataConsulta;
    private int dia;
    private int mes;
    private int ano;

    // OUTROS
    private ConsultasMensais consultaMensal;
    private ConsultasMensaisHelper consultasMensaisHelper;
    private char AdicionarEditar = 'A';


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_mensais);

        setTitle("Adicionar");

        Intent intent = getIntent();
        consultaMensal = (ConsultasMensais) intent.getSerializableExtra("consultaMensal");

        // SPINNER POSICAO FETAL
        Spinner cmSpPosicaoFetal = (Spinner) findViewById(R.id.cmSpPosicaoFetal);
        ArrayAdapter<CharSequence> adapterPosicaoFetal = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_posicao_fetal,
                android.R.layout.simple_spinner_item
        );
        adapterPosicaoFetal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmSpPosicaoFetal.setAdapter(adapterPosicaoFetal);
        // SPINNER

        // SPINNER TIPO PROFISSIONAL
        Spinner cmSpTipoProfissional = (Spinner) findViewById(R.id.cmSpTipoProfissional);
        ArrayAdapter<CharSequence> adapterTipoProfissional = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_tipo_profissional,
                android.R.layout.simple_spinner_item
        );
        adapterTipoProfissional.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmSpTipoProfissional.setAdapter(adapterTipoProfissional);
        // SPINNER

        consultasMensaisHelper = new ConsultasMensaisHelper(this, 1, null);

        if (consultaMensal != null) {
            setTitle("Editar");
            AdicionarEditar = 'E';
            consultasMensaisHelper.preencheFormularioConsultasMensais(
                    consultaMensal,
                    adapterTipoProfissional.getPosition(consultaMensal.getPosicaoFetal()),
                    adapterTipoProfissional.getPosition(consultaMensal.getTipoProfissional())
            );
        }

        pegaDataConsulta(consultaMensal);

        Button cmBtSalvar = (Button) findViewById(R.id.cmBtSalvar);
        cmBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    ConsultasMensais consultaMensal = consultasMensaisHelper.pegaConsultasMensais(dataConsulta);

                    DaoCaderneta dao = new DaoCaderneta(ConsultasMensaisActivity.this);

                    Intent intent = new Intent();

                    if (AdicionarEditar == 'E') {
                        dao.alteraConsultasMensais(consultaMensal);
                        intent.putExtra("numeroConsulta", consultaMensal.getNumeroConsulta());
                        Toast.makeText(ConsultasMensaisActivity.this, "A consulta foi atualizada !", Toast.LENGTH_SHORT).show();
                        dao.close();
                        setResult(RESULT_OK, intent);
                        finish();

                    } else if (AdicionarEditar == 'A') {

                        if (!dao.existeConsulta(consultaMensal.getNumeroConsulta())) {
                            dao.salvaConsultasMensais(consultaMensal);
                            Toast.makeText(ConsultasMensaisActivity.this, "A consulta foi adicionada !", Toast.LENGTH_SHORT).show();
                            dao.close();
                            setResult(RESULT_OK, intent);
                            finish();

                        } else {
                            Toast.makeText(ConsultasMensaisActivity.this, "A consulta informada já existe...", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(ConsultasMensaisActivity.this, "Existem campos não preenchidos...", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    private void pegaDataConsulta(final ConsultasMensais consultaMensal) {

        dataConsulta = Calendar.getInstance();

        cmTvFormDataConsulta = (TextView) findViewById(R.id.cmTvFormDataConsulta);
        cmTvFormDataConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (consultaMensal != null) {

                    dataConsulta = consultaMensal.getDataConsulta();
                }

                dia = dataConsulta.get(Calendar.DAY_OF_MONTH);
                mes = dataConsulta.get(Calendar.MONTH);
                ano = dataConsulta.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ConsultasMensaisActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        datePickerListener,
                        ano, mes, dia
                );

                datePickerDialog.getWindow();
                datePickerDialog.show();
            }
        });

        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {

                dataConsulta.set(Calendar.DAY_OF_MONTH, dia);
                dataConsulta.set(Calendar.MONTH, mes);
                dataConsulta.set(Calendar.YEAR, ano);

                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataConsulta.getTime());

                cmTvFormDataConsulta.setText(data);
            }
        };
    }
}
