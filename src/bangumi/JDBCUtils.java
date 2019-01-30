package bangumi;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
JDBC的工具类，获取数据库的连接
采用读取配置文件的方式
读取文件获取连接，执行一次
 */
public class JDBCUtils {
    private static Connection con;
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            readConfig();

            Class.forName(driverClass);
            //使用配置文件的参数连接数据库
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException("数据库连接失败");
        }
    }

    private static void readConfig() throws Exception {
        InputStream in =
                JDBCUtils.class.getClassLoader().getResourceAsStream("database.properties");
        Properties pro = new Properties();
        pro.load(in);


        driverClass = pro.getProperty("driverClass");
        url = pro.getProperty("url");
        username = pro.getProperty("username");
        password = pro.getProperty("password");


    }

    public static Connection getConnection() {
        return con;
    }
}
