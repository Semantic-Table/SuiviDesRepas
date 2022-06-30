package fr.afpa.suividesrepas.servlets;

import fr.afpa.suividesrepas.bo.Aliments;
import fr.afpa.suividesrepas.bo.Repas;
import fr.afpa.suividesrepas.dal.AlimentsRepasSQL;
import fr.afpa.suividesrepas.dal.AlimentsSQL;
import fr.afpa.suividesrepas.dal.RepasSQL;
import fr.afpa.suividesrepas.exceptions.AfterNowException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@WebServlet(name = "AddRepas", value = "/AddRepas")
public class AddRepas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/ajouter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepasSQL RepasSQL = new RepasSQL();
        AlimentsSQL AlimentsSQL = new AlimentsSQL();
        AlimentsRepasSQL AlimentsRepasSQL = new AlimentsRepasSQL();
        String[] tabAliments = request.getParameter("aliment").split(",");
        tabAliments = retraitEspace(tabAliments);
        System.out.println();
        try {
            if (!tabEmpty(tabAliments)) {
                RepasSQL.insert(request.getParameter("date"), request.getParameter("time"));
                Repas repas = RepasSQL.selectLast();
                for (String c : tabAliments
                ) {
                    if (AlimentsSQL.selectOne(c) == null) {
                        AlimentsSQL.insert(c);
                    }
                    AlimentsRepasSQL.insert(repas.getID_repas(), AlimentsSQL.selectOne(c).getID_aliments());
                }
            }
        } catch (AfterNowException e) {
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher("WEB-INF/ajouter.jsp").forward(request, response);

    }

    private boolean tabEmpty(String[] tab) {
        for (String c : tab) {
            if (c.equals("")) {
                return true;
            }
        }
        return false;
    }

    private String[] retraitEspace(String[] tab) {
        String[] temp = new String[tab.length];
        for (int i = 0; i < tab.length; i++) {
            temp[i] = tab[i].replace(' ', '\0');
        }
        return temp;
    }
}
