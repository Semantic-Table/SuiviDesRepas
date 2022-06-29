package fr.afpa.suividesrepas.dal;

import fr.afpa.suividesrepas.bo.Aliments;
import fr.afpa.suividesrepas.bo.Repas;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class AlimentsSQL {
    public ArrayList<Aliments> selectAll() {
        ArrayList<Aliments> aliments = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_aliments ,nom,ID_repas from aliments"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                aliments.add(new Aliments(
                        rs.getString("nom"),
                        rs.getInt("ID_aliments"),
                        rs.getInt("ID_repas")
                ));
            }
            System.out.println(aliments.toString());
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return aliments;
    }
    public void insert(String nom, int ID_repas) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO aliments(nom) values(?,?);" +
                            "insert into alimentsrepas(ID_aliments, ID_repas) VALUES (?,?);"
            );
            System.out.println(nom);
            System.out.println(ID_repas);
            pstmt.setString(1, nom);
            pstmt.setString(2, String.valueOf(ID_repas));
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
