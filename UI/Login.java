package UI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Dao.Dao;
import Dao.Utils;

import model.Operator;



public class Login extends JFrame implements ActionListener {

	/**
	 * ��¼����
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel logoLabel,userNameLabel,passwordLabel;
	private JTextField userNameInput;
	private JPasswordField passwordInput;
	private JButton login,reset;
	
	public static void main(String[] args) {
		new Login();

	}

	public Login(){
		
		//�õ���������
		Container container=this.getContentPane();
		this.setLayout(null);
		
		logoLabel=new JLabel("ͼ�����ϵͳ");
		logoLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/logo.png")));
		logoLabel.setFont(Utils.f1);
		logoLabel.setBounds(150, 50, 200, 40);
		container.add(logoLabel);
		
		userNameLabel=new JLabel("�û���:");
		userNameLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/user.png")));
		userNameLabel.setFont(Utils.f2);
		userNameLabel.setBounds(120, 140, 80, 40);
		container.add(userNameLabel);
		
		userNameInput=new JTextField();
		userNameInput.setBounds(220, 148, 150, 20);
		container.add(userNameInput);
		
		passwordLabel=new JLabel("�ܡ���:");
		passwordLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/password.png")));	
		passwordLabel.setFont(Utils.f2);
		passwordLabel.setBounds(120, 180, 80, 40);
		container.add(passwordLabel);
		
		passwordInput=new JPasswordField();
		passwordInput.setBounds(220, 188, 150, 20);
		container.add(passwordInput);
		
		login=new JButton("��¼");
		login.setFont(Utils.f2);
		login.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/login.png")));
		login.setBounds(100, 260, 90, 30);
		login.addActionListener(this);
		container.add(login);
		
		reset=new JButton("����");
		reset.setFont(Utils.f2);
		reset.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/reset.png")));
		reset.setBounds(300, 260, 90, 30);
		reset.addActionListener(this);
		container.add(reset);
		
		this.setSize(500,400);
		this.setTitle("ͼ�����ϵͳ��¼");
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/tittle.png")).getImage());
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
		String userName=this.userNameInput.getText().trim();
		//���ﲻ����toString����!!!!!!!!!!!!!!!
		String password=new String(this.passwordInput.getPassword());
		Operator user=new Operator();
		user.setName(userName);
		user.setPassword(password);
		if(e.getSource()==login){
			//�ж��û��Ƿ�������
			if(Utils.isEmpty(user.getName())&&Utils.isEmpty(user.getPassword())){
				if(Dao.check(user)){
					
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

