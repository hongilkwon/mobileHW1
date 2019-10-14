package com.example.moblieprogramminghw1;

public class ToDo {

    static int num;
    private String title;
    private String contents;

    public ToDo(int num, String title, String contents) {
        this.num = num;
        this.title = title;
        this.contents = contents;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
