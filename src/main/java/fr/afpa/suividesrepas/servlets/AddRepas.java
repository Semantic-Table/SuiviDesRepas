package fr.afpa.suividesrepas.servlets;

import fr.afpa.suividesrepas.dal.AlimentsSQL;
import fr.afpa.suividesrepas.dal.RepasSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Time;
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
        String[] tabAliments = request.getParameter("aliment").split(",");



        RepasSQL.insert(request.getParameter("date"), request.getParameter("time"));
        for (String c: tabAliments
        ) {
            c.replace(' ','\0');
            AlimentsSQL.insert(c, RepasSQL.selectOne(request.getParameter("date"), request.getParameter("time")).getID_repas());
        }
        request.getRequestDispatcher("WEB-INF/afficher.jsp").forward(request, response);

    }
}
