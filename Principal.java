import java.util.*;

import src.menus.Logado;
import entidades.Usuario.ArquivoUsuario;
import entidades.Usuario.Usuario;
//import entidades.Curso;

public class Principal {
    public static void main(String[] args)  throws Exception{
        ArquivoUsuario arqUsuario = new ArquivoUsuario("dados/usuario/usuarios.db");

        Scanner sc = new Scanner(System.in);
        int s = 0;
        String aux = "";
        char opcao;

        while (s == 0) {
            System.out.println("TP01 Aeds3");
            System.out.println("----------");
            System.out.println();
            System.out.println("(A) Login");
            System.out.println("(B) Novo Usuário");
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

            // logar
            else if (opcao == 'A') {
                System.out.print("Digite seu email: ");
                String email = sc.next();

                System.out.print("Digite sua senha: ");
                String senha = sc.next();
                System.out.println();

                Usuario u = arqUsuario.login(email, senha);

                if (u != null) {
                    // próximo menu
                    Logado.menu(u);
                } else {
                    System.out.println("Email ou senha inválidos.");
                }

            }

            // cadastrar
            else if (opcao == 'B') {

                System.out.print("Digite seu nome: ");
                String nome = sc.next();
                System.out.println();

                System.out.print("Digite seu email: ");
                String email = sc.next();
                System.out.println();

                System.out.print("Digite sua senha: ");
                String senha1 = sc.next();
                System.out.println();

                System.out.print("Confirme sua senha: ");
                String senha2 = sc.next();
                System.out.println();

                while (!senha1.equals(senha2)) {
                    System.out.println("As senhas devem ser iguais. Digite novamente");
                    senha1 = sc.next();
                    System.out.println();
                    senha2 = sc.next();
                    System.out.println();
                }

                System.out.print("Digite uma pergunta secreta: ");
                String perguntaSecreta = sc.next();
                System.out.println();

                System.out.print("Digite a resposta da sua pergunta secreta: ");
                System.out.println();
                System.out.print("Observação: Você precisará dela caso esqueça sua senha! ");
                String respostaSecreta = sc.next();
                System.out.println();

                System.out.print("Deseja confirmar seu cadastro? S/N: ");
                char desejo2 = Character.toUpperCase(sc.next().charAt(0));
                System.out.println();

                if (desejo2 == 'S') {
                    Usuario novo = new Usuario(
                        0,
                        nome,
                        email,
                        Usuario.gerarHash(senha1),
                        perguntaSecreta,
                        Usuario.gerarHash(respostaSecreta)
                    );
                    arqUsuario.create(novo);
                    System.out.println("Cadastro realizado com sucesso!");
                } else if (desejo2 == 'N') {
                    continue;
                }

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