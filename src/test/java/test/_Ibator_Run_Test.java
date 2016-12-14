package test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Config;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class _Ibator_Run_Test {

    private final static Log logger = LogFactory.getLog(_Ibator_Run_Test.class);

    public static void main(String[] args) throws Exception {
        readFile();
//
        _Ibator_Run_Test test = new _Ibator_Run_Test();
        test.main1("grid1.xml");
        // test.main1("mysql.xml");
        // test.main1("oracle.xml");
    }

    private static void readFile() throws Exception {
        String filePath = "D:\\work\\java\\idea\\workspace 2016\\mybatis-generator-core\\src\\test\\resources\\generator.properties";

        InputStream inputStream = new FileInputStream(new File(filePath));
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException ioE) {
            ioE.printStackTrace();
        } finally {
            inputStream.close();
        }

        //get set方法
        Boolean getSetMethodFlag = Boolean.parseBoolean(properties.get("generator.get.set.method.flag").toString());
        //get set注释
        Boolean getSetCommentsFlag = Boolean.parseBoolean(properties.get("generator.get.set.comments.flag").toString());
        //字段注释
        Boolean fieldCommentsFlag = Boolean.parseBoolean(properties.get("generator.field.comments.flag").toString());
        //字段注解
        Boolean fieldAnnotationFlag = Boolean.parseBoolean(properties.get("generator.field.annotation.flag").toString());
        //lombok 是否引入
        Boolean lombokImport = Boolean.parseBoolean(properties.get("generator.lombok.import.flag").toString());
        //java   引入
        Boolean javaImport = Boolean.parseBoolean(properties.get("generator.java.import.flag").toString());
        //表名字注释
        Boolean tableAnnotation = Boolean.parseBoolean(properties.get("generator.table.annotation.flag").toString());

        logger.debug("是否添加getset方法:" + getSetMethodFlag);
        logger.debug("是否添加getset注释:" + getSetCommentsFlag);
        logger.debug("是否添加字段注释:" + fieldCommentsFlag);
        logger.debug("是否添加字段注解:" + fieldAnnotationFlag);
        logger.debug("是否添加lombok引入:" + lombokImport);
        logger.debug("是否添加java引入:" + javaImport);
        logger.debug("是否添加表名字注释:" + tableAnnotation);
        Config.fieldAnnotationFlag = fieldAnnotationFlag;
        Config.fieldCommentsFlag = fieldCommentsFlag;
        Config.getSetMethodFlag = getSetMethodFlag;
        Config.getSetCommentsFlag = getSetCommentsFlag;
        Config.lombokImport = lombokImport;
        Config.javaImport = javaImport;
        Config.tableAnnotation = tableAnnotation;

    }

    public void main1(String fileName) {
        try {
            List<String> warnings = new ArrayList<String>();
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(this.getClass().getClassLoader().getResourceAsStream(fileName));

            DefaultShellCallback shellCallback = new DefaultShellCallback(true);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            logger.error("Exception:", e);
        }
    }

}
