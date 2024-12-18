package com.example.library_management_system.modles;

import java.util.Date;

public class MagazineModel extends ResourcesModel{
    private String editor;
    private String issn;
    private int volume;

    public MagazineModel(boolean availalbeState, String title,
                         String publisher, int totalCopies, int copiesLeft,
                         String editor, String issn, int volume){
        this(0, availalbeState, title, publisher, totalCopies, copiesLeft, editor,
                issn, volume, new Date(), new Date());
    }

    public MagazineModel(int id, boolean availalbeState, String title,
                         String publisher, int totalCopies, int copiesLeft,
                         String editor, String issn, int volume, Date createdAt, Date updatedAt) {
        super(id, availalbeState, title, publisher, totalCopies, copiesLeft, createdAt, updatedAt);
        this.editor = editor;
        this.issn = issn;
        this.volume = volume;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString(){
        return super.toString() + ", editor = " + editor
                + ", issn = " + issn
                + ", volume = " +volume + " }\n";
    }
}
