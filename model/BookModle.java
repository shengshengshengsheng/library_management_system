package model;

import java.util.Vector;
import javax.swing.table.*;
import Dao.Dao;

public class BookModle extends AbstractTableModel{

	/**
	 * 图书的table数据模型
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] columnName=null;
	private Vector<Object> rowDate=null;
	
	public BookModle(Book book){

		//得到共用多少种类型的书
		rowDate=Dao.getBooks(book);
		//得到列名
		columnName=Dao.getColumnName();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnName[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowDate.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnName.length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		return ((Vector<String>)rowDate.get(rowIndex)).get(columnIndex);
	}

}
