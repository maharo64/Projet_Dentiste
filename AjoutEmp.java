/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlety;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import obj.*;
import java.sql.*;
import connex.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 *
 * @author PC
 */
public class AjoutEmp extends HttpServlet {

    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();
        String nom = req.getParameter("Nom");
        String prenom = req.getParameter("Prenom");
        int idgenre = Integer.parseInt(req.getParameter("genre"));
        String datenaiss = req.getParameter("Date_de_naissance");
        int nivetu = Integer.parseInt(req.getParameter("diplome"));
        int salaire = Integer.parseInt(req.getParameter("salaire"));
        String[] spe = req.getParameterValues("specialite");
        try {
            DateFormat dns = new SimpleDateFormat("yyyy-mm-dd");
            Date d = dns.parse(datenaiss);
            Emp e = new Emp(nom,prenom,idgenre,d,nivetu,salaire);
            e.ajoutEmp();
            int y = e.getId();
            Specialisation s = new Specialisation(y,spe);
            s.ajoutSpecialisation();
        } catch (Exception e) {
            out.print(e.getMessage());
        }
    }

}