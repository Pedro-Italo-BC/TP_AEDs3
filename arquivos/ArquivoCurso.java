package arquivos;

import java.io.*;
import entidades.Curso;

public class ArquivoCurso {

    private RandomAccessFile arquivo;
    private final int TAM_CABECALHO = 4;

    public ArquivoCurso(String nome) throws Exception {
        arquivo = new RandomAccessFile("./dados/" + nome + ".db", "rw");

        if (arquivo.length() < TAM_CABECALHO) {
            arquivo.seek(0);
            arquivo.writeInt(0); // último ID
        }
    }

    // 🔹 CREATE
    public int create(Curso c) throws Exception {

        arquivo.seek(0);
        int ultimoID = arquivo.readInt();
        int novoID = ultimoID + 1;

        arquivo.seek(0);
        arquivo.writeInt(novoID);

        c.setIdCurso(novoID);

        arquivo.seek(arquivo.length());

        byte[] ba = c.toByteArray();

        arquivo.writeByte(' '); // lápide
        arquivo.writeInt(ba.length);
        arquivo.write(ba);

        return novoID;
    }

    // 🔹 READ (por ID)
    public Curso read(int id) throws Exception {

        arquivo.seek(TAM_CABECALHO);

        while (arquivo.getFilePointer() < arquivo.length()) {

            byte lapide = arquivo.readByte();
            int tam = arquivo.readInt();

            if (lapide != '*') {

                byte[] ba = new byte[tam];
                arquivo.readFully(ba);

                Curso c = new Curso();
                c.fromByteArray(ba);

                if (c.getIdCurso() == id)
                    return c;

            } else {
                arquivo.skipBytes(tam);
            }
        }

        return null;
    }

    // 🔹 UPDATE
    public boolean update(Curso novo) throws Exception {

        arquivo.seek(TAM_CABECALHO);

        while (arquivo.getFilePointer() < arquivo.length()) {

            long pos = arquivo.getFilePointer();

            byte lapide = arquivo.readByte();
            int tam = arquivo.readInt();

            if (lapide != '*') {

                byte[] ba = new byte[tam];
                arquivo.readFully(ba);

                Curso atual = new Curso();
                atual.fromByteArray(ba);

                if (atual.getIdCurso() == novo.getIdCurso()) {

                    byte[] novoBa = novo.toByteArray();

                    if (novoBa.length <= tam) {
                        // cabe no mesmo espaço
                        arquivo.seek(pos + 5); // pula lápide + tamanho
                        arquivo.write(novoBa);
                    } else {
                        // não cabe → lápide + escreve no final
                        arquivo.seek(pos);
                        arquivo.writeByte('*');

                        arquivo.seek(arquivo.length());
                        arquivo.writeByte(' ');
                        arquivo.writeInt(novoBa.length);
                        arquivo.write(novoBa);
                    }

                    return true;
                }

            } else {
                arquivo.skipBytes(tam);
            }
        }

        return false;
    }

    // 🔹 DELETE
    public boolean delete(int id) throws Exception {

        arquivo.seek(TAM_CABECALHO);

        while (arquivo.getFilePointer() < arquivo.length()) {

            long pos = arquivo.getFilePointer();

            byte lapide = arquivo.readByte();
            int tam = arquivo.readInt();

            if (lapide != '*') {

                byte[] ba = new byte[tam];
                arquivo.readFully(ba);

                Curso c = new Curso();
                c.fromByteArray(ba);

                if (c.getIdCurso() == id) {
                    arquivo.seek(pos);
                    arquivo.writeByte('*');
                    return true;
                }

            } else {
                arquivo.skipBytes(tam);
            }
        }

        return false;
    }

    // 🔹 LISTAR CURSOS DE UM USUÁRIO
    public void listarPorUsuario(int idUsuario) throws Exception {

        arquivo.seek(TAM_CABECALHO);

        while (arquivo.getFilePointer() < arquivo.length()) {

            byte lapide = arquivo.readByte();
            int tam = arquivo.readInt();

            if (lapide != '*') {

                byte[] ba = new byte[tam];
                arquivo.readFully(ba);

                Curso c = new Curso();
                c.fromByteArray(ba);

                if (c.getIdUsuario() == idUsuario) {
                    System.out.println(c);
                }

            } else {
                arquivo.skipBytes(tam);
            }
        }
    }

    // 🔹 CLOSE
    public void close() throws Exception {
        arquivo.close();
    }
}