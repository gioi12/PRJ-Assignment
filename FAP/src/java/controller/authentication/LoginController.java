/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dal.AccountDBcontext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author ACER
 */
public class LoginController extends HttpServlet {

  
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
        request.getRequestDispatcher("/view/account/login.jsp").forward(request, response);
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
   String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        AccountDBcontext login = new AccountDBcontext();
        Account ac = login.login(user, pass);
        if (ac != null) {
//            String remember = request.getParameter("remember");
//            if (remember != null) {
//                Cookie cookie_user = new Cookie("user", user);
//                Cookie cookie_pass = new Cookie("pass", pass);
//                cookie_user.setMaxAge(3600 * 24 * 7);
//                cookie_pass.setMaxAge(3600 * 24 * 7);
//                response.addCookie(cookie_pass);
//                response.addCookie(cookie_user);
//            }
           HttpSession session = request.getSession();
            session.setAttribute("account", ac);
            response.sendRedirect(" port");
            
        } else {
            response.getWriter().println("login failed");

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
