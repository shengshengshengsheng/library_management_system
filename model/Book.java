package model;
//实现model中的Book类
public class Book {
    private int id;
    private String name;
    private String author;
    public Book(){
    }
    public Book(int _id,String _name,String _author){
    	id=_id;
    	name=_name;
    	author=_author;
    }
    public void setId(int _id){
    	id=_id;
    }
    public void setName(String _name){
    	name=_name;
    }
    public void setAuthor(String _author){
    	author=_author;
    }
    public int getId(){
    	return id;
    }
    public String getName(){
    	return name;
    }
    public String getAuthor(){
    	return author;
    }
}
