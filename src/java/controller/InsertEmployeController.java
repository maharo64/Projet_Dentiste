/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmployeDao;
import dao.SpecialDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modele.Employe;
import modele.Genre;
import modele.Special;
import modele.Specialite;

/**
 *
 * @author LEGION
 */
@WebServlet(name = "InsertEmployeController", urlPatterns = {"/InsertEmployeController"})
public class InsertEmployeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        RequestDispatcher dispat = null;
        try{
           page ="ma page.jsp";
           dispat = request.getRequestDispatcher(page);
           dispat.forward(request, response);
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         List<Integer> liste  = null; 
        Employe employe= null;
        int specialite1Id, specialite2Id, specialite3Id = 0;
        String nom, prenom = null;
        Date dateNaissance;
        int idEmploye, idGenre = 0;
        Genre genre = null;
        Special special = null;
        Employe employe1 = null;
        Specialite specialite = null;
        
        try{
            employe = new Employe();
            genre = new Genre();
            nom = request.getParameter("nom");
            prenom = request.getParameter("prenom");
            idGenre = Integer.parseInt(request.getParameter("idGenre"));
            dateNaissance = java.sql.Date.valueOf(request.getParameter("dateNaissance"));
            employe.setNom(nom);
            employe.setPrenom(prenom);
            genre.setId(idGenre);
            employe.setGenre(genre);
            EmployeDao.addEmploye(employe);
            
            idEmploye = EmployeDao.getLastIdEmploye(); 
            liste = new ArrayList<>();
            specialite1Id = Integer.parseInt(request.getParameter("specialite1"));
            specialite2Id = Integer.parseInt(request.getParameter("specialite2"));
            specialite3Id = Integer.parseInt(request.getParameter("specialite3"));
            liste.add(specialite1Id);
            liste.add(specialite2Id);
            liste.add(specialite3Id);
            
            for(int i = 0; i<liste.size(); i++){
                if(liste.get(i)!=null){
                    special = new Special();
                    specialite = new Specialite();
                    specialite.setId(liste.get(i));
                    employe1 = new Employe();
                    employe1.setId(idEmploye);
                    special.setEmploye(employe1);
                    special.setSpecialite(specialite);
                    SpecialDao.addSpecial(special);
                }  
         }
            }
         catch(Exception e){
             e.printStackTrace();
         }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
