package bangumi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MybaseInsert {
    public static void insert(List<Anime> animes){
        System.out.println("正在往数据库里添加数据...");
        String sql = "insert into animes values(NULL ,?,?,?,?,?)";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement p = c.prepareStatement(sql);){
            for(Anime a:animes){
                p.setString(1,a.getName());
                p.setString(2,a.getOldName());
                p.setString(3,a.getMessage());
                p.setDouble(4,a.getRating());
                p.setInt(5,a.getNumber());
                p.execute();
            }
            System.out.println("添加完毕！");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
