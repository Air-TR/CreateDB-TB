package cn.tr.mysql.utils;


import java.io.IOException;

/**
 * 自动建库工具类
 * 
 * @author taorun
 * @date 2017年7月11日 下午1:52:08
 *
 */

public class CreateDBUtil {
	
    public static void main(String[] args) {
    	createDataBase();
	}
    
    /**
     * 自动建库
     * 参考：http://blog.csdn.net/runming56/article/details/35985479
     * 
     * @author taorun
     */
    public static void createDataBase() {
    	String databaseName = "create_database";
    	String uname = "root";
    	String password = "root";
    	String host = "127.0.0.1";
    	int port = 3306;
    	String cmd = String.format("/usr/local/mysql/bin/mysqladmin -u%s -p%s -h%s -P%s create %s --default-character-set=utf8",
    			uname,password,host,port,databaseName);
    	String[] cmdarray = new String[] { "/bin/sh", "-c", cmd};
    	try {
    		// 方式一
    		Runtime.getRuntime().exec(cmdarray);
    		
    		// 方式二（可不用，方式一走不通可打开试一试）
//    		Process process = Runtime.getRuntime().exec(cmdarray);
//    		LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));  
//    		StringBuffer sb = new StringBuffer();  
//    		String line;
//    		while ((line = br.readLine()) != null) {  
//    			System.out.println(line);  
//    			sb.append(line).append("\n");  
//    		}  
//    		System.out.println(sb.toString());
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
