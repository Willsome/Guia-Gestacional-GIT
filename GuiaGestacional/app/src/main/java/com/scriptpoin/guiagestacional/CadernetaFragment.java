package com.scriptpoin.guiagestacional;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.caderneta.consultas_mensais.ConsultasMensais;
import com.scriptpoin.guiagestacional.caderneta.consultas_mensais.ConsultasMensaisActivity;
import com.scriptpoin.guiagestacional.caderneta.consultas_mensais.ConsultasMensaisHelper;
import com.scriptpoin.guiagestacional.caderneta.consultas_mensais.ListaConsultasMensaisActivity;
import com.scriptpoin.guiagestacional.caderneta.dados_obstetricos.DadosObstetricos;
import com.scriptpoin.guiagestacional.caderneta.dados_obstetricos.DadosObstetricosActivity;
import com.scriptpoin.guiagestacional.caderneta.dados_obstetricos.DadosObstetricosHelper;
import com.scriptpoin.guiagestacional.caderneta.dados_pessoais.DadosPessoais;
import com.scriptpoin.guiagestacional.caderneta.dados_pessoais.DadosPessoaisActivity;
import com.scriptpoin.guiagestacional.caderneta.dados_pessoais.DadosPessoaisHelper;
import com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultados;
import com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultadosActivity;
import com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultadosHelper;
import com.scriptpoin.guiagestacional.caderneta.ultrassonografia.Ultrassonografia;
import com.scriptpoin.guiagestacional.caderneta.ultrassonografia.UltrassonografiaActivity;
import com.scriptpoin.guiagestacional.caderneta.ultrassonografia.UltrassonografiaHelper;
import com.scriptpoin.guiagestacional.caderneta.uso_de_medicamento.UsoDeMedicamentoActivity;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadernetaFragment extends Fragment {

    private View view;

    private LayoutInflater inflater;

    public CadernetaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Caderneta da Gestante");

        this.inflater = inflater;

        this.view = inflater.inflate(R.layout.fragment_caderneta, container, false);

        Button dpBtAlterar = (Button) view.findViewById(R.id.dpBtAlterar);
        dpBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DadosPessoaisActivity.class);
                startActivity(intent);
            }
        });

        Button doBtAlterar = (Button) view.findViewById(R.id.doBtAlterar);
        doBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DadosObstetricosActivity.class);
                startActivity(intent);
            }
        });

        Button esBtAlterar = (Button) view.findViewById(R.id.esBtAlterar);
        esBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ExamesSolicitadosResultadosActivity.class);
                startActivity(intent);
            }
        });

        Button uBtAlterar = (Button) view.findViewById(R.id.uBtAlterar);
        uBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UltrassonografiaActivity.class);
                startActivity(intent);
            }
        });

        Button umBtAlterar = (Button) view.findViewById(R.id.umBtAlterar);
        umBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UsoDeMedicamentoActivity.class);
                startActivity(intent);
            }
        });

        Button cmBtAdicionar = (Button) view.findViewById(R.id.cmBtAdicionar);
        cmBtAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConsultasMensaisActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        carregaConsultasMensais(view, -1);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        carregaDadosPessoais(view);
        carregaDadosObstetricos(view);
        carregaExamesSolicitadosResultados(view);
        carregaUltrassonografia(view);
        carregaUsoDeMedicamento(view);
    }

    public void carregaDadosPessoais(View view) {
        DaoCaderneta daoCaderneta = new DaoCaderneta(getContext());
        DadosPessoais dp = daoCaderneta.pegaDadosPessoais();
        daoCaderneta.close();

        if (dp.getNome() != null) {
            DadosPessoaisHelper dph = new DadosPessoaisHelper(getActivity(), 2, view);
            dph.preencheDadosPessoais(dp);
        }
    }

    public void carregaDadosObstetricos(View view) {
        DaoCaderneta daoCaderneta = new DaoCaderneta(getContext());
        DadosObstetricos dadosObstetricos = daoCaderneta.pegaDadosObstetricos();
        daoCaderneta.close();

        if (dadosObstetricos.getDum() != null) {
            DadosObstetricosHelper doh = new DadosObstetricosHelper(getActivity(), 2, view);
            doh.preencheDadosObstetricos(dadosObstetricos);
        }
    }

    public void carregaExamesSolicitadosResultados(View view) {
        DaoCaderneta daoCaderneta = new DaoCaderneta(getContext());
        ExamesSolicitadosResultados examesSolicitadosResultados = daoCaderneta.pegaExamesSolicitadosResultados();
        daoCaderneta.close();

        ExamesSolicitadosResultadosHelper esh = new ExamesSolicitadosResultadosHelper(getActivity(), 2, view);
        esh.preencheExamesSolicitadosResultados(examesSolicitadosResultados);
    }

    public void carregaUltrassonografia(View view) {
        DaoCaderneta daoCaderneta = new DaoCaderneta(getContext());
        Ultrassonografia ultrassonografia = daoCaderneta.pegaUltrassonografia();
        daoCaderneta.close();

        if (ultrassonografia.getData() != null) {
            UltrassonografiaHelper uh = new UltrassonografiaHelper(getActivity(), 2, view);
            uh.preencheUltrassonografia(ultrassonografia);
        }
    }

    public void carregaUsoDeMedicamento(View view) {
        DaoCaderneta daoCaderneta = new DaoCaderneta(getContext());
        ArrayList<String> medicamentos = daoCaderneta.pegaUsoDeMedicamento();
        daoCaderneta.close();

        CheckBox umCbSim = (CheckBox) view.findViewById(R.id.umCbSim);
        CheckBox umCbNao = (CheckBox) view.findViewById(R.id.umCbNao);

        LinearLayout umLlMedicamento = (LinearLayout) view.findViewById(R.id.umLlMedicamento);

        if (medicamentos.size() > 0) {

            umLlMedicamento.removeAllViews();

            umCbSim.setChecked(true);
            umCbNao.setChecked(false);

            for (int i = 0; i < medicamentos.size(); i++) {

                View innerView = inflater.inflate(R.layout.layout_uso_de_medicamento, null);

                TextView umTvMedicamento = (TextView) innerView.findViewById(R.id.umTvMedicamento);
                umTvMedicamento.setText(i + 1 + ") " + medicamentos.get(i));

                umLlMedicamento.addView(innerView);
            }

        } else {
            umCbSim.setChecked(false);
            umCbNao.setChecked(true);

            umLlMedicamento.removeAllViews();
        }
    }

    public void carregaConsultasMensais(View view, int filtro) {

        DaoCaderneta dao = new DaoCaderneta(getContext());

        consultasMensais = dao.pegaConsultasMensais(filtro);

        dao.close();

        LinearLayout cmLlConsultasMensais = (LinearLayout) view.findViewById(R.id.cmLlConsultasMensais);

        cmLlConsultasMensais.removeAllViews();

        if (consultasMensais.getNomeDoProfissional() != null) {

            registerForContextMenu(cmLlConsultasMensais);

            LayoutInflater inflater = LayoutInflater.from(getActivity());
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

    ConsultasMensais consultasMensais;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getActivity(), ConsultasMensaisActivity.class);
                intent.putExtra("consultaMensal", consultasMensais);
                startActivity(intent);
                return false;
            }
        });

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                DaoCaderneta dao = new DaoCaderneta(getContext());
                if (dao.deletaConsultasMensais(consultasMensais.getNumeroConsulta())) {
                    carregaConsultasMensais(view, -1);
                }
                return false;
            }
        });

        MenuItem mudarConsulta = menu.add("Mudar de Consulta");
        mudarConsulta.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getContext(), ListaConsultasMensaisActivity.class);
                startActivityForResult(intent, 2);
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            carregaConsultasMensais(view, -1);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            int numeroConsulta = data.getIntExtra("numeroConsulta", -1);
            carregaConsultasMensais(view, numeroConsulta);
        }
    }
}
