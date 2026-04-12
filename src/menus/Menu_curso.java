package src.menus;

import entidades.Curso.*;
import entidades.Usuario.Usuario;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Menu_curso {

    public static void menu(Usuario user) {
        Scanner sc = new Scanner(System.in);

        try {
            ArquivoCurso arqCurso = new ArquivoCurso();

            int s = 0;
            String aux = "";
            char opcao;

            while (s == 0) {
                System.out.println("EntrePares 1.0");
                System.out.println("--------------");
                System.out.println("> Início > Meus Cursos");
                System.out.println();
                System.out.println("CURSOS");

                Curso[] cursos = arqCurso.readCursosPorUsuario(user.getIdUsuario());
                Arrays.sort(cursos, (a, b) -> a.getNome().compareToIgnoreCase(b.getNome()));

                for (int i = 0; i < cursos.length; i++) {
                    System.out.println("(" + (i + 1) + ") " + cursos[i].getNome() + " - " + cursos[i].getInicio());
                }

                System.out.println();
                System.out.println("(A) Novo curso");
                System.out.println("(R) Retornar ao menu anterior");

                System.out.println();
                System.out.print("Opção: ");

                aux = sc.next();
                opcao = Character.toUpperCase(aux.charAt(0));

                if (opcao == 'R') {
                    s = 1;
                    break;
                }

                else if (opcao == 'A') {

                    sc.nextLine();

                    System.out.print("Digite o nome do curso: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite a data de início (AAAA-MM-DD): ");
                    String dataStr = sc.next();
                    LocalDate inicio = LocalDate.parse(dataStr);
                    sc.nextLine();

                    System.out.print("Digite a descrição do curso: ");
                    String descricao = sc.nextLine();

                    int estado = 0;

                    Curso novo = new Curso(user.getIdUsuario(), nome, descricao, inicio, estado);

                    arqCurso.create(novo);

                    System.out.println("Curso criado com sucesso.");
                }

                else if (Character.isDigit(opcao)) {

                    int index = Character.getNumericValue(opcao) - 1;

                    if (index >= 0 && index < cursos.length) {
                        Curso c = cursos[index];

                        int voltar = 0;

                        while (voltar == 0) {

                            System.out.println("EntrePares 1.0");
                            System.out.println("--------------");
                            System.out.println("> Início > Meus Cursos > " + c.getNome());
                            System.out.println();

                            System.out.println("CÓDIGO........: " + c.getCodigo());
                            System.out.println("NOME..........: " + c.getNome());
                            System.out.println("DESCRIÇÃO.....: " + c.getDescricao());
                            System.out.println("DATA DE INÍCIO: " + c.getInicio());
                            System.out.println();

                            if (c.getEstado() == 0)
                                System.out.println("Este curso está aberto para inscrições!");
                            else if (c.getEstado() == 1)
                                System.out.println("Este curso está ativo, mas sem novas inscrições.");
                            else if (c.getEstado() == 2)
                                System.out.println("Este curso já foi concluído.");
                            else
                                System.out.println("Este curso foi cancelado.");

                            System.out.println();
                            System.out.println("(B) Corrigir dados do curso");
                            System.out.println("(C) Encerrar inscrições");
                            System.out.println("(D) Concluir curso");
                            System.out.println("(E) Cancelar curso");
                            System.out.println("(R) Retornar");

                            System.out.print("Opção: ");
                            char op2 = Character.toUpperCase(sc.next().charAt(0));

                            if (op2 == 'R') {
                                voltar = 1;
                            }

                            else if (op2 == 'B') {

                                sc.nextLine();

                                System.out.print("Novo nome: ");
                                c.setNome(sc.nextLine());

                                System.out.print("Nova descrição: ");
                                c.setDescricao(sc.nextLine());

                                arqCurso.update(c);
                            }

                            else if (op2 == 'C') {
                                c.setEstado(1);
                                arqCurso.update(c);
                            }

                            else if (op2 == 'D') {
                                c.setEstado(2);
                                arqCurso.update(c);
                            }

                            else if (op2 == 'E') {
                                c.setEstado(3);
                                arqCurso.update(c);
                            }
                        }
                    }
                }

                else {
                    System.out.println("Opção inválida.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}