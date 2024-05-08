import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Util {

    // Menu principal
    static void menu(){
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;

        do{
            System.out.println("--------------------");
            System.out.println("Menu:");
            System.out.println("1. Aluno");
            System.out.println("2. Instrutor");
            System.out.println("3. Equipamento");
            System.out.println("4. Sair");
            System.out.println("Escolha uma opção: ");
            System.out.println("--------------------\n");
            escolha = Integer.parseInt(scanner.nextLine());


            switch (escolha) {
                case 1:
                    System.out.println("Menu aluno.");
                    menuAluno();
                    break;
                case 2:
                    System.out.println("Menu instrutor.");
                    menuInstrutor();
                    break;
                case 3:
                    System.out.println("Menu equipamento.");
                    menuEquipamento();
                    break;
                case 4:
                    System.out.println("Encerrando o programa.");
                    System.exit(1);;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }

        } while (escolha != 4);

        scanner.close();
    }

    // Menu secundario aluno
    static void menuAluno(){
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;

        do{
            System.out.println("--------------------");
            System.out.println("1. Registrar aluno");
            System.out.println("2. Remover aluno");
            System.out.println("3. Atualizar aluno");
            System.out.println("4. Ver progresso");
            System.out.println("5. Ver Treino");
            System.out.println("6. Vizualizar aluno");
            System.out.println("7. Listar alunos: ");
            System.out.println("8. Listar alunos ordenados por nome: ");
            System.out.println("9. Voltar");
            System.out.println("Escolha uma opção: ");
            System.out.println("--------------------");
            escolha = Integer.parseInt(scanner.nextLine());

            switch (escolha) {
                case 1:

                    // Criando professores ( não consegui instaciar na main :(  )
                    System.out.println("\n\n\n\n\n\n\n\n");
                    Instrutor carlos = new Instrutor("carlos", "Ginasta", 28, "TreinoGinasta", true);
                    Instrutor yago = new Instrutor("yago", "ganho de massa", 19, "TreinoGanhoDeMassa", true);
                    Instrutor nicole = new Instrutor("nicole", "Emagrecimento", 46, "TreinoEmagrecimento", true);
                    Instrutor eduarda = new Instrutor("eduarda", "Cardiovascular", 42, "TreinoCardiovascular", true);
                    ArrayList<Instrutor> instrutores = new ArrayList<>();

                    // Se o arquivo estiver vazio, adiciona os objetos
                    if (FileManager.verificarArquivoInstrutorVazio()){
                        instrutores.add(carlos);
                        instrutores.add(yago);
                        instrutores.add(nicole);
                        instrutores.add(eduarda);
                        FileManager.salvarInstrutor(instrutores);
                    }
                    Aluno aluno = Aluno.cadastrarAluno();
                    FileManager.salvarAluno(aluno);
                    break;
                case 2:
                    // Remover alunos por matricula
                    System.out.println("\n\n\n\n\n\n\n\n");
                    System.out.println("Qual aluno deseja remover? (matricula)");
                    String matriculaRemover = scanner.nextLine();
                    FileManager.removerAluno(matriculaRemover);
                    break;
                case 3:
                    // Atualizar alunos por matricula
                    System.out.println("\n\n\n\n\n\n\n\n");
                    System.out.println("Qual aluno deseja atualizar? (matricula)");
                    String matriculaAtualizar = scanner.nextLine();
                    FileManager.atualizarAluno(matriculaAtualizar);

                    break;
                case 4:
                    // Vizualizar progresso alunos por matricula
                    System.out.println("\n\n\n\n\n\n\n\n");
                    System.out.println("Qual aluno deseja ver o progresso? (matricula)");
                    String matriculaProgresso = scanner.nextLine();
                    FileManager.progressoAluno(matriculaProgresso);
                    break;
                case 5:
                    // Vizualizar treinos
                    System.out.println("\n\n\n\n\n\n\n\n");
                    Treino.printTreinos();
                    break;
                case 6:
                    // Vizualizar aluno por amtricula
                    System.out.println("\n\n\n\n\n\n\n\n");
                    System.out.println("Qual aluno deseja vizualizar? (matricula)");
                    String matriculaVizualizar = scanner.nextLine();
                    FileManager.pesquisarAluno(matriculaVizualizar);
                    break;
                case 7:
                    // Listar alunos
                    FileManager.listarAlunos();
                    break;
                case 8:
                    // Ordenar e listar alunos
                    FileManager.ordernarAluno();
                    FileManager.listarAlunos();
                    break;
                case 9:
                    // Encerrar programa
                    System.out.println("\n\n\n\n\n\n\n\n");
                    System.out.println("Encerrando o programa.");
                    menu();
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }

        } while (escolha != 9);

        scanner.close();
    }
    // Menu secundario instrutor
    static void menuInstrutor(){
        Scanner scanner = new Scanner(System.in);
        Integer escolha;
        Instrutor.printInstrutor();

        do{
            System.out.println("Menu:");
            System.out.println("1. Vizualizar instrutores");
            System.out.println("2. Voltar");
            escolha = Integer.parseInt(scanner.nextLine());

            switch (escolha) {
                case 1:
                    System.out.println("Instrutor Carlos - especialidade --> TreinoGinasta");
                    System.out.println("Instrutor Yago - especialidade --> TreinoGanhoDeMassa");
                    System.out.println("Instrutor Nicole - especialidade --> TreinoEmagrecimento");
                    System.out.println("Instrutor Eduarda - especialidade --> TreinoCardiovascular");
                    break;
                case 2:
                    System.out.println("Voltando para o menu.");
                    menu();
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }

        } while (escolha != 2);

        scanner.close();
    }

    // Menu secundario equipamento
    static void menuEquipamento(){
        Scanner scanner = new Scanner(System.in);
        Integer escolha;

        do{
            System.out.println("--------------------");
            System.out.println("Menu:");
            System.out.println("1. Vizualizar equipamentos");
            System.out.println("2. Reservar equipamento");
            System.out.println("3. Voltar");
            System.out.println("--------------------");
            escolha = Integer.parseInt(scanner.nextLine());

            switch (escolha) {
                case 1:
                    // Criando equipamentos
                    Equipamento esteira = new Equipamento("esteira", "Cardio", true);
                    Equipamento halteres = new Equipamento("halteres", "Musculação", true);
                    Equipamento bolaSuica = new Equipamento("bolasuica", "Funcional", true);
                    // Se o arquivo estiver vazio, adiciona os objetos
                    if (FileManager.verificarArquivoEquipamentoVazio()){
                        List<Equipamento> equipamentos = new ArrayList<>();
                        equipamentos.add(esteira);
                        equipamentos.add(halteres);
                        equipamentos.add(bolaSuica);
                        FileManager.salvarEquipamentos(equipamentos);
                    }
                    FileManager.listarEquipamentos();
                    break;
                case 2:
                    System.out.println("\n\n\n\n\n\n\n\n");
                    System.out.println("Qual aluno deseja reservar o equipamento? (matricula)");
                    String matriculaReservar = scanner.nextLine();
                    System.out.println("Qual equipamento deseja reservar:");
                    FileManager.listarEquipamentos();
                    String equipamentoReservado = scanner.nextLine().toLowerCase();
                    FileManager.reservarEquipamento(matriculaReservar, equipamentoReservado);
                    break;
                case 3:
                    System.out.println("Voltando para o menu.");
                    menu();
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }

        } while (escolha != 3);

        scanner.close();
    }

}
