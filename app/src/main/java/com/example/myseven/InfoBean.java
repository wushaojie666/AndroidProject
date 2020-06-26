package com.example.myseven;

public class InfoBean {

    public  String name;
    public String phone;
    public  int id;
    public  String sex;

public InfoBean(){
    super();
}
    public InfoBean(int id,String name,String phone,String sex){
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.phone=phone;
    }

    public int getId(){ return id; }
    public String getName(){
        return name;
    }
    public String getSex(){
        return sex;
    }
    public String getPhone(){
        return  phone;
    }

}
