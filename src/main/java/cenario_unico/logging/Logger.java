package cenario_unico.logging;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    final StringBuilder log;

    public Logger() {
        log = new StringBuilder();
    }

    public void log(String message) {
        log.append(message + "\n");
    }

    public void logComando(String message, String estruturaDados) {
        log.append("\nComando: " + message + "\nEstrutura de dados antes do comando -> " + estruturaDados + "\n");
    }

    public void logErro(String message, String estruturaDados) {
        log.append("Erro: " + message + "\nEstrutura de dados apos comando -> " + estruturaDados + "\n");
    }

    public void logSucesso(String message, String estruturaDados) {
        log.append("Sucesso: " + message + "\nEstrutura de dados apos comando -> " + estruturaDados + "\n");
    }

    public void generateFile(String path) {
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(log.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar gravar o log no arquivo: " + path);
            e.printStackTrace();
        }
    }
}
