package com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.util.ArrayList;
import java.util.Collections;

public class ExamesSolicitadosResultadosActivity extends AppCompatActivity {

    private LinearLayout esLlExamesSolicitados;

    private CheckBox esCbSolicitacao;
    private CheckBox esCbResultado;

    private ArrayList<Exame> examesSolicitados;
    private ArrayList<Long> examesSolicitadosIds;
    private ArrayList<View> viewsDosExamesSelecionados;

    private LayoutInflater layoutInflater;
    private EditText esEtConsultaSolicitacao;
    private TextView esEtTvConsultaResultado;
    private EditText esEtConsultaResultado;

    private ArrayList<ExamesSolicitadosResultados> examesSolicitadosResultados;
    private ExamesSolicitadosResultadosHelper examesSolicitadosResultadosHelper;

    private char adicionarEditar = 'A';

    private ArrayList<View> viewsExames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames_solicitados_resultados);

        getApplicationContext().setTheme(R.style.AppTheme);

        setTitle("Adicionar");

        // POSSÍVEIS EXAMES SOLICITADOS == 12
        DaoCaderneta dao = new DaoCaderneta(this);
        final ArrayList<Exame> exames = dao.pegaExames();
        Collections.sort(exames);
        dao.close();

        layoutInflater = getLayoutInflater();
        esLlExamesSolicitados = (LinearLayout) findViewById(R.id.esLlExamesSolicitados);

        // CAMPOS DA CONSULTA SOLICITAÇÃO/RESULTADO
        esEtConsultaSolicitacao = findViewById(R.id.esEtConsultaSolicitacao);
        esEtTvConsultaResultado = findViewById(R.id.esEtTvConsultaResultado);
        esEtConsultaResultado = findViewById(R.id.esEtConsultaResultado);
        esEtTvConsultaResultado.setVisibility(View.GONE);
        esEtConsultaResultado.setVisibility(View.GONE);

        // CHECK BOXES
        esCbSolicitacao = findViewById(R.id.esCbSolicitacao);
        esCbSolicitacao.setChecked(true);
        esCbResultado = findViewById(R.id.esCbResultado);

        // EXAMES SOLICITADOS COM CHECKBOX
        examesSolicitados = new ArrayList<Exame>();
        viewsDosExamesSelecionados = new ArrayList<View>();
        viewsExames = new ArrayList<>();
        examesSolicitadosIds = new ArrayList<>();

        Intent intent = getIntent();
        examesSolicitadosResultados
                = (ArrayList<ExamesSolicitadosResultados>) intent.getSerializableExtra("examesSolicitadosResultados");

        examesSolicitadosResultadosHelper =
                new ExamesSolicitadosResultadosHelper(
                        ExamesSolicitadosResultadosActivity.this,
                        1,
                        examesSolicitados,
                        viewsDosExamesSelecionados);

        if (examesSolicitadosResultados != null) {
            setTitle("Editar");
            adicionarEditar = 'E';
            esEtConsultaSolicitacao.setEnabled(false);
            esEtConsultaSolicitacao.setTextColor(Color.rgb(183, 68, 139));
            if (examesSolicitadosResultados.get(0).getSolicitacao() == 1) {
                esEtConsultaSolicitacao.setText(String.valueOf(examesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao()));
                for (ExamesSolicitadosResultados es : examesSolicitadosResultados) {
                    examesSolicitadosIds.add(es.getExame().getId());
                }
                adicionaViewsDeExamesSolicitados(exames, examesSolicitados, 1);

            } else {
                // CHECK BOX
                esCbSolicitacao.setChecked(false);
                esCbResultado.setChecked(true);
                // EDIT TEXT DA CONSULTA DE SOLICITAÇÃO
                esEtConsultaSolicitacao.setText(String.valueOf(examesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao()));
                // EDIT TEXT DA CONSULTA DE RESULTADO
                esEtConsultaResultado.setText(String.valueOf(examesSolicitadosResultados.get(0).getNumeroConsultaResultado()));
                esEtTvConsultaResultado.setVisibility(View.VISIBLE);
                esEtConsultaResultado.setVisibility(View.VISIBLE);
                // ADIÇÃO DOS CAMPOS COM EXAMES E RESULTADOS
                for (ExamesSolicitadosResultados es : examesSolicitadosResultados) {
                    examesSolicitadosIds.add(es.getExame().getId());
                }
                adicionaViewsDeExamesSolicitados(exames, examesSolicitados, 1);
                checaResultado();
            }

        } else {
            adicionaViewsDeExamesSolicitados(exames, examesSolicitados, 0);
        }


        esCbSolicitacao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean ativado) {
                if (ativado) {
                    checaSolicitacao();
                    esCbResultado.setChecked(false);
                }
            }
        });

        esCbResultado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ativado) {
                if (ativado) {
                    checaResultado();
                    esCbSolicitacao.setChecked(false);
                    esEtTvConsultaResultado.setVisibility(View.VISIBLE);
                    esEtConsultaResultado.setVisibility(View.VISIBLE);

                } else {
                    esEtTvConsultaResultado.setVisibility(View.GONE);
                    esEtConsultaResultado.setVisibility(View.GONE);
                }
            }
        });


        Button esBtSalvar = (Button) findViewById(R.id.esBtSalvar);
        esBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    examesSolicitadosResultadosHelper =
                            new ExamesSolicitadosResultadosHelper(ExamesSolicitadosResultadosActivity.this, 1, examesSolicitados, viewsDosExamesSelecionados);

                    ArrayList<ExamesSolicitadosResultados> ListaDeExamesSolicitadosResultados =
                            examesSolicitadosResultadosHelper.pegaExamesSolicitadosResultados(examesSolicitados);

                    DaoCaderneta dao = new DaoCaderneta(ExamesSolicitadosResultadosActivity.this);

                    Intent intent = new Intent();

                    if (!dao.existeConsulta(ListaDeExamesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao())) {
                        Toast.makeText(ExamesSolicitadosResultadosActivity.this, "A consulta de solicitação dos exames não existe...", Toast.LENGTH_SHORT).show();

                    } else {
                        if (adicionarEditar == 'E') {
                            dao.alteraExamesSolicitadosResultados(ListaDeExamesSolicitadosResultados);
                            intent.putExtra("consultaSolicitacao", ListaDeExamesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao());
                            Toast.makeText(ExamesSolicitadosResultadosActivity.this, "Exames Solicitados/Resultados atualizados !", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK, intent);
                            dao.close();
                            finish();

                        } else {
                            if (!dao.existeExamesSolicitadosResultados(ListaDeExamesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao())) {
                                dao.salvaExamesSolicitadosResultados(ListaDeExamesSolicitadosResultados);
                                intent.putExtra("consultaSolicitacao", ListaDeExamesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao());
                                Toast.makeText(ExamesSolicitadosResultadosActivity.this, "Exames Solicitados/Resultados salvos !", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK, intent);
                                dao.close();
                                finish();

                            } else {
                                Toast.makeText(ExamesSolicitadosResultadosActivity.this, "Já existem exames solicitados/resultados para a consulta informada...", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                } catch (Exception e) {
                    Toast.makeText(ExamesSolicitadosResultadosActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

    }

    public void checaSolicitacao() {
        for (int i = 0; i < viewsExames.size(); i++) {
            LinearLayout v = (LinearLayout) viewsExames.get(i);
            v.setVisibility(View.VISIBLE);

            LinearLayout esLlExame = (LinearLayout) v.getChildAt(0);
            CheckBox checkBox = (CheckBox) esLlExame.getChildAt(1);
            checkBox.setEnabled(true);

            // LAYOUT COM TEXT_VIEW "Resultado:" + EDIT_TEXT||SPINNER
            LinearLayout esLlRoot = (LinearLayout) v.getChildAt(1);

            TextView esTvResultado = (TextView) esLlRoot.getChildAt(0);
            esTvResultado.setVisibility(View.GONE);

            LinearLayout esLlResultado = (LinearLayout) esLlRoot.getChildAt(1);
            esLlResultado.setVisibility(View.GONE);
        }
    }

    public void checaResultado() {
        for (int i = 0; i < viewsExames.size(); i++) {
            if (!viewsDosExamesSelecionados.contains(viewsExames.get(i))) {
                viewsExames.get(i).setVisibility(View.GONE);

            } else {
                LinearLayout v = (LinearLayout) viewsExames.get(i);
                v.setVisibility(View.VISIBLE);

                LinearLayout esLlExame = (LinearLayout) v.getChildAt(0);
                CheckBox checkBox = (CheckBox) esLlExame.getChildAt(1);
                checkBox.setEnabled(false);

                // LAYOUT COM TEXT_VIEW "Resultado:" + EDIT_TEXT||SPINNER
                LinearLayout esLlRoot = (LinearLayout) v.getChildAt(1);

                TextView esTvResultado = (TextView) esLlRoot.getChildAt(0);
                esTvResultado.setVisibility(View.VISIBLE);

                LinearLayout esLlResultado = (LinearLayout) esLlRoot.getChildAt(1);
                esLlResultado.setVisibility(View.VISIBLE);
            }
        }
    }

    public void adicionaViewsDeExamesSolicitados(final ArrayList<Exame> exames, final ArrayList<Exame> examesSolicitados, int j) {

        // ADICIONA VIEWS COM AS OPÇÕES DE EXAMES (==12)
        for (int i = 0; i < exames.size(); i++) {

            final View view = layoutInflater.inflate(R.layout.layout_opcao_exames_solicitados, null);

            view.setId(13 + i);

            TextView esTvNomeDoExame = view.findViewById(R.id.esTvNomeDoExame);
            esTvNomeDoExame.setText(exames.get(i).getNomeDoExame());

            CheckBox esCbExame = view.findViewById(R.id.esCbExame);
            if (j == 1) {
                if (examesSolicitadosIds.contains(exames.get(i).getId())) {
                    esCbExame.setChecked(true);
                    examesSolicitados.add(exames.get(i));
                    viewsDosExamesSelecionados.add(view);
                }
            }

            TextView esTvResultado = view.findViewById(R.id.esTvResultado);
            esTvResultado.setVisibility(View.GONE);

            LinearLayout esLlResultado = (LinearLayout) view.findViewById(R.id.esLlResultado);
            esLlResultado.setVisibility(View.GONE);

            defineCampoResultado(exames.get(i).getId(), esLlResultado);

            final int finalI = i;
            esCbExame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean ativado) {
                    if (ativado) {
                        examesSolicitados.add(exames.get(finalI));
                        viewsDosExamesSelecionados.add(view);
//                        examesSolicitadosResultadosHelper =
//                                new ExamesSolicitadosResultadosHelper(ExamesSolicitadosResultadosActivity.this, 1, examesSolicitados, viewsDosExamesSelecionados);
                    } else {
                        examesSolicitados.remove(exames.get(finalI));
                        viewsDosExamesSelecionados.remove(view);
                    }
                }
            });

            viewsExames.add(view);
            esLlExamesSolicitados.addView(view);
        }
    }

    ArrayList<Long> idsPermitidas = new ArrayList<>();

    public void defineCampoResultado(Long id, LinearLayout esLlResultado) {

        int indexDoExameSolicitadoResultado = 0;
        if (adicionarEditar == 'E') {
            for (ExamesSolicitadosResultados es : examesSolicitadosResultados) {
                if (es.getExame().getId().equals(id)) {
                    idsPermitidas.add(id);
                    indexDoExameSolicitadoResultado = examesSolicitadosResultados.indexOf(es);
                }
            }
        }

        if (id == 1) {
            // SPINNER
            Spinner v = new Spinner(getApplicationContext());
            v.setId((int) (long) id);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.spinner_resultado_ABORH,
                    android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            v.setAdapter(adapter);
            if (adicionarEditar == 'E' && idsPermitidas.contains(id)) {
                v.setSelection(adapter.getPosition(examesSolicitadosResultados.get(indexDoExameSolicitadoResultado).getResultado()));
            }
            // SPINNER
            esLlResultado.addView(v);

        } else if (id == 5 || id == 6 || id == 8 || id == 9 || id == 12) {
            // SPINNER
            Spinner v = new Spinner(getApplicationContext());
            v.setId((int) (long) id);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.spinner_resultado_reagente,
                    android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            v.setAdapter(adapter);
            if (adicionarEditar == 'E' && idsPermitidas.contains(id)) {
                v.setSelection(adapter.getPosition(examesSolicitadosResultados.get(indexDoExameSolicitadoResultado).getResultado()));
            }
            // SPINNER
            esLlResultado.addView(v);

        } else if (id == 11) {
            // SPINNER
            Spinner v = new Spinner(getApplicationContext());
            v.setId((int) (long) id);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.spinner_resultado_positivo_negativo,
                    android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            v.setAdapter(adapter);
            if (adicionarEditar == 'E' && idsPermitidas.contains(id)) {
                v.setSelection(adapter.getPosition(examesSolicitadosResultados.get(indexDoExameSolicitadoResultado).getResultado()));
            }
            // SPINNER
            esLlResultado.addView(v);

        } else if (id == 2 || id == 4 || id == 7) {
            EditText v = new EditText(getApplicationContext());
            v.setId((int) (long) id);
            v.setHint("(mg/dl)");
            v.setInputType(InputType.TYPE_CLASS_NUMBER);
            v.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            v.setKeyListener(DigitsKeyListener.getInstance(false, true));
            v.setGravity(Gravity.CENTER);
            if (adicionarEditar == 'E' && idsPermitidas.contains(id)) {
                v.setText(examesSolicitadosResultados.get(indexDoExameSolicitadoResultado).getResultado());
            }

            esLlResultado.addView(v);

        } else if (id == 3) {
            EditText v = new EditText(getApplicationContext());
            v.setId((int) (long) id);
            v.setHint("(%)");
            v.setInputType(InputType.TYPE_CLASS_NUMBER);
            v.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            v.setKeyListener(DigitsKeyListener.getInstance(false, true));
            v.setGravity(Gravity.CENTER);
            if (adicionarEditar == 'E' && idsPermitidas.contains(id)) {
                v.setText(examesSolicitadosResultados.get(indexDoExameSolicitadoResultado).getResultado());
            }

            esLlResultado.addView(v);

        } else if (id == 10) {
            EditText v = new EditText(getApplicationContext());
            v.setId((int) (long) id);
            v.setHint("(mm³)");
            v.setInputType(InputType.TYPE_CLASS_NUMBER);
            v.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            v.setKeyListener(DigitsKeyListener.getInstance(false, true));
            v.setGravity(Gravity.CENTER);
            if (adicionarEditar == 'E' && idsPermitidas.contains(id)) {
                v.setText(examesSolicitadosResultados.get(indexDoExameSolicitadoResultado).getResultado());
            }

            esLlResultado.addView(v);
        }
    }

}
