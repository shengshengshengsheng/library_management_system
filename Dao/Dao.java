package Dao;
import java.sql.*;

import model.Book;
import java.util.Vector;
import model.Operator;
import model.Scutend;
import model.Record;

public class Dao {   
	protected static String dbClassName =      "com.microsoft.sqlserver.jdbc.SQLServerDriver";//数据库连接驱动类  
	protected static String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=Library;";//数据库连接 URL   
	protected static String dbUser = "sa";     //数据库用户名  
	protected static String dbPwd = "shifu961228";   //数据库密码  
	private static Connection conn = null;      //数据库连接对象   
	private static String ISBN;  
	private Dao() {               //构造方法      
		try {       if (conn == null) {          //如果连接对象为空      
			Class.forName(dbClassName);       //加载驱动类        
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);//获得连接对 象       
			}     
		} 
		catch (Exception ee) {      
			ee.printStackTrace();    
			}   
		}  
	private static ResultSet executeQuery(String sql) { //查询方法      
		try {        
			if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);//执行查询 
		} 
		catch (SQLException e) {         
			e.printStackTrace();
			return null;       //返回null值        
			}  finally {
				}   
		}   
	private static int executeUpdate(String sql) {   //更新方法     
		try {        
			if(conn==null)  new Dao(); //如果连接对象为空，则重新调用构造方法       
			return conn.createStatement().executeUpdate(sql);//执行更新       
			}
		catch (SQLException e) {         
			e.printStackTrace();          
			return -1;       
			} finally {      
				
			} 
		}   
	
