package Utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class Excel {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static final String caminho = "C:\\Users\\Lucas Gomes\\RestAssured\\MassaDados_ApiForwardCar";

    private enum massaRegistro{
        FIRSTNAME,
        LASTNAME,
        USERNAME,
        PASSWORD
    }

    private enum massaLogin{
        USERNAME,
        PASSWORD
    }

    private enum massaEmprestimo{
        FIRSTNAME,
        LASTNAME,
        ADDRESS1,
        CITY,
        STATE,
        ZIP,
        COUNTRY,
        DOB,
        SSN,
        EMPLOYER,
        PHONENUMBER,
        DURATIONOFJOB,
        INCOME,
        LOANTERM,
        LOANAMOUNT,
        VALIDATEADDRESS
    }

    public static int getCellDadosCriandoRegistro(String xlFile, String xlSheet) throws Exception {
        DataFormatter formatter = new DataFormatter();

        fi = new FileInputStream(caminho + "\\" + xlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);

        int rowCount = ws.getLastRowNum();

        PrintWriter arq = ArquivoTxt.abrirArquivo(caminho, "Dados_Excel-criarRegistro");

        for(int i = 1; i <= rowCount; i++) {
            row = ws.getRow(i);

            if (row == null) {
                wb.close();
                fi.close();
                return 0;
            }
            ArquivoTxt.escreverTexto(Integer.toString(i), "FIRSTNAME", formatter.formatCellValue(row.getCell(massaRegistro.FIRSTNAME.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "LASTNAME", formatter.formatCellValue(row.getCell(massaRegistro.LASTNAME.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "USERNAME", formatter.formatCellValue(row.getCell(massaRegistro.USERNAME.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "PASSWORD", formatter.formatCellValue(row.getCell(massaRegistro.PASSWORD.ordinal())), arq);
        }

        ArquivoTxt.fecharArquivo(arq);

        wb.close();
        fi.close();

        return rowCount;
    }

    public static int getCellDadosCriandoLogin(String xlFile, String xlSheet) throws Exception {
        DataFormatter formatter = new DataFormatter();

        fi = new FileInputStream(caminho + "\\" + xlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);

        int rowCount = ws.getLastRowNum();

        PrintWriter arq = ArquivoTxt.abrirArquivo(caminho, "Dados_Excel-criarLogin");

        for(int i = 1; i <= rowCount; i++) {
            row = ws.getRow(i);

            if (row == null) {
                wb.close();
                fi.close();
                return 0;
            }

            ArquivoTxt.escreverTexto(Integer.toString(i), "USERNAME", formatter.formatCellValue(row.getCell(massaLogin.USERNAME.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "PASSWORD", formatter.formatCellValue(row.getCell(massaLogin.PASSWORD.ordinal())), arq);
        }

        ArquivoTxt.fecharArquivo(arq);

        wb.close();
        fi.close();

        return rowCount;
    }

    public static int getCellDadosCriandoEmprestimo(String xlFile, String xlSheet) throws Exception {
        DataFormatter formatter = new DataFormatter();

        fi = new FileInputStream(caminho + "\\" + xlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);

        int rowCount = ws.getLastRowNum();

        PrintWriter arq = ArquivoTxt.abrirArquivo(caminho, "Dados_Excel-criarEmprestimo");

        for(int i = 1; i <= rowCount; i++) {
            row = ws.getRow(i);

            if (row == null) {
                wb.close();
                fi.close();
                return 0;
            }

            ArquivoTxt.escreverTexto(Integer.toString(i), "FIRSTNAME", formatter.formatCellValue(row.getCell(massaEmprestimo.FIRSTNAME.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "LASTNAME", formatter.formatCellValue(row.getCell(massaEmprestimo.LASTNAME.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "ADDRESS1", formatter.formatCellValue(row.getCell(massaEmprestimo.ADDRESS1.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "CITY", formatter.formatCellValue(row.getCell(massaEmprestimo.CITY.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "STATE", formatter.formatCellValue(row.getCell(massaEmprestimo.STATE.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "ZIP", formatter.formatCellValue(row.getCell(massaEmprestimo.ZIP.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "COUNTRY", formatter.formatCellValue(row.getCell(massaEmprestimo.COUNTRY.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "DOB", formatter.formatCellValue(row.getCell(massaEmprestimo.DOB.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "SSN", formatter.formatCellValue(row.getCell(massaEmprestimo.SSN.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "EMPLOYER", formatter.formatCellValue(row.getCell(massaEmprestimo.EMPLOYER.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "PHONENUMBER", formatter.formatCellValue(row.getCell(massaEmprestimo.PHONENUMBER.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "DURATIONOFJOB", formatter.formatCellValue(row.getCell(massaEmprestimo.DURATIONOFJOB.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "INCOME", formatter.formatCellValue(row.getCell(massaEmprestimo.INCOME.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "LOANTERM", formatter.formatCellValue(row.getCell(massaEmprestimo.LOANTERM.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "LOANAMOUNT", formatter.formatCellValue(row.getCell(massaEmprestimo.LOANAMOUNT.ordinal())), arq);
            ArquivoTxt.escreverTexto(Integer.toString(i), "VALIDATEADDRESS", formatter.formatCellValue(row.getCell(massaEmprestimo.VALIDATEADDRESS.ordinal())), arq);
        }

        ArquivoTxt.fecharArquivo(arq);

        wb.close();
        fi.close();

        return rowCount;
    }
}
