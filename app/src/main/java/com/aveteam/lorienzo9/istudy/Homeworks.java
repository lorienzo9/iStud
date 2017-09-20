package com.aveteam.lorienzo9.istudy;

/**
 * Created by lorienzo9 on 20/09/17.
 */

public class Homeworks {
    String titolo, descrizione;
    int TAG;

    public Homeworks(String titolo, String descrizione, int TAG){
        this.descrizione = descrizione;
        this.titolo = titolo;
        this.TAG = TAG;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getTAG() {
        return TAG;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
