package UI;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import model.Book;
import model.BookModle;
import Dao.Dao;
import Dao.Utils;

public class UpdateBook extends JDialog implements ActionListener,ListSelectionListener{

	/**
	 * ͼ��ά���Ի���
	 */
	private static final long serialVersionUID = 1L;
	private JLabel bookNameTop,bookOtherTop;
	private JLabel bookId,bookName,bookOther;
	private JTextField bookNameTextTop,bookOtherTextTop,bookIdText,bookNameText,bookOtherText;

	private JButton searchButton,reviseButton,deleteButton;
	private JScrollPane tableScroll;
	private JTable bookTable;
	private Container container=null;
	private Book book=new Book();
	private BookModle model=null;

	
	
	public UpdateBook(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		container=this.getContentPane();
		this.setLayout(null);
		
		bookNameTop=new JLabel("ͼ������:");
		bookNameTop.setFont(Utils.f2);
		bookNameTop.setBounds(20, 30, 70, 20);
		container.add(bookNameTop);
		
		bookNameTextTop=new JTextField();
		bookNameTextTop.setFont(Utils.f2);
		bookNameTextTop.setBounds(100, 30, 100, 20);
		container.add(bookNameTextTop);
		
		bookOtherTop=new JLabel("ͼ������:");
		bookOtherTop.setFont(Utils.f2);
		bookOtherTop.setBounds(230, 30, 70, 20);
		container.add(bookOtherTop);
		
		bookOtherTextTop=new JTextField();
		bookOtherTextTop.setFont(Utils.f2);
		bookOtherTextTop.setBounds(310, 30, 100, 20);
		container.add(bookOtherTextTop);
		
	
		
		
		
		
		searchButton=new JButton("��ѯ");
		searchButton.setFont(Utils.f2);
		searchButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/search.png")));
		searchButton.setBounds(660, 24, 90, 30);
		searchButton.addActionListener(this);
		container.add(searchButton);
		
		bookTable=new JTable(new BookModle(new Book()));
		bookTable.getSelectionModel().addListSelectionListener(this);
		bookTable.setFont(Utils.f2);
		//���ӹ�����������ʾ����
		tableScroll=new JScrollPane(bookTable);
		tableScroll.setBorder(BorderFactory.createTitledBorder(""));
		tableScroll.setBounds(20, 80, 730, 200);
		container.add(tableScroll);
		
		//������������
		this.initBottom();
		
		this.setTitle(title);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("images/manageBookType.png")).getImage());
		this.setSize(780,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initBottom(){
		
		bookId=new JLabel("ͼ����:");
		bookId.setFont(Utils.f2);
		bookId.setBounds(80, 320, 70, 20);
		container.add(bookId);
		
		bookIdText=new JTextField();
		bookIdText.setFont(Utils.f2);
		bookIdText.setBounds(160, 320, 100, 20);
		bookIdText.setEditable(false);
		container.add(bookIdText);
		
		bookName=new JLabel("ͼ������:");
		bookName.setFont(Utils.f2);
		bookName.setBounds(290, 320, 70, 20);
		container.add(bookName);
		
		bookNameText=new JTextField();
		bookNameText.setFont(Utils.f2);
		bookNameText.setBounds(370, 320, 100, 20);
		container.add(bookNameText);
		
		bookOther=new JLabel("ͼ������:");
		bookOther.setFont(Utils.f2);
		bookOther.setBounds(500, 320, 70, 20);
		container.add(bookOther);
		
		bookOtherText=new JTextField();
		bookOtherText.setFont(Utils.f2);
		bookOtherText.setBounds(580, 320, 100, 20);
		container.add(bookOtherText);
		
		
	
		
		reviseButton=new JButton("�޸�");
		reviseButton.setFont(Utils.f2);
		reviseButton.setBounds(255, 523, 90, 30);
		reviseButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/pen.png")));
		reviseButton.addActionListener(this);
		container.add(reviseButton);
		
		deleteButton=new JButton("ɾ��");
		deleteButton.setFont(Utils.f2);
		deleteButton.setBounds(405, 523, 90, 30);
		deleteButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/delete.png")));
		deleteButton.addActionListener(this);
		container.add(deleteButton);
	}
	
	//��װ����book����
	public Book getBookBySearch(){
		
		Book book=new Book();
		
		book.setName(bookNameTextTop.getText().trim());
		book.setAuthor(bookOtherTextTop.getText().trim());
		return book;
	}
	
	//�õ�ѡ���е����ݲ���װ��book����
	public void getSelectRowDate(int rowNum){
	
		//�����ж�rowNum==-1����ᱨ�±�Խ���쳣
		if(rowNum!=-1){
			String bookId=bookTable.getValueAt(rowNum, 0).toString();
			String bookName=bookTable.getValueAt(rowNum, 1).toString();
			String bookOther=bookTable.getValueAt(rowNum, 2).toString();
			
			
			
			bookIdText.setText(bookId);
			bookNameText.setText(bookName);
			bookOtherText.setText(bookOther);
			
			
			
			
			
		}
	
	}
	
	//���������е�����
	public void clearInput(){
		
		bookIdText.setText("");
		bookNameText.setText("");
		bookOtherText.setText("");
		
	}
	
	//��װbook����
	public Book getBook(){
		
		Book book=new Book();
		
		//��ȡ����
		String bookId=bookIdText.getText().trim();
		String bookName=bookNameText.getText().trim();
		String bookOther=bookOtherText.getText().trim();
		
		
		//�����ȡ�����ݶ���Ϊ�վͷ�װ��book����
		if(Utils.isEmpty(bookId)&&Utils.isEmpty(bookName)&&Utils.isEmpty(bookOther)){
            int id=Integer.valueOf(bookId);
			book=new Book(id, bookName, bookOther);
			
			book.setId(id);
			book.setName(bookName);
			book.setAuthor(bookOther);
			
		} else {
			
			JOptionPane.showMessageDialog(this, "ÿһ�����Ϊ��!");
			
		}

		return book;
	}

	
	//��������ģ��
		public void updateModel(){
			
			book.setId(0);
			model=new BookModle(book);
			//��������ģ��
			this.bookTable.setModel(model);
			//�������������ÿ�
			this.clearInput();
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==searchButton){
			
			Book book=this.getBookBySearch();
			
			//��֤����book�����Ƿ�Ϊ��
			if (!(Utils.isEmpty(book.getName())||Utils.isEmpty(book.getAuthor()))) {
				
				bookTable.setModel(new BookModle(book));
				
				//�������������
				bookNameTextTop.setText("");
				bookOtherTextTop.setText("");
				
			} else {
			
				BookModle model=new BookModle(book);
				bookTable.setModel(model);
			}
			
		} else if (e.getSource()==reviseButton){

			//�޸�
			
			Book bookResive=this.getBook();
			
			if(bookResive==null){
				
				return;
				
			} else {

				if(Dao.UpdateBook(bookResive.getId(),bookResive.getName(),bookResive.getAuthor())!=0){
					
					JOptionPane.showMessageDialog(this, "�޸ĳɹ�!");
					this.updateModel();
					
				} else {
					
					JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
					
				}
			}
			
			
		} else if (e.getSource()==deleteButton){
			
			//ɾ��
			Book book=new Book();
			String bookId=bookIdText.getText().trim();
			
			if(Utils.isEmpty(bookId)){
				
				book.setId(Integer.valueOf(bookId));
				
				if(Dao.DelBook(book.getId())!=0){
					
					JOptionPane.showMessageDialog(this, "ɾ���ɹ�!");
					this.updateModel();
					
				} else {
					
					JOptionPane.showMessageDialog(this, "ɾ��ʧ��!");
					
				}
				
			} else{
				
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫɾ������!");
				return;
			}
		
		}
		
	}
	
	//�ú�����ÿ�θ�������ģ��ʱ�Զ����� ����table.getSelectedRow()�᷵��-1  ע�Ⱑ!!!!!!
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		this.getSelectRowDate(bookTable.getSelectedRow());
		
	}



}