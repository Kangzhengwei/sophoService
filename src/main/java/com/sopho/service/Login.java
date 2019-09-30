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

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String phoneNumber = request.getParameter("phoneNumber");
        String passWord = request.getParameter("passWord");
        PrintWriter out = response.getWriter();
        UserDaoImpl userDao = new UserDaoImpl();
        BaseResult result;
        List<User> list = userDao.login(phoneNumber, passWord);
        if (list != null && list.size() > 0) {
            result = new BaseResult(0, "登录成功", list.get(0));
        } else {
            result = new BaseResult(1, "用户名或密码有误", null);
        }
        out.print(GsonUtils.getInstance().toJson(result));
        out.flush();
        out.close();
    }
}
