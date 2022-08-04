package com.example.memotest;

public class Memo {
    private String title;
    private String content;
    private String date;

    public void Memo(String title, String content,String date){
        this.title=title;
        this.content=content;
        this.date = date;
    }

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
}
