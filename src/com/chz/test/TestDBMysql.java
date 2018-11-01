package com.chz.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

 



public class TestDBMysql {

	//测试数据库连接
	@Test
	public void test1() throws Exception {
		Connection con = TestDBMysql.getConnection();
		if (con != null) {
			System.out.println("连接数据库成功！");
			System.out.println(con);
		}else {
			System.out.println("连接数据库失败！");
		}
		
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/newclass?useUnicode=true&characterEncoding=utf-8";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return con;
	}
}
