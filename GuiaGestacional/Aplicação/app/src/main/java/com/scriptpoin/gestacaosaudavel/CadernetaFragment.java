package com.scriptpoin.gestacaosaudavel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.gestacaosaudavel.caderneta.consultas_mensais.ConsultasMensais;
import com.scriptpoin.gestacaosaudavel.caderneta.consultas_mensais.ConsultasMensaisActivity;
import com.scriptpoin.gestacaosaudavel.caderneta.consultas_mensais.ConsultasMensaisHelper;
import com.scriptpoin.gestacaosaudavel.caderneta.consultas_mensais.ListaConsultasMensaisActivity;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_obstetricos.DadosObstetricos;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_obstetricos.DadosObstetricosActivity;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_obstetricos.DadosObstetricosHelper;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_pessoais.DadosPessoais;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_pessoais.DadosPessoaisActivity;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_pessoais.DadosPessoaisHelper;
import com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados.Exame;
import com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultados;
import com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultadosActivity;
import com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados.ListaExamesSolicitadosResultadosActivity;
import com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia.ListaUltrassonografiasActivity;
import com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia.Ultrassonografia;
import com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia.UltrassonografiaActivity;
import com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia.UltrassonografiaHelper;
import com.scriptpoin.gestacaosaudavel.caderneta.uso_de_medicamento.UsoDeMedicamentoActivity;
import com.scriptpoin.gestacaosaudavel.dao.DaoCaderneta;

