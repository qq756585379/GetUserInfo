package com.ming.dao;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.ming.model.User;



public class UserDao {
	
	SqlManager manager;
	
	public UserDao() {
		try {
			manager=SqlManager.createInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<User> GetMobile(int count) throws SQLException{
		ArrayList<User> list=new ArrayList<>();
		String sql="SELECT a.id, a.mobile,a.uuid ,b.cnid ,b.created_at,b.\"name\" FROM users a ,profiles b WHERE a.\"id\"=b.borrower_id LIMIT ?";
		Object [] params=new Object[]{count};
		manager.connectDB();
		ResultSet rs=manager.executeQuery(sql, params);
		while (rs.next()){
			User user=new User();
			user.setBorrower_id(rs.getInt("id")+"");
			user.setMobile(rs.getString("mobile"));
			user.setUuid(rs.getString("uuid"));
			user.setName(rs.getString("name"));
			user.setCnid(rs.getString("cnid"));
			user.setCreated_at(rs.getTimestamp("created_at"));
			list.add(user);
		}
		return list;
	}
	
	@Test
	public void testDB(){
		UserDao userDao=new UserDao();
		try {
			ArrayList<User> list=userDao.GetMobile(100);
			System.out.println(list.size());
			for(User user:list){
				System.out.println(user.getMobile());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
