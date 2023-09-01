package steps;

import AtributosJson.AtributosJsonEmprestimo;
import AtributosJson.AtributosJsonFabrica;
import AtributosJson.AtributosJsonLogin;
import MetodosTestes.Metodos;
import Utils.Excel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.StringContains.containsString;

public class StepForwardCar {
    private static List<Map<String, String>> list;
    public static int linhas;
    Response resultadoGetEmprestimo;
    Response resultadoPostLogin;
    Response resultadoPostEmprestimo;

    /////////////////@Teste001 @%ForwardCar/////////////////

    @Dado("que crio um registro")
    public void queCrioUmRegistro(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellDadosCriandoRegistro(list.get(0).get("planilha"), list.get(0).get("aba"));
        Metodos.geraRegistro(linhas);
    }

    @Quando("crio meu login")
    public void crioMeuLogin(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        Excel.getCellDadosCriandoLogin(list.get(0).get("planilha"), list.get(0).get("aba"));
    }

    @E("envio os dados para realizar um metodo Post em Emprestimo")
    public void envioOsDadosParaRealizarUmMetodoPostEmEmprestimo(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellDadosCriandoEmprestimo(list.get(0).get("planilha"), list.get(0).get("aba"));
        resultadoPostEmprestimo = Metodos.recebeTokenERealizaPostEmprestimo(linhas);
    }

    @Entao("confirmo que meu post foi feito com sucesso validando o retorno do meu body")
    public void confirmoQueMeuPostFoiFeitoComSucessoValidandoORetornoDoMeuBody() {
        resultadoPostEmprestimo.then()
                                    .log().all()
                                    .body(containsString("status: accepted"));
    }

    /////////////////@Teste002 @%ForwardCar/////////////////

    @Quando("realizo o login")
    public void realizoOLogin(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        Excel.getCellDadosCriandoLogin(list.get(0).get("planilha"), list.get(0).get("aba"));
    }

    @E("envio um metodo Get para Emprestimo")
    public void envioUmMetodoGetParaEmprestimo(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellDadosCriandoEmprestimo(list.get(0).get("planilha"), list.get(0).get("aba"));
        resultadoGetEmprestimo = Metodos.recebeEmprestimoCriadoERealizaGetEmprestimo(linhas);
    }

    @Entao("ao enviar a requisicao valido o retorno do meu body")
    public void aoEnviarARequisicaoValidoQueORetornoDoMeuBody() {
        resultadoGetEmprestimo.then().log().all();
        Assert.assertEquals(resultadoGetEmprestimo.jsonPath().getString(AtributosJsonEmprestimo.firstName), "[lucas]");
    }

    /////////////////@Teste003 @%ForwardCar/////////////////

    @Quando("crio um metodo Post em Login")
    public void crioUmMetodoPostEmLogin(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellDadosCriandoLogin(list.get(0).get("planilha"), list.get(0).get("aba"));
        resultadoPostLogin = Metodos.realizaLogin(linhas);
    }

    @Entao("valido que meu login foi criado com sucesso")
    public void validoQueMeuLoginFoiCriadoComSucesso() {
        resultadoPostLogin.then().log().all();
        Assert.assertEquals(resultadoPostLogin.jsonPath().getString(AtributosJsonLogin.userName), "lucassilva");
    }
}
