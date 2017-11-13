package org.androidtown.exam09;

public class SingerItem {
    String name;
    String birth;
    String tel;
    int resId;

    public SingerItem(String name, String birth, String tel, int resId) {
        this.name = name;
        this.birth = birth;
        this.tel = tel;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
