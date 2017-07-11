package cn.tr.mysql.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 自动建库和建表
 * 
 * @author taorun
 * @date 2017年7月11日 下午2:44:05
 *
 */

public class CreateDBAndTBUtil {

	public static void main(String[] args) {
		createDatebaseAndTable("test");
	}

	/**
	 * 建库和建表
	 * 
	 * @param tenantId
	 */
	public static void createDatebaseAndTable(String tenantId) {
		// 建库
		String databaseName = "fzjx_" + tenantId;
		String uname = "root";
		String password = "root";
		String host = "127.0.0.1";
		int port = 3306;
		String cmd = String.format(
				"/usr/local/mysql/bin/mysqladmin -u%s -p%s -h%s -P%s create %s --default-character-set=utf8", uname,
				password, host, port, databaseName);
		String[] cmdarray = new String[] { "/bin/sh", "-c", cmd };
		try {
			Runtime.getRuntime().exec(cmdarray);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// 建表
		try {
			InputStream in = CreateDBAndTBUtil.class.getResourceAsStream("/init.sql");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int buf_size = 2048;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			String sql = new String(bos.toByteArray(), "UTF-8");
			String[] strings = sql.split(";");
			Connection con = null; // 数据库连接对象
			Statement stmt = null;
			Class.forName("com.mysql.jdbc.Driver"); // 1、使用CLASS 类加载驱动程序
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, "root", "root"); // 2、连接数据库
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
