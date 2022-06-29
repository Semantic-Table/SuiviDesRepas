package fr.afpa.suividesrepas.dal;

import fr.afpa.suividesrepas.bo.Aliments;
import fr.afpa.suividesrepas.bo.Repas;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;



public class RepasSQL {
    public ArrayList<Repas> selectAll() {
        ArrayList<Repas> repas = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT repas.date, repas.time, ID_repas from repas"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                repas.add(new Repas(
                        rs.getDate("date"),
                        rs.getTime("time"),
                        rs.getInt("ID_repas")
                ));
            }
            System.out.println(repas.toString());
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return repas;
    }

    public void insert(String date, String time) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO repas(date,time) values (?,?);"
            );
            pstmt.setString(1,date);
            System.out.println(date);
            System.out.println(time);
            pstmt.setString(2, time);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Repas selectOne(String date, String time) {
        ArrayList<Repas> repas = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT repas.date, repas.time, ID_repas from repas where time = ? and date = ?"
            );
            pstmt.setString(2,date);
            pstmt.setString(1,time);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                repas.add(new Repas(
                        rs.getDate("date"),
                        rs.getTime("time"),
                        rs.getInt("ID_repas")
                ));
            }
            System.out.println(repas.toString());
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(repas.toString());
        return repas.get(0);
    }
}
