package com.sopho.dao;

import com.sopho.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl {

    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = null;

    /**
     * 查询已注册用户
     *
     * @return
     */
    public List<User> queryUser() {
        List<User> list = new ArrayList<>();
        conn = this.getConnection();
        try {
            pstmt = conn.prepareStatement("select * from users");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User content = new User();
                content.setId(rs.getString("id"));
                content.setUserName(rs.getString("userName"));
                content.setPassWord(rs.getString("passWord"));
                content.setPhoneNumber(rs.getString("phoneNumber"));
                content.setNickName(rs.getString("nickName"));
                content.setCity(rs.getString("city"));
                content.setSex(rs.getString("sex"));
                content.setYears(rs.getString("years"));
                list.add(content);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            this.closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 查询用户
     *
     * @param phoneNumber
     * @return
     */
    public List<User> queryUserByPhoneNumber(String phoneNumber) {
        List<User> list = new ArrayList<>();
        conn = this.getConnection();
        User user = null;
        try {
            pstmt = conn.prepareStatement("select * from users where phoneNumber='" + phoneNumber + "'");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassWord(rs.getString("passWord"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setNickName(rs.getString("nickName"));
                user.setCity(rs.getString("city"));
                user.setSex(rs.getString("sex"));
                user.setYears(rs.getString("years"));
                list.add(user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            this.closeAll(rs, pstmt, conn);
        }
        return list;
    }

    public List<User> queryOpenfireDBByUserName(String userName) {
        List<User> list = new ArrayList<>();
        conn = this.getOpenfireConnection();
        User user = null;
        try {
            pstmt = conn.prepareStatement("select * from ofuser where username'" + userName + "'");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserName(rs.getString("username"));
                user.setPassWord(rs.getString("name"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 判断是否登录成功
     *
     * @param phoneNumber
     * @param passWord
     * @return
     */
    public List<User> login(String phoneNumber, String passWord) {
        List<User> list = new ArrayList<>();
        conn = this.getConnection();
        User user = null;
        try {
            pstmt = conn.prepareStatement("select * from users where phoneNumber='" + phoneNumber + "'and passWord='" + passWord + "'");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassWord(rs.getString("passWord"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setNickName(rs.getString("nickName"));
                user.setCity(rs.getString("city"));
                user.setSex(rs.getString("sex"));
                user.setYears(rs.getString("years"));
                list.add(user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            this.closeAll(rs, pstmt, conn);
        }
        return list;
    }

    public List<User> queryUserById(String id) {
        List<User> list = new ArrayList<>();
        conn = this.getConnection();
        User user = null;
        try {
            pstmt = conn.prepareStatement("select * from users where id='" + id + "'");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassWord(rs.getString("passWord"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setNickName(rs.getString("nickName"));
                user.setCity(rs.getString("city"));
                user.setSex(rs.getString("sex"));
                user.setYears(rs.getString("years"));
                list.add(user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            this.closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 保存新用户
     *
     * @param user
     * @return
     */
    public boolean regist(User user) {
        conn = this.getConnection();
        try {
            pstmt = conn.prepareStatement("insert into users(id,userName,passWord,phoneNumber,avatar,nickName,city,sex,years)values(?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassWord());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getAvatar());
            pstmt.setString(6, user.getNickName());
            pstmt.setString(7, user.getCity());
            pstmt.setString(8, user.getSex());
            pstmt.setString(9, user.getYears());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            this.closeAll(null, pstmt, conn);
        }
    }

    /**
     * 修改用户资料
     *
     * @param user
     * @return
     */
    public boolean updateUser(User user) {
        conn = this.getConnection();
        try {
            pstmt = conn.prepareStatement("update users set nickName=?,city=?,sex=?,years=? where id='" + user.getId() + "'");
            pstmt.setString(1, user.getNickName());
            pstmt.setString(2, user.getCity());
            pstmt.setString(3, user.getSex());
            pstmt.setString(4, user.getYears());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            this.closeAll(null, pstmt, conn);
        }
    }

    /**
     * 修改xmpp用户资料
     *
     * @param phoneNumber
     * @param name
     * @return
     */
    public boolean updateXmppUser(String phoneNumber, String name) {
        conn = this.getOpenfireConnection();
        try {
            pstmt = conn.prepareStatement("update ofuser set NAME=? where username='" + phoneNumber + "'");
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            this.closeAll(null, pstmt, conn);
        }
    }


    /**
     * 修改密码
     *
     * @param id
     * @param passWord
     * @return
     */
    public boolean updatePassWord(String id, String passWord) {
        conn = this.getConnection();
        try {
            pstmt = conn.prepareStatement("update users set passWord=? where id='" + id + "'");
            pstmt.setString(1, passWord);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            this.closeAll(null, pstmt, conn);
        }
    }


    /**
     * 修改头像
     *
     * @param userName
     * @param avatar
     * @return
     */
    public boolean updateAvatar(String userName, String avatar) {
        conn = this.getConnection();
        try {
            pstmt = conn.prepareStatement("update users set avatar=? where userName='" + userName + "'");
            pstmt.setString(1, avatar);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            this.closeAll(null, pstmt, conn);
        }
    }

}
