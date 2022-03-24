import java.util.ArrayList;

public class MemberManagement {
    private ArrayList<Member> memberArr;
    public MemberManagement(){
        memberArr = new ArrayList<>();
    }
    public void add(String id, String pwd, String name, int age){
        memberArr.add(new Member(id,pwd,name,age));
    }
    public void delete(String id){
        for(int i=0;i<memberArr.size();i++){
            if(memberArr.get(i).getId().equals(id)){
                memberArr.remove(i);
            }
        }
    }

}
