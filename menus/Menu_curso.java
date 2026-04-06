public class Menu_curso {
    public static void menu(Usuario user) {
        int s = 0;
         while(s==0){
            System.out.println("TP01 Aeds3");
            System.out.println("----------");
            System.out.println("> Inicio > Meus Cursos ");
            System.out.println();
            System.out.println("MEUS CURSOS");

            //listar aqui todos os cursos com o read

            System.out.println("(A) Novo curso");
            System.out.println("(R) Retornar ao menu anterior");

            System.out.println();
            aux = sc.next();
            opcao = aux.charAt(0);

            //sair
            if(opcao ==  'R'){
                s=1;
                break;
            }

            //cadastrar um novo curso
            else if(opcao == 'A'){

                System.out.print("Digite seu nome: ");
                String nome;
                nome = sc.next();
                System.out.println();

                //pegar a data de início

                //gerar automaticamente o codigo


                System.out.print("Digite sua senha: ");
                String senha1;
                senha1 = sc.next();
                System.out.println();

                System.out.print("Confirme sua senha: ");
                String senha2;
                senha2 = sc.next();
                System.out.println();
                
            } 

            else {
                System.out.println("Opção Inválida. Deseja tentar novamente? S/N");
                char desejo;
                desejo = sc.next().charAt(0);
                if(desejo == 'S'){
                    s=0;
                } else if(desejo == 'N'){
                    s=1;
                    break;
                } else{
                    System.out.println("Opção Inválida");
                    break;
                }
            }
        }
    }
}