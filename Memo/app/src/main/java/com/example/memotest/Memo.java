package com.example.memotest;

public class Memo implements Comparable<Memo>{
    private String title;
    private String content;
    private String date;
    private int pos;

    public void Memo(String title, String content,String date,int pos){
        this.title=title;
        this.content=content;
        this.date = date;
        this.pos = pos;
    }

    public void setPos(int pos){this.pos=pos;}
    public int getPos(){return pos;}
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Memo memo) {
        if(pos> memo.getPos()) return 1;
        else return -1;
    }
}
