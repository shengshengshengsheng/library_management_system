package UI;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Dao.Dao;
import Dao.Utils;

import model.Scutend;

public class AddScutend extends JDialog implements ActionListener{

	/**
	 * 添加图书界面
	 */
	private JLabel bookName,bookAuthor,bookId,scutentAge;
	private JTextField bookNameText,bookAuthorText,bookIdText,scutentAgeText;
	private JButton add,reset;
	
	private static final long serialVersionUID = 1L;

	public AddScutend(Frame owner, String title, boolean modal) {
		super(owner,title, modal);
		
		Container container=this.getContentPane();
		this.setLayout(null);
		
		bookName=new JLabel("学生姓名:");
		bookName.setFont(Utils.f2);
		bookName.setBounds(30, 30, 70, 20);
		container.add(bookName);
		
		bookNameText=new JTextField();
		bookNameText.setFont(Utils.f2);
		bookNameText.setBounds(120, 30, 100, 20);
		container.add(bookNameText);
		
		bookAuthor=new JLabel("学生性别:");
		bookAuthor.setFont(Utils.f2);
		bookAuthor.setBounds(280, 30, 70, 20);
		container.add(bookAuthor);
		
		bookAuthorText=new JTextField();
		bookAuthorText.setFont(Utils.f2);
		bookAuthorText.setBounds(370, 30, 100, 20);
		container.add(bookAuthorText);
		
		scutentAge=new JLabel("年龄:");
		scutentAge.setFont(Utils.f2);
		scutentAge.setBounds(280, 80, 70, 20);
		container.add(scutentAge);
		
		scutentAgeText=new JTextField();
		scutentAgeText.setFont(Utils.f2);
		scutentAgeText.setBounds(370, 80, 100, 20);
		container.add(scutentAgeText);
		
		bookId=new JLabel("学生ID:");
		bookId.setFont(Utils.f2);
		bookId.setBounds(30, 80, 70, 20);
		container.add(bookId);
		
		bookIdText=new JTextField();
		bookIdText.setFont(Utils.f2);
		bookIdText.setBounds(120, 80, 100, 20);
		container.add(bookIdText);
	    
		
		add=new JButton("添加");
		add.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/add.png")));
		add.setBounds(140, 320, 90, 30);
		add.setFont(Utils.f2);
		add.addActionListener(this);
		container.add(add);
		
		reset=new JButton("重置");
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
			
			String bookName=bookNameText.getText().trim();
			String bookAuthor=bookAuthorText.getText().trim();
			String bookId=bookIdText.getText().trim();
			int scutendAge=Integer.valueOf(scutentAgeText.getText().trim());
			
			if(!(Utils.isEmpty(bookName)&&Utils.isEmpty(bookAuthor)&&Utils.isEmpty(bookId))){
				JOptionPane.showMessageDialog(this, "其中有一项为空!");
				return;
			}
			
			int id=Integer.valueOf(bookId);
			
			//封装book对象
			Scutend scutend=new Scutend(id,bookAuthor,scutendAge,bookName );
			if(Dao.addScutend(id,bookAuthor,scutendAge,bookName)==1){
				JOptionPane.showMessageDialog(this, "添加成功!");
				this.inputClear();
			}else{
				JOptionPane.showMessageDialog(this, "添加失败!");
			}
			
		}else if(e.getSource()==reset){
			this.inputClear();
		}
		
	}
	
	//清空输入框的内容
	public void inputClear(){
		bookNameText.setText("");
		bookAuthorText.setText("");
		bookIdText.setText("");
		scutentAgeText.setText("");
	}
}