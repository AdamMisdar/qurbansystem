package com.qurban.servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.qurban.dao.*;
import com.qurban.javabean.*;



@WebServlet("/")
public class AnimalOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private AnimalOrderDAO animalOrderDAO;

    public AnimalOrderServlet() {
        super();
        this.animalOrderDAO = new AnimalOrderDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath(); // Returns the part of this request's URL that calls the servlet.
		
		switch(action) {
		
		case "/new":
			showNewForm(request, response);
			break;
			
		case "/insert":
			try {
				insertAnimalOrder(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/delete":
			try {
				deleteAnimalOrder(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/update":
			try {
				updateAnimalOrder(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			try {
				listAnimalOrder(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		}
	}
	
	// Create New Animal Order - new form
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("animal-order-form.jsp");
		dispatcher.forward(request, response);
	}
	
	// Insert Animal Order
	private void insertAnimalOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
			
		String animalOrderType = request.getParameter("animalOrderType");
		double animalOrderPrice = Double.parseDouble(request.getParameter("animalOrderPrice"));
		String dependentName = request.getParameter("dependentName");
		String supplierName = request.getParameter("supplierName");
		
			
		AnimalOrder newAnimalOrder = new AnimalOrder(animalOrderType, animalOrderPrice, dependentName, supplierName);
		animalOrderDAO.insertAnimalOrder(newAnimalOrder);
		response.sendRedirect("list");
	
	}
	
	// Delete Animal Order
	private void deleteAnimalOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int animalOrderID = Integer.parseInt(request.getParameter("animalOrderID"));
		animalOrderDAO.deleteAnimalOrder(animalOrderID);
		response.sendRedirect("list");
		
	}
	
	// Show edit form..
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int animalOrderID = Integer.parseInt(request.getParameter("animalOrderID"));
		AnimalOrder existingAnimalOrder = animalOrderDAO.selectAnimalOrder(animalOrderID);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("animal-order-form.jsp");
		request.setAttribute("animalOrder", existingAnimalOrder);
		dispatcher.forward(request, response);
		
	}
	
	// Update Animal Order
	private void updateAnimalOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
			
		int animalOrderID = Integer.parseInt(request.getParameter("animalOrderID"));
		String animalOrderType = request.getParameter("animalOrderType");
		double animalOrderPrice = Double.parseDouble(request.getParameter("animalOrderPrice"));
		String dependentName = request.getParameter("dependentName");
		String supplierName = request.getParameter("supplierName");
			
		AnimalOrder animalOrder = new AnimalOrder(animalOrderID, animalOrderType, animalOrderPrice, dependentName, supplierName);
		animalOrderDAO.updateAnimalOrder(animalOrder);
		response.sendRedirect("list");
	}
	
	// List of animal orders
		private void listAnimalOrder(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			
			List<AnimalOrder> listAnimalOrder = animalOrderDAO.selectAllAnimalOrders();
			request.setAttribute("listAnimalOrder", listAnimalOrder);
			RequestDispatcher dispatcher = request.getRequestDispatcher("animal-order-list.jsp");
			dispatcher.forward(request, response);
			
		}

}
