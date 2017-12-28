package com.aveteam.lorienzo9.istudy.Constructors;

/**
 * Created by lorienzo9 on 28/12/17.
 */

public class Homework {
    String title, content, assegnato, classe, scadenza;
    int tag;
    public Homework(){}
    public Homework(String title, String content, int tag, String classe, String assegnato, String scadenza){
        this.assegnato = assegnato;
        this.classe = classe;
        this.content = content;
        this.scadenza = scadenza;
        this.tag = tag;
        this.title = title;
    }

    public String getAssegnato() {
        return assegnato;
    }

    public String getClasse() {
        return classe;
    }

    public String getContent() {
        return content;
    }

    public String getScadenza() {
        return scadenza;
    }

    public int getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }

    public void setAssegnato(String assegnato) {
        this.assegnato = assegnato;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
