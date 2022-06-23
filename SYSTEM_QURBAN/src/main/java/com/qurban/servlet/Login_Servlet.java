package com.qurban.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.qurban.connection.ConnectionPool;

import java.sql.*;



@WebServlet("/login")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login_Servlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String client_email = request.getParameter("email");
		String client_password = request.getParameter("password");
		
		//Create session
		HttpSession session = request.getSession();
		
		//Connection
		Connection connection = null;
		
		//request dispatcher
		RequestDispatcher dispatcher = null;
		
		try {
		/*	Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/QurbanDatabase?useSSL=false", "postgres", "system");
		*/	
			
			connection = ConnectionPool.getInstance().getConnection();
			
			//SQL Statement/Query 
			PreparedStatement pst = connection.prepareStatement("select * from client where client_email = ? and client_password = ?");
			
			// Set string - set for ? by order
			pst.setString(1, client_email);
			pst.setString(2, client_password);
			
			// Execute Query Method
			ResultSet result = pst.executeQuery();
			
			// if result has data
			if(result.next()) {
				//will redirect to homepage
				session.setAttribute("name", result.getString("client_name"));
				
				//request dispatcher - if login successful
				dispatcher = request.getRequestDispatcher("animal-order-list.jsp");
			}
			else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			
			dispatcher.forward(request, response);
			
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
