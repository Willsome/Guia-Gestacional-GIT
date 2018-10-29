package com.scriptpoin.gestacaosaudavel.web;

public interface Constants {

    String ROOT_URL_XAMPP = "http://192.168.0.13:888/android/v1/";
    String ROOT_URL_TOMCAT = "http://192.168.0.13:8080";
    String ROOT_URL_PROD = "http://gestacaosaudavel.com.br";

    String URL_REGISTER = ROOT_URL_XAMPP + "registerUser.php";
    String URL_LOGIN = ROOT_URL_XAMPP + "userLogin.php";

    String URL_CADASTRO = ROOT_URL_PROD + "/cadastrar";
    String URL_FAZER_LOGIN = ROOT_URL_PROD + "/login";
    String URL_ATUALIZAR_DADOS = ROOT_URL_PROD + "/atualizar-dados";
}