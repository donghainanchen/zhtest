package com.example.zhtest.model;

public class SendButton {
    private String listedit;
    private String listbutton;
    private String listonClick;

    public SendButton() {
    }

    public SendButton(String listedit, String listbutton, String listonClick) {
        this.listedit = listedit;
        this.listbutton = listbutton;
        this.listonClick = listonClick;
    }

    public String getlistedit() {
        return listedit;
    }

    public String getlistbutton() {
        return listbutton;
    }

    public String getlistonClick() {
        return listonClick;
    }

    public void setlistedit(String listedit) {
        this.listedit = listedit;
    }

    public void setlistbutton(String listbutton) {
        this.listbutton = listbutton;
    }

    public void setlistonClick(String listonClick) {
        this.listonClick = listonClick;
    }
}
