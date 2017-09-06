package com.scriptpoin.guiagestacional;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadernetaFragment extends Fragment {

    private View view;

    public CadernetaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Caderneta do Pré-natal");

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


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        carregaDadosPessoais(view);
        carregaDadosObstetricos(view);
        carregaExamesSolicitadosResultados(view);
        carregaUltrassonografia(view);
    }

    public void carregaDadosPessoais(View view) {
        DaoCaderneta daoCaderneta = new DaoCaderneta(getContext());
        DadosPessoais dp = daoCaderneta.pegaDadosPessoais();
        daoCaderneta.close();

        if(dp.getNome() != null) {
            DadosPessoaisHelper dph = new DadosPessoaisHelper(getActivity(), 2, view);
            dph.preencheDadosPessoais(dp);
        }
    }

    public void carregaDadosObstetricos(View view) {
        DaoCaderneta daoCaderneta = new DaoCaderneta(getContext());
        DadosObstetricos dadosObstetricos = daoCaderneta.pegaDadosObstetricos();
        daoCaderneta.close();

        if(dadosObstetricos.getDum() != null) {
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

        if(ultrassonografia.getData() != null) {
            UltrassonografiaHelper uh = new UltrassonografiaHelper(getActivity(), 2, view);
            uh.preencheUltrassonografia(ultrassonografia);
        }
    }

}
