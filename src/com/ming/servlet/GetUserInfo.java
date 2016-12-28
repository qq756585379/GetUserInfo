package com.ming.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.ming.dao.ResponseJsonUtils;
import com.ming.dao.UserDao;
import com.ming.model.User;

public class GetUserInfo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		int count=Integer.parseInt(request.getParameter("count"));
		System.out.println("----------------count="+count);
		UserDao userDao=new UserDao();
		JSONObject jsonObject=new JSONObject();
		try {
			ArrayList<HashMap<String, Object>> maps=new  ArrayList<HashMap<String, Object>>();
			ArrayList<User> users=userDao.GetMobile(count);
			for(User user:users){
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("mobile", user.getMobile());
				map.put("uuid", user.getUuid());
				map.put("borrower_id", user.getBorrower_id());
				map.put("cnid", user.getCnid());
				map.put("created_at", user.getCreated_at());
				map.put("name",user.getName());
				maps.add(map);
			}
			
			ResponseJsonUtils.json(response, maps);
			
//			try {
//				jsonObject.put("info", maps);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		PrintWriter out = response.getWriter();
//		out.println(jsonObject);
//		out.flush();
//		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

}
