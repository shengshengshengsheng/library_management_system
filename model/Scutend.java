package model;

public class Scutend {
    private int id;
    private String sex;
    private String name;
    private int age;
    public Scutend(){
    }
    public Scutend(int _id,String _sex,int _age,String _name){
    	id=_id;
    	name=_name;
    	age=_age;
    	sex=_sex;
    }
    public void setId(int _id){
    	id=_id;
    }
    public void setName(String _name){
    	name=_name;
    }
    public void setAge(int _age){
    	age=_age;
    }
    public void setSex(String _sex){
    	sex=_sex;
    }
    public int getId(){
    	return id;
    }
    public String getName(){
    	return name;
    }
    public String getSex(){
    	return sex;
    }
    public int getAge(){
    	return age;
    }
}
