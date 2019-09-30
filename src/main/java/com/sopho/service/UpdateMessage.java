package com.sopho.service;

import com.sopho.dao.UserDaoImpl;
import com.sopho.entity.BaseResult;
import com.sopho.entity.User;
import com.sopho.utils.GsonUtils;
import com.sopho.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UpdateMessage")
public class UpdateMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        request.setCharacterEncoding("utf-8");
        String body = IOUtils.BufferedReaderToString(request.getInputStream());
        if (StringUtils.isNotBlank(body)) {
            System.out.println("business receive something with body :" + body);
        }
        PrintWriter out = response.getWriter();
        UserDaoImpl userDao = new UserDaoImpl();
        User user = GsonUtils.getInstance().fromJson(body, User.class);
        BaseResult result;
        List<User> list = userDao.queryUserById(user.getId());
        if (list != null && list.size() > 0) {
            if (userDao.updateUser(user)) {
                userDao.updateXmppUser(user.getPhoneNumber(), user.getNickName());
                result = new BaseResult(0, "操作成功", null);
            } else {
                result = new BaseResult(2, "操作失败", null);
            }
        } else {
            result = new BaseResult(1, "该用户不存在", null);
        }
        out.print(GsonUtils.getInstance().toJson(result));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
