package com.scriptpoin.gestacaosaudavel.web.server;

import com.scriptpoin.gestacaosaudavel.web.Constants;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {

    public String post(String json) {

        try {

            URL url = new URL(Constants.URL_ATUALIZAR_DADOS);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

//            connection.setRequestProperty("Content-type", "text/plain");

            connection.setRequestMethod("POST");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintStream saida = new PrintStream(connection.getOutputStream());
            saida.println(json);

            connection.connect();

            String resposta = new Scanner(connection.getInputStream()).next();

            System.out.println(resposta);

            return resposta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Não foi possível enviar os dados.\nTente novamente mais tarde...";
    }


}
