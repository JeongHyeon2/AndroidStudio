import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args){

        try {
            Socket socket = new Socket("192.168.123.254", 3003);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
