package cn.tr.mysql.utils;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 读取执行SQL文件工具类（用于建表等数据库操作）
 * 
 * @author taorun
 * @date 2017年7月11日 下午2:08:18
 *
 */

public class ReadSqlUtil {
	
	
	
	public static void main(String[] args) {
		readSql();
	}
	
	/**
	 * 读取执行SQL文件
	 * @author taorun
	 */
	public static void readSql() {
		try {
			InputStream in = ReadSqlUtil.class.getResourceAsStream("/init.sql"); // 读取SQL文件
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int buf_size = 2048;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer,0,buf_size))) {
                bos.write(buffer,0,len);
            }
            String sql = new String(bos.toByteArray(),"UTF-8");
            String[] strings = sql.split(";");
            Connection con = null; // 数据库连接对象
    		Statement stmt = null;
            Class.forName("com.mysql.cj.jdbc.Driver"); // 1、加载数据库驱动程序
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/create_database?characterEncoding=utf8&useSSL=true","root","root"); // 2、连接数据库
            stmt = con.createStatement(); // 3、Statement 接口需要通过Connection 接口进行实例化操作
            for (int i = 0; i < strings.length - 1; i++) {
            	stmt.execute(strings[i]);
			}
    		con.close(); // 4、关闭数据库
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
