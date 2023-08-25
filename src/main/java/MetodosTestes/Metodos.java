package MetodosTestes;

import Constantes.AtributosJsonRegistro;
import Utils.ArquivoTxt;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;

public class Metodos {
    public static String uri = "http://localhost:3434/cars-app";

    private static List<DadosParaCriarRegistro> valorColunas = new ArrayList<>();
    public static HashMap<String, String> token = new HashMap<>();

    public static Response geraRegistro(){
        Response registro =
                given()
                    .contentType("application/json")
                    .body(geraCorpoRegistro())
                .when()
                    .post(uri + "/register")
                .then()
                    .extract().response();

        return registro;
    }

    public static Response realizaLogin(){
        Response login =
                given()
                    .contentType("application/json")
                    .body(geraCorpoLogin())
                .when()
                    .post(uri + "/api/login")
                .then()
                    .extract().response();

        return login;
    }

    public static Response guardaToken(){
        Response emprestimo = realizaLogin();

        String meuToken = emprestimo.jsonPath().getString("access_token");
        token.put("Authorization", "Bearer " + meuToken);

        given()
            .headers(token)
        .when()
            .get(uri + "/api/loanApp")
        .then()
            .extract().response();

        return emprestimo;
    }

    public static String geraCorpoRegistro(){
        JSONObject registro = new JSONObject();
        DadosParaCriarRegistro registro1;

        valorColunas.add(registro1 = new DadosParaCriarRegistro(
                ArquivoTxt.lerArquivo("FIRSTNAME_1", "Dados_Excel-criarRegistro.txt"),
                ArquivoTxt.lerArquivo("LASTNAME_1", "Dados_Excel-criarRegistro.txt"),
                ArquivoTxt.lerArquivo("USERNAME_1", "Dados_Excel-criarRegistro.txt"),
                ArquivoTxt.lerArquivo("PASSWORD_1", "Dados_Excel-criarRegistro.txt")));

        for (int i = 0; i < valorColunas.size(); i++) {
            registro.put(AtributosJsonRegistro.firstName, valorColunas.get(i).getFirstName());
            registro.put(AtributosJsonRegistro.lastName, valorColunas.get(i).getLastName());
            registro.put(AtributosJsonRegistro.userName, valorColunas.get(i).getUserName());
            registro.put(AtributosJsonRegistro.passWord, valorColunas.get(i).getPassword());
        }

        return registro.toString();
    }

    public static String geraCorpoLogin(){
        JSONObject login = new JSONObject();
        login.put(AtributosJsonRegistro.userName, "lucassgomes");
        login.put(AtributosJsonRegistro.passWord, "teste123456");

        return login.toString();
    }
}
