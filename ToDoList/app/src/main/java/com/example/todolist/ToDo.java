package com.example.todolist;

public class ToDo {
    private String content;
    private String date;

    public void ToDo(String content,String date){
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

}