public static String[] executeColumn(String sql,String[] parameters){
		
		String columnName[]=null;
		
		try {
			if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
			//得到连接
			
			PreparedStatement statement=conn.prepareStatement(sql);
			//给问号赋值
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					statement.setString(i+1, parameters[i]);
				}
			}
			ResultSet result=statement.executeQuery();
			//得到共有多少列
			ResultSetMetaData metaData=result.getMetaData();
			int column=metaData.getColumnCount();
			columnName=new String[column];
			//循环的取出数据
			while(result.next()){
				
				for(int i=0;i<columnName.length;i++){
					//得到列名
					columnName[i]=metaData.getColumnName(i+1);
				}
			
			}
			
		} catch (SQLException e) {
			new RuntimeException(e.getMessage());
			e.printStackTrace();
		}finally{
			Dao.close();
		}
		
		return columnName;
		
	}
	

	public static void close() {//关闭方法      
		try {        
			conn.close();//关闭连接对象             
			} 
		catch (SQLException e) {           
			e.printStackTrace();         
			}
		finally{           
			conn = null; //设置连接对象为null值          
			}   
		}
	
	public static int DelScutend(int id){   //删除学生
		int i=0;    
		try{ 
			String sql="delete from Scutend where S_id="+id;    //
			System.out.println(sql);     
			i=Dao.executeUpdate(sql); 
			}catch(Exception e){ 
	    e.printStackTrace();   
	    }     
		Dao.close();//关闭连接            ……       
		return i;
	    }
    
	public static int DelBook(int id){   //删除书本
		int i=0;    
		try{ 
			String sql="delete from Book where B_id="+id;    //
			System.out.println(sql);     
			i=Dao.executeUpdate(sql); 
			}catch(Exception e){ 
	    e.printStackTrace();   
	    }     
		Dao.close();//关闭连接            …… 
		return i;
	    }
    
	public static void DelOperator(int id){   //删除管理员
		int i=0;    
		try{ 
			String sql="delete from Scutend where O_id="+id;    //
			System.out.println(sql);     
			i=Dao.executeUpdate(sql); 
			}catch(Exception e){ 
	    e.printStackTrace();   
	    }     
		Dao.close();//关闭连接            ……       
	    }
	
	public static void DelRecord(int id){   //删除借书记录
		int i=0;    
		try{ 
			String sql="delete from Scutend where R_id="+id;    //
			System.out.println(sql);     
			i=Dao.executeUpdate(sql); 
			}catch(Exception e){ 
	    e.printStackTrace();   
	    }     
		Dao.close();//关闭连接            ……       
	    }
	
	public static int UpdateScutend(int id,String sex,int age,String name){ //修改Scutend 
		int i=0;   
		try{
			String sql="update Scutend set   S_sex='"+sex+"',  S_age='"+age+"',S_name='"+name+"' where S_id="+id;    
			i=Dao.executeUpdate(sql);// 执行更新  
			}
		catch(Exception e){    
			e.printStackTrace();    
			}      
		Dao.close();//关闭连接  
		return i;
		}
	
	public static int UpdateRecordisBack(int id){ //修改Record 中的isBack参数 
		int i=0;   
		try{
			String sql="update Record set R_isBack=1"+" where B_id="+id+" and R_isBack=0";    
			i=Dao.executeUpdate(sql);// 执行更新  
			}
		catch(Exception e){    
			e.printStackTrace();    
			}      
		Dao.close();//关闭连接  
		return i;
		}
	
	public static int UpdateBook(int id,String name,String author){  //修改Book
		int i=0;   
		try{
			String sql="update Book set   B_name='"+name+"',  B_author='"+author+"' where B_id="+id;    
			i=executeUpdate(sql);// 执行更新  
			}
		catch(Exception e){    
			e.printStackTrace();    
			}      
		Dao.close();//关闭连接  '
		return i;
		}

	public static int UpdateOperator(int id,String name,String password){  //修改Operator
		int i=0;   
		try{
			String sql="update Operator set   O_name='"+name+"',  O_password='"+password+"' where O_id="+id;    
			i=Dao.executeUpdate(sql);// 执行更新  
			}
		catch(Exception e){    
			e.printStackTrace();    
			}      
		Dao.close();//关闭连接  
		return i;
		}
	
	public static int addOperator(int id,String name,String password) {//添加Operator
	    int i = 0;
	    String Id=""+id;
	    String sql = "insert into Operator (O_id,O_name,O_password) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	    	if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, Id);
	        pstmt.setString(2, name);
	        pstmt.setString(3, password);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    Dao.close();
	    return i;
	}
	
	public static int addBook(int id,String name,String author) {//添加Book
	    int i = 0;
	    String Id=""+id;
	    String sql = "insert into Book (B_id,B_name,B_author) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	    	if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, Id);
	        pstmt.setString(2, name);
	        pstmt.setString(3, author);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    Dao.close();
	    return i;
	}
	
	public static int addRecord(Record record) {//添加Record
	    int i = 0;
	    
	    String sql = "insert into Record (B_id,O_id,S_id,R_isBack,R_date) values(?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	    	if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, record.getB_id()+"");
	        pstmt.setString(2, record.getO_id()+"");
	        pstmt.setString(3, record.getS_id()+"");
	        pstmt.setString(4, record.getIsBack()+"");
	        pstmt.setString(5, record.getDateTome());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    Dao.close();
	    return i;
	}
	
	//针对table的查询函数
		public static Vector<Object> executeTable(String sql,String parameters[]){
			
			Vector<Object> vector=new Vector<Object>();
			
			try {
				//得到连接
				if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
				
				PreparedStatement statement=conn.prepareStatement(sql);
				//给问号赋值
				if(parameters!=null){
					for(int i=0;i<parameters.length;i++){
						statement.setString(i+1, parameters[i]);
					}
				}
				ResultSet result=statement.executeQuery();
				//得到共有多少列
				ResultSetMetaData metaData=result.getMetaData();
				//循环的取出数据
				while(result.next()){
					//临时集合
					Vector<String> temp=new Vector<String>();
					
					for(int i=0;i<metaData.getColumnCount();i++){
						//把每列的数据加入临时集合
						temp.add(result.getString(i+1));
					}
					//把每行数据加入集合
					vector.add(temp);
				}
				
			} catch (SQLException e) {
				new RuntimeException(e.getMessage());
				e.printStackTrace();
			}finally{
				Dao.close();
			}
			
			return vector;
			
		}
	// 查询所有图书类型并封装
		public static Vector<Object> getBooks(Book book) {

			Vector<Object> rowDate = null;
			StringBuffer sql = new StringBuffer("select B_Id,B_name,B_author from Book");
			
			
			if (Utils.isEmpty(book.getName())) {
				
				sql.append(" and B_name like '%" + book.getName() + "%'");
			}
			
			if (Utils.isEmpty(book.getAuthor())) {
				
				sql.append(" and B_author like '%" + book.getAuthor() + "%'");
			}
			
			

			
			if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
			rowDate = executeTable(sql.toString().replaceFirst("and", "where"), null);
   
			return rowDate;
		}
		
	// 得到列名并二次封装
		public static String[] getColumnName() {

			String columnName[] = null;
			String sql = "select B_id,B_name,B_author from book" ;
		
			columnName = executeColumn(sql, null);

			for (int i = 0; i < columnName.length; i++) {

				if ("B_id".equals(columnName[i])) {

					columnName[i] = "图书ID";
				} else if("B_name".equals(columnName[i])){
					
					columnName[i] = "图书名称";
				} else if ("B_author".equals(columnName[i])) {

					columnName[i] = "图书作者";
				}
			}

			return columnName;
		}
		
		// 查询所有读者类型并封装
				public static Vector<Object> getScutends(Scutend scutend) {

					Vector<Object> rowDate = null;
					StringBuffer sql = new StringBuffer("select S_Id,S_sex,S_age,S_name from Scutend");
					
					
					if (Utils.isEmpty(scutend.getName())) {
						
						sql.append(" and S_name like '%" + scutend.getName() + "%'");
					}
					
					if (Utils.isEmpty(scutend.getSex())) {
						
						sql.append(" and S_sex like '%" + scutend.getSex() + "%'");
					}
					
                    if (Utils.isEmpty(scutend.getAge()+"")) {
						
						sql.append(" and S_age like '%" + scutend.getAge() + "%'");
					}
					
					

					
					if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
					rowDate = executeTable(sql.toString().replaceFirst("and", "where"), null);
		   
					return rowDate;
				}
				
			// 得到列名并二次封装
				public static String[] getScutendColumnName() {

					String columnName[] = null;
					String sql = "select S_id,S_sex,S_age,S_name from Scutend" ;
				
					columnName = executeColumn(sql, null);

					for (int i = 0; i < columnName.length; i++) {

						if ("S_id".equals(columnName[i])) {

							columnName[i] = "读者ID";
						} else if("S_sex".equals(columnName[i])){
							
							columnName[i] = "读者性别";
						} else if ("S_age".equals(columnName[i])) {

							columnName[i] = "读者年龄";
						} else if ("S_name".equals(columnName[i])) {

							columnName[i] = "读者姓名";
						}
					}

					return columnName;
				}
				
				
	public static int addScutend(int id,String sex,int age,String name) {//添加Scudent
	    int i = 0;
	    String Id=""+id;
	    String Age=""+age;
	    String sql = "insert into Scutend (S_id,S_sex,S_age,S_name) values(?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	    	if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, Id);
	        pstmt.setString(2, sex);
	        pstmt.setString(3, Age);
	        pstmt.setString(4, name);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    Dao.close();
	    return i;
	}
	
	public static int addRecord(int B_id,int O_id,int S_id,int isBack,String Date) {//添加Record
	    int i = 0;
	    String Bid=""+B_id;
	    String Oid=""+O_id;
	    String Sid=""+S_id;
	    String IsBack=""+isBack;
	    String sql = "insert into Record (B_id,O_id,S_id,isBack,R_date) values(?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	    	if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法        
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, Bid);
	        pstmt.setString(2, Oid);
	        pstmt.setString(3, Sid);
	        pstmt.setString(4, IsBack);
	        pstmt.setString(5, Date);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    Dao.close();
	    return i;
	}
	
	public static boolean check(Operator user) { 
		Operator operator=new Operator();//操作员信息对象   
		String sql = "select *  from Operator where O_id='" + user.getId()+"'" ;    
		
		try {  
			if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法    
			ResultSet rs = Dao.executeQuery(sql);    
			while (rs.next()) {
			operator.setId(rs.getInt("O_Id"));
			operator.setName(rs.getString("O_Name"));
			operator.setPassword(rs.getString("O_Password"));
		}
	    }
		catch (SQLException e) {
	        e.printStackTrace();
		}
		System.out.println(user.getName()+user.getPassword());
		System.out.println(operator.getName()+operator.getPassword());
		if(user.getPassword().equals(operator.getPassword()))
			{
			 System.out.println("相等");
			 Dao.close();
			 return true;
			}
		else
		{
			Dao.close();
			System.out.println("不相等");
			return false;
		}
	}


