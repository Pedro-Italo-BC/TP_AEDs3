package entidades;

public class Curso  {
    private int idCurso;
    private String nome;
    private String email;
    private String hashSenha; 
    private String perguntaSecreta;
    private String hashRespostaSercreta;

    public Usuario() {

    }

    public Usuario(int id, String nome, String email, String hashSenha, String perguntaSecreta, String hashRespostaSercreta) {
        this.idUsuario = id;
        this.nome = nome;
        this.email = email;
        this.hashSenha = hashSenha;
        this.perguntaSecreta = perguntaSecreta;
        this.hashRespostaSercreta = hashRespostaSercreta;
    }


    public int getidUsuario() {
        return idUsuario;
    }
    public void setidUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHashSenha() {
        return hashSenha;
    }
    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }
    public String getPerguntaSecreta() {
        return perguntaSecreta;
    }
    public void setPerguntaSecreta(String perguntaSecreta) {
        this.perguntaSecreta = perguntaSecreta;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + 
               "\nEmail: " + email;
    }

    public int PegarOHash(String senha){

    }

    public int PegarOHashRespSecreta(String respostaSecreta){

    }

    //logar uma pessoa
    public Usuario logar(String email, String senha) throws Exception {
        arq.seek(TAM_CABECALHO);
        int HashSenha = PegarOHash(senha);

        while (arq.getFilePointer() < arq.length()) {
            byte lapide = arq.readByte();
            short tam = arq.readShort();

            if (lapide != '*') {
                byte[] ba = new byte[tam];
                arq.readFully(ba);

                Usuario p = new Usuario();
                p.fromByteArray(ba);

                if (p.email == email && p.hashSenha.compareTo(Hashsenha)){
                    return p;
                }
            } else {
                arq.skipBytes(tam);
            }
        }
        return null;
    }

    //cadastrar (create)
    public static boolean cadastrar(nome, email, senha, perguntaSecreta, respostaSecreta){
        int hashSenha = PegarOHash(senha);
        int hashrespostaSecreta = PegarOHashRespSecreta(senha);

        arq.seek(0);
        int ultimoID = arq.readInt();
        int id = ultimoID + 1;

        Usuario p = new Usuario(id, nome, email, hashSenha, perguntaSecreta, hashrespostaSecreta); 

        arq.seek(0);
        arq.writeInt(id);

        p.id = id;

        arq.seek(arq.length());
        byte[] ba = p.toByteArray();

        arq.writeByte(' ');
        arq.writeShort(ba.length);
        arq.write(ba);

        return id;
    }

}
