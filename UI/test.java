package UI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Dao.Dao;
import Dao.Utils;

import model.Operator;


import java.sql.*; 
public class test extends JFrame implements ActionListener{
    
	/**
	 * @param args
	 */
	/*
	public static void main(String [] args)  
	 {  
	  /*String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Library";  
	  String userName="Js";  
	  String userPwd="12345678";  
	 try  
	{  
	    Class.forName(driverName);  
	    System.out.println("加载驱动成功！");  
	}catch(Exception e){  
	    e.printStackTrace();  
	    System.out.println("加载驱动失败！");  
	}  
	try{  
	    Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);  
	        System.out.println("连接数据库成功！");  
	}catch(Exception e)  
	{  
	    e.printStackTrace();  
	    System.out.print("SQL Server连接失败！");  
	} 
		Dao.addRecord(0,1,1,0,"2017-08-14");
	    System.out.println("dd");
 }*/
private static final long serialVersionUID = 1L;
	
	private JLabel logoLabel,userNameLabel,passwordLabel;
	private JTextField userNameInput;
	private JPasswordField passwordInput;
	private JButton login,reset;
	
	public static void main(String[] args) {
		new test();

	}

	public test(){
		
		//得到顶级容器
		Container container=this.getContentPane();
		this.setLayout(null);
		
		logoLabel=new JLabel("华工图书系统");
		logoLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/logo.png")));
		logoLabel.setFont(Utils.f1);
		logoLabel.setBounds(150, 50, 200, 40);
		container.add(logoLabel);
		
		userNameLabel=new JLabel("ID:");
		userNameLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/user.png")));
		userNameLabel.setFont(Utils.f2);
		userNameLabel.setBounds(120, 140, 80, 40);
		container.add(userNameLabel);
		
		userNameInput=new JTextField();
		userNameInput.setBounds(220, 148, 150, 20);
		container.add(userNameInput);
		
		passwordLabel=new JLabel("密　码:");
		passwordLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/password.png")));	
		passwordLabel.setFont(Utils.f2);
		passwordLabel.setBounds(120, 180, 80, 40);
		container.add(passwordLabel);
		
		passwordInput=new JPasswordField();
		passwordInput.setBounds(220, 188, 150, 20);
		container.add(passwordInput);
		
		login=new JButton("登录");
		login.setFont(Utils.f2);
		login.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/login.png")));
		login.setBounds(100, 260, 90, 30);
		login.addActionListener(this);
		container.add(login);
		
		reset=new JButton("重置");
		reset.setFont(Utils.f2);
		reset.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/reset.png")));
		reset.setBounds(300, 260, 90, 30);
		reset.addActionListener(this);
		container.add(reset);
		
		this.setSize(500,400);
		this.setTitle("华工图书管理系统登录");
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("images/tittle.png")).getImage());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//得到用户输入的信息
		String _userName=this.userNameInput.getText().trim();
		int userName=Integer.valueOf(_userName);
		//这里不能用toString方法!!!!!!!!!!!!!!!
		String password=new String(this.passwordInput.getPassword());
		Operator user=new Operator();
		user.setId(userName);
		user.setPassword(password);
		if(e.getSource()==login){
			//判断用户是否有输入
			if(Utils.isEmpty(user.getId()+"")&&Utils.isEmpty(user.getPassword())){
				if(Dao.check(user)){
					new MainFrame(user);
					System.out.println("成功登陆");
					this.dispose();
				}else{
					
					JOptionPane.showMessageDialog(this, "用户名或密码错误");
				}
			}else{
				JOptionPane.showMessageDialog(this, "用户名或密码不能为空");
			}
			
		}else if(e.getSource()==reset){
			
			this.passwordInput.setText("");
			this.userNameInput.setText("");
		}
	}

}
