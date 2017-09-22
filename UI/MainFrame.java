package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import model.Operator;
import Dao.Utils;

public class MainFrame extends JFrame implements  ActionListener {
	/**
	 * ������
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menubar;
	private JMenu manageBook, manageOperator, borrowAndReturn,manageScutend;
	private JMenuItem Type, book, addBook, borrowBook, returnBook,scutend,addScutend;
    private Operator user1;
	
	// ��Ļ���
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	// ��Ļ�ĸ߶�
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	public static void main(String[] args) {

		new MainFrame(new Operator());
	}

	public MainFrame(Operator user) {
        user1=new Operator();
        
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        
		menubar = new JMenuBar();

		manageOperator = new JMenu("����Ա����");
		manageOperator.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/manageBookType.png")));
		manageOperator.setFont(Utils.f2);

		

		Type = new JMenuItem("�޸�����");
		Type.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/set.png")));
		Type.setFont(Utils.f2);
		Type.addActionListener(this);
		manageOperator.add(Type);

		manageBook = new JMenu("ͼ�����");
		manageBook.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/manageBook.png")));
		manageBook.setFont(Utils.f2);

		addBook = new JMenuItem("���ͼ��");
		addBook.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/add.png")));
		addBook.setFont(Utils.f2);
		addBook.addActionListener(this);
		manageBook.add(addBook);

		book = new JMenuItem("ͼ��ά��");
		book.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/set.png")));
		book.setFont(Utils.f2);
		book.addActionListener(this);
		manageBook.add(book);

		borrowAndReturn = new JMenu("�軹ϵͳ");
		borrowAndReturn.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/about.png")));
		borrowAndReturn.setFont(Utils.f2);

		borrowBook = new JMenuItem("�軹��");
		borrowBook.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/help.png")));
		borrowBook.setFont(Utils.f2);
		borrowBook.addActionListener(this);
		borrowAndReturn.add(borrowBook);

		returnBook = new JMenuItem("����");
		returnBook.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/aboutUs.png")));
		returnBook.setFont(Utils.f2);
		returnBook.addActionListener(this);
		//borrowAndReturn.add(returnBook);
		
		manageScutend = new JMenu("���߹���");
		manageScutend.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/manageBook.png")));
		manageScutend.setFont(Utils.f2);

		addScutend = new JMenuItem("��Ӷ���");
		addScutend.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/add.png")));
		addScutend.setFont(Utils.f2);
		addScutend.addActionListener(this);
		manageScutend.add(addScutend);

		scutend = new JMenuItem("����ά��");
		scutend.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/set.png")));
		scutend.setFont(Utils.f2);
		scutend.addActionListener(this);
		manageScutend.add(scutend);

		menubar.add(manageOperator);
		menubar.add(manageBook);
		menubar.add(borrowAndReturn);
		menubar.add(manageScutend);
		

		// ������
		

		BgImage bg = new BgImage();
		bg.setBounds(0, 0, width, height - 30);
		
		this.add(bg);
		this.setJMenuBar(menubar);
		this.setTitle("ͼ�����ϵͳ   ��ǰ�û�:" + user.getName());
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader()
				.getResource("images/tittle.png")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height - 30);
		this.setVisible(true);
		try {
			// ʹ��ϵͳ�ָ���ʾ
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
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
	}

	// ����ͼ�ڲ���
	class BgImage extends JPanel {

		private static final long serialVersionUID = 1L;

		Image im = null;

		public BgImage() {
			im = new ImageIcon(this.getClass().getClassLoader()
					.getResource("images/bg2.jpg")).getImage();

		}

		// ��ͼƬ����
		public void paintComponent(Graphics g) {

			g.drawImage(im, 0, 0, width, height, this);
		}
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// �˵�����¼�
		if (e.getSource() == borrowBook) {
			
			new BorrowBook(this, "����ϵͳ", true,user1);
			
		} else if (e.getSource() == Type) {
			
			new ModifyPassword(this, "�޸�����", true,user1);
		} else if (e.getSource() == addBook) {
			
			new AddBook(this, "���ͼ��", true);
			
		} else if (e.getSource() == book) {
			
			new UpdateBook(this, "ͼ��ά��", true);
		} else if (e.getSource() == addScutend) {
			
			new AddScutend(this, "��Ӷ���", true);
		} else if (e.getSource() == scutend) {
			
			new UpdateScutend(this, "����ά��", true);
		}
	}

}
