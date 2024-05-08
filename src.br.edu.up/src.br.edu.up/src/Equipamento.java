import java.util.ArrayList;

public class Equipamento {
    private String nome;
    private String tipo;
    private boolean disponivel;
    public static ArrayList<Equipamento> equipamentos = new ArrayList();

    // Construtor equipamento
    public Equipamento(String nome, String tipo, boolean disponivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.disponivel = disponivel;
        equipamentos.add(this);
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

}