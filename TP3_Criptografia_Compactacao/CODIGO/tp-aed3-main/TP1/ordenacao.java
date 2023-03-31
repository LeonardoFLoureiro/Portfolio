package TP1;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import CRUD.Crud;
import CRUD.ContaMODEL;

public class ordenacao {
    long pos_big_arq;
    short caminhos;
    int lim_registros;
    ArrayList<String> arq_temps_nomes;
    Crud CRUD;

    public ordenacao() { // Construtor da ordenacao
        CRUD = new Crud("banco.dat");
        pos_big_arq = 12;

        arq_temps_nomes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // System.out.println("Digite a quantidade de caminhos: ");
        // caminhos = Short.parseShort(sc.nextLine());
        caminhos = 2;

        // System.out.println("Digite o limite de registro a ser ordenado por vez em
        // memoria primaria: ");
        // lim_registros = Short.parseShort(sc.nextLine());
        lim_registros = 5;

        for (int i = 0; i < caminhos; i++) { // Cria os nomes dos arquivos
            arq_temps_nomes.add("Arq_Temp_" + i + ".dat");
        }

        intercalacao_comum(); // Primeira parte da intercalacao

        try {
            newIntercalaoPart2();
        } catch (Exception e) {

            e.printStackTrace();
        }
        sc.close();

    }

