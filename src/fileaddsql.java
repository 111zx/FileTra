import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fileaddsql {
    /**
     * 服务端数据库内文件的存储
     * @param name 文件名
     * @param size 文件大小
     * @param path 文件路径
     */
    public void fileAdd(String name, long size, String path) {
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.得到链接 127.0.0.1:1433
            ct = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myfiletra?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8","root","123456");
            String sql = "insert into file values(?,?,?)";
            ps = ct.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, String.valueOf(size));
            ps.setString(3, path);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (ct != null) ct.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 服务端数据库内文件的删除
     * @param name 文件名
     */
    public void fileRremove(String name) {
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.得到链接 127.0.0.1:1433
            ct = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myfiletra?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8","root","123456");
            String sql = "delete from file where filepath = ?";
            ps = ct.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (ct != null) ct.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
