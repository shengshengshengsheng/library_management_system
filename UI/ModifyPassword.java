package UI;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Dao.Dao;
import Dao.Utils;

import model.Operator;

public class ModifyPassword extends JDialog implements ActionListener{

	/**
	 * �޸��������
	 */
	private JLabel oldPassword,newPassword,name;
	private JTextField oldPasswordText,newPasswordText,nameText;
	private JButton add,reset;
	private Operator user;
	private static final long serialVersionUID = 1L;

	public ModifyPassword(Frame owner, String title, boolean modal,Operator _user) {
		super(owner,title, modal);
		user=new Operator();
		user.setId(_user.getId());
		user.setPassword(_user.getPassword());
		Container container=this.getContentPane();
		this.setLayout(null);
		
		oldPassword=new JLabel("������:");
		oldPassword.setFont(Utils.f2);
		oldPassword.setBounds(30, 30, 70, 20);
		container.add(oldPassword);
		
		oldPasswordText=new JTextField();
		oldPasswordText.setFont(Utils.f2);
		oldPasswordText.setBounds(120, 30, 100, 20);
		container.add(oldPasswordText);
		
		newPassword=new JLabel("������:");
		newPassword.setFont(Utils.f2);
		newPassword.setBounds(280, 30, 70, 20);
		container.add(newPassword);
		
		newPasswordText=new JTextField();
		newPasswordText.setFont(Utils.f2);
		newPasswordText.setBounds(370, 30, 100, 20);
		container.add(newPasswordText);
		
		name=new JLabel("�û���:");
		name.setFont(Utils.f2);
		name.setBounds(30, 80, 70, 20);
		container.add(name);
		
		nameText=new JTextField();
		nameText.setFont(Utils.f2);
		nameText.setBounds(120, 80, 100, 20);
		container.add(nameText);
	
		
		add=new JButton("ȷ��");
		add.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/add.png")));
		add.setBounds(140, 320, 90, 30);
		add.setFont(Utils.f2);
		add.addActionListener(this);
		container.add(add);
		
		reset=new JButton("����");
		reset.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/reset.png")));
		reset.setBounds(270, 320, 90, 30);
		reset.setFont(Utils.f2);
		reset.addActionListener(this);
		container.add(reset);
		
		this.setTitle(title);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/add.png")).getImage());
		this.setSize(500, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource()==add){
			
			String oldPassword=oldPasswordText.getText().trim();
			String newPassword=newPasswordText.getText().trim();
			String name=nameText.getText().trim();
			
			if(!(Utils.isEmpty(oldPassword)&&Utils.isEmpty(newPassword))){
				JOptionPane.showMessageDialog(this, "������һ��Ϊ��!");
				return;
			}
			
			
			if(user.getPassword().equals(oldPassword)){
				if(Dao.UpdateOperator(user.getId(),name,newPassword)!=0){
				JOptionPane.showMessageDialog(this, "�޸ĳɹ�!");
				user.setPassword(newPassword);
				user.setName(name);
				this.inputClear();
			}else{
				JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
			}
			}
			else{
				JOptionPane.showMessageDialog(this, "�������!");
				this.inputClear();
			}
			
			
		}else if(e.getSource()==reset){
			this.inputClear();
		}
		
	}
	
	//�������������
	public void inputClear(){
		oldPasswordText.setText("");
		newPasswordText.setText("");
	}
}