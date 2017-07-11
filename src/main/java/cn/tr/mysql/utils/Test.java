package cn.tr.mysql.utils;

/**
 * 测试自动建库和建表
 * 
 * @author taorun
 * @date 2017年7月11日 下午2:45:50
 *
 */

public class Test {
	
	public static void main(String[] args) {
		
		// hhh
		
		// 建库
		CreateDBUtil.createDataBase();
		
		// 建表
		ReadSqlUtil.readSql();
		
	}

}
