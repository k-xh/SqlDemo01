package build;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
public class DBUtill implements SqlDao{
	
	final private String url="jdbc:mysql://localhost:3306/jspdemo?"+
	"serverTimezone=UTC&useSSL=false";
	final private String user="root";
	final private String pwd="123456";
	final private String driver="com.mysql.cj.jdbc.Driver";
	Connection conn;
	public static void main(String[] args) {
		new DBUtill();
	}
	
	public DBUtill(){
		try {
			//������
			Class.forName(driver);
			//�������ݿ�
			conn=DriverManager.getConnection(url,user,pwd);
			//Ԥ����sql���
//			PreparedStatement stmt=conn.prepareStatement(
//					"select * from logintable");
//			Map<String,String> values=new HashMap<>();
//			values.put("username"," 356");
//			values.put("password"," 123");
////			stmt.setString(1,"356");
//			ResultSet rs=stmt.executeQuery();
//			while(rs.next()) {
//				System.out.println(rs.getString("username"));
//				System.out.println(rs.getString("password"));
//			}
			String Sql="insert into logintable values(?,?),(?,?)";
			int resultint=this.insertexecute(Sql, new String[] {"360","134","362","321"});
			System.out.println(resultint);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet selectexecute(String Sql, Map<String, String> values) {
		PreparedStatement stmt;
		try {
			//Sql='select * from logintable where username=?'
//			stmt = conn.prepareStatement(Sql);
			StringBuffer stb=new StringBuffer(Sql);
			if(values != null) {
				stb.append(" where ");
				boolean flag=true;
				for(Map.Entry<String,String> entry:values.entrySet()) {
					if(flag) {
						flag=false;
						stb.append(entry.getKey()+"='"+entry.getValue()+"' ");
					}
					stb.append("and "+entry.getKey()+"='"+entry.getValue()+"' ");
				}
			}
			stmt=conn.prepareStatement(Sql);
//			stmt.setString(1, "356");
			return stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//		while(rs.next()) {
//			System.out.println(rs.getString("username"));
//			System.out.println(rs.getString("password"));
//		}
	}

	public int insertexecute(String Sql, String[] arg) {
		PreparedStatement stmt;
		try {
			//Sql='insert into logintable values=(?,?)'
			stmt = conn.prepareStatement(Sql);
			for(int i;i<arg.length;i++) 
				stmt.setString(1, arg[1]);
			//�޸ĺͲ���ʹ��executeUpdate()
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertexecute(String Sql, Map<String, String> values) {
		// TODO Auto-generated method stub
		return 0;
	}
}
