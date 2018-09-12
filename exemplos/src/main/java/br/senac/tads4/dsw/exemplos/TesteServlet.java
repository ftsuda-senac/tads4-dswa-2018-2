package br.senac.tads4.dsw.exemplos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "TesteServlet", urlPatterns = {"/teste"})
public class TesteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String valor = request.getParameter("valor");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TesteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TesteServlet at " + request.getContextPath() + "</h1>");
            if (valor != null && valor.trim().length() > 0) {
                out.println("<h2>Valor enviado: " + valor + "</h2>");
            } else {
                out.println("<h2>Nenhum valor enviado</h2>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
