package com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.scriptpoin.gestacaosaudavel.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Willi on 02-Sep-17.
 */

public class ExamesSolicitadosResultadosHelper {

    // FORMULÁRIO
    private CheckBox esCbSolicitacao;
    private CheckBox esCbResultado;
    private EditText esEtConsultaSolicitacao;
    private EditText esEtConsultaResultado;
    private Spinner esSpAboRh;
    private EditText esEtGlicemiaJejum;
    private EditText esEthematocrito;
    private EditText esEthemoglobina;
    private Spinner esSpHepatiteB;
    private Spinner esSpHiv;
    private EditText esEtToleranciaGlicose;
    private Spinner esSpToxoplasmoseIgg;
    private Spinner esSpToxoplasmoseIgm;
    private EditText esEtUrinaTipo1;
    private Spinner esSpUrocultura;
    private Spinner esSpVdrl;

    // CADERNETA

//    private ExamesSolicitadosResultados examesSolicitadosResultados;

    private ArrayList<View> camposComOResultado = new ArrayList<>();


    public ExamesSolicitadosResultadosHelper(Activity activity, int i, ArrayList<Exame> examesSolicitados, ArrayList<View> viewsDosExames) {

        if (i == 1) {
            esCbSolicitacao = activity.findViewById(R.id.esCbSolicitacao);
            esCbResultado = activity.findViewById(R.id.esCbResultado);
            esEtConsultaSolicitacao = activity.findViewById(R.id.esEtConsultaSolicitacao);
            esEtConsultaResultado = activity.findViewById(R.id.esEtConsultaResultado);

            for (int j = 0; j < viewsDosExames.size(); j++) {
                if (examesSolicitados.get(j).getId() == 1) {
                    esSpAboRh = (Spinner) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 1);
                    camposComOResultado.add(esSpAboRh);
                } else if (examesSolicitados.get(j).getId() == 2) {
                    esEtGlicemiaJejum = (EditText) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 2);
                    camposComOResultado.add(esEtGlicemiaJejum);
                } else if (examesSolicitados.get(j).getId() == 3) {
                    esEthematocrito = (EditText) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 3);
                    camposComOResultado.add(esEthematocrito);
                } else if (examesSolicitados.get(j).getId() == 4) {
                    esEthemoglobina = (EditText) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 4);
                    camposComOResultado.add(esEthemoglobina);
                } else if (examesSolicitados.get(j).getId() == 5) {
                    esSpHepatiteB = (Spinner) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 5);
                    camposComOResultado.add(esSpHepatiteB);
                } else if (examesSolicitados.get(j).getId() == 6) {
                    esSpHiv = (Spinner) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 6);
                    camposComOResultado.add(esSpHiv);
                } else if (examesSolicitados.get(j).getId() == 7) {
                    esEtToleranciaGlicose = (EditText) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 7);
                    camposComOResultado.add(esEtToleranciaGlicose);
                } else if (examesSolicitados.get(j).getId() == 8) {
                    esSpToxoplasmoseIgg = (Spinner) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 8);
                    camposComOResultado.add(esSpToxoplasmoseIgg);
                } else if (examesSolicitados.get(j).getId() == 9) {
                    esSpToxoplasmoseIgm = (Spinner) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 9);
                    camposComOResultado.add(esSpToxoplasmoseIgm);
                } else if (examesSolicitados.get(j).getId() == 10) {
                    esEtUrinaTipo1 = (EditText) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 10);
                    camposComOResultado.add(esEtUrinaTipo1);
                } else if (examesSolicitados.get(j).getId() == 11) {
                    esSpUrocultura = (Spinner) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 11);
                    camposComOResultado.add(esSpUrocultura);
                } else if (examesSolicitados.get(j).getId() == 12) {
                    esSpVdrl = (Spinner) ((activity.findViewById((int) viewsDosExames.get(j).getId())).findViewById(R.id.esLlResultado)).findViewById((int) 12);
                    camposComOResultado.add(esSpVdrl);
                }
            }

        }

