package Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArquivoTxt {
    public static PrintWriter abrirArquivo(String nomePasta, String nomeArquivo) throws IOException {
        Path txt = Paths.get(nomePasta + File.separator+nomeArquivo+".txt");
        if(Files.notExists(txt.getParent())){
            Files.createDirectory(txt.getParent());
        }
        if(Files.notExists(txt)) {
            Files.createFile(txt);
        }

        FileWriter arq = new FileWriter(txt.toString());
        PrintWriter gravarArq = new PrintWriter(arq);

        return gravarArq;
    }

    public static void escreverTexto(String linha, String titulo, String valor, PrintWriter gravarArq){
        gravarArq.printf(titulo + "_" + linha + ": ");
        gravarArq.printf(Geral.removerAcentos(valor.trim().toLowerCase()) + "%n");
    }

    public static void fecharArquivo(PrintWriter gravarArq){
        gravarArq.close();
    }

    public static String lerArquivo(String busca, String arquivoTxt){
        try {
            FileReader arq = new FileReader(Hooks.SETUP.getCaminho() + "\\" + arquivoTxt);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê a primeira linha

            while (linha != null) {
                if(linha.split(":")[0].equals(busca)) {
                    arq.close();
                    return linha.split(":")[1].trim();
                }

                linha = lerArq.readLine();
            }

            arq.close();

            return null;

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return null;
    }
}
