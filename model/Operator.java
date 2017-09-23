package model;

public class Operator {
    private int id;
    private String name;
    private String password;

    public Operator(){
    }
    public Operator(int _id,String _name,String _password){
    	id=_id;
    	name=_name;
    	password=_password;
    }
    public void setId(int _id){
    	id=_id;
    }
    public void setName(String _name){
    	name=_name;
    }
    public void setPassword(String _password){
    	password=_password;
    }
    public int getId(){
    	return id;
    }
    public String getName(){
    	return name;
    }
    public String getPassword(){
    	return password;
    }
   
}
