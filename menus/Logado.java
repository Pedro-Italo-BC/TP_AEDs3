public class Logado {
    public static void menu(Usuario user) {
        int s = 0;
         while(s==0){
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
            opcao = aux.charAt(0);

            //sair
            if(opcao ==  'S'){
                s=1;
                break;
            }

            //mostrar os dados
            else if(opcao == 'A'){
                System.out.println("TP01 Aeds3");
                System.out.println("----------");
                System.out.println("> Inicio > Meus Dados");
                System.out.println();

                System.out.println("Meus dados: ");
                System.out.println(user.toString());

                System.out.println("Digite qualquer coisa para sair");
                System.out.println();
                sc.next();
            } 

            //meus cursos
            else if(opcao == 'B'){
                

            } 
            
            //minhas inscrições
            else if(opcao == 'C') {

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