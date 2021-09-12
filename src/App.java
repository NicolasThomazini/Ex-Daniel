import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            File file = new File(args[0]);
            if (file.exists()) {
                String stringChecagem = leitorArquivo(file);
                if (checkBalancedBrackets(stringChecagem)) {
                    System.out.println(stringChecagem + " - Válido");
                } else {
                    System.out.println(stringChecagem + " - Inválido");
                }
            } else {
                System.out.println("Arquivo não encontrado. Por Favor, aponte um arquivo válido.");
            }
        } else {
            System.out.println("É necessário dar um caminho de arquivo como argumento.");
        }
    }

    private static String leitorArquivo(File file) throws FileNotFoundException, IOException {
        String stringChecagem = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            stringChecagem = sb.toString();
        }
        return stringChecagem;
    }

    private static Boolean checkBalancedBrackets(String content) {
        Stack<Character> abreDelimitadorStack = new Stack<Character>();

        List<Character> abreDelimitador = Arrays.asList(new Character[] { '(', '[', '{', '<' });
        List<Character> fechaDelimitador = Arrays.asList(new Character[] { ')', ']', '}', '>' });

        for (int i = 0; i < content.length(); i++) {
            char character = content.charAt(i);

            if (abreDelimitador.contains(character)) {
                abreDelimitadorStack.push(character);
            } else if (fechaDelimitador.contains(character)) {
                if (abreDelimitadorStack.size() == 0) {
                    return false;
                }
                if (abreDelimitador.indexOf(abreDelimitadorStack.lastElement()) == fechaDelimitador
                        .indexOf(character)) {
                    abreDelimitadorStack.pop();
                } else {
                    return false;
                }
            }
        }
        return abreDelimitadorStack.isEmpty();
    
    }
}
