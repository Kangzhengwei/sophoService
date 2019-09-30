package com.sopho.service;

import com.sopho.Xmpp.XmppHelper;
import com.sopho.dao.UserDaoImpl;
import com.sopho.entity.BaseResult;
import com.sopho.entity.User;
import com.sopho.utils.GsonUtils;
import com.sopho.utils.IOUtils;
import com.sopho.utils.IdUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Regist")
public class Regist extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        request.setCharacterEncoding("utf-8");
        String body = IOUtils.BufferedReaderToString(request.getInputStream());
        //String name = request.getParameter("name");//需要在获取io流之后调用
        if (StringUtils.isNotBlank(body)) {
            System.out.println("business receive something with body :" + body);
        }
        PrintWriter out = response.getWriter();
        UserDaoImpl userDao = new UserDaoImpl();
        User user = GsonUtils.getInstance().fromJson(body, User.class);
        List<User> list = userDao.queryUserByPhoneNumber(user.getPhoneNumber());
        BaseResult result;
        if (list != null && list.size() > 0) {
            result = new BaseResult(1, "该手机号已注册", null);
        } else {
            user.setId(IdUtils.getId());
            if (userDao.regist(user)) {
                if (XmppHelper.getInstance().create(user)) {
                    result = new BaseResult(0, "注册成功", null);
                } else {
                    result = new BaseResult(3, "openfire注册失败", null);
                }
            } else {
                result = new BaseResult(2, "注册失败", null);
            }
        }
        out.print(GsonUtils.getInstance().toJson(result));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
