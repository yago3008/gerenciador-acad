import java.util.Locale;
import java.util.Scanner;
import java.util.List;

public class Aluno {


    // Definição de variaveis
    private String nome;
    private String matricula;
    private int idade;
    private Float peso;
    private Float altura;
    private String treino;
    private String equipamento;

    // Construtor Aluno
    public Aluno(String nome, String matricula, int idade, Float peso, Float altura, String treino, String equipamento) {
        this.nome = nome;
        this.matricula = matricula;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.treino = treino;
        this.equipamento = equipamento;
    }

    // Metodo cadastrar aluno
    public static Aluno cadastrarAluno() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar informações do aluno

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do aluno: ");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite a peso do aluno: ");
        Float peso = Float.parseFloat(scanner.nextLine());

        System.out.print("Digite a altura do aluno: ");
        Float altura = Float.parseFloat(scanner.nextLine());

        String equipamento = "noEquipement";

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        while (FileManager.matriculaExiste(matricula)) {
            System.out.print("Matrícula já cadastrada!\n");
            System.out.print("Digite a matrícula do aluno: ");
            matricula = scanner.nextLine();
        }
        Main.clearConsole();
        System.out.println("Escolha um instrutor disponivel");
        System.out.println("Lista de instrutores disponiveis: ");
        System.out.println("--------------------");
        Instrutor.printInstrutor();
        System.out.println("Instrutor escolhido: ");
        String instrutorEscolhido = scanner.nextLine().toLowerCase();

        FileManager.reservarInstrutor(instrutorEscolhido);

        Instrutor.mudarStatusInstrutor(instrutorEscolhido);

        String treino = Treino.atribuirTreino(instrutorEscolhido);

        // Criar um objeto Aluno com as informações fornecidas e retornar
        return new Aluno(nome, matricula, idade, peso, altura, treino, equipamento);
    }

    // Metodo calcular o IMC
    public static float calcularIMC(int idade, float peso, float altura) {
        // Verificar se altura é zero para evitar divisão por zero
        if (altura == 0) {
            System.out.println("Altura inválida. IMC não pode ser calculado.");
            return -1; // Retornar um valor inválido
        }
        // Calcular o IMC
        float imc = peso / (altura * altura);

        return imc;
    }

    // Getters

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getIdade() {
        return idade;
    }

    public Float getPeso() {
        return peso;
    }

    public Float getAltura() {
        return altura;
    }

    public String getTreino() {
        return treino;
    }

    public String getEquipamento() {
        return equipamento;
    }

    // toString para imprimir informações do aluno
    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", idade=" + idade +
                ", peso=" + peso +
                ", altura=" + altura +
                ", treino='" + treino + '\'' +
                '}';
    }
}