    public void intercalacao_comum() {

        int i = 0;
        boolean flag = true;
        while (flag) {
            // System.out.println("POS BIG ARQ = " + pos_big_arq);
            LinkedList<ContaMODEL> tempList = new LinkedList<>();
            try {

                for (int j = 0; j < lim_registros; j++) {
                    // System.out.println("EXECUTANDO LIM_REGISTROS");
                    ContaMODEL tempAcc = CRUD.readRegistro(pos_big_arq);

                    if (tempAcc.getLapide()) {
                        tempList.add(tempAcc);

                    }
                    pos_big_arq += CRUD.getTamanhoTotalRegistro(pos_big_arq); // pos_big_arq == tam_antigo;
                }

            } catch (EOFException e) {
                flag = false;
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                RandomAccessFile tempArq = new RandomAccessFile(arq_temps_nomes.get(i), "rw");
                Crud tempCrud = new Crud(arq_temps_nomes.get(i));

                tempList.sort(null);

                for (ContaMODEL c : tempList) {
                    tempCrud.create(c);
                    // tempCrud.writeRegistro(c, pos);
                }
                tempArq.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
            i = i % caminhos;
            System.out.println(tempList.toString());
        }

    }

    public void newIntecalacao() throws IOException {

        RandomAccessFile rf;

        File a = new File("Arq_Temp_0.dat");
        File b = new File("Arq_Temp_1.dat");
        File c = new File("Arq_Temp_2.dat");
        File d = new File("Arq_Temp_3.dat");

        //boolean flag = true;
        boolean flag2 = true;

        long pos0 = 0;
        long pos1 = 0;
        lim_registros = 10;
        do { // 1 Arquivo
            System.out.println("outro teste");

            pos0 = 12;
            pos1 = 12;

            flag2 = true;
            while ((pos0 < a.length() || pos1 < b.length()) && flag2) { // C COM O D
                System.out.println(pos0 + "  " + pos1);
                System.out.println("teste");
                for (int index = 0; index < lim_registros; index++) { // A E O B NO C
                    ContaMODEL acc0 = null;
                    if (pos0 < a.length()) {
                        Crud CRUD = new Crud(a);
                        try {
                            acc0 = CRUD.readRegistro(pos0);
                        } catch (IOException e) {
                            acc0 = null;
                        }
                    }

                    ContaMODEL acc1 = null;
                    if (pos1 < b.length()) {
                        Crud CRUD = new Crud(b);
                        try {
                            acc1 = CRUD.readRegistro(pos1);
                        } catch (IOException e) {
                            acc1 = null;
                        }
                    }

                    rf = new RandomAccessFile(c, "rw");
                    CRUD = new Crud(c);

                    if (acc0 != null && acc1 != null) {
                        if (acc0.getIdConta() < acc1.getIdConta()) {
                            CRUD.create(acc0);
                            pos0 += acc0.getTam_registro();
                        } else {
                            CRUD.create(acc1);
                            pos1 += acc1.getTam_registro();
                        }
                    } else if (acc1 == null && acc0 == null) {
                        flag2 = false;
                    } else if (acc0 == null) {
                        CRUD.create(acc1);
                        pos1 += acc1.getTam_registro();
                    } else if (acc1 == null) {
                        CRUD.create(acc0);
                        pos0 += acc0.getTam_registro();
                    }
                    rf.close();

                }
                // swap c d
                File aux2 = c;
                c = d;
                d = aux2;
            }

            System.gc();

            lim_registros += lim_registros;

            System.out.println(a.getName() + "\n" + b.getName());
            String pathNameA = a.getName();
            String pathNameB = b.getName();
            System.out.println(a.delete());
            System.out.println(b.delete());
            a = new File(pathNameA);
            b = new File(pathNameB);
            // swap a c
            File aux = a;
            a = c;
            c = aux;
            // swap b d
            aux = b;
            b = d;
            d = aux;

            System.out.println(a.getName() + "\n" + b.getName());

        } while (pos0 != 12 && pos1 != 12);
    }

    public void newIntercalaoPart2() {

        File a = new File("Arq_Temp_0.dat");
        File b = new File("Arq_Temp_1.dat");
        File c = new File("Arq_Temp_2.dat");
        File d = new File("Arq_Temp_3.dat");

        File dest = c;

        long posA = 12;
        long posB = 12;

        while (posB == 12) {

            System.out.println("while 1");
            int n_registros = 0;
            posA = 12;
            posB = 12;
            boolean flag = true;
            while ((posA < a.length() || posB < b.length())&&  flag) {
                System.out.println("while 2");
                System.out.println("a " + posA + " " + a.length() + "b  " + posB + " " + b.length());

                while (n_registros < lim_registros) {
                    System.out.println("while 3");

                    // Ler o A
                    ContaMODEL accA=null;
                    try {
                        Crud CRUD = new Crud(a);
                        accA = CRUD.readRegistro(posA);

                    } catch (Exception e) {
                        accA = null;
                    }

                    // Ler o B

                    ContaMODEL accB=null;
                    try {
                        Crud CRUD = new Crud(b);
                        accB = CRUD.readRegistro(posB);

                    } catch (Exception e) {
                        accB = null;
                    }

                    // Escreve em C
                    RandomAccessFile rfC;

                    try {
                        rfC = new RandomAccessFile(dest, "rw");
                        rfC.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CRUD = new Crud(dest);

                    ////////////////// SE EXISTIREM OS 2
                    if (accA != null && accB != null) {
                        if (accA.getIdConta() < accB.getIdConta()) {
                            CRUD.create(accA);
                            posA += accA.getTam_registro();
                            n_registros++;
                        } else {
                            CRUD.create(accB);
                            posB += accB.getTam_registro();
                            n_registros++;
                        }
                    } ///////////// SE SÓ O A EXISTIR
                    else if (accA != null) {
                        CRUD.create(accA);
                        posA += accA.getTam_registro();
                        n_registros++;
                    } else if (accB != null) {
                        CRUD.create(accB);
                        posB += accB.getTam_registro();
                        n_registros++;
                    } else {
                        flag=false;
                        break;
                    }
                }
                lim_registros += lim_registros;
                if (dest.equals(c)) {
                    dest = d;
                } else {
                    dest = c;
                }
            }
            a = c;
            b = d;
            System.out.println(c.delete());
            System.out.println(d.delete());
        }
        

    }

    public int id_registro_temp(int num_arq_temp) {

        return num_arq_temp;
    }
}
