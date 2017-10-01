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
import android.widget.Toast;

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

    private DadosPessoais dadosPessoais;
    private DadosObstetricos dadosObstetricos;
    private ExamesSolicitadosResultados examesSolicitadosResultados;
    private Ultrassonografia ultrassonografia;
    private ArrayList<String> medicamentos;
    private ConsultasMensais consultasMensais;


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
                intent.putExtra("dadosPessoais", dadosPessoais);
                startActivity(intent);
            }
        });

        Button doBtAlterar = (Button) view.findViewById(R.id.doBtAlterar);
        doBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DadosObstetricosActivity.class);
                intent.putExtra("dadosObstetricos", dadosObstetricos);
                startActivity(intent);
            }
        });

        Button esBtAlterar = (Button) view.findViewById(R.id.esBtAlterar);
        esBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ExamesSolicitadosResultadosActivity.class);
                intent.putExtra("examesSolicitadosResultados", examesSolicitadosResultados);
                startActivity(intent);
            }
        });

        Button uBtAlterar = (Button) view.findViewById(R.id.uBtAlterar);
        uBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UltrassonografiaActivity.class);
                intent.putExtra("ultrassonografia", ultrassonografia);
                startActivity(intent);
            }
        });

        Button umBtAlterar = (Button) view.findViewById(R.id.umBtAlterar);
        umBtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UsoDeMedicamentoActivity.class);
                intent.putExtra("medicamentos", medicamentos);
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

        DaoCaderneta dao = new DaoCaderneta(getContext());

        carregaDadosPessoais(view, dao);
        carregaDadosObstetricos(view, dao);
        carregaExamesSolicitadosResultados(view, dao);
        carregaUltrassonografia(view, dao);
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

    public void carregaExamesSolicitadosResultados(View view, DaoCaderneta dao) {
        examesSolicitadosResultados = dao.pegaExamesSolicitadosResultados();

        ExamesSolicitadosResultadosHelper examesSolicitadosResultadosHelper =
                new ExamesSolicitadosResultadosHelper(getActivity(), 2, view);

        if (examesSolicitadosResultados != null) {
            examesSolicitadosResultadosHelper.preencheExamesSolicitadosResultados(examesSolicitadosResultados);
        }
    }

    public void carregaUltrassonografia(View view, DaoCaderneta dao) {
        ultrassonografia = dao.pegaUltrassonografia();

        if (ultrassonografia != null) {
            UltrassonografiaHelper ultrassonografiaHelper = new UltrassonografiaHelper(getActivity(), 2, view);
            ultrassonografiaHelper.preencheUltrassonografia(ultrassonografia);
        }
    }

    public void carregaUsoDeMedicamento(View view, DaoCaderneta dao) {
        medicamentos = dao.pegaUsoDeMedicamento();

        CheckBox umCbSim = (CheckBox) view.findViewById(R.id.umCbSim);
        CheckBox umCbNao = (CheckBox) view.findViewById(R.id.umCbNao);

        LinearLayout umLlMedicamento = (LinearLayout) view.findViewById(R.id.umLlMedicamento);

        if (medicamentos.size() > 0) {

            umLlMedicamento.removeAllViews();

            umCbSim.setChecked(true);
            umCbNao.setChecked(false);

            for (int i = 0; i < medicamentos.size(); i++) {

                View innerView = inflater.inflate(R.layout.layout_uso_de_medicamento, null);

                TextView umTvNuMedicamento = (TextView) innerView.findViewById(R.id.umTvNuMedicamento);
                TextView umTvMedicamento = (TextView) innerView.findViewById(R.id.umTvMedicamento);

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
    public void carregaConsultasMensais(View view, int filtro) {

        DaoCaderneta dao = new DaoCaderneta(getContext());

        consultasMensais = dao.pegaConsultasMensais(filtro);

        dao.close();

        LinearLayout cmLlConsultasMensais = (LinearLayout) view.findViewById(R.id.cmLlConsultasMensais);

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getActivity(), ConsultasMensaisActivity.class);
                intent.putExtra("consultaMensal", consultasMensais);
                startActivityForResult(intent, 2);
                return false;
            }
        });

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                DaoCaderneta dao = new DaoCaderneta(getContext());
                if (dao.deletaConsultasMensais(consultasMensais)) {
                    carregaConsultasMensais(view, -1);
                    Toast.makeText(getContext(), "A consulta foi deletada !", Toast.LENGTH_SHORT).show();
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
