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
                    "SELECT ID_aliments ,nom from aliments"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                aliments.add(new Aliments(
                        rs.getString("nom"),
                        rs.getInt("ID_aliments")
                ));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("alimentrepas");
        }
        return aliments;
    }

    public void insert(String nom) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO aliments(nom) values(?);"
            );
            System.out.println(nom);
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Aliments selectOne(String nom) {
        ArrayList<Aliments> aliments = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_aliments ,nom from aliments WHERE nom = ?"
            );
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                aliments.add(new Aliments(
                        rs.getString("nom"),
                        rs.getInt("ID_aliments")
                ));
            }
            System.out.println(aliments.toString());
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (aliments.isEmpty()) {
            return null;
        } else {
            return aliments.get(0);
        }


    }
}
