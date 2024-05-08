import java.util.ArrayList;

public class Treino {

    private Treino treinoGinasta;
    private Treino treinoGanhoDeMassa;
    private Treino treinoEmagrecimento;
    private Treino treinoCardiovascular;
    private String ex1;
    private String ex2;
    private static ArrayList<Treino> treinos = new ArrayList<>();

    // Construtor Treino
    public Treino(String ex1, String ex2) {
        this.ex1 = ex1 ;
        this.ex2 = ex2;
        treinos.add(this);
    }

    // metodo atribuir treino
    public static String atribuirTreino(String instrutor){
        if(instrutor.equals("carlos")){
            return "treinoGinasta";
        }
        else if(instrutor.equals("yago")){
            return "treinoGanhoDeMassa";
        }
        else if(instrutor.equals("nicole")){
            return "treinoEmagrecimento";
        }
        else if(instrutor.equals("eduarda")){
            return "treinoCardiovascular";
        }
        return null;
    }

    // metodo printar treinos
    public static void printTreinos() {
        for (Treino treino : treinos) {
            System.out.println(treino.ex1);
            System.out.println(treino.ex2);
            System.out.println();
        }
    }
}
