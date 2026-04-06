package src.menus;
import java.util.Scanner;
import java.time.LocalDate;
import entidades.Usuario;

public class Menu_curso {
    public static void menu(Usuario user) {
        Scanner sc = new Scanner(System.in);

        int s = 0;
        String aux = "";
        char opcao;

        while (s == 0) {
            System.out.println("TP01 Aeds3");
            System.out.println("----------");
            System.out.println("> Inicio > Meus Cursos ");
            System.out.println();
            System.out.println("MEUS CURSOS");

            // futuramente: listar cursos do usuário
            // ex: ArquivoCurso.readByUsuario(user.getIdUsuario())

            System.out.println();
            System.out.println("(A) Novo curso");
            System.out.println("(R) Retornar ao menu anterior");

            System.out.println();
            System.out.print("Opção: ");

            aux = sc.next();
            opcao = Character.toUpperCase(aux.charAt(0));

            // sair
            if (opcao == 'R') {
                s = 1;
                break;
            }

            // cadastrar novo curso
            else if (opcao == 'A') {

                System.out.print("Digite o nome do curso: ");
                String nome = sc.next();
                System.out.println();

                System.out.print("Digite a data de início (AAAA-MM-DD): ");
                String dataStr = sc.next();
                LocalDate inicio = LocalDate.parse(dataStr);
                System.out.println();

                System.out.print("Digite a descrição do curso: ");
                String descricao = sc.next();
                System.out.println();

                // gerar código automaticamente (simples por enquanto)
                String codigo = "CUR" + System.currentTimeMillis();

                int estado = 0; // ativo com inscrições

                int idUsuario = user.getIdUsuario();

                // aqui você vai chamar o arquivo depois
                // ex:
                // ArquivoCurso.create(new Curso(...))

                System.out.println("Curso criado (ainda não salvo em arquivo).");
            }

            else {
                System.out.println("Opção Inválida. Deseja tentar novamente? S/N");
                char desejo = Character.toUpperCase(sc.next().charAt(0));

                if (desejo == 'S') {
                    s = 0;
                } else if (desejo == 'N') {
                    s = 1;
                    break;
                } else {
                    System.out.println("Opção Inválida");
                    break;
                }
            }
        }
    }
}