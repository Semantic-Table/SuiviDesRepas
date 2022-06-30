package fr.afpa.suividesrepas.dal;

import fr.afpa.suividesrepas.bo.Aliments;
import fr.afpa.suividesrepas.bo.Repas;
import fr.afpa.suividesrepas.exceptions.AfterNowException;

import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class RepasSQL {
    public ArrayList<Repas> selectAll() {
        ArrayList<Repas> repas = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT date, time, ID_repas from repas"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                repas.add(new Repas(
                        rs.getDate("date"),
                        rs.getTime("time"),
                        rs.getInt("ID_repas")
                ));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("alimentrepas");
        }
        return repas;
    }

    public void insert(String date, String time) throws AfterNowException {
        String[] tabDate = date.split("-");
        if (LocalDate.of(Integer.parseInt(tabDate[0]), Integer.parseInt(tabDate[1]), Integer.parseInt(tabDate[2])).isBefore(LocalDate.now())) {


            try {
                Connection connection = ConnectionProvider.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO repas(date,time) values (?,?);"
                );
                pstmt.setString(1, date);
                System.out.println(date);
                System.out.println(time);
                pstmt.setString(2, time);
                pstmt.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new AfterNowException("La date selectionner n'est pas valable");
        }
    }

    public Repas selectLast() {
        ArrayList<Repas> repas = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT repas.date, repas.time, ID_repas from repas order by ID_repas desc limit 1"
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
        System.out.println(repas.toString());
        return repas.get(0);
    }
}
