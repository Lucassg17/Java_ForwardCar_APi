package steps;

import MetodosTestes.DadosParaCriarRegistro;
import MetodosTestes.Metodos;
import Utils.ArquivoTxt;
import Utils.Excel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StepForwardCar {
    private static List<Map<String, String>> list;
    Response resultadoGetEmprestimo;
    private static int linhasDeRegistro;
    private static List<DadosParaCriarRegistro> valorColunas = new ArrayList<>();

    @Dado("que crio um registro")
    public void queCrioUmRegistro(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhasDeRegistro = Excel.getCellDadosCriandoRegistro(list.get(0).get("planilha"), list.get(0).get("aba"));
    }

    @E("preencho o body da minha requisicao com os dados que criei")
    public void preenchoOBodyDaMinhaRequisicaoComOsDadosQueCriei() {
        Metodos.geraRegistro();
    }

    @Quando("realizo o login")
    public void realizoOLogin() {
        resultadoGetEmprestimo = Metodos.guardaToken();
    }

    @Entao("ao enviar uma requisicao Get para Emprestimos valido que a acao ocorreu com sucesso")
    public void aoEnviarUmaRequisicaoGetParaEmprestimosValidoQueAAcaoOcorreuComSucesso() {
        Assertions.assertEquals(resultadoGetEmprestimo.statusCode(), 200);
    }
}
