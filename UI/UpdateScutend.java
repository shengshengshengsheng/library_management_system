package UI;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import model.Scutend;
import model.ScutendModle;
import Dao.Dao;
import Dao.Utils;

public class UpdateScutend extends JDialog implements ActionListener,ListSelectionListener{

	/**
	 * 读者维护对话框
	 */
	private static final long serialVersionUID = 1L;
	private JLabel bookNameTop,bookOtherTop,scutendAgeTop;
	private JLabel bookId,bookName,bookOther,scutendAge;
	private JTextField bookNameTextTop,bookOtherTextTop,bookIdText,bookNameText,bookOtherText,scutendAgeTextTop,scutendAgeText;

	private JButton searchButton,reviseButton,deleteButton;
	private JScrollPane tableScroll;
	private JTable bookTable;
	private Container container=null;
	private Scutend scutend=new Scutend();
	private ScutendModle model=null;

	
	
	public UpdateScutend(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		container=this.getContentPane();
		this.setLayout(null);
		
		bookNameTop=new JLabel("读者名称:");
		bookNameTop.setFont(Utils.f2);
		bookNameTop.setBounds(20, 30, 70, 20);
		container.add(bookNameTop);
		
		bookNameTextTop=new JTextField();
		bookNameTextTop.setFont(Utils.f2);
		bookNameTextTop.setBounds(100, 30, 100, 20);
		container.add(bookNameTextTop);
		
		bookOtherTop=new JLabel("读者性别:");
		bookOtherTop.setFont(Utils.f2);
		bookOtherTop.setBounds(230, 30, 70, 20);
		container.add(bookOtherTop);
		
		bookOtherTextTop=new JTextField();
		bookOtherTextTop.setFont(Utils.f2);
		bookOtherTextTop.setBounds(310, 30, 100, 20);
		container.add(bookOtherTextTop);
		
		scutendAgeTop=new JLabel("读者年龄:");
		scutendAgeTop.setFont(Utils.f2);
		scutendAgeTop.setBounds(440, 30, 70, 20);
		container.add(scutendAgeTop);
		
		scutendAgeTextTop=new JTextField();
		scutendAgeTextTop.setFont(Utils.f2);
		scutendAgeTextTop.setBounds(520, 30, 100, 20);
		container.add(scutendAgeTextTop);
		
	
		searchButton=new JButton("查询");
		searchButton.setFont(Utils.f2);
		searchButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/search.png")));
		searchButton.setBounds(660, 24, 90, 30);
		searchButton.addActionListener(this);
		container.add(searchButton);
		
		bookTable=new JTable(new ScutendModle(new Scutend()));
		bookTable.getSelectionModel().addListSelectionListener(this);
		bookTable.setFont(Utils.f2);
		//不加滚动条不会显示列名
		tableScroll=new JScrollPane(bookTable);
		tableScroll.setBorder(BorderFactory.createTitledBorder(""));
		tableScroll.setBounds(20, 80, 730, 200);
		container.add(tableScroll);
		
		//加载下面的组件
		this.initBottom();
		
		this.setTitle(title);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("images/manageBookType.png")).getImage());
		this.setSize(780,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initBottom(){
		
		bookId=new JLabel("读者ID:");
		bookId.setFont(Utils.f2);
		bookId.setBounds(80, 320, 70, 20);
		container.add(bookId);
		
		bookIdText=new JTextField();
		bookIdText.setFont(Utils.f2);
		bookIdText.setBounds(160, 320, 100, 20);
		bookIdText.setEditable(false);
		container.add(bookIdText);
		
		
		bookOther=new JLabel("读者性别:");
		bookOther.setFont(Utils.f2);
		bookOther.setBounds(290, 320, 70, 20);
		container.add(bookOther);
		
		bookOtherText=new JTextField();
		bookOtherText.setFont(Utils.f2);
		bookOtherText.setBounds(370, 320, 100, 20);
		container.add(bookOtherText);
		
		scutendAge=new JLabel("读者年龄:");
		scutendAge.setFont(Utils.f2);
		scutendAge.setBounds(500, 320, 70, 20);
		container.add(scutendAge);
		
		scutendAgeText=new JTextField();
		scutendAgeText.setFont(Utils.f2);
		scutendAgeText.setBounds(580, 320, 100, 20);
		container.add(scutendAgeText);
		
		bookName=new JLabel("读者姓名:");
		bookName.setFont(Utils.f2);
		bookName.setBounds(80, 350, 70, 20);
		container.add(bookName);
		
		bookNameText=new JTextField();
		bookNameText.setFont(Utils.f2);
		bookNameText.setBounds(160, 350, 100, 20);
		container.add(bookNameText);

		
		reviseButton=new JButton("修改");
		reviseButton.setFont(Utils.f2);
		reviseButton.setBounds(255, 523, 90, 30);
		reviseButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/pen.png")));
		reviseButton.addActionListener(this);
		container.add(reviseButton);
		
		deleteButton=new JButton("删除");
		deleteButton.setFont(Utils.f2);
		deleteButton.setBounds(405, 523, 90, 30);
		deleteButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/delete.png")));
		deleteButton.addActionListener(this);
		container.add(deleteButton);
	}
	
	//封装搜索book对象
	public Scutend getScutendBySearch(){
		
		Scutend scutend=new Scutend();
		
		scutend.setName(bookNameTextTop.getText().trim());
		scutend.setSex(bookOtherTextTop.getText().trim());
		scutend.setAge(Integer.valueOf(scutendAgeTextTop.getText().trim()));
		return scutend;
	}
	
	//得到选定行的数据并封装成book对象
	public void getSelectRowDate(int rowNum){
	
		//必须判断rowNum==-1否则会报下标越界异常
		if(rowNum!=-1){
			String scutendId=bookTable.getValueAt(rowNum, 0).toString();
			String scutendSex=bookTable.getValueAt(rowNum, 1).toString();
			String scutendAge=bookTable.getValueAt(rowNum, 2).toString();
			String scutendName=bookTable.getValueAt(rowNum, 3).toString();
			
			bookIdText.setText(scutendId);
			bookNameText.setText(scutendName);
			bookOtherText.setText(scutendSex);
			scutendAgeText.setText(scutendAge);
			
			
		}
	
	}
	
	//清空输入框中的内容
	public void clearInput(){
		
		bookIdText.setText("");
		bookNameText.setText("");
		bookOtherText.setText("");
		scutendAgeText.setText("");
	}
	
	//封装book对象
	public Scutend getScutend(){
		
		Scutend scutend=new Scutend();
		
		//获取数据
		String scutendId=bookIdText.getText().trim();
		String scutendName=bookNameText.getText().trim();
		String scutendSex=bookOtherText.getText().trim();
		String scutendAge=scutendAgeText.getText().trim();
		
		//如果获取的数据都不为空就封装成book对象
		if(Utils.isEmpty(scutendId)&&Utils.isEmpty(scutendName)&&Utils.isEmpty(scutendSex)&&Utils.isEmpty(scutendAge)){
            int id=Integer.valueOf(scutendId);
            int age=Integer.valueOf(scutendAge);
			scutend=new Scutend(id, scutendSex,age,scutendName);
			
		} else {
			JOptionPane.showMessageDialog(this, "每一项都不能为空!");
		}

		return scutend;
	}

	
	//更新数据模型
		public void updateModel(){
			
			scutend.setId(0);
			model=new ScutendModle(scutend);
			//更新数据模型
			this.bookTable.setModel(model);
			//把输入框的内容置空
			this.clearInput();
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==searchButton){
			
			Scutend scutend=this.getScutendBySearch();
			
			//验证搜索book对象是否为空
			if (!(Utils.isEmpty(scutend.getName())||Utils.isEmpty(scutend.getSex())||Utils.isEmpty(scutend.getAge()+""))) {
				
				bookTable.setModel(new ScutendModle(scutend));
				
				//清除输入框的内容
				bookNameTextTop.setText("");
				bookOtherTextTop.setText("");
				
			} else {
			
				ScutendModle model=new ScutendModle(scutend);
				bookTable.setModel(model);
			}
			
		} else if (e.getSource()==reviseButton){

			//修改
			
			Scutend scutendResive=this.getScutend();
			
			if(scutendResive==null){
				
				return;
				
			} else {

				if(Dao.UpdateScutend(scutendResive.getId(),scutendResive.getSex(),scutendResive.getAge(),scutendResive.getName())!=0){
					
					JOptionPane.showMessageDialog(this, "修改成功!");
					this.updateModel();
					
				} else {
					
					JOptionPane.showMessageDialog(this, "修改失败!");
					
				}
			}
			
			
		} else if (e.getSource()==deleteButton){
			
			//删除
			Scutend scutend=new Scutend();
			String scutendId=bookIdText.getText().trim();
			
			if(Utils.isEmpty(scutendId)){
				
				scutend.setId(Integer.valueOf(scutendId));
				
				if(Dao.DelScutend(scutend.getId())!=0){
					
					JOptionPane.showMessageDialog(this, "删除成功!");
					this.updateModel();
					
				} else {
					
					JOptionPane.showMessageDialog(this, "删除失败!");
					
				}
				
			} else{
				
				JOptionPane.showMessageDialog(this, "请选择您要删除的行!");
				return;
			}
		
		}
		
	}
	
	//该函数在每次更新数据模型时自动调用 并且table.getSelectedRow()会返回-1  注意啊!!!!!!
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		this.getSelectRowDate(bookTable.getSelectedRow());
		
	}



}	

