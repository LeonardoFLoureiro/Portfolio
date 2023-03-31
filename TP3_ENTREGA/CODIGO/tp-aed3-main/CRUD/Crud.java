package CRUD;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import TP3.Criptografia;

public class Crud {
    RandomAccessFile arq;
    Header header;
    // Construtores

    public Crud(String filePath) {

        try {
            arq = new RandomAccessFile(filePath, "rw");
            header = new Header(arq); // Criação do Header
        } catch (Exception e) {
            System.out.println("Erro na declaracao do randomacessarq");
        }
    }

    public Crud(File f) {

        try {
            arq = new RandomAccessFile(f, "rw");
            header = new Header(arq); // Criação do Header
        } catch (Exception e) {
            System.out.println("Erro na declaracao do randomacessarq");
        }
    }

    public void create(ContaMODEL conta) {

        long pos = Header.SIZE;
        while (true) {
            try {

                int tam_res = arq.readInt() + 4; // Para descobrir o tamanho do registro

                ContaMODEL c = readRegistro(pos);
                if (c.getLapide() && conta.getNomeUsuario().equals(c.getNomeUsuario())) { // If para conferir se o
                                                                                          // NomeUsuario ja existe
                    System.out.println("Nome Usuario ja existe");
                    return;
                }
                pos += tam_res;
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            arq.seek(arq.length());

            if (arq.getFilePointer() == 0) {
                header.writeHeader(0, 0, 0); // Escrever o header se não existe
            }

            writeRegistro(conta, arq.length());

            header.writeHeader(header.lastId, header.totalRegistros + 1, header.totalLapides);

        } catch (Exception e) {
            System.out.println("Erro no Create");
        }
    }

    public void writeRegistro(ContaMODEL conta, long endereco_escrita) { // Func para efetivamente escrever o regustro
                                                                         // na posicao indicada

        try {
            arq.seek(endereco_escrita);
            arq.writeInt(conta.getTam_registro());
            arq.writeBoolean(conta.getLapide());
            if (conta.getIdConta() == 0) {
                // System.out.println("Caiu aqui!");
                arq.writeInt(header.lastId += 1);
                conta.setIdConta(header.lastId);
            } else {
                arq.writeInt(conta.getIdConta());
            }
            arq.writeShort(conta.getQtd_email());
            for (String s : conta.getEmail()) {
                arq.writeUTF(s);
            }
            arq.writeUTF(conta.getNomePessoa());
            arq.writeUTF(conta.getNomeUsuario());
            // TODO linha que criptografa o arquivo
            arq.writeUTF(Criptografia.criptografar(conta.getSenha()));
            arq.writeUTF(conta.getCpf());
            arq.writeUTF(conta.getCidade());
            arq.writeInt(conta.getTransferencias());
            arq.writeFloat(conta.getSaldo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(ContaMODEL conta) { // Func de update do crud
        try {
            long pos = Header.SIZE;
            while (true) {
                try {

                    int tam_res = arq.readInt() + 4;

                    ContaMODEL c = readRegistro(pos);
                    if (c.getLapide() && conta.getNomeUsuario().equals(c.getNomeUsuario())) { // Verifica se o Nome de
                                                                                              // Usuario ja existe
                        System.out.println("Nome Usuario ja existe");
                        return;
                    }
                    pos += tam_res;
                } catch (EOFException e) {
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            pos = search(conta.getIdConta()); // Procura a posicao do registro a ser modificado
            arq.seek(pos);
            int tam_anterior = arq.readInt();

            if (tam_anterior >= conta.getTam_registro()) { // Se o tamanho do registro novo for menor ou igual ele
                                                           // sobre-escreve os dados
                conta.setLapide(true);
                writeRegistro(conta, pos);
            } else { // Se for maior ele marca a lapide e escreve no final do arquivo
                arq.writeBoolean(false);
                conta.setLapide(true);
                create(conta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            arq.seek(search(id) + 4);
            arq.writeBoolean(false); // Marca a lapide

            header.writeHeader(header.lastId, header.totalRegistros - 1, header.totalLapides + 1); // Diminui o total de
                                                                                                   // registros no
                                                                                                   // header e aumenta a
                                                                                                   // quantidade de
                                                                                                   // lapides

        } catch (Exception e) {
            System.out.println("PROBLEMA NO DELETE " + e);
        }
    }

    public ContaMODEL read(int id) { // Metodo read do crud
        long pos = search(id);

        if (pos != -1) {
            try {
                return readRegistro(pos);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    public ContaMODEL readRegistro(long pos) throws IOException { // Retorna o Model lido na posicao pos do Arquivo

        arq.seek(pos + 4);

        boolean lapide = arq.readBoolean();

        int idConta = arq.readInt();
        short qtd_email = arq.readShort();

        ArrayList<String> email = new ArrayList<>();
        for (int i = 0; i < qtd_email; i++) {
            email.add(arq.readUTF());
        }

        String nomePessoa = arq.readUTF();
        String nomeUsuario = arq.readUTF();
        // TODO descomentar linha pra imprimir a senha criptografada
        String senha = Criptografia.descriptografar(arq.readUTF());
        // String senha = arq.readUTF();

        String cpf = arq.readUTF();
        String cidade = arq.readUTF();
        int transferencias = arq.readInt();
        float saldo = arq.readFloat();

        return new ContaMODEL(idConta,
                nomePessoa,
                email,
                nomeUsuario,
                senha,
                cpf,
                cidade,
                transferencias,
                saldo,
                lapide);

    }

    private long search(int idConta) { // Retorna a posicao no arquivo do uma conta de id idConta

        long pos = Header.SIZE;

        while (true) {
            try {
                arq.seek(pos);
                int tam_res = arq.readInt() + 4;
                if (arq.readBoolean() && (arq.readInt() == idConta)) {
                    return pos;
                }

                pos += tam_res;
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    public int getTamanhoTotalRegistro(long pos) { // Metodo pra retornar a quantidade total de registros
        try {
            arq.seek(pos);
            return arq.readInt() + 4;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    public String getAllRegistros() { // Dev-Only
        StringBuilder s = new StringBuilder();

        long pos = Header.SIZE;
        while (true) {
            try {
                arq.seek(pos);
                int tam_res = arq.readInt() + 4;

                s.append(readRegistro(pos));

                pos += tam_res;
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s.toString();
    }

    public String arqToSring() { // Dev-Only
        StringBuilder s = new StringBuilder();

        long pos = Header.SIZE;
        while (true) {
            try {
                arq.seek(pos);
                int tam_res = arq.readInt() + 4;

                s.append(readRegistro(pos).contaFormatToString());

                pos += tam_res;
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s.toString();
    }

    public void printRawFile() {
        try {
            arq.seek(0);
            System.out.println(arq.readInt());
            System.out.println(arq.readInt());
            System.out.println(arq.readInt());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