public static boolean checkBookRecord(Record record) { 
	Record record1=new Record();//借阅记录信息对象   
	
	String sql = "select *  from Record where B_id=" + record.getB_id()+" and R_isBack=0" ;    
	
	try {  
		if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法    
		ResultSet rs = Dao.executeQuery(sql);    
		while (rs.next()) {
		record1.setId(rs.getInt("R_id"));
		record1.setB_id(rs.getInt("B_id"));
		record1.setO_id(rs.getInt("O_id"));
		record1.setS_id(rs.getInt("S_id"));
		record1.setIsBack(rs.getInt("R_isBack"));
		record1.setDateTime(rs.getString("R_date"));
		System.out.println(record1.getDateTome());
	}
    }
	catch (SQLException e) {
        e.printStackTrace();
	}
	
	if(record1.getDateTome()==null)
		{
		 System.out.println("不存在");
		 Dao.close();
		 return true;
		}
	else
	{
		Dao.close();
		System.out.println("存在");
		return false;
	}
}

public static int returnBook(Record record) { 
	
	int i=0;
	Record record1=new Record();//借阅记录信息对象   
	String sql = "select *  from Record where B_id=" + record.getId()+" and R_isBack="+record.getIsBack() ;    
	
	try {  
		if(conn==null)  new Dao();  //如果连接对象为空，则重新调用构造方法    
		ResultSet rs = Dao.executeQuery(sql);    
		while (rs.next()) {
		record1.setId(rs.getInt("R_id"));
		record1.setB_id(rs.getInt("B_id"));
		record1.setO_id(rs.getInt("O_id"));
		record1.setS_id(rs.getInt("S_id"));
		record1.setIsBack(rs.getInt("R_isBack"));
		record1.setDateTime(rs.getString("R_date"));
	}
    }
	catch (SQLException e) {
        e.printStackTrace();
	}
	
	if(record.getIsBack()==record1.getIsBack())
		{
		 i=UpdateRecordisBack(record1.getB_id());
		 System.out.println("存在");
		 Dao.close();
		 return i;
		}
	else
	{
		Dao.close();
		System.out.println("不存在");
		return i;
	}
}
}
