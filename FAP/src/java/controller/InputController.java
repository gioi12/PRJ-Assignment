/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LectureDBcontext;
import dal.LessionDBcontext;
import dal.TimeSlotDBcontext;
import entity.Lecture;
import entity.Lession;
import entity.Slot;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import util.DateTimeHelper;

/**
 *
 * @author ACER
 */
public class InputController extends HttpServlet {

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
         String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        Date today = new Date();
        java.sql.Date from = null; 
        java.sql.Date to = null;
        
        if(raw_from ==null)
        {
            from = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.getWeekStart(today));
        }
        else
        {
            from = java.sql.Date.valueOf(raw_from);
        }
        
        if(raw_to == null)
        {
            to = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.
                    addDaysToDate(DateTimeHelper.getWeekStart(today),6));
        }
        else
        {
            to = java.sql.Date.valueOf(raw_to);
        }
        
        ArrayList<java.sql.Date> dates = DateTimeHelper.getDatesBetween(from, to);
           request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("dates", dates);
        request.getRequestDispatcher("../view/attendence/input.jsp").forward(request, response);

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
        TimeSlotDBcontext timeDB = new TimeSlotDBcontext();
        ArrayList<Slot> slots = new ArrayList<>();
        slots = timeDB.getSlot();     
        request.setAttribute("slots", slots);
        int lid = Integer.parseInt(request.getParameter("lid"));
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        Date today = new Date();
        java.sql.Date from = null; 
        java.sql.Date to = null;
        
        if(raw_from ==null)
        {
            from = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.getWeekStart(today));
        }
        else
        {
            from = java.sql.Date.valueOf(raw_from);
        }
        
        if(raw_to == null)
        {
            to = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.
                    addDaysToDate(DateTimeHelper.getWeekStart(today),6));
        }
        else
        {
            to = java.sql.Date.valueOf(raw_to);
        }
        
        ArrayList<java.sql.Date> dates = DateTimeHelper.getDatesBetween(from, to);
        
      
        
        LessionDBcontext lessDB = new LessionDBcontext();
        ArrayList<Lession> lessions = lessDB.getInfoTimeTable(lid, from, to);
        
        
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("dates", dates);
        request.setAttribute("lessions", lessions);
                request.getRequestDispatcher("../view/attendence/input.jsp").forward(request, response);

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
