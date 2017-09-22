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
	    System.out.println("���������ɹ���");  
	}catch(Exception e){  
	    e.printStackTrace();  
	    System.out.println("��������ʧ�ܣ�");  
	}  
	try{  
	    Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);  
	        System.out.println("�������ݿ�ɹ���");  
	}catch(Exception e)  
	{  
	    e.printStackTrace();  
	    System.out.print("SQL Server����ʧ�ܣ�");  
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
		
		//�õ���������
		Container container=this.getContentPane();
		this.setLayout(null);
		
		logoLabel=new JLabel("����ͼ��ϵͳ");
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
		
		passwordLabel=new JLabel("�ܡ���:");
		passwordLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/password.png")));	
		passwordLabel.setFont(Utils.f2);
		passwordLabel.setBounds(120, 180, 80, 40);
		container.add(passwordLabel);
		
		passwordInput=new JPasswordField();
		passwordInput.setBounds(220, 188, 150, 20);
		container.add(passwordInput);
		
		login=new JButton("��¼");
		login.setFont(Utils.f2);
		login.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/login.png")));
		login.setBounds(100, 260, 90, 30);
		login.addActionListener(this);
		container.add(login);
		
		reset=new JButton("����");
		reset.setFont(Utils.f2);
		reset.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/reset.png")));
		reset.setBounds(300, 260, 90, 30);
		reset.addActionListener(this);
		container.add(reset);
		
		this.setSize(500,400);
		this.setTitle("����ͼ�����ϵͳ��¼");
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
		
		//�õ��û��������Ϣ
		String _userName=this.userNameInput.getText().trim();
		int userName=Integer.valueOf(_userName);
		//���ﲻ����toString����!!!!!!!!!!!!!!!
		String password=new String(this.passwordInput.getPassword());
		Operator user=new Operator();
		user.setId(userName);
		user.setPassword(password);
		if(e.getSource()==login){
			//�ж��û��Ƿ�������
			if(Utils.isEmpty(user.getId()+"")&&Utils.isEmpty(user.getPassword())){
				if(Dao.check(user)){
					new MainFrame(user);
					System.out.println("�ɹ���½");
					this.dispose();
				}else{
					
					JOptionPane.showMessageDialog(this, "�û������������");
				}
			}else{
				JOptionPane.showMessageDialog(this, "�û��������벻��Ϊ��");
			}
			
		}else if(e.getSource()==reset){
			
			this.passwordInput.setText("");
			this.userNameInput.setText("");
		}
	}

}
