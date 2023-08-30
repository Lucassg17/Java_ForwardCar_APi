package steps;

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
    Response resultadoGetFabrica;
    Response resultadoPostEmprestimo;

    /////////////////@Teste001 @%ForwardCar/////////////////

    @Dado("que crio um registro")
    public void queCrioUmRegistro(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellDadosCriandoRegistro(list.get(0).get("planilha"), list.get(0).get("aba"));
        Metodos.geraRegistro();
    }

    @Quando("crio meu login")
    public void crioMeuLogin(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        Excel.getCellDadosCriandoLogin(list.get(0).get("planilha"), list.get(0).get("aba"));
    }

    @E("envio os dados para realizar um metodo Post em Emprestimo")
    public void envioOsDadosParaRealizarUmMetodoPostEmEmprestimo(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        Excel.getCellDadosCriandoEmprestimo(list.get(0).get("planilha"), list.get(0).get("aba"));
        resultadoPostEmprestimo = Metodos.recebeTokenERealizaPostEmprestimo();
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
        //Metodos.realizaLogin();
    }

    @E("envio um metodo Get pata Emprestimo")
    public void envioUmMetodoGetPataEmprestimo() {
        resultadoGetEmprestimo = Metodos.recebeEmprestimoCriadoERealizaGetEmprestimo();
    }

    @Entao("ao enviar uma requisicao Get para Emprestimos valido que a acao ocorreu com sucesso")
    public void aoEnviarUmaRequisicaoGetParaEmprestimosValidoQueAAcaoOcorreuComSucesso() {
        //resultadoGetEmprestimo.then().log().all();
        //Assert.assertEquals(resultadoGetEmprestimo.jsonPath().getString("firstName"), "henrique");
    }

    /////////////////@Teste003 @%ForwardCar/////////////////

    @Dado("que realizo um metodo Get em Fabrica")
    public void queRealizoUmMetodoGetEmFabrica() {
        resultadoGetFabrica = Metodos.retornaFabrica();
    }

    @Entao("valido o tamanho da lista de fabricas que retorna no body do meu response")
    public void validoOTamanhoDaListaDeFabricasQueRetornaNoBodyDoMeuResponse() {
        Assert.assertEquals(resultadoGetFabrica.jsonPath().getInt(AtributosJsonFabrica.size), 9);
    }

    /////////////////@Teste004 @%ForwardCar/////////////////

    @Quando("crio um metodo Post em Login")
    public void crioUmMetodoPostEmLogin(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        Excel.getCellDadosCriandoLogin(list.get(0).get("planilha"), list.get(0).get("aba"));
        resultadoPostLogin = Metodos.realizaLogin();
    }

    @Entao("valido que meu login foi realizado com sucesso")
    public void validoQueMeuLoginFoiRealizadoComSucesso() {
        Assert.assertEquals(resultadoPostLogin.jsonPath().getString(AtributosJsonLogin.userName), "lucassilvag");
    }

    /////////////////@Teste005 @%ForwardCar/////////////////

    @Dado("que realizo um metodo Get em Modelo")
    public void queRealizoUmMetodoGetEmModelo() {
    }
}
