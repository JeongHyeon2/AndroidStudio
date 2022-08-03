import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Server extends Thread {
    private Socket socket;
    private MemberManagement management;
    private OutputStream os;
    private InputStream is;

    public Server(Socket connectedClientSocket) {
        this.socket = connectedClientSocket;
        management = new MemberManagement();
        try {
            os = socket.getOutputStream();
            is = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        try {
//            ServerSocket sSocket = new ServerSocket(3003);
//            System.out.println("클라이언트 접속 대기중...");
//            Socket socket = sSocket.accept();
//            System.out.println("클라이언트 접속");


            while (true) {
                Protocol protocol = new Protocol(); // 새 Protocol 객체 생성
                byte[] buf = protocol.getPacket();
                is.read(buf); // 클라이언트로부터 수신
                int packetType = buf[0]; // 수신 데이터에서 패킷 타입 얻음
                String[] arr = new String(buf).split("/");
                for (int i = 1; i < arr.length - 1; i++) System.out.print(arr[i]+" ");

                switch (packetType) {
                    case Protocol.REQ_ADD:
                        try {
                            management.add(arr[1], arr[2], arr[3], Integer.parseInt(arr[4]));
                        }catch (DuplicationException e){
                            Protocol protocol1 = new Protocol(Protocol.DUP_ID);
                            os.write(protocol1.getPacket());
                            System.out.println("id 증복");
                            break;
                        }
                        Protocol protocol1 = new Protocol(Protocol.SUCCESS);
                        os.write(protocol1.getPacket());
                        System.out.println("성공");
                        break;
                    case Protocol.REQ_LOGIN:
                        System.out.println("id:"+arr[1]+" pwd:"+arr[2]);
                        if(management.login(arr[1],arr[2])){
                            Protocol protocol2 = new Protocol(Protocol.SUCCESS_LOGIN);
                            os.write(protocol2.getPacket());
                            System.out.println(arr[1]+" 로그인 성공");
                        }else{
                            Protocol protocol2 = new Protocol(Protocol.FAIL);
                            os.write(protocol2.getPacket());
                            System.out.println(arr[1]+" 로그인 실패");
                        }
                        break;

                }
            }
        } catch (Exception e) {

        }
    }

}
