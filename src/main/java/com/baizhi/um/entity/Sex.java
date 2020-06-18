package com.baizhi.um.entity;

/**
 * @Author SIMBA1949
 * @Date 2020/6/13 16:46
 */

public class Sex {
    private String sex;
    private Integer count;

    public Sex() {
    }

    public Sex(String sex, Integer count) {
        this.sex = sex;
        this.count = count;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Sex{" +
            "sex='" + sex + '\'' +
            ", count=" + count +
            '}';
    }
}
