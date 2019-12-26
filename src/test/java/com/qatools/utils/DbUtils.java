package com.qatools.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;

	/**
	 * Method will establish connection with Database
	 */
	public static void createConnection() {
		
		try {
			conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
					ConfigsReader.getProperty("dbUsername"),ConfigsReader.getProperty("dbPassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method will return data from database in List<Map>
	 */
	
	public static List<Map<String, String>> getResultSetData (String sqlQuery){
		List<Map<String, String>> rsList=new ArrayList<>();
		
		try {
			statement=conn.createStatement();
			rs=statement.executeQuery(sqlQuery);
		ResultSetMetaData rsMetaData=rs.getMetaData();
			while (rs.next()) {
				Map<String, String> rsMap=new LinkedHashMap<>();
				for (int i=1; i <=rsMetaData.getColumnCount(); i++) {
				rsMap.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());
				}
				rsList.add(rsMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsList;
	}
	/**
	 * Method will close all db connections/resources
	 */
	public static void closeConnection() {
		try {
		if (rs !=null) {
			rs.close();
			}
		if (statement !=null) {
			statement.close();
		}
		if (conn !=null) {
			conn.close();
		}
		}
		catch (SQLException e) {
		e.printStackTrace();
			}
		
	}
}
