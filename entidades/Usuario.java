package entidades;

public class Usuario  {
    private int idUsaurio;
    private String nome;
    private String email;
    private String hashSenha; 
    private String perguntaSecreta;
    private String hashRespostaSercreta;

    public Usuario() {

    }

    public Usuario(int id, String nome, String email, String hashSenha, String perguntaSecreta, String hashRespostaSercreta) {
        this.idUsaurio = id;
        this.nome = nome;
        this.email = email;
        this.hashSenha = hashSenha;
        this.perguntaSecreta = perguntaSecreta;
        this.hashRespostaSercreta = hashRespostaSercreta;
    }


    public int getIdUsaurio() {
        return idUsaurio;
    }
    public void setIdUsaurio(int idUsaurio) {
        this.idUsaurio = idUsaurio;
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
        return "ID...." + idUsaurio; 
    }

}
