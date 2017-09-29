package com.zj.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 根据entity生成dao文件
 * @author <a href="1006652872@qq.com">yangxi</a>
 */
public class EntityToDao {
	private static final Logger logger = LoggerFactory.getLogger(EntityToDao.class);
	private static final String COMMON_DAO_PACKAGE_NAME = "com.web.common.dao";
	private static final String DAO_PACKAGE_NAME = "com.web.dao";
	private static final String MODEL_PACKAGE_NAME = "com.web.model";
	
	public static void main(String[] args) {
		addAllDaoAndImpl();
	}



	private static void addAllDaoAndImpl() {
		File file = FileUtil.getFile(getDirByPackage(MODEL_PACKAGE_NAME));
		File[] models = file.listFiles();
		for (File model : models) {
			String modelName = model.getName().replace(".java", "");
			addDaoFile(modelName);
		}
	}
	
	

	private static void addDaoFile(String modelName) {
		String className = "I"+modelName+"Dao";
		String daoDir = getDirByPackage(DAO_PACKAGE_NAME)+File.separator+className+".java";
		String implClassName = "Impl"+modelName+"Dao";
		String implDaoDir = getDirByPackage(DAO_PACKAGE_NAME)+File.separator+"impl"+File.separator+implClassName+".java";
		if(!(new File(daoDir).exists())){
			//add dao
			StringBuffer sb = new StringBuffer();
			addLine(sb, "package "+DAO_PACKAGE_NAME+";");
			addLine(sb, "");
			addLine(sb, "import "+COMMON_DAO_PACKAGE_NAME+".IBaseDao;");
			addLine(sb, "import "+MODEL_PACKAGE_NAME+"."+modelName+";");
			addLine(sb, "");
			addLine(sb, "public interface "+className+" extends IBaseDao<"+modelName+"> {");
			addLine(sb, "");
			addLine(sb, "}");
			FileUtil.save(daoDir, sb.toString().getBytes());
			//add impl dao
			sb = new StringBuffer();
			addLine(sb, "package "+DAO_PACKAGE_NAME+".impl;");
			addLine(sb, "");
			addLine(sb, "import org.springframework.stereotype.Repository;");
			addLine(sb, "import org.springframework.transaction.annotation.Transactional;");
			addLine(sb, "");
			addLine(sb, "import "+COMMON_DAO_PACKAGE_NAME+".impl.ImplBaseDao;");
			addLine(sb, "import "+DAO_PACKAGE_NAME+"."+className+";");
			addLine(sb, "import "+MODEL_PACKAGE_NAME+"."+modelName+";");
			addLine(sb, "");
			addLine(sb, "@Repository");
			addLine(sb, "@Transactional");
			addLine(sb, "public class "+implClassName+" extends ImplBaseDao<"+modelName+"> implements "+className+" {");
			addLine(sb, "");
			addLine(sb, "}");
			FileUtil.save(implDaoDir, sb.toString().getBytes());
			logger.info("add "+ modelName+" dao and ipml success!");
		}
		
	}
	
	public static void addLine(StringBuffer sb,String lineString){
		sb.append(lineString).append("\r\n");
	}
	
	public static String getDirByPackage(String packageName) {
		return System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+packageName.replace(".", File.separator);
	}
}
