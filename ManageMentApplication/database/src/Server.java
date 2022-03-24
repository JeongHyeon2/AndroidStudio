import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket sSocket = new ServerSocket(3003);
        System.out.println("클라이언트 접속 대기중...");
        Socket socket = sSocket.accept();
        System.out.println("클라이언트 접속");

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();



        while (true) {
            Protocol protocol = new Protocol(); // 새 Protocol 객체 생성
            byte[] buf = protocol.getPacket();
            is.read(buf); // 클라이언트로부터 단어,정수 수신
            int packetType = buf[0]; // 수신 데이터에서 패킷 타입 얻음
            String[] arr =new String(buf).split("/");
            for(int i=1;i<arr.length-1;i++) System.out.println(arr[i]);

            switch (packetType) {
                case Protocol.REQ_ADD:
                    break;

            }
        }

    }
}