//        this.examesSolicitadosResultados = new ExamesSolicitadosResultados();
    }


    public ArrayList<ExamesSolicitadosResultados> pegaExamesSolicitadosResultados(ArrayList<Exame> examesSolicitados) throws Exception {

        ArrayList<ExamesSolicitadosResultados> ListaDeExamesSolicitadosResultados =
                new ArrayList<>();

        if (esCbSolicitacao.isChecked() && !esEtConsultaSolicitacao.getText().toString().equals("")) {
            if (examesSolicitados.size() == 0) {
                throw new Exception("Escolha ao menos um exame solicitado...");

            } else {
                Collections.sort(examesSolicitados);
                for (int i = 0; i < examesSolicitados.size(); i++) {
                    ExamesSolicitadosResultados es = new ExamesSolicitadosResultados();
                    es.setSolicitacao(1L);
                    es.setNumeroConsultaSolicitacao(Long.parseLong(esEtConsultaSolicitacao.getText().toString()));
                    es.setExame(examesSolicitados.get(i));

//            es.setId(this.examesSolicitadosResultados.getId());

                    ListaDeExamesSolicitadosResultados.add(es);
                }
            }

        } else if (!esCbSolicitacao.isChecked() && !esCbResultado.isChecked()) {
            throw new Exception("Escolha uma das opções acima...");

        } else if (esCbSolicitacao.isChecked() && esEtConsultaSolicitacao.getText().toString().equals("")
                || esCbResultado.isChecked() && esEtConsultaSolicitacao.getText().toString().equals("") || esEtConsultaResultado.getText().toString().equals("")) {
            throw new Exception("Existem campos não preenchidos...");

        } else {

            if (examesSolicitados.size() == 0) {
                throw new Exception("Escolha ao menos um exame solicitado...");

            } else {

                for (int i = 0; i < examesSolicitados.size(); i++) {

                    if (examesSolicitados.get(i).getId() == 2
                            || examesSolicitados.get(i).getId() == 3
                            || examesSolicitados.get(i).getId() == 4
                            || examesSolicitados.get(i).getId() == 7
                            || examesSolicitados.get(i).getId() == 10) {
                        if (esCbResultado.isChecked() && esEtConsultaSolicitacao.getText().toString().equals("")
                                || esEtConsultaResultado.getText().toString().equals("")
                                || ((EditText) camposComOResultado.get(i)).getText().toString().equals("")) {
                            throw new Exception("Existem campos não preenchidos...");

                        } else {
                            adicionaParaListaDeExamesSolicitados(examesSolicitados, ListaDeExamesSolicitadosResultados, i);
                        }

                    } else if (esCbResultado.isChecked() && esEtConsultaSolicitacao.getText().toString().equals("")
                            || esEtConsultaResultado.getText().toString().equals("")
                            || ((Spinner) camposComOResultado.get(i)).getSelectedItem().toString().equals("")) {
                        throw new Exception("Existem campos não preenchidos...");

                    } else {
                        adicionaParaListaDeExamesSolicitados(examesSolicitados, ListaDeExamesSolicitadosResultados, i);
                    }
                }
            }
        }

        return ListaDeExamesSolicitadosResultados;
    }

    public void adicionaParaListaDeExamesSolicitados(ArrayList<Exame> examesSolicitados, ArrayList<ExamesSolicitadosResultados> listaDeExamesSolicitadosResultados, int i) {
        ExamesSolicitadosResultados es = new ExamesSolicitadosResultados();
//            es.setId(this.examesSolicitadosResultados.getId());
        es.setNumeroConsultaSolicitacao(Long.parseLong(esEtConsultaSolicitacao.getText().toString()));
        es.setSolicitacao(0L);
        es.setNumeroConsultaResultado(Long.parseLong(esEtConsultaResultado.getText().toString()));
        es.setExame(examesSolicitados.get(i));

        if (examesSolicitados.get(i).getId() == 2
                || examesSolicitados.get(i).getId() == 3
                || examesSolicitados.get(i).getId() == 4
                || examesSolicitados.get(i).getId() == 7
                || examesSolicitados.get(i).getId() == 10) {
            es.setResultado(((EditText) camposComOResultado.get(i)).getText().toString());
        } else {
            es.setResultado(((Spinner) camposComOResultado.get(i)).getSelectedItem().toString());
        }

        listaDeExamesSolicitadosResultados.add(es);
    }

}