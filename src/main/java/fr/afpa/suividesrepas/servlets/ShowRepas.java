package fr.afpa.suividesrepas.servlets;

import fr.afpa.suividesrepas.bo.Aliments;
import fr.afpa.suividesrepas.bo.Repas;
import fr.afpa.suividesrepas.dal.AlimentsSQL;
import fr.afpa.suividesrepas.dal.RepasSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShowRepas", value = "/ShowRepas")
public class ShowRepas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepasSQL RepasSQL = new RepasSQL();
        AlimentsSQL AlimentsSQL = new AlimentsSQL();
        ArrayList<Repas> repas = RepasSQL.selectAll();
        ArrayList<Aliments> aliments = AlimentsSQL.selectAll();
        request.setAttribute("repas", repas);
        request.setAttribute("aliments",aliments);
        request.getRequestDispatcher("WEB-INF/afficher.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
