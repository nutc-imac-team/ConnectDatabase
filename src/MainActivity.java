import java.sql.SQLException;

public class MainActivity {
	public static void main(String[] args) {

		// �N�Ҳ�new�X��
		ConnectDatabase CM = new ConnectDatabase();
		// ��JIP�Bport�B�b���B�K�X�B�s������Ʈw�W�١B�s�����ظ�Ʈw
		CM.connect("10.21.10.14", "3306", "tranews06", "tranews06",
				"connecttest", "MySQL");
		// ��J�n���檺�y�k(�j�M���)
		CM.setResponseQuery("select * from new");
		try {
			// ���o�j�M�����G
			while (CM.getrs().next()) {
				System.out.println(CM.getrs().getString("TEST"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		// �N�Ҳ�new�X��
		Connect CM = new Connect();
		// ��JIP�Bport�B�b���B�K�X�B�s������Ʈw�W�١B�s�����ظ�Ʈw
		CM.connect("10.21.10.14", "3306", "tranews06", "tranews06",
				"connecttest", "MySQL");
		// ��J�n���檺�y�k(�s�W���)
		CM.setNoResponseQuery("Insert into new (TEST,TEST2) VALUES (5,5)");
		*/
		
		/*
		Connect CM = new Connect();
		// ��JIP�Bport�B�b���B�K�X�B�s������Ʈw�W�١B�s�����ظ�Ʈw
		CM.connect("10.21.10.16", "1400", "su", "123456",
				"test", "MSSQL");
		// ��J�n���檺�y�k(�j�M���)
		CM.setResponseQuery("select * from test");
		try {
			// ���o�j�M�����G
			while (CM.getrs().next()) {
				System.out.println(CM.getrs().getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		/*
		Connect CM = new Connect();
		// ��JIP�Bport�B�b���B�K�X�B�s������Ʈw�W�١B�s�����ظ�Ʈw
		CM.connect("10.21.10.16", "1400", "su", "123456",
				"FlowBak", "MSSQL");
		// ��J�n���檺�y�k(�s�W���)
		CM.setNoResponseQuery("Insert into test (Name,sex) values(7,8)");
		CM.disConnect();*/
	}
}
