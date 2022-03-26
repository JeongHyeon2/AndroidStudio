import java.io.*;
import java.util.ArrayList;

public class MemberManagement {
    private ArrayList<Member> memberArr;
    private String path;

    public MemberManagement() {
        memberArr = new ArrayList<>();
        path="C:\\Users\\35752\\Desktop\\MemberList.txt";
        readFile();
    }
    public boolean login(String id, String pwd){
        for(int i=0;i<memberArr.size();i++){
            if(memberArr.get(i).getId().equals(id))
                if(memberArr.get(i).getPassword().equals(pwd))return true;
        }
        return false;
    }

    public void add(String id, String pwd, String name, int age) throws DuplicationException {
        for(int i=0;i<memberArr.size();i++){
            if(memberArr.get(i).getId().equals(id)){
                throw new DuplicationException();
            }
        }
        memberArr.add(new Member(id, pwd, name, age));
        saveFile(id+" "+pwd+" "+name+" "+age+"\n");
    }
    public void add_(String id, String pwd, String name, int age) {
        memberArr.add(new Member(id, pwd, name, age));

    }

    public void delete(String id) {
        for (int i = 0; i < memberArr.size(); i++) {
            if (memberArr.get(i).getId().equals(id)) {
                memberArr.remove(i);
            }
        }
    }

    public void saveFile(String str) {
        System.out.println("Saved to " + path);
        File f =  new File(path);
        try {
            FileWriter fw = new FileWriter(f,true);
            fw.write(str);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void readFile() {
        System.out.println("Loaded from " + path);
        File file = new File(path);
        try {
            if (file.isFile()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
                String s = "";
                while ((s = br.readLine()) != null) {
                    String[] arr = s.split(" ");
                    add_(arr[0],arr[1],arr[2],Integer.parseInt(arr[3]));
                }
                br.close();
            }
        } catch (IOException e) {

        }
    }

}
class DuplicationException extends Exception{

}

