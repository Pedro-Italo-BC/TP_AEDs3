package entidades;

public class Curso  {
    private int idCurso;
    private String nome;
    private LocalDate inicio;
    private int codigo;
    private String estado;

    public Curso() {

    }

    public Curso(int id, String nome, LocalDate inicio, int codigo, String estado) {
        this.idCurso = id;
        this.nome = nome;
        this.email = email;
        this.codigo = codigo;
        this.estado = estado;
    }

    public int getidCurso() {
        return idCurso;
    }
    public void setidCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getInicio() {
        return inicio;
    }
    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    //cadastrar (create)
    public static boolean cadastrar(nome, inicio, codigo, estado){

        arq.seek(0);
        int ultimoID = arq.readInt();
        int id = ultimoID + 1;

        Curso p = new Curso(id, nome, inicio, codigo, estado); 

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

//ler todos (read)
    public ArrayList<Curso> readAll() throws Exception {
        ArrayList<Curso> lista = new ArrayList<>();

        arq.seek(TAM_CABECALHO);

        while (arq.getFilePointer() < arq.length()) {
            byte lapide = arq.readByte();
            short tam = arq.readShort();

            if (lapide != '*') {
                byte[] ba = new byte[tam];
                arq.readFully(ba);

                Curso p = new Curso();
                p.fromByteArray(ba);

                lista.add(p);
            } else {
                arq.skipBytes(tam);
            }
        }
        return lista;
    }

}
