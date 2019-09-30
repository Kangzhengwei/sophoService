package com.sopho.service;

import com.sopho.dao.UserDaoImpl;
import com.sopho.entity.BaseResult;
import com.sopho.utils.GsonUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangePassWord")
public class ChangePassWord extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String passWord = request.getParameter("passWord");
        PrintWriter out = response.getWriter();
        UserDaoImpl userDao = new UserDaoImpl();
        BaseResult result;
        if (userDao.updatePassWord(id, passWord)) {
            result = new BaseResult(0, "操作成功", null);
        } else {
            result = new BaseResult(1, "操作失败", null);
        }
        out.print(GsonUtils.getInstance().toJson(result));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
