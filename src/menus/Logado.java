package src.menus;
import java.util.Scanner;
import entidades.Usuario;

public class Logado {
    public static void menu(Usuario user) {
        Scanner sc = new Scanner(System.in);

        int s = 0;
        String aux = "";
        char opcao;

        while (s == 0) {
            System.out.println("TP01 Aeds3");
            System.out.println("----------");
            System.out.println("> Inicio");
            System.out.println();
            System.out.println("(A) Meus dados");
            System.out.println("(B) Meus cursos");
            System.out.println("(C) Minhas inscrições");
            System.out.println();
            System.out.println("(S) Sair");
            System.out.println();
            System.out.print("Opção: ");
            System.out.println();

            aux = sc.next();
            opcao = Character.toUpperCase(aux.charAt(0));

            // sair
            if (opcao == 'S') {
                s = 1;
                break;
            }

            // mostrar os dados
            else if (opcao == 'A') {
                System.out.println("TP01 Aeds3");
                System.out.println("----------");
                System.out.println("> Inicio > Meus Dados");
                System.out.println();

                System.out.println("Meus dados:");
                System.out.println(user.toString());
                System.out.println();

                System.out.println("Pressione qualquer tecla para voltar...");
                sc.next();
            }

            // meus cursos
            else if (opcao == 'B') {
                Menu_curso.menu(user); // já preparando integração
            }

            // minhas inscrições
            else if (opcao == 'C') {
                System.out.println("Funcionalidade ainda não implementada.");
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