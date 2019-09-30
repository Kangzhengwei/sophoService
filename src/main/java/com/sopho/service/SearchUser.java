package com.sopho.service;

import com.sopho.dao.UserDaoImpl;
import com.sopho.entity.BaseResult;
import com.sopho.entity.User;
import com.sopho.utils.GsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SearchUser")
public class SearchUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String phoneNumber = request.getParameter("phoneNumber");
        PrintWriter out = response.getWriter();
        UserDaoImpl userDao = new UserDaoImpl();
        BaseResult result;
        List<User> list = userDao.queryUserByPhoneNumber(phoneNumber);
        if (list != null && list.size() > 0) {
            result = new BaseResult(0, "获取成功", list);
        } else {
            result = new BaseResult(1, "没有该用户", null);
        }
        out.print(GsonUtils.getInstance().toJson(result));
        out.flush();
        out.close();
    }
}