import java.util.ArrayList;
import java.util.Collections;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadernetaFragment extends Fragment {

    private View view;

    private LayoutInflater inflater;

    private DadosPessoais dadosPessoais;
    private DadosObstetricos dadosObstetricos;
    private ArrayList<ExamesSolicitadosResultados> examesSolicitadosResultados;
    private Ultrassonografia ultrassonografia;
    private ArrayList<String> medicamentos;
    private ConsultasMensais consultasMensais;


    public CadernetaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Caderneta Virtual da Gestante");

        this.inflater = inflater;

        this.view = inflater.inflate(R.layout.fragment_caderneta, container, false);


        Button dpBtAlterar = view.findViewById(R.id.dpBtAlterar);
        dpBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DadosPessoaisActivity.class);
                intent.putExtra("dadosPessoais", dadosPessoais);
                startActivity(intent);
            }
        });

        Button doBtAlterar = view.findViewById(R.id.doBtAlterar);
        doBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DadosObstetricosActivity.class);
                intent.putExtra("dadosObstetricos", dadosObstetricos);
                startActivity(intent);
            }
        });

        Button esBtAdicionar = view.findViewById(R.id.esBtAdicionar);
        esBtAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ExamesSolicitadosResultadosActivity.class);
                startActivityForResult(intent, 5);
            }
        });

        Button uBtAdicionar = view.findViewById(R.id.uBtAdicionar);
        uBtAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UltrassonografiaActivity.class);
                startActivityForResult(intent, 3);
            }
        });

        Button umBtAlterar = view.findViewById(R.id.umBtAlterar);
        umBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UsoDeMedicamentoActivity.class);
                intent.putExtra("medicamentos", medicamentos);
                startActivity(intent);
            }
        });

        Button cmBtAdicionar = view.findViewById(R.id.cmBtAdicionar);
        cmBtAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConsultasMensaisActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        carregaExamesSolicitadosResultados(view, -1);
        carregaUltrassonografia(view, -1);
        carregaConsultasMensais(view, -1);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        DaoCaderneta dao = new DaoCaderneta(getContext());

        carregaDadosPessoais(view, dao);
        carregaDadosObstetricos(view, dao);
        carregaUsoDeMedicamento(view, dao);

        dao.close();
    }

    public void carregaDadosPessoais(View view, DaoCaderneta dao) {
        dadosPessoais = dao.pegaDadosPessoais();

        if (dadosPessoais != null) {
            DadosPessoaisHelper dadosPessoaisHelper = new DadosPessoaisHelper(getActivity(), 2, view);
            dadosPessoaisHelper.preencheDadosPessoais(dadosPessoais);
        }
    }

    public void carregaDadosObstetricos(View view, DaoCaderneta dao) {
        dadosObstetricos = dao.pegaDadosObstetricos();

        if (dadosObstetricos != null) {
            DadosObstetricosHelper dadosObstetricosHelper = new DadosObstetricosHelper(getActivity(), 2, view);
            dadosObstetricosHelper.preencheDadosObstetricos(dadosObstetricos);
        }
    }

    public void carregaExamesSolicitadosResultados(View view, int numeroDaConsulta) {

        DaoCaderneta dao = new DaoCaderneta(getContext());

        examesSolicitadosResultados = dao.pegaExameSolicitadosResultado(numeroDaConsulta, getContext());

        Collections.sort(examesSolicitadosResultados);

        ArrayList<Exame> exames = new ArrayList<>();
        for (ExamesSolicitadosResultados es : examesSolicitadosResultados) {
            exames.add(es.getExame());
        }

        dao.close();

        LinearLayout esLlExamesSolicitadosResultados = view.findViewById(R.id.esLlExamesSolicitadosResultados);

        esLlExamesSolicitadosResultados.removeAllViews();

        if (examesSolicitadosResultados.size() != 0) {

            registerForContextMenu(esLlExamesSolicitadosResultados);

            if (examesSolicitadosResultados.get(0).getSolicitacao() == 1) {

                View v = inflater.inflate(R.layout.layout_mostra_solicitacao_exames_solicitados_resultados, null);

                esLlExamesSolicitadosResultados.addView(v);

                TextView esTvConsultaSolicitacao = v.findViewById(R.id.esTvConsultaSolicitacao);
                esTvConsultaSolicitacao.setText(examesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao() + "ª");

                LinearLayout esLlExamesSolicitadosResultadosSolicitacao = v.findViewById(R.id.esLlExamesSolicitadosResultadosSolicitacao);

                for (Exame exame : exames) {
                    View viewExame = inflater.inflate(R.layout.layout_mostra_solicitacao_exames_solicitados_resultados_exame, null);

                    esLlExamesSolicitadosResultadosSolicitacao.addView(viewExame);

                    TextView esTvExame = viewExame.findViewById(R.id.esTvExame);
                    esTvExame.setText(exame.getNomeDoExame() + ":");

                    CheckBox esCbExame = viewExame.findViewById(R.id.esCbExame);
                    esCbExame.setEnabled(false);
                    esCbExame.setChecked(true);
                }

            } else {

                View v = inflater.inflate(R.layout.layout_mostra_resultado_exames_solicitados_resultados, null);

                esLlExamesSolicitadosResultados.addView(v);

                TextView esTvConsultaSolicitacao = v.findViewById(R.id.esTvConsultaSolicitacao);
                esTvConsultaSolicitacao.setText(examesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao() + "ª");

                TextView esTvConsultaResultado = v.findViewById(R.id.esTvConsultaResultado);
                esTvConsultaResultado.setText(examesSolicitadosResultados.get(0).getNumeroConsultaResultado() + "ª");

                LinearLayout esLlExamesSolicitadosResultadosResultado = v.findViewById(R.id.esLlExamesSolicitadosResultadosResultado);

                for (ExamesSolicitadosResultados es : examesSolicitadosResultados) {
                    View viewExame = inflater.inflate(R.layout.layout_mostra_resultado_exames_solicitados_resultados_exame, null);

                    esLlExamesSolicitadosResultadosResultado.addView(viewExame);

                    TextView esTvNomeExame = viewExame.findViewById(R.id.esTvNomeExame);
                    esTvNomeExame.setText(es.getExame().getNomeDoExame() + ":  ");

                    TextView esTvResultadoCaderneta = viewExame.findViewById(R.id.esTvResultadoCaderneta);
                    if (es.getExame().getId() == 2 || es.getExame().getId() == 4 || es.getExame().getId() == 7) {
                        esTvResultadoCaderneta.setText(es.getResultado() + " mg/dl");

                    } else if (es.getExame().getId() == 3) {
                        esTvResultadoCaderneta.setText(es.getResultado() + "%");

                    } else if (es.getExame().getId() == 10) {
                        esTvResultadoCaderneta.setText(es.getResultado() + " mm³");

                    } else {
                        esTvResultadoCaderneta.setText(es.getResultado());
                    }

                }
            }

        } else {
            TextView tv = new TextView(getContext());
            tv.setText("Nenhum exame solicitado adicionado");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            esLlExamesSolicitadosResultados.addView(tv);
        }
    }

    public void carregaUltrassonografia(View view, int numeroDaConsulta) {

        DaoCaderneta dao = new DaoCaderneta(getContext());

        ultrassonografia = dao.pegaUltrassonografia(numeroDaConsulta);

        dao.close();

        LinearLayout uLlUltrassonografia = view.findViewById(R.id.uLlUltrassonografia);

        uLlUltrassonografia.removeAllViews();

        if (ultrassonografia != null) {

            registerForContextMenu(uLlUltrassonografia);

            if (ultrassonografia.getSolicitacao() == 1) {

                View v = inflater.inflate(R.layout.layout_mostra_solicitacao_ultrassonografia, null);

                uLlUltrassonografia.addView(v);

                UltrassonografiaHelper helper = new UltrassonografiaHelper(getActivity(), 2, v);
                helper.preencheUltrassonografiaSolicitacao(ultrassonografia);

            } else {

                View v = inflater.inflate(R.layout.layout_mostra_resultado_ultrassonografia, null);

                uLlUltrassonografia.addView(v);

                UltrassonografiaHelper helper = new UltrassonografiaHelper(getActivity(), 2, v);
                helper.preencheUltrassonografiaResultado(ultrassonografia);
            }

        } else {
            TextView tv = new TextView(getContext());
            tv.setText("Nenhuma ultrassonografia adicionada");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            uLlUltrassonografia.addView(tv);
        }
    }

    public void carregaUsoDeMedicamento(View view, DaoCaderneta dao) {
        medicamentos = dao.pegaUsoDeMedicamento();

        CheckBox umCbSim = view.findViewById(R.id.umCbSim);
        CheckBox umCbNao = view.findViewById(R.id.umCbNao);

        LinearLayout umLlMedicamento = view.findViewById(R.id.umLlMedicamento);

        if (medicamentos.size() > 0) {

            umLlMedicamento.removeAllViews();

            umCbSim.setChecked(true);
            umCbNao.setChecked(false);

            for (int i = 0; i < medicamentos.size(); i++) {

                View innerView = inflater.inflate(R.layout.layout_uso_de_medicamento, null);

                TextView umTvNuMedicamento = innerView.findViewById(R.id.umTvNuMedicamento);
                TextView umTvMedicamento = innerView.findViewById(R.id.umTvMedicamento);

                umTvNuMedicamento.setText(i + 1 + ") ");
                umTvMedicamento.setText(medicamentos.get(i));

                umLlMedicamento.addView(innerView);
            }

        } else {
            umCbSim.setChecked(false);
            umCbNao.setChecked(true);

            umLlMedicamento.removeAllViews();
        }
    }


    // CONSULTAS MENSAIS
    public void carregaConsultasMensais(View view, int numeroDaConsulta) {

        DaoCaderneta dao = new DaoCaderneta(getContext());

        consultasMensais = dao.pegaConsultaMensal(numeroDaConsulta);

        dao.close();

        LinearLayout cmLlConsultasMensais = view.findViewById(R.id.cmLlConsultasMensais);

        cmLlConsultasMensais.removeAllViews();

        if (consultasMensais != null) {

            registerForContextMenu(cmLlConsultasMensais);

            View v = inflater.inflate(R.layout.layout_mostra_consulta_mensal, null);

            cmLlConsultasMensais.addView(v);

            ConsultasMensaisHelper helper = new ConsultasMensaisHelper(getActivity(), 2, v);
            helper.preencheConsultasMensais(consultasMensais);

        } else {
            TextView tv = new TextView(getContext());
            tv.setText("Nenhuma consulta adicionada");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            cmLlConsultasMensais.addView(tv);
        }

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {

        // EDIÇÃO DE EXAMES SOLICITADOS, ULTRA, CONSULTA...
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (v.getId() == R.id.esLlExamesSolicitadosResultados) {
                    Intent intent = new Intent(getActivity(), ExamesSolicitadosResultadosActivity.class);
                    intent.putExtra("examesSolicitadosResultados", examesSolicitadosResultados);
                    startActivityForResult(intent, 6);
                }

                if (v.getId() == R.id.uLlUltrassonografia) {
                    Intent intent = new Intent(getActivity(), UltrassonografiaActivity.class);
                    intent.putExtra("ultrassonografia", ultrassonografia);
                    startActivityForResult(intent, 4);
                }

                if (v.getId() == R.id.cmLlConsultasMensais) {
                    Intent intent = new Intent(getActivity(), ConsultasMensaisActivity.class);
                    intent.putExtra("consultaMensal", consultasMensais);
                    startActivityForResult(intent, 2);
                }

                return false;
            }
        });

        // EXCLUSÃO DE EXAMES SOLICITADOS, ULTRA, CONSULTA...
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (v.getId() == R.id.esLlExamesSolicitadosResultados) {
                    DaoCaderneta dao = new DaoCaderneta(getContext());
                    if (dao.deletaExamesSolicitados(examesSolicitadosResultados)) {
                        carregaExamesSolicitadosResultados(view, -1);
                        Toast.makeText(getContext(), "Os exames foram deletados !", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                }

                if (v.getId() == R.id.uLlUltrassonografia) {
                    DaoCaderneta dao = new DaoCaderneta(getContext());
                    if (dao.deletaUltrassonografia(ultrassonografia)) {
                        carregaUltrassonografia(view, -1);
                        Toast.makeText(getContext(), "A ultra foi deletada !", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                }

                if (v.getId() == R.id.cmLlConsultasMensais) {
                    DaoCaderneta dao = new DaoCaderneta(getContext());
                    if (dao.deletaConsultasMensais(consultasMensais)) {
                        carregaConsultasMensais(view, -1);
                        Toast.makeText(getContext(), "A consulta foi deletada !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Delete primeiro os outros dados com o número da consulta", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                }

                return false;
            }
        });

        // MUDANÇA DE EXAMES SOLICITADOS, ULTRA, CONSULTA...
        MenuItem mudar;
        if (v.getId() == R.id.esLlExamesSolicitadosResultados) {
            mudar = menu.add("Mudar de Exames Solicitados");
            mudar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Intent intent = new Intent(getContext(), ListaExamesSolicitadosResultadosActivity.class);
                    startActivityForResult(intent, 6);
                    return false;
                }
            });
        }

        if (v.getId() == R.id.uLlUltrassonografia) {
            mudar = menu.add("Mudar de Ultrassonografia");
            mudar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Intent intent = new Intent(getContext(), ListaUltrassonografiasActivity.class);
                    startActivityForResult(intent, 4);
                    return false;
                }
            });
        }

        if (v.getId() == R.id.cmLlConsultasMensais) {
            mudar = menu.add("Mudar de Consulta");
            mudar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Intent intent = new Intent(getContext(), ListaConsultasMensaisActivity.class);
                    startActivityForResult(intent, 2);
                    return false;
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            carregaConsultasMensais(view, -1);

        } else if (requestCode == 2 && resultCode == RESULT_OK) {

            int numeroConsulta = data.getIntExtra("numeroConsulta", -1);
            carregaConsultasMensais(view, numeroConsulta);

        } else if (requestCode == 3 && resultCode == RESULT_OK) {

            carregaUltrassonografia(view, -1);

        } else if (requestCode == 4 && resultCode == RESULT_OK) {

            int consultaSolicitacao = data.getIntExtra("consultaSolicitacao", -1);
            carregaUltrassonografia(view, consultaSolicitacao);

        } else if (requestCode == 5 && resultCode == RESULT_OK) {

            carregaExamesSolicitadosResultados(view, -1);

        } else if (requestCode == 6 && resultCode == RESULT_OK) {

            int consultaSolicitacao = data.getIntExtra("consultaSolicitacao", -1);
            carregaExamesSolicitadosResultados(view, consultaSolicitacao);
        }
    }
}
