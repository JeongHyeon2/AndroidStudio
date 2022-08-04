
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServer {
    public static void main(String[] args) {
        System.out.println("****************************************");
        System.out.println("Thread를 이용한 다중 접속 서버 작동됨...");
        System.out.println("****************************************");

        ServerSocket server = null;
        int connectCount=0;

        try {
            server = new ServerSocket(3003);

            while(true) {
                // 클라이언트가 접속해 오기를 기다립니다.
                Socket connectedClientSocket = server.accept();

                InetAddress ia = connectedClientSocket.getInetAddress();
                int port = connectedClientSocket.getLocalPort();// 접속에 사용된 서버측 PORT
                String ip = ia.getHostAddress(); // 접속된 원격 Client IP

                ++connectCount;  //접속자수 카운트
                System.out.print(connectCount);
                System.out.print(" 접속-Local Port: "+ port);
                System.out.println(" Client IP: " + ip);


                // -------------------------------------------
                // 스레드 관련 부분
                // -------------------------------------------
                //Handler 클래스로 client 소켓 전송
                Server handler = new Server(connectedClientSocket);
                //스레드 시작, run()호출
                handler.start(); // start() --> run() 호출
                // -------------------------------------------

            }
        } catch(IOException ioe) {
            System.err.println("Exception generated...");
        } finally {
            try {
                server.close();
            } catch(IOException ignored) {}
        }
    }
}
