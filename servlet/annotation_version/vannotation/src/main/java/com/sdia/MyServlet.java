package com.sdia;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "msa" , urlPatterns = {"/hello2" , "/hi"})
public class MyServlet extends HttpServlet {
    private String message ;

    public void init(){
        message = "hello world ";
    }

    public void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<h1>"+message+" with annotation version </h1>");
    }
}
