package fr.afpa.suividesrepas.bo;

import fr.afpa.suividesrepas.dal.AlimentsSQL;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

@Data @AllArgsConstructor
public class Repas {
    private Date date;
    private int ID_repas;
    private Time time;

    public Repas(Date date, Time time, int ID_repas) {
        this.date = date;
        this.ID_repas = ID_repas;
        this.time = time;
    }



   /* public Repas(Date date, Time time) {
        this.date = date;
        this.time = time;
    }*/
}
