import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
	private String URL;
	private Driver DRIVER;
	public static boolean connection = false;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	// �����Ʈw�s����FUNCTION�ݶǤJIP(���}),PORT(��),USERNAME(�ϥΪ̦W��),PASSWORD(�K�X),DBNAME(��Ʈw�W��),NODE(�s�����ظ�Ʈw)
	public void connect(String IP, String PORT, String USERNAME,
			String PASSWORD, String dbName, String MODE) {
		try {
			// �P�_�s�����P����Ʈw�ϥΤ��P�Ҧ�
			if (MODE.equals("MySQL")) {
				// �s��MySQL���JMySQL�X��
				DRIVER = new com.mysql.jdbc.Driver();
				// MySQL��URL�Φ�
				String url = "jdbc:mysql://";
				URL = url + IP + ":" + PORT + "/" + dbName
						+ "?userUnicode=true";
			} else if (MODE.equals("MSSQL")) {
				// �s��MSSQL���JMSSQL�X��
				DRIVER = new com.microsoft.sqlserver.jdbc.SQLServerDriver();
				// MSSQL��URL�Φ�
				String url = "jdbc:sqlserver://";
				URL = url + IP + ":" + PORT + ";databaseName=" + dbName
						+ ";user=" + USERNAME + ";password=" + PASSWORD + ";";
			}
			// ���U�X��
			DriverManager.registerDriver(DRIVER);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			// �]�w�s����Ʈw(�ǤJURL�A�ϥΪ̦W�ٸ�K�X)
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = con.createStatement();
			System.out.println("�s�u���\");
			connection = true;

		} catch (SQLException e) {
			// ��Ʈw�s������
			System.out.println("�s�u����");
			e.printStackTrace();
		}
	}

	public void setNoResponseQuery(String query) {
		try {
			// �]�w��Ʈw�P�k���O�O�S���^�ǭȪ�(�pinset�Bdelete)
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setResponseQuery(String query) {
		try {
			// �]�w��Ʈw�P�k���O�O���^�ǭȪ�(�pselect)
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ���o�^�ǭ�
	public ResultSet getrs() {
		return rs;
	}

	// ���o�s�u���A
	public Boolean getConnection() {
		return connection;
	}

	// �_�}��Ʈw�s�u
	public void disConnect() {
		try {
			DriverManager.deregisterDriver(DRIVER);
			connection = false;
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			System.out.println("�_�}�s�u");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
