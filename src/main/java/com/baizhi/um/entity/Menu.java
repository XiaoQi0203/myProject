package com.baizhi.um.entity;

import java.util.List;

/**
 * @Author SIMBA1949
 * @Date 2020/6/11 14:00
 */

public class Menu {
    private String id;
    private String text;
    private String iconCls;
    private String href;
    private String desc;
    private List<Menu> children;

    public Menu() {
    }

    public Menu(String id, String text, String iconCls, String href, String desc, List<Menu> children) {
        this.id = id;
        this.text = text;
        this.iconCls = iconCls;
        this.href = href;
        this.desc = desc;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id='" + id + '\'' +
            ", text='" + text + '\'' +
            ", iconCls='" + iconCls + '\'' +
            ", href='" + href + '\'' +
            ", desc='" + desc + '\'' +
            ", children=" + children +
            '}';
    }
}
