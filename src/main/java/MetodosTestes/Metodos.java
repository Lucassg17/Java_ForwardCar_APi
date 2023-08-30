package MetodosTestes;

import AtributosJson.AtributosJsonEmprestimo;
import AtributosJson.AtributosJsonLogin;
import AtributosJson.AtributosJsonRegistro;
import EndPoints.EndPoints;
import Utils.ArquivoTxt;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Metodos {
    public static String uri = "http://localhost:3434/cars-app";
    private static List<DadosParaCriarRegistro> valorColunasRegistros = new ArrayList<>();
    private static List<DadosParaCriarLogin> valorColunasLogin = new ArrayList<>();
    private static List<DadosParaCriarEmprestimo> valorColunasEmprestimo = new ArrayList<>();
    private static HashMap<String, String> token = new HashMap<>();

    public static Response retornaFabrica(){
        Response fabrica =
                given()
                .when()
                    .get(uri + EndPoints.fabrica)
                .then()
                    .extract().response();

        return fabrica;
    }

    public static Response geraRegistro(){
        Response registro =
                given()
                    .contentType(ContentType.JSON)
                    .body(geraCorpoRegistro())
                .when()
                    .post(uri + EndPoints.registro)
                .then()
                    .extract().response();

        return registro;
    }

    public static Response realizaLogin(){
        Response login =
                given()
                    .contentType(ContentType.JSON)
                    .body(geraCorpoLogin())
                .when()
                    .post(uri + EndPoints.login)
                .then()
                    .extract().response();

        return login;
    }

    public static Response recebeTokenERealizaPostEmprestimo(){
        Response tokenCriado = realizaLogin();

        token.put("Authorization", "Bearer " + tokenCriado.jsonPath().getString("access_token"));

        Response postEmprestimo =
                            given()
                                .headers(token)
                                .body(geraCorpoEmprestimo())
                                .contentType(ContentType.JSON)
                            .when()
                                .post(uri + EndPoints.emprestimo)
                            .then()
                                .extract().response();

        return postEmprestimo;
    }

    public static Response recebeEmprestimoCriadoERealizaGetEmprestimo(){
        Response emprestimoCriado = recebeTokenERealizaPostEmprestimo();

        token.put("Authorization", "Bearer " + emprestimoCriado.jsonPath().getString("access_token"));

        Response getEmprestimo =
                            given()
                                .headers(token)
                            .when()
                                .get(uri + EndPoints.emprestimo)
                            .then()
                                .extract().response();

        return getEmprestimo;
    }


    public static String geraCorpoRegistro(){
        JSONObject registro = new JSONObject();
        //DadosParaCriarRegistro registro1;

        valorColunasRegistros.add(new DadosParaCriarRegistro(
                ArquivoTxt.lerArquivo("FIRSTNAME_1", "Dados_Excel-criarRegistro.txt"),
                ArquivoTxt.lerArquivo("LASTNAME_1", "Dados_Excel-criarRegistro.txt"),
                ArquivoTxt.lerArquivo("USERNAME_1", "Dados_Excel-criarRegistro.txt"),
                ArquivoTxt.lerArquivo("PASSWORD_1", "Dados_Excel-criarRegistro.txt")));

        for (int i = 0; i < valorColunasRegistros.size(); i++) {
            registro.put(AtributosJsonRegistro.firstName, valorColunasRegistros.get(i).getFirstName());
            registro.put(AtributosJsonRegistro.lastName, valorColunasRegistros.get(i).getLastName());
            registro.put(AtributosJsonRegistro.userName, valorColunasRegistros.get(i).getUserName());
            registro.put(AtributosJsonRegistro.passWord, valorColunasRegistros.get(i).getPassword());
        }

        return registro.toString();
    }

    public static String geraCorpoLogin(){
        JSONObject login = new JSONObject();
        //DadosParaCriarLogin login1;

        valorColunasLogin.add(new DadosParaCriarLogin(
                ArquivoTxt.lerArquivo("USERNAME_1", "Dados_Excel-criarLogin.txt"),
                ArquivoTxt.lerArquivo("PASSWORD_1", "Dados_Excel-criarLogin.txt")));

        for (int i = 0; i < valorColunasLogin.size(); i++){
            login.put(AtributosJsonLogin.userName, valorColunasLogin.get(i).getUserName());
            login.put(AtributosJsonLogin.passWord, valorColunasLogin.get(i).getPassword());
        }

        return login.toString();
    }

    public static String geraCorpoEmprestimo(){
        JSONObject emprestimo = new JSONObject();
        //DadosParaCriarEmprestimo emprestimo1;

        valorColunasEmprestimo.add(new DadosParaCriarEmprestimo(
                ArquivoTxt.lerArquivo("FIRSTNAME_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("LASTNAME_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("ADDRESS1_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("CITY_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("STATE_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("ZIP_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("COUNTRY_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("DOB_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("SSN_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("EMPLOYER_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("PHONENUMBER_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("DURATIONOFJOB_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("INCOME_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("LOANTERM_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("LOANAMOUNT_1", "Dados_Excel-criarEmprestimo.txt"),
                ArquivoTxt.lerArquivo("VALIDATEADDRESS_1", "Dados_Excel-criarEmprestimo.txt")));

        for (int i = 0; i < valorColunasEmprestimo.size(); i++){
            emprestimo.put(AtributosJsonEmprestimo.firstName, valorColunasEmprestimo.get(i).getFirstName());
            emprestimo.put(AtributosJsonEmprestimo.lastName, valorColunasEmprestimo.get(i).getLastName());
            emprestimo.put(AtributosJsonEmprestimo.address1, valorColunasEmprestimo.get(i).getAddress1());
            emprestimo.put(AtributosJsonEmprestimo.city, valorColunasEmprestimo.get(i).getCity());
            emprestimo.put(AtributosJsonEmprestimo.state, valorColunasEmprestimo.get(i).getState());
            emprestimo.put(AtributosJsonEmprestimo.zip, valorColunasEmprestimo.get(i).getZip());
            emprestimo.put(AtributosJsonEmprestimo.country, valorColunasEmprestimo.get(i).getCountry());
            emprestimo.put(AtributosJsonEmprestimo.dob, valorColunasEmprestimo.get(i).getDob());
            emprestimo.put(AtributosJsonEmprestimo.ssn, valorColunasEmprestimo.get(i).getSsn());
            emprestimo.put(AtributosJsonEmprestimo.employer, valorColunasEmprestimo.get(i).getEmployer());
            emprestimo.put(AtributosJsonEmprestimo.phoneNumber, valorColunasEmprestimo.get(i).getPhoneNumber());
            emprestimo.put(AtributosJsonEmprestimo.durationOfJob, valorColunasEmprestimo.get(i).getDurationOfJob());
            emprestimo.put(AtributosJsonEmprestimo.income, valorColunasEmprestimo.get(i).getIncome());
            emprestimo.put(AtributosJsonEmprestimo.loanTerm, valorColunasEmprestimo.get(i).getLoanTerm());
            emprestimo.put(AtributosJsonEmprestimo.loanAmount, valorColunasEmprestimo.get(i).getLoanAmount());
            emprestimo.put(AtributosJsonEmprestimo.validateAddress, valorColunasEmprestimo.get(i).getValidateAddress());
        }

        return emprestimo.toString();
    }
}
