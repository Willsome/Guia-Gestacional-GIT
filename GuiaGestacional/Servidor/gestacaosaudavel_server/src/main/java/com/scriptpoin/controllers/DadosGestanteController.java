package com.scriptpoin.controllers;

import com.mysql.cj.xdevapi.JsonArray;
import com.scriptpoin.helper.DadosDaGestanteHelper;
import com.scriptpoin.models.DadosDaGestante;
import com.scriptpoin.models.Usuario;
import com.scriptpoin.repository.UsuarioRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class DadosGestanteController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @RequestMapping(value = "/atualizar-dados", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public @ResponseBody
    void teste(@RequestBody String dadosJson, HttpServletResponse response) throws IOException {

        JSONObject dados = new JSONObject(dadosJson);

        JSONArray dadosDaGestanteJSON = dados.getJSONArray("dados_da_gestante");

        DadosDaGestanteHelper helper = new DadosDaGestanteHelper();

        for (int i = 0; i < dadosDaGestanteJSON.length(); i++) {

            JSONObject dadosDaGestanteJSONTmp = (JSONObject) dadosDaGestanteJSON.get(i);

            switch (i) {
                case 0:
                    helper.addDadosPessoais(dadosDaGestanteJSONTmp);
                    break;
                case 1:
                    helper.addDadosObstetricos(dadosDaGestanteJSONTmp);
                    break;
                case 2:
                    helper.addUltrassonografia(dadosDaGestanteJSON);
                    break;
            }
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();

        json.put("mensagem", "Seus dados foram atualizados com sucesso !");
        out.println(json);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public @ResponseBody
    void cadastrarUsuario(
            @RequestParam("nome") String campoNome,
            @RequestParam("email") String campoEmail,
            @RequestParam("usuario") String campoUsuario,
            @RequestParam("senha") String campoSenha,
            HttpServletResponse response) throws IOException {

        JSONObject json = new JSONObject();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Usuario usuario = new Usuario(campoNome, campoEmail, campoUsuario, campoSenha);

        try {

            usuarioRepository.save(usuario);
            json.put("mensagem", "Usuário cadastrado com sucesso !");
            out.println(json);
        } catch (Exception e) {
            json.put("mensagem", "Ocorreu um erro ao tentar criar seu usuário.\nTente novamente...");
            out.println(json);
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    void loginUsuario(
            @RequestParam("usuario") String campoUsuario,
            @RequestParam("senha") String campoSenha,
            HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();

        try {

            Usuario usuario = usuarioRepository.findByUsuarioAndSenha(campoUsuario, campoSenha);

            if (usuario != null) {
                json
                        .put("mensagem", "")
                        .put("id", usuario.getId())
                        .put("nome", usuario.getNome())
                        .put("email", usuario.getEmail())
                        .put("usuario", usuario.getUsuario())
                ;

                out.println(json);

            } else {
                json.put("mensagem", "Login incorreto");
                out.println(json);
            }

        } catch (Exception e) {
            json.put("mensagem", "Ocorreu um erro ao tentar fazer login...Tente novamente");
            out.println(json);
        }
    }

}