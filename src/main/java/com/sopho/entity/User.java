package com.sopho.entity;

public class User {
    private String id;
    private String userName;// 用户名
    private String phoneNumber;
    private String passWord;// 密码
    private String avatar;// 头像地址
    private String nickName;// 昵称
    private String city;// 城市
    private String sex;// 性别
    private String years;// 年龄

    public User() {
    }

    public User(String userName, String phoneNumber, String passWord, String avatar, String nickName,
                String city, String sex, String years) {
        super();
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.avatar = avatar;
        this.nickName = nickName;
        this.city = city;
        this.sex = sex;
        this.years = years;
    }

    public User(String id, String userName, String phoneNumber, String passWord, String avatar, String nickName,
                String city, String sex, String years) {
        super();
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.avatar = avatar;
        this.nickName = nickName;
        this.city = city;
        this.sex = sex;
        this.years = years;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

}
