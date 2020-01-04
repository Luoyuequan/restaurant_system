package com.system.backgroundmanagement;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author luoyuequan
 * @time 2020/1/4 12:46
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerate {
    //entity后缀
    static String entitySuffix = "";
    //dao层接口和对应的xml文件后缀
    static String daoSuffix = "Dao";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/front-management";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("luoyuequan");
        //实体命名方式
        //默认值：null 例如：%sEntity 生成 UserEntity
        gc.setEntityName("%s" + entitySuffix);
        //mapper 命名方式
        //默认值：null 例如：%sDao 生成 UserDao
        gc.setMapperName("%s" + daoSuffix);
        //Mapper xml 命名方式
        //默认值：null 例如：%sDao 生成 UserDao.xml
        gc.setXmlName("%s" + daoSuffix);

        //指定生成的主键的ID类型
        //默认值：null
        gc.setIdType(IdType.AUTO);
        //是否打开输出目录
        gc.setOpen(false);
//        设置二级缓存
        gc.setEnableCache(true);
        //覆盖已存在的文件
        gc.setFileOverride(true);
//        开启 BaseResultMap
        gc.setBaseResultMap(true);
//        开启 baseColumnList
        gc.setBaseColumnList(true);

        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/restaurant_system?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=true");
        // dsc.setSchemaName("public");
        //配置数据库类型
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("rootadmin");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //分布式时候用
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.system.frontmanagement");
        //mapper ，dao层 包名
        pc.setMapper("dao");
        //xml包名
//        pc.setXml()
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ，
                return projectPath + "/src/main/resources/mapper/" + (pc.getModuleName() == null ? "" : pc.getModuleName())
                        //实体类后缀截取掉
                        + "/" + tableInfo.getEntityName().replace(entitySuffix, "") + daoSuffix + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        //乐观锁属性名称
        strategy.setVersionFieldName("updateTime");
        //boolean类型字段是否移除is前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        //设置逻辑删除属性字段
        strategy.setLogicDeleteFieldName("delete");
        //表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
