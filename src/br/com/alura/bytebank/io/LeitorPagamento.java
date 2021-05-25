package br.com.alura.bytebank.io;

import br.com.alura.bytebank.model.Pagamento;
import br.com.alura.bytebank.model.Tipo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeitorPagamento {

    public static final String RECURSOS = "recursos";
    public static final String BACKUP = "backup";

    private void geraBackup(Path arquivo) throws IOException {
        Path backup = Paths.get(BACKUP);
        if (!Files.isDirectory(backup)) {
            Files.createDirectory(backup);
        }

        Path nomeDoArquivo = arquivo.getFileName();

        String arquivoAbsoluto = System.currentTimeMillis() + "-" + nomeDoArquivo.toString();

        Path origem = Paths.get(arquivo.toString());
        Path destino = Paths.get(backup.toString(), arquivoAbsoluto);

        Files.copy(origem, destino);

    }

    public List<Pagamento> ler(int codigo) throws IOException {
        String arquivo = devolveArquivo(codigo);

        ArrayList<Pagamento> pagamentos = new ArrayList<>();

        Path caminho = Paths.get(arquivo);

        List<String> linhas = Files.readAllLines(caminho, Charset.forName("utf-8"));

        linhas.forEach(linha -> {
            String[] palavras = linha.split(",");
            if (!linha.isEmpty()) {
                Double valor = Double.valueOf(palavras[0]);
                String descricao = palavras[1];
                Tipo tipo = Tipo.valueOf(palavras[2]);
                Pagamento pagamento = new Pagamento(tipo, valor, descricao);
                pagamentos.add(pagamento);
            }
        });

        geraBackup(caminho);
        return pagamentos;
    }

    private String devolveArquivo(int codigo) throws IOException {
        Iterator<Path> arquivos = lista();
        int i = 0;

        Path path = null;

        while (i < codigo) {
            path = arquivos.next();
            i++;
        }

        return path.toString();
    }

    public Iterator<Path> lista() throws IOException {
        return Files.newDirectoryStream(Paths.get(RECURSOS)).iterator();
    }
}
