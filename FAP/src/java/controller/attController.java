/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttendenceDBcontext;
import dal.LessionDBcontext;
import dal.StudentDBcontext;
import entity.Attendence;
import entity.Lession;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class attController extends HttpServlet {

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
        int lesId = Integer.parseInt(request.getParameter("id"));
        LessionDBcontext db = new LessionDBcontext();
        ArrayList<Attendence> atts = db.getAttsById(lesId);
        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/attendence/att.jsp").forward(request, response);

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
        int lesId = Integer.parseInt(request.getParameter("id"));
        StudentDBcontext stuDB = new StudentDBcontext();
        ArrayList<Student> students = stuDB.getStudentBylession(lesId);
        ArrayList<Attendence> atts = new ArrayList<>();
        AttendenceDBcontext attDB = new AttendenceDBcontext();
        boolean check = false;
        Lession lession = new Lession();
        lession.setId(lesId);
        
        boolean isAtt = attDB.isAttStatusByLession(lesId);
        if(isAtt == true){
        for (Student student : students) {
            String description = request.getParameter("description" + student.getId());
            boolean isPresent = request.getParameter("present" + student.getId()).equals("yes");
            Attendence attCheck = new Attendence();
            attCheck = attDB.getInfoStudentStatusByLession(lesId, student.getId());
            if (attCheck != null) {
                if ((attCheck.isPresent() != isPresent) && (attCheck.getDescription() != description)) {
                   attDB.uppdateByChange(lesId, student.getId(), description, isPresent);
                }
            }
        }
        }
        else {
             for (Student student : students) {
            Attendence att = new Attendence();
            att.setLession(lession);
            att.setStudent(student);
            att.setPresent(request.getParameter("present"+student.getId()).equals("yes"));
            att.setDescription(request.getParameter("description"+student.getId()));
            atts.add(att);
        }
   
            attDB.batchupdateByLession(lesId, atts);
            attDB.uppdateLession(lesId);
        }
        response.sendRedirect("att?id=" + lesId);
    
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
