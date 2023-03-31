package CRUD;
import java.io.RandomAccessFile;

public class Header {
    public int lastId;
    public int totalRegistros;
    public int totalLapides;

    public static final int SIZE = 12;

    private RandomAccessFile arq;

    Header(Header h) {
        this.lastId = h.lastId;
        this.totalLapides = h.totalLapides;
        this.totalRegistros = h.totalRegistros;
    }

    Header(RandomAccessFile arq) {
        this.arq = arq;
        readHeader();
    }


    public void writeHeader(int lastID, int totalRegistros, int totalLapides) {
        try {
            arq.seek(0);
            arq.writeInt(lastID);
            arq.writeInt(totalRegistros);
            arq.writeInt(totalLapides);

        } catch (Exception e) {

        }
    }

    public void writeHeader(Header header) {
        try {
            arq.seek(0);
            arq.writeInt(header.lastId);
            arq.writeInt(header.totalRegistros);
            arq.writeInt(header.totalLapides);

        } catch (Exception e) {

        }
    }

    public Header readHeader() {

        try {
            arq.seek(0);
            this.lastId = arq.readInt();
            this.totalRegistros = arq.readInt();
            this.totalLapides = arq.readInt();

        } catch (Exception e) {
        }

        return this;

    }
}