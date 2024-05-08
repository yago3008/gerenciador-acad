import java.io.*;
import java.util.*;



public class FileManager {

    // Verifica se o arquivo ALUNO.TXT existe
    public static void verificarArquivoAluno(){
        try {
            File arquivo = new File("alunos.txt");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados do aluno: " + e.getMessage());
        }
    }

    // Metodo para salvar o aluno no ALUNO.TXT
    public static void salvarAluno(Aluno aluno) {
        verificarArquivoAluno();
        // Abre o arquivo e escreve os getters dentro
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt", true))) {
            writer.write(aluno.getNome() + "," + aluno.getMatricula() + "," + aluno.getIdade() + "," + aluno.getAltura() + "," + aluno.getPeso() + "," + aluno.getTreino() +
                    "," + aluno.getEquipamento());
            // Nova linha
            writer.newLine();
            System.out.println("Aluno cadastrado com sucesso!");
            System.out.println("\n\n\n\n\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o aluno: " + e.getMessage());
        }
    }

    // Verifica se a matricula existe
    public static boolean matriculaExiste(String matriculaProcurada) {
        boolean matriculaEncontrada = false;
        // Abre o arquivo e compara a matricula procurada com a matricula do txt
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.txt"))) {
            String linha;
            // divide em linhas
            while ((linha = br.readLine()) != null) {
                // Splita por ","
                String[] partes = linha.split(",");
                String matricula = partes[1].trim();
                if (matricula.equals(matriculaProcurada)) {
                    matriculaEncontrada = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return matriculaEncontrada;
    }

    // Metodo remover Aluno
    public static void removerAluno(String matriculaParaRemover) {
        List<String> linhas = new ArrayList<>();
        boolean alunoRemovido = false;
        // Tenta ler o arquivo
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.txt"))) {
            String linha;
            // Divide em linhas
            while ((linha = br.readLine()) != null) {
                // splita por ","
                String[] partes = linha.split(",");
                String matricula = partes[1];
                if (!matricula.equals(matriculaParaRemover)) {
                    // adiciona as linnhas
                    linhas.add(linha);
                } else {
                    alunoRemovido = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        if (alunoRemovido) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt"))) {
                // escreve todas as linhas
                for (String linha : linhas) {
                    writer.write(linha);
                    writer.newLine();
                }
                System.out.println("Aluno removido com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    // Atualizar o aluno
    public static void atualizarAluno(String matricula){
        Scanner scanner = new Scanner(System.in);

        // Verificar se a matrícula existe
        if (!matriculaExiste(matricula)) {
            System.out.println("Matrícula não encontrada.");
            return;
        }

        // Abre o arquivo, divide por linhas, splita por "," e pega as informações necessarias pra atualizar
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.txt"))) {
            List<String> linhas = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String matriculaAtual = partes[1].trim();
                if (matriculaAtual.equals(matricula)) {
                    // Atualizar peso e altura
                    System.out.print("Digite o novo peso do aluno: ");
                    Float novoPeso = Float.parseFloat(scanner.nextLine());
                    System.out.print("Digite a nova altura do aluno: ");
                    Float novaAltura = Float.parseFloat(scanner.nextLine());
                    System.out.println("Escolha um instrutor disponivel");
                    System.out.println("Lista de instrutores disponiveis: ");
                    Instrutor.printInstrutor();
                    System.out.println("Instrutor escolhido: ");
                    String instrutorEscolhido = scanner.nextLine().toLowerCase();
                    String novoTreino = Treino.atribuirTreino(instrutorEscolhido);
                    partes[3] = novoPeso.toString();
                    partes[4] = novaAltura.toString();
                    partes[5] = novoTreino;
                    linha = String.join(",", partes); // Reunir as partes em uma linha novamente
                    System.out.println("Aluno atualizado com sucesso!");
                }
                linhas.add(linha);
            }

            // Escrever as linhas atualizadas de volta no arquivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt"))) {
                for (String l : linhas) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Metodo progresso do aluno
    public static void progressoAluno(String matricula){

        if (!matriculaExiste(matricula)) {
            System.out.println("Matrícula não encontrada.");
            return;
        }

        // Abre o arquivo, divide por linhas, splita por "," e pega as informações necessarias pra ver o IMC
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String matriculaAtual = partes[1].trim();
                if (matriculaAtual.equals(matricula)) {
                    String nome = partes[0].trim();
                    int idade = Integer.parseInt(partes[2].trim());
                    Float peso = Float.parseFloat(partes[3].trim());
                    Float altura = Float.parseFloat(partes[4].trim());

                    Float imc = Aluno.calcularIMC(idade, peso, altura);
                    System.out.println("O IMC atual do aluno " + nome + " é: " + imc);
                    System.out.println("Peso atual: " + peso);
                    System.out.println("Altura atual: " + altura);
                }

            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

    }

    // Metodo Pesquisar aluno
    public static void pesquisarAluno(String matriculaProcurada){
        if (!matriculaExiste(matriculaProcurada)) {
            System.out.println("Matrícula não encontrada.");
        }
        // Abre o arquivo, divide por linhas, splita por "," e pega as informações necessarias pra pesquisar o aluno
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.txt"))) {
            List<String> lista = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String matricula = partes[1].trim();
                if (matricula.equals(matriculaProcurada)) {
                    String nome = partes[0].trim();
                    String idade = partes[2].trim();
                    Float peso = Float.parseFloat(partes[3].trim());
                    Float altura = Float.parseFloat(partes[4].trim());
                    String tipoTreino = partes[5].trim();
                    String equipamento = partes[6].trim();
                    System.out.println("Nome do aluno: " + nome + "\nIdade: " + idade + "\nPeso: " + peso + "\nAltura: " + altura + "\nTipo de treino: " + tipoTreino + "\nEquipamento: " + equipamento);
                    System.out.println();
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

    }

    // Metodo listar alunos

    public static void listarAlunos() {
        String path = "alunos.txt";

        // Percorre o arquivo inteiro pegando as linhas e printa
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            System.out.println("Conteúdo do arquivo " + path + ":");
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Metodo ordenar aluno
    public static void ordernarAluno(){
        // Abre o arquivo, divide por linhas, splita por "," e pega as informações necessarias para ordenar
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.txt"))) {
            List<String> linhasAlunosOrdernados = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                linhasAlunosOrdernados.add(linha);
            }
            Collections.sort(linhasAlunosOrdernados);
            // Escrever as linhas atualizadas de volta no arquivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt"))) {
                for (String l : linhasAlunosOrdernados) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // verifica se o arquivo EQUIPMAENTO.TXT esta vazio
    public static boolean verificarArquivoEquipamentoVazio(){

        String path = "equipamentos.txt";
        File arquivo = new File(path);

        // Verificar se o arquivo existe e se está vazio
        if (arquivo.length() == 0) return true;

        return false;
    }

    // Verifica se o arquivo EQUIPAMENTO.TXT existe
    public static void verificarArquivoEquipamento() {
        String path = "equipamentos.txt";
        File arquivo = new File(path);
        if (!arquivo.exists()) {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo " + path + " criado com sucesso.");

                } else {
                    System.out.println("Falha ao criar arquivo " + path + ".");
                }
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo " + path + ": " + e.getMessage());
            }
        }
    }
    // metodo salvar equipamento
    public static void salvarEquipamentos(List<Equipamento> equipamentos) {
        verificarArquivoEquipamento();
        // Abre o arquivo, e escreve os getters
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("equipamentos.txt", true))) {
            for (Equipamento equipamento : equipamentos) {
                writer.write(equipamento.getNome() + "," + equipamento.getTipo() + "," + equipamento.isDisponivel());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar as informações dos equipamentos: " + e.getMessage());
        }
    }

    // metodo reservar equipamento
    public static void reservarEquipamento(String matricula, String equipamento){
        Scanner scanner = new Scanner(System.in);

        // Verificar se a matrícula existe
        if (!matriculaExiste(matricula)) {
            System.out.println("Matrícula não encontrada.");
            return;
        }
        // Abre o arquivo, divide por linhas, splita por "," e pega as informações necessarias pra reservar
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.txt"))) {
            List<String> linhas = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String matriculaAtual = partes[1].trim();
                String equipamentoExists = partes[6].trim();
                if (matriculaAtual.equals(matricula)) {
                    if (estaDisponivel(equipamento)){
                            partes[6] = equipamento;
                            linha = String.join(",", partes);
                            // Bloquear o equipamento (caso necessário)
                            bloquearEquipamento(equipamento);
                    }
                    else{
                            System.out.println("Equipamento já reservado, tente novamente!");
                        }
                    linhas.add(linha);
                } else {
                    // Adicionar a linha original na lista de linhas
                    linhas.add(linha);
                }
            }

            // Escrever as linhas atualizadas de volta no arquivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt"))) {
                for (String l : linhas) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // metodo bloquear equipamento
    public static void bloquearEquipamento(String equipamentoReservado) {

        // Abre o arquivo, divide por linhas, splita por "," e pega as informações necessarias pra bloquear, mudando a info de true pra false
        try (BufferedReader br = new BufferedReader(new FileReader("equipamentos.txt"))) {
            List<String> linhasEquipamentos = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String equipamento = partes[0].trim();  // Aqui você está acessando o índice 0
                String disponivel = partes[2].trim();
                if (equipamento.equals(equipamentoReservado) && disponivel.equals("true")) {
                    partes[2] = "false";
                    linha = String.join(",", partes);
                    linhasEquipamentos.add(linha);
                }
                else{
                    linhasEquipamentos.add(linha);
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("equipamentos.txt"))) {
                for (String l : linhasEquipamentos) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
            // Restante do código...
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // metodo para verificar se o equipmaneto esta disponivel
    public static boolean estaDisponivel(String equipamentoSolicitado){
        // Abre o arquivo, divide por linhas, splita por "," e pega as informações necessarias pra verificar se esta disponivel
        try (BufferedReader br = new BufferedReader(new FileReader("equipamentos.txt"))) {
            List<String> equipamentos = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String equipamento = partes[0].trim();
                String disponivel = partes[2].trim();
                if (equipamento.equals(equipamentoSolicitado) && disponivel.equals("false")) {
                    return false;
                }
                else if(equipamento.equals(equipamentoSolicitado) && disponivel.equals("true")){
                    return true;
                }
            }
            // Restante do código...
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return false;
    }

    // metodo para listar os equipamentos
    public static void listarEquipamentos() {
        String path = "equipamentos.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // metodos verificar se o arquivo INSTRUTOR.TXT existe
    public static void verificarArquivoInstrutor(){
        try {
            File arquivo = new File("instrutores.txt");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados do aluno: " + e.getMessage());
        }
    }

    // metodo verificar o arquivo INSTRUTOR.TXT esta vazio
    public static boolean verificarArquivoInstrutorVazio(){

        String path = "instrutores.txt";
        File arquivo = new File(path);

        // Verificar se o arquivo existe e se está vazio
        if (arquivo.length() == 0) return true;

        return false;
    }

    // metodo salvar isntrutor
    public static void salvarInstrutor(ArrayList<Instrutor> instrutores) {
        verificarArquivoInstrutor();
        // Abre o arquivo, e da os getters
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("instrutores.txt", true))) {
            for (Instrutor i : instrutores) {
                writer.write(i.getNome() + "," + i.getDescricao() + "," + i.getIdade() + "," + i.getTreino() + "," + i.getDisponibilidade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar instrutores: " + e.getMessage());
        }
    }

    // metodo reservar
    public static void reservarInstrutor(String instrutor){
        Scanner scanner = new Scanner(System.in);

        // Abre o arquivo, divide por linhas, splita por "," e pega as informações necessarias pra reservar
        try (BufferedReader br = new BufferedReader(new FileReader("instrutores.txt"))) {
            List<String> linhasInstrutores = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String instrutorAtual = partes[0].trim();
                String estaDisponivel = partes[4].trim();
                if (instrutorAtual.equals(instrutor)) {
                    if (estaDisponivel.equals("true")) {
                        partes[4] = "false";
                        linha = String.join(",", partes);
                    }
                    else{
                        System.out.println("Instrutor já reservado, tente novamente!");
                    }
                    linhasInstrutores.add(linha);
                } else {
                    // Adicionar a linha original na lista de linhas
                    linhasInstrutores.add(linha);
                }
            }

            // Escrever as linhas atualizadas de volta no arquivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("instrutores.txt"))) {
                for (String l : linhasInstrutores) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // metodo listar
    public static void listarInstrutores() {
        String path = "instrutores.txt";
        // Abre o arquivo, pega as linhas e printa
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
