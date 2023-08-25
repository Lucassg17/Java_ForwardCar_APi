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
}
