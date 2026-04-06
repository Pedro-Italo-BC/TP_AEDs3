package entidades;

import java.time.LocalDate;
import java.io.*;

public class Curso {
    private int idCurso;
    private int idUsuario; // relacionamento 1:N
    private String nome;
    private LocalDate inicio;
    private String codigo;
    private int estado;

    public Curso() {
    }

    public Curso(int idCurso, int idUsuario, String nome, LocalDate inicio, String codigo, int estado) {
        this.idCurso = idCurso;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.inicio = inicio;
        this.codigo = codigo;
        this.estado = estado;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    // serialização
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(ba);

        dos.writeInt(idCurso);
        dos.writeInt(idUsuario);
        dos.writeUTF(nome);
        dos.writeUTF(inicio.toString());
        dos.writeUTF(codigo);
        dos.writeInt(estado);

        return ba.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bi = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bi);

        idCurso = dis.readInt();
        idUsuario = dis.readInt();
        nome = dis.readUTF();
        inicio = LocalDate.parse(dis.readUTF());
        codigo = dis.readUTF();
        estado = dis.readInt();
    }
}