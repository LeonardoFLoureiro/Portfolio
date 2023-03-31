package TP2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Celula {
    Byte simbolo;

    Integer freq;

    Celula esq = null;
    Celula dir = null;

    Celula(Byte simbolo, Integer freq) {
        this.simbolo = simbolo;
        this.freq = freq;
    }

    public Celula(Byte simbolo, Integer freq, Celula esq, Celula dir) {
        this.simbolo = simbolo;
        this.freq = freq;
        this.esq = esq;
        this.dir = dir;
    }

    public boolean isFolha() {
        return this.esq == null && this.dir == null;
    }
}

public class Huffman {

    private static void preencherSimbFreqMap(String filePath, HashMap<Byte, Integer> simbFreqMap) {

        try {
            RandomAccessFile arq = new RandomAccessFile(filePath, "rw");
            while (true) {
                try {
                    byte b = arq.readByte();
                    simbFreqMap.put(b, simbFreqMap.getOrDefault(b, 0) + 1);
                } catch (EOFException e) {
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            arq.close();

        } catch (IOException e) {

        }
    }

    private static void encodeData(Celula cell, String str, HashMap<Byte, String> codgioHuffman) {
        if (cell == null) {
            return;
        }
        if (cell.isFolha()) {
            codgioHuffman.put(cell.simbolo, str.length() > 0 ? str : "1");
        }
        encodeData(cell.esq, str + '0', codgioHuffman);
        encodeData(cell.dir, str + '1', codgioHuffman);
    }

    private static Celula criarArvoreHuffman(HashMap<Byte, Integer> simbFreqMap) {
        PriorityQueue<Celula> listaCelulas = new PriorityQueue<>(Comparator.comparingInt(c -> c.freq));

        for (var e : simbFreqMap.entrySet()) {
            listaCelulas.add(new Celula(e.getKey(), e.getValue()));
        }

        while (listaCelulas.size() != 1) {

            Celula esq = listaCelulas.poll();
            Celula dir = listaCelulas.poll();

            int somaFreq = esq.freq + dir.freq;

            listaCelulas.add(new Celula(null, somaFreq, esq, dir));

        }

        return listaCelulas.peek();

    }

    public static void comprimirArquivo(String filePath) {

        HashMap<Byte, Integer> simbFreqMap = new HashMap<>();
        HashMap<Byte, String> codgioHuffman = new HashMap<>();

        preencherSimbFreqMap(filePath, simbFreqMap);

        Celula raiz = criarArvoreHuffman(simbFreqMap);

        encodeData(raiz, "", codgioHuffman);

        BitSequence sequenciaBits = new BitSequence();

        try {
            RandomAccessFile arq = new RandomAccessFile(filePath, "rw");
            while (true) {
                try {
                    byte b = arq.readByte();
                    sequenciaBits.appendBits(codgioHuffman.get(b));
                } catch (EOFException e) {
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            arq.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        HuffmanSave arqSave = new HuffmanSave(sequenciaBits, simbFreqMap);
        String huffmanFileName = "Huffman_" + filePath;
        writeHuffman(huffmanFileName, arqSave);
    }

    private static void writeHuffman(String fileName, HuffmanSave data) {
        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(data);
            o.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static HuffmanSave readHuffman(String filePath) {
        try {
            FileInputStream fi = new FileInputStream(new File(filePath));
            ObjectInputStream oi = new ObjectInputStream(fi);

            HuffmanSave hs = (HuffmanSave) oi.readObject();

            oi.close();
            fi.close();
            return hs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("deu ruim na hora de ler");
        return null;
    }

    public static void descomprimirArquivo(String filePath) {

        HashMap<Byte, Integer> simbFreqMap = new HashMap<>();

        HuffmanSave hf = readHuffman(filePath);
        simbFreqMap = hf.getFrequencies();
        BitSequence bits = hf.getEncoding();
        Celula raiz = criarArvoreHuffman(simbFreqMap);

        try {
            RandomAccessFile arq = new RandomAccessFile("Decoded_" + filePath, "rw");
            if (raiz.isFolha()) {
                // special case: For input like a, aa, aaa, etc.
                while (raiz.freq-- > 0) {
                    System.out.print("erro ao descomprimir arquivo e uma sequencia de um unico simbolo");
                }
            } else {
                int index = -1;
                while (index < bits.length() - 1) {
                    index = decodeData(raiz, index, bits.toString(), arq);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static int decodeData(Celula cell, int index, String encodedString, RandomAccessFile arq) {
        if (cell == null) {
            return index;
        }
        if (cell.isFolha()) {
            try {
                arq.writeByte(cell.simbolo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return index;
        }
        index++;
        cell = (encodedString.charAt(index) == '0') ? cell.esq : cell.dir;
        index = decodeData(cell, index, encodedString, arq);
        return index;
    }
}
