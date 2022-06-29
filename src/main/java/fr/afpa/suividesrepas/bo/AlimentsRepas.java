package fr.afpa.suividesrepas.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class AlimentsRepas {
    private Aliments aliments;
    private Repas repas;
}
