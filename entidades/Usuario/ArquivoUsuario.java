package entidades.Usuario;

import aed3.*;
import java.io.*;

public class ArquivoUsuario extends Arquivo<Usuario> {
    
    RandomAccessFile arq;
    HashExtensivel<ParEmailId> indiceEmail;
    final int TAM_CABECALHO = 4;

    public ArquivoUsuario(String nomeArquivo) throws Exception {
        super("usuario", Usuario.class.getConstructor());
        
    
        indiceEmail = new HashExtensivel<>(
            ParEmailId.class.getConstructor(), 
            TAM_CABECALHO,
            "./dados/usuario/indiceEmail.d.db",
            "./dados/usuario/indiceEmail.c.db");

     
    }

    // CREATE
    public int create(Usuario u) throws Exception {
        int id = super.create(u);
        indiceEmail.create(new ParEmailId(u.getEmail(), id));
        return id;
    }

    // LOGIN (busca sequencial por enquanto)
    public Usuario login(String email, String senha) throws Exception {
        ParEmailId pei = indiceEmail.read(Math.abs(email.hashCode()));
        
        if(pei == null || !pei.getEmail().equals(email))
            return null;

        Usuario u = read(pei.getId());

        String hash = Usuario.gerarHash(senha);

        if(u != null && u.getHashSenha().equals(hash)) {
            return u;
        }

        return null;
    }
    //READ
    // public Usuario read(int id) throws Exception {
    //     arq.seek(TAM_CABECALHO);

    //     while (arq.getFilePointer() < arq.length()) {

    //         byte lapide = arq.readByte();
    //         short tam = arq.readShort();

    //         if (lapide != '*') {
    //             byte[] ba = new byte[tam];
    //             arq.readFully(ba);

    //             Usuario u = new Usuario();
    //             u.fromByteArray(ba);

    //             if (u.getIdUsuario() == id) {
    //                 return u;
    //             }

    //         } else {
    //             arq.skipBytes(tam);
    //         }
    //     }

    //     return null;
    // }

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