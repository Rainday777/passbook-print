package kbtg.kbond.passbookprint.serialport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 * @author ThaChie
 */
public class Printer {

    public String printer;
    public boolean escp24pin; //boolean to indicate whether the printer is a 24 pin esc/p2 epson
    public OutputStream ostream;
    public PrintStream pstream;
    public boolean streamOpenSuccess;

    public void close() {
        System.out.println("[method]close");
        
        //post: closes the stream, used when printjob ended
        try {
            ostream.close();
            pstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
    public void close(String title, String message) {
        System.out.println("[method]close");
        
        //post: closes the stream, used when printjob ended
        try {
            ostream.close();
            pstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public boolean isInitialized() {
        //post: returns true iff printer was successfully initialized
        return streamOpenSuccess;
    }

    public String getShare() {
        //post: returns printer share name (Windows network)
        return printer;
    }
    
    public String toString() {
        //post: returns String representation of ESCPrinter e.g. <ESCPrinter[share=...]>
        StringBuilder strb = new StringBuilder();
        strb.append("<Printer [share=").append(printer).append(", 24pin=").append(escp24pin).append("]>");
        return strb.toString();
    }
}