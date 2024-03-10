/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LessionDBcontext;
import dal.TimeSlotDBcontext;
import entity.Lession;
import entity.Slot;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import util.DateTimeHelper;

/**
 *
 * @author ACER
 */
public class InfoController extends HttpServlet {

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
        int lid = Integer.parseInt(request.getParameter("id"));
        
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        Date today = new Date();
        java.sql.Date from = null; 
        java.sql.Date to = null;
        String dayFrom = null;
        String dayTo = null;
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
        // find day
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(raw_from == null){
            dayFrom = dateFormat.format(DateTimeHelper.getWeekStart(today));
        }
        else{
            dayFrom=raw_from;
        }
        if(raw_to == null ){
            dayTo= dateFormat.format(DateTimeHelper.
                    addDaysToDate(DateTimeHelper.getWeekStart(today),6));
        }
        else{
            dayTo = raw_to;
        }
        ArrayList<String> days = new ArrayList<>();
        days = DateTimeHelper.findDayOfWeekRange(dayFrom, dayTo);
        //-------
        TimeSlotDBcontext slotDB = new TimeSlotDBcontext();
        ArrayList<Slot> slots = slotDB.getSlot();
        
        LessionDBcontext lessDB = new LessionDBcontext();
        ArrayList<Lession> lessions = lessDB.getBy(lid, from, to);
        
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("lessions", lessions);
        request.setAttribute("days", days);
        request.getRequestDispatcher("../view/attendence/info.jsp").forward(request, response);
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
