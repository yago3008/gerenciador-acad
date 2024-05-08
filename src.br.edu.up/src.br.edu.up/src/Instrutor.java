import java.util.ArrayList;

public class Instrutor{

    private String nome;
    private String descricao;
    private int idade;
    private String treino;
    private Boolean disponibilidade;
    public static ArrayList<Instrutor> instrutores = new ArrayList();

    // Construtor instrutor
    public Instrutor(String nome, String descricao, int idade, String treino, Boolean disponibilidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.idade = idade;
        this.treino = treino;
        this.disponibilidade = disponibilidade;
        instrutores.add(this);
    }

    // printar instrutor
    public static void printInstrutor(){
        for (Instrutor i : instrutores){
            System.out.println("Nome: " + i.nome);
            System.out.println("Descricao: " + i.descricao);
            System.out.println("Idade: " + i.idade);
            System.out.println("Treino: " + i.treino);
            System.out.println();
        }
    }

    // mudar o status do instrutor
    public static void mudarStatusInstrutor(String instrutor){
        for (Instrutor i : instrutores){
            if (i.nome.equals(instrutor)){
                i.disponibilidade = false;
            }
        }
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdade() {
        return idade;
    }

    public String getTreino() {
        return treino;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }
}
