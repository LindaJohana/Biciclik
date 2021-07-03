package com.example.biciclik.utils;

public class KeyPairBoolDataCustom {
    private String id;
    private String name;
    private String extra;
    private boolean isSelected;
    private Object object;

    public KeyPairBoolDataCustom() {
    }

    public KeyPairBoolDataCustom(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
