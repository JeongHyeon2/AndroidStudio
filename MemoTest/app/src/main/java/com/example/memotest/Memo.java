package com.example.memotest;

public class Memo {
    private String title;
    private String content;

    public void Memo(String title, String content){
        this.title=title;
        this.content=content;
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
