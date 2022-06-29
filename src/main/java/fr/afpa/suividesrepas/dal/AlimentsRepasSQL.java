package fr.afpa.suividesrepas.dal;

import fr.afpa.suividesrepas.bo.Aliments;
import fr.afpa.suividesrepas.bo.AlimentsRepas;
import fr.afpa.suividesrepas.bo.Repas;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlimentsRepasSQL {
    public ArrayList<Repas> selectFromAliments(String aliment){
        ArrayList<Repas> repas = new ArrayList<>();

        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
              "select r.date,r.time,r.ID_repas from alimentsrepas left join aliments a on alimentsrepas.ID_aliments = a.ID_aliments right join repas r on r.ID_repas = alimentsrepas.ID_repas where a.nom = ?"
            );
            pstmt.setString(1,aliment);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                repas.add(new Repas(
                        rs.getDate("date"),
                        rs.getTime("time"),
                        rs.getInt("ID_repas")
                        ));

            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return repas;
    }

    public void insert(int ID_repas, int ID_aliments){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(

                    "INSERT INTO alimentsrepas(ID_repas,ID_aliments) VALUES (?,?)"
            );
            pstmt.setInt(1,ID_repas);
            pstmt.setInt(2,ID_aliments);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {

        }
    }

    public ArrayList<AlimentsRepas> selectAll() {
        ArrayList<AlimentsRepas> alimentsrepas = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_aliments , ID_repas from alimentsrepas"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                alimentsrepas.add(new AlimentsRepas(
                        rs.getInt("ID_aliments"),
                        rs.getInt("ID_repas")
                ));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("alimentrepas");
        }
        return alimentsrepas;
    }
}
