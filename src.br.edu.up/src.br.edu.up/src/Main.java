import java.util.*;
public class Main {
    public static void main(String[] args) {

        // Criar objetos Treinos
        Treino treinoGinasta = new Treino("3x 10 flexoes", "3x 10 abdominal");
        Treino treinoGanhoDeMassa = new Treino("3x 12 supino reto", "4x 10 pull down");
        Treino treinoEmagrecimento = new Treino("4x 15 agaixamento", "30min esteira");
        Treino treinoCardiovascular = new Treino("40min natação", "45min bicicleta");

        // Chamar menu
        Util.menu();
    }
        // Metodo limpar tela
    public final static void clearConsole()
    {
        for (int i = 0; i < 100; ++i)
            System.out.println();
    }

}
