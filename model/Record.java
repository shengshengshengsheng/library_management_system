package model;

public class Record {
    private int id;
    private int B_id;
    private int O_id;
    private int S_id;
    private int isBack;
    private String dateTime;
    public Record(){
    }
    public Record(int _id,int _B_id,int _O_id,int _S_id,int _isBack,String _dateTime){
    	id=_id;
    	B_id=_B_id;
    	O_id=_O_id;
    	S_id=_S_id;
    	isBack=_isBack;
    	dateTime=_dateTime;
    }
    public void setId(int _id){
    	id=_id;
    }
    public void setB_id(int _B_id){
    	B_id=_B_id;
    }
    
    public void setO_id(int _O_id){
    	O_id=_O_id;
    }
    public void setS_id(int _S_id){
    	S_id=_S_id;
    }
    public void setIsBack(int _isBack){
    	isBack=_isBack;
    }
    public void setDateTime(String _dateTime){
    	dateTime=_dateTime;
    }
    public int getId(){
    	return id;
    }
    public int getB_id(){
    	return B_id;
    }
    public int getO_id(){
    	return O_id;
    }
    public int getS_id(){
    	return S_id;
    }
    public int getIsBack(){
    	return isBack;
    }
    public String getDateTome(){
    	return dateTime;
    }
}
