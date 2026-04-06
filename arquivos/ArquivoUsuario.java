package arquivos;

import entidades.Usuario;
import java.io.*;

public class ArquivoUsuario {

    RandomAccessFile arq;
    final int TAM_CABECALHO = 4;

    public ArquivoUsuario(String nomeArquivo) throws Exception {
        arq = new RandomAccessFile(nomeArquivo, "rw");

        if (arq.length() < TAM_CABECALHO) {
            arq.writeInt(0); // último ID
        }
    }

    // CREATE
    public int create(Usuario u) throws Exception {
        arq.seek(0);
        int ultimoID = arq.readInt();
        int novoID = ultimoID + 1;
        u.setIdUsuario(novoID);

        arq.seek(0);
        arq.writeInt(novoID);

        arq.seek(arq.length());
        byte[] ba = u.toByteArray();

        arq.writeByte(' ');
        arq.writeShort(ba.length);
        arq.write(ba);

        return novoID;
    }

    // LOGIN (busca sequencial por enquanto)
    public Usuario login(String email, String senha) throws Exception {
        arq.seek(TAM_CABECALHO);

        String hash = Usuario.gerarHash(senha);

        while (arq.getFilePointer() < arq.length()) {
            byte lapide = arq.readByte();
            short tam = arq.readShort();

            if (lapide != '*') {
                byte[] ba = new byte[tam];
                arq.readFully(ba);

                Usuario u = new Usuario();
                u.fromByteArray(ba);

                if (u.getEmail().equals(email) &&
                    u.getHashSenha().equals(hash)) {
                    return u;
                }
            } else {
                arq.skipBytes(tam);
            }
        }

        return null;
    }

    //READ
    public Usuario read(int id) throws Exception {
        arq.seek(TAM_CABECALHO);

        while (arq.getFilePointer() < arq.length()) {

            byte lapide = arq.readByte();
            short tam = arq.readShort();

            if (lapide != '*') {
                byte[] ba = new byte[tam];
                arq.readFully(ba);

                Usuario u = new Usuario();
                u.fromByteArray(ba);

                if (u.getIdUsuario() == id) {
                    return u;
                }

            } else {
                arq.skipBytes(tam);
            }
        }

        return null;
    }

    //Update
    /*public boolean update(Usuario novo) throws Exception {
        arq.seek(TAM_CABECALHO);

        while (arq.getFilePointer() < arq.length()) {

            long pos = arq.getFilePointer();

            byte lapide = arq.readByte();
            short tam = arq.readShort();

            if (lapide != '*') {

                byte[] ba = new byte[tam];
                arq.readFully(ba);

                Usuario atual = new Usuario();
                atual.fromByteArray(ba);

                if (atual.getIdUsuario() == novo.getIdUsuario()) {

                    byte[] novoBa = novo.toByteArray();

                    if (novoBa.length <= tam) {
                        // sobrescreve no mesmo espaço
                        arq.seek(pos + 3); // pula lápide + tamanho
                        arq.write(novoBa);
                    } else {
                        // marca antigo como deletado
                        arq.seek(pos);
                        arq.writeByte('*');

                        // escreve no final
                        arq.seek(arq.length());
                        arq.writeByte(' ');
                        arq.writeShort(novoBa.length);
                        arq.write(novoBa);
                    }

                    return true;
                }

            } else {
                arq.skipBytes(tam);
            }
        }

        return false;
    }*/
}