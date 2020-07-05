
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import java.util.Vector;

 

import javax.swing.JOptionPane;

 

public class PutinStorage {

	// �õ����ݿ������

	public static Vector getRows(){

		String sql_url = "jdbc:mysql://127.0.0.1:3306/myfiletra?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8";	//���ݿ�·����һ�㶼������д����test�����ݿ�����

		String name = "root";		//�û���

		String password = "123456";	//����

		Connection conn;

		
		PreparedStatement preparedStatement = null;

 

		Vector rows = null;

		Vector columnHeads = null;

		

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");	//��������

			conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�

//			if(!conn.isClosed())
			System.out.println("sussesful mysql");

			preparedStatement = conn.prepareStatement("select filename,filesize from file");
            //������ֵ
			ResultSet result1 = preparedStatement.executeQuery();

			

//			if(result1.wasNull())
//
//				JOptionPane.showMessageDialog(null, "NO RESULT");
//
//

			rows = new Vector();

			

			ResultSetMetaData rsmd = result1.getMetaData();

					

			while(result1.next()){

				rows.addElement(getNextRow(result1,rsmd));

			}

			

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block

			System.out.println("FAILED DRIVEN MYSQL");

			e.printStackTrace();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			System.out.println("FAILED FAILED MYSQL");

			e.printStackTrace();

		}

		return rows;

	}

	

	// �õ����ݿ��ͷ

	public static Vector getHead(){

		String sql_url = "jdbc:mysql://127.0.0.1:3306/myfiletra?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8";	//���ݿ�·����һ�㶼������д����test�����ݿ�����

		String name = "root";		//�û���

		String password = "123456";	//����

		Connection conn;

		PreparedStatement preparedStatement = null;

 

		Vector rows = null;

		Vector columnHeads = null;

		

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");		//��������

			conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�

//			if(!conn.isClosed())

			preparedStatement = conn.prepareStatement("select distinct filename,filesize from file");
            //������ֵ
			ResultSet result1 = preparedStatement.executeQuery();

			

			boolean moreRecords = result1.next();

			if(!moreRecords)

				JOptionPane.showMessageDialog(null, "NO RESULT");

			

			columnHeads = new Vector();

			ResultSetMetaData rsmd = result1.getMetaData();

			for(int i = 1; i <= rsmd.getColumnCount(); i++)

				columnHeads.addElement(rsmd.getColumnName(i));

			

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block

			System.out.println("FAILED DRIVEN MYSQL");

			e.printStackTrace();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			System.out.println("FAILED OPEN MYSQL");

			e.printStackTrace();

		}

		return columnHeads;

	}

	

	// �õ����ݿ�����һ������

	private static Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{

		Vector currentRow = new Vector();

		for(int i = 1; i <= rsmd.getColumnCount(); i++){

			currentRow.addElement(rs.getString(i));

		}

		return currentRow;

	}

	

	/*//������

	 public static void main(String[] args){

		 getRows();

	}*/

}
