import java.sql.SQLException;

public class MainActivity {
	public static void main(String[] args) {

		// 將模組new出來
		ConnectDatabase CM = new ConnectDatabase();
		// 輸入IP、port、帳號、密碼、連接的資料庫名稱、連接哪種資料庫
		CM.connect("10.21.10.14", "3306", "tranews06", "tranews06",
				"connecttest", "MySQL");
		// 輸入要執行的語法(搜尋資料)
		CM.setResponseQuery("select * from new");
		try {
			// 取得搜尋的結果
			while (CM.getrs().next()) {
				System.out.println(CM.getrs().getString("TEST"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		// 將模組new出來
		Connect CM = new Connect();
		// 輸入IP、port、帳號、密碼、連接的資料庫名稱、連接哪種資料庫
		CM.connect("10.21.10.14", "3306", "tranews06", "tranews06",
				"connecttest", "MySQL");
		// 輸入要執行的語法(新增資料)
		CM.setNoResponseQuery("Insert into new (TEST,TEST2) VALUES (5,5)");
		*/
		
		/*
		Connect CM = new Connect();
		// 輸入IP、port、帳號、密碼、連接的資料庫名稱、連接哪種資料庫
		CM.connect("10.21.10.16", "1400", "su", "123456",
				"test", "MSSQL");
		// 輸入要執行的語法(搜尋資料)
		CM.setResponseQuery("select * from test");
		try {
			// 取得搜尋的結果
			while (CM.getrs().next()) {
				System.out.println(CM.getrs().getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		/*
		Connect CM = new Connect();
		// 輸入IP、port、帳號、密碼、連接的資料庫名稱、連接哪種資料庫
		CM.connect("10.21.10.16", "1400", "su", "123456",
				"FlowBak", "MSSQL");
		// 輸入要執行的語法(新增資料)
		CM.setNoResponseQuery("Insert into test (Name,sex) values(7,8)");
		CM.disConnect();*/
	}
}
