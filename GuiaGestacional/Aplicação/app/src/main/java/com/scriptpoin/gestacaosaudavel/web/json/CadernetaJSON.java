package com.scriptpoin.gestacaosaudavel.web.json;

import com.scriptpoin.gestacaosaudavel.caderneta.consultas_mensais.ConsultasMensais;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_obstetricos.DadosObstetricos;
import com.scriptpoin.gestacaosaudavel.caderneta.dados_pessoais.DadosPessoais;
import com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultados;
import com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia.Ultrassonografia;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;

public class CadernetaJSON {

    private JSONStringer stringer;

    public String toJSON(DadosPessoais dadosPessoais,
                         DadosObstetricos dadosObstetricos,
                         ArrayList<ExamesSolicitadosResultados> examesSolicitadosResultados,
                         ArrayList<Ultrassonografia> ultrassonografias,
                         ArrayList<String> medicamentos,
                         ArrayList<ConsultasMensais> consultasMensais,
                         int id) {

        try {

            stringer = new JSONStringer();

            stringer.object().key("dados_da_gestante").array();
            dadosPessoaisToJSON(stringer, dadosPessoais);
            dadosObstetricosToJSON(stringer, dadosObstetricos);
            ultrassonografiaToJSON(stringer, ultrassonografias);
            usoDeMedicamentosToJSON(stringer, medicamentos);
            examesSolicitadosResultadosToJSON(stringer, examesSolicitadosResultados);
            consultasMensaisToJSON(stringer, consultasMensais);
            stringer.object().key("usuario").value(id).endObject()
                    .endArray().endObject();

            return stringer.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    public JSONStringer dadosPessoaisToJSON(JSONStringer stringer, DadosPessoais dadosPessoais) {

        try {
            stringer.object().key("dados_pessoais")
                    .object()
                    .key("id").value(dadosPessoais.getId())
                    .key("nome").value(dadosPessoais.getNome())
                    .key("data_nascimento").value(dadosPessoais.getDataNascimento().getTimeInMillis())
                    .key("idade").value(dadosPessoais.getIdade())
                    .key("endereco").value(dadosPessoais.getEndereco())
                    .key("nome_companheiro").value(dadosPessoais.getNomeCompanheiro())
                    .endObject()
                    .endObject();

            return stringer;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONStringer dadosObstetricosToJSON(JSONStringer stringer, DadosObstetricos dadosObstetricos) {
        try {
            stringer.object().key("dados_obstetricos")
                    .object()
                    .key("id").value(dadosObstetricos.getId())
                    .key("dum").value(dadosObstetricos.getDum().getTimeInMillis())
                    .key("dpp").value(dadosObstetricos.getDpp().getTimeInMillis())
                    .endObject()
                    .endObject();

            return stringer;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONStringer examesSolicitadosResultadosToJSON(JSONStringer stringer, ArrayList<ExamesSolicitadosResultados> examesSolicitadosResultados) {
        try {
            stringer.object().key("exames_solicitados_resultados").array();

            for (ExamesSolicitadosResultados exameSolicitadoResultado : examesSolicitadosResultados) {
                stringer.object()
                        .key("id").value(exameSolicitadoResultado.getId())
                        .key("solicitacao").value(exameSolicitadoResultado.getSolicitacao())
                        .key("numero_consulta_solicitacao").value(exameSolicitadoResultado.getNumeroConsultaSolicitacao())
                        .key("numero_consulta_resultado").value(exameSolicitadoResultado.getNumeroConsultaResultado())
                        .key("exame_id").value(exameSolicitadoResultado.getExame().getId())
                        .key("resultado").value(exameSolicitadoResultado.getResultado())
                        .endObject();
            }

            stringer.endArray().endObject();

            return stringer;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONStringer ultrassonografiaToJSON(JSONStringer stringer, ArrayList<Ultrassonografia> ultrassonografias) {
        try {
            stringer.object().key("ultrassonografia").array();

            for (Ultrassonografia ultrassonografia : ultrassonografias) {
                stringer.object()
                        .key("id").value(ultrassonografia.getId())
                        .key("solicitacao").value(ultrassonografia.getSolicitacao())
                        .key("numero_consulta_solicitacao").value(ultrassonografia.getNumeroConsultaSolicitacao())
                        .key("numero_consulta_resultado").value(ultrassonografia.getNumeroConsultaResultado())
                        .key("data").value(ultrassonografia.getData().getTimeInMillis())
                        .key("ig_dum_semanas").value(ultrassonografia.getIgDumSemanas())
                        .key("ig_dum_dias").value(ultrassonografia.getIgDumDias())
                        .key("ig_usg_semanas").value(ultrassonografia.getIgUsgSemanas())
                        .key("ig_usg_dias").value(ultrassonografia.getIgUsgDias())
                        .key("peso_fetal").value(ultrassonografia.getPesoFetal())
                        .key("placenta").value(ultrassonografia.getPlacenta())
                        .key("liquido_amniotico").value(ultrassonografia.getLiquidoAmniotico())
                        .endObject();
            }

            stringer.endArray().endObject();

            return stringer;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONStringer usoDeMedicamentosToJSON(JSONStringer stringer, ArrayList<String> medicamentos) {
        try {
            stringer.object().key("medicamentos").array();

            for (String medicamento : medicamentos) {
                stringer.value(medicamento);
            }

            stringer.endArray().endObject();

            return stringer;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONStringer consultasMensaisToJSON(JSONStringer stringer, ArrayList<ConsultasMensais> consultasMensais) {
        try {
            stringer.object().key("consultas_mensais").array();

            for (ConsultasMensais consultaMensal : consultasMensais) {
                stringer.object()
                        .key("numero").value(consultaMensal.getNumeroConsulta())
                        .key("data_consulta").value(consultaMensal.getDataConsulta().getTimeInMillis())
                        .key("queixa").value(consultaMensal.getQueixa())
                        .key("ig").value(consultaMensal.getIg())
                        .key("peso").value(consultaMensal.getPeso())
                        .key("imc").value(consultaMensal.getImc())
                        .key("edema").value(consultaMensal.getEdema())
                        .key("pa_i").value(consultaMensal.getPaI())
                        .key("pa_ii").value(consultaMensal.getPaII())
                        .key("altura_uterina").value(consultaMensal.getAlturaUterina())
                        .key("posicao_fetal").value(consultaMensal.getPosicaoFetal())
                        .key("bcf").value(consultaMensal.getBcf())
                        .key("movimento_fetal").value(consultaMensal.getMovFetal())
                        .key("tipo_profissional").value(consultaMensal.getTipoProfissional())
                        .key("nome_do_profissional").value(consultaMensal.getNomeDoProfissional())
                        .endObject();
            }

            stringer.endArray().endObject();

            return stringer;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }



}
