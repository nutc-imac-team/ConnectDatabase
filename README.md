# ConnectDatabase
讓使用者選擇連接MySQL或MSSQL



##使用說明


```
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
```



##程式碼說明

* [讓使用者輸入要連接的是MySQL或者是MSSQL](##讓使用者輸入要連接的是MySQL或者是MSSQL)

* [不一樣的資料庫連接要載入不一樣的驅動](##不一樣的資料庫連接要載入不一樣的驅動)

* [讓使用者輸入IP、PORT、資料庫名稱、資料表名稱、帳號、密碼](##讓使用者輸入IP、PORT、資料庫名稱、資料表名稱、帳號、密碼)


```
if (MODE.equals("MySQL")) {
        // 連接MySQL載入MySQL驅動
        DRIVER = new com.mysql.jdbc.Driver();
        // MySQL的URL形式
        String url = "jdbc:mysql://";
        URL = url + IP + ":" + PORT + "/" + dbName
            + "?userUnicode=true";
      } else if (MODE.equals("MSSQL")) {
        // 連接MSSQL載入MSSQL驅動
        DRIVER = new com.microsoft.sqlserver.jdbc.SQLServerDriver();
        // MSSQL的URL形式
        String url = "jdbc:sqlserver://";
        URL = url + IP + ":" + PORT + ";databaseName=" + dbName
            + ";user=" + USERNAME + ";password=" + PASSWORD + ";";
      }
      // 註冊驅動
      DriverManager.registerDriver(DRIVER);
    } catch (SQLException e1) {
      e1.printStackTrace();
}

```



* [設定連線](##設定連線)


```
con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    stmt = con.createStatement();
    System.out.println("連線成功");
    connection = true;

  } catch (SQLException e) {
    // 資料庫連接失敗
    System.out.println("連線失敗");
    e.printStackTrace();
  }
```


* [可以讓使用者呼叫的方法但是是沒有回傳值的，如:INSERT、DELETE](##可以讓使用者呼叫的方法但是是沒有回傳值的，如:INSERT、DELETE)


```
public void setNoResponseQuery(String query) {
    try {
      // 設定資料庫與法但是是沒有回傳值的(如inset、delete)
      stmt.execute(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
```


* [可以讓使用者呼叫的方法但是是有回傳值的，如:SELECT](##可以讓使用者呼叫的方法但是是有回傳值的，如:SELECT)


```
public void setResponseQuery(String query) {
    try {
      // 設定資料庫與法但是是有回傳值的(如select)
      rs = stmt.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
```

* [設定與資料庫段開連線](##設定與資料庫段開連線)


```
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
      System.out.println("斷開連線");
    } catch (SQLException e) {
      e.printStackTrace();
    }
```


##完整程式碼

```
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
  private Connection con = null;
  private Statement stmt = null;
  private ResultSet rs = null;

  // 執行資料庫連接的FUNCTION需傳入IP(網址),PORT(埠號),USERNAME(使用者名稱),PASSWORD(密碼),DBNAME(資料庫名稱),NODE(連接哪種資料庫)
  public void connect(String IP, String PORT, String USERNAME,
      String PASSWORD, String dbName, String MODE) {
    try {
      // 判斷連接不同的資料庫使用不同模式
      if (MODE.equals("MySQL")) {
        // 連接MySQL載入MySQL驅動
        DRIVER = new com.mysql.jdbc.Driver();
        // MySQL的URL形式
        String url = "jdbc:mysql://";
        URL = url + IP + ":" + PORT + "/" + dbName
            + "?userUnicode=true";
      } else if (MODE.equals("MSSQL")) {
        // 連接MSSQL載入MSSQL驅動
        DRIVER = new com.microsoft.sqlserver.jdbc.SQLServerDriver();
        // MSSQL的URL形式
        String url = "jdbc:sqlserver://";
        URL = url + IP + ":" + PORT + ";databaseName=" + dbName
            + ";user=" + USERNAME + ";password=" + PASSWORD + ";";
      }
      // 註冊驅動
      DriverManager.registerDriver(DRIVER);
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    try {
      // 設定連接資料庫(傳入URL，使用者名稱跟密碼)
      con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      stmt = con.createStatement();
      System.out.println("連線成功");
      connection = true;

    } catch (SQLException e) {
      // 資料庫連接失敗
      System.out.println("連線失敗");
      e.printStackTrace();
    }
  }

  public void setNoResponseQuery(String query) {
    try {
      // 設定資料庫與法但是是沒有回傳值的(如inset、delete)
      stmt.execute(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void setResponseQuery(String query) {
    try {
      // 設定資料庫與法但是是有回傳值的(如select)
      rs = stmt.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 取得回傳值
  public ResultSet getrs() {
    return rs;
  }

  // 取得連線狀態
  public Boolean getConnection() {
    return connection;
  }

  // 斷開資料庫連線
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
      System.out.println("斷開連線");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
```
