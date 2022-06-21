package com.qurban.dao;

import java.sql.*;
import java.util.*;

import com.qurban.connection.ConnectionPool;
import com.qurban.javabean.*;

//This DAO class provides CRUD database operations for the table Animal Order in the database
public class AnimalOrderDAO {
	
	//Defining final SQL Statements
	private static final String INSERT_ANIMAL_ORDER = // Insert new animal order
	"INSERT INTO animalorder(animalOrderType, animalOrderPrice, dependentName, supplierName ) " +
	"VALUES (?, ?, ?, ?);";
	
	private static final String SELECT_ANIMAL_ORDER_BY_ID = // Select 1 animal order by id
	"SELECT animalOrderID, animalOrderType, animalOrderPrice, dependentName, supplierName " +
	"FROM animalorder " +
	"WHERE animalOrderID = ?;";
	
	private static final String SELECT_ALL_ANIMAL_ORDERS = // Select all animal order
	"SELECT * " +
	"FROM animalorder " +
	"ORDER BY animalOrderID;";
	
	private static final String DELETE_ANIMAL_ORDER_BY_ID = // Delete 1 animal order by id
	"DELETE FROM animalorder " +
	"WHERE animalOrderID = ?;";
	
	private static final String UPDATE_ANIMAL_ORDER_BY_ID = // Update 1 animal order by id
	"UPDATE animalorder " +
	"SET animalOrderType = ?, animalOrderPrice = ?, dependentName = ?, supplierName = ?" +
	"WHERE animalOrderID = ?;";
	
	// ------------------------------------------------------------------------------
	
	// Create or insert animal order
	public void insertAnimalOrder(AnimalOrder animalOrder) {
		
		try(
			//Connect to database
			Connection connection = ConnectionPool.getInstance().getConnection();
			
			// Make PreparedStatement
			PreparedStatement sqlCreate = connection.prepareStatement(INSERT_ANIMAL_ORDER); ) // close try()
			
			{
			// Set ? values in sql statement
			sqlCreate.setString(1, animalOrder.getAnimalOrderType());
			sqlCreate.setDouble(2, animalOrder.getAnimalOrderPrice());
			sqlCreate.setString(3, animalOrder.getDependentName());
			if(animalOrder.getSupplierName().equals("")) {
				sqlCreate.setString(4, null); 
			} else {
				sqlCreate.setString(4, animalOrder.getSupplierName());
			}
			
			// Execute SQL
			sqlCreate.executeUpdate();
			
			
		} // close try{}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	} // end of Create animal order ------------------------------------------------------------------------------
	
	// Update animal order - returns true when rows are updated
	public boolean updateAnimalOrder(AnimalOrder animalOrder) throws SQLException {
		boolean rowUpdated = false;
		try( 
			// Connect to database
			Connection connection = ConnectionPool.getInstance().getConnection();
				
			// Make PreparedStatement
			PreparedStatement sqlUpdate = connection.prepareStatement(UPDATE_ANIMAL_ORDER_BY_ID); ) // close try()
				
			{
			// Set ? values in sql statement
			sqlUpdate.setString(1, animalOrder.getAnimalOrderType());
			sqlUpdate.setDouble(2, animalOrder.getAnimalOrderPrice());
			sqlUpdate.setString(3, animalOrder.getDependentName());
			if(animalOrder.getSupplierName().equals("")) {
				sqlUpdate.setString(4, null); 
			} else {
				sqlUpdate.setString(4, animalOrder.getSupplierName());
			}
			sqlUpdate.setInt(5, animalOrder.getAnimalOrderID());
				
			rowUpdated = sqlUpdate.executeUpdate() > 0;
				
		} // close try{}
			
		catch(Exception e) {
			e.printStackTrace();
		} 	
			return rowUpdated;
			
	} // end of Update animal order -------------------------------------------------------------------------
	
	// Select animal order by id - returns 1 animal order
		public AnimalOrder selectAnimalOrder(int id) throws SQLException {
			AnimalOrder animalOrder = null;

			try(
				// Step 1: Establishing a Connection..
				Connection connection = ConnectionPool.getInstance().getConnection();
					
				// Step 2 : Make PreparedStatement
				PreparedStatement sqlSelect = connection.prepareStatement( SELECT_ANIMAL_ORDER_BY_ID); ) // close try()
				{
				// Set ? values in sql statement
				sqlSelect.setInt(1, id);
				
				// Step 3: Execute the query or update query
				ResultSet result = sqlSelect.executeQuery();
				
				// Step 4: Process the ResultSet object
				while(result.next()) {
					String animalOrderType = result.getString("animalOrderType");
					double animalOrderPrice = result.getDouble("animalOrderPrice");
					String dependentName = result.getString("dependentName");
					String supplierName = result.getString("supplierName");
					animalOrder = new AnimalOrder(id, animalOrderType, animalOrderPrice, dependentName, supplierName);
				}
			} // close try{}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
			return animalOrder;
			
		} // end of Select 1 animal order --------------------------------------------------------------------------
		
		// Select all animal order - returns a list of all animal orders
		public List<AnimalOrder> selectAllAnimalOrders() throws SQLException {
			List<AnimalOrder> animalOrderList = new ArrayList<>();
			
			try(
				// Step 1: Establishing a Connection
				Connection connection = ConnectionPool.getInstance().getConnection();
					
				// Step 2 : Make PreparedStatement
				PreparedStatement sqlSelectAll = connection.prepareStatement(SELECT_ALL_ANIMAL_ORDERS); ) // close try()
				{
				System.out.println(sqlSelectAll);
				
				// Step 3: Execute the query or update query
				ResultSet result = sqlSelectAll.executeQuery();
				
				// Step 4: Process the ResultSet object
				while(result.next()) {
					int animalOrderID = result.getInt("animalOrderID");
					String animalOrderType = result.getString("animalOrderType");
					double animalOrderPrice = result.getDouble("animalOrderPrice");
					String dependentName = result.getString("dependentName");
					String supplierName = result.getString("supplierName");
					
					animalOrderList.add(new AnimalOrder(animalOrderID, animalOrderType, animalOrderPrice, dependentName, supplierName));
				} 
			} // close try{}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			return animalOrderList;
			
		} // end of Select all User ------------------------------------------------------------------------------
		
		// Delete animal order
		public boolean deleteAnimalOrder(int id) throws SQLException {
			boolean rowDeleted = false;
			
			try(
				// Step 1: Establishing a Connection
				Connection connection = ConnectionPool.getInstance().getConnection();
						
				// Step 2 : Make PreparedStatement
				PreparedStatement sqlDelete = connection.prepareStatement(DELETE_ANIMAL_ORDER_BY_ID); ) // close try()
				{
				sqlDelete.setInt(1, id);
				
				rowDeleted = sqlDelete.executeUpdate() > 0;
				
			} // close try{}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
			return rowDeleted;
			
		} // end of Delete animal order ------------------------------------------------------------------------------
		
	
}
