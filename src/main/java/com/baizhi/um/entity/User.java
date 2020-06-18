package com.baizhi.um.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author SIMBA1949
 * @Date 2020/6/1 5:39
 */
public class User implements Serializable {
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "名字")
    private String name;
    @Excel(name = "性别",replace = {"女_0","男_1"})
    private int sex;
    private String password;
    @Excel(name = "出生日期",exportFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;
    @Excel(name = "图片",type = 2,imageType = 1,width = 20,height = 20)
    private String photo;
    @Excel(name = "邮箱")
    private String email;

    public User() {
    }

    public User(Integer id, String name, int sex, String password, Date birthday, String photo, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.birthday = birthday;
        this.photo = photo;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", sex=" + sex +
            ", password='" + password + '\'' +
            ", birthday=" + birthday +
            ", photo='" + photo + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
