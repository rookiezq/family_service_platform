package com.rookied;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

/**
 * @author zhangqiang
 * @date 2021/8/6
 */

public class MybatisPlusGenerator {

    @Test
    public void generate() {

        GlobalConfig globalConfig = new GlobalConfig();
        //项目路径
        String path = "/Users/rookied/Desktop/hejiayun/family_service_platform/src/main/java";
        globalConfig.setAuthor("rookied") //设置作者
                .setOutputDir(path)
                .setFileOverride(true) //设置文件覆盖
                .setIdType(IdType.AUTO) //设置主键生成策略
                .setServiceName("%sService") //service接口名称
                .setControllerName("%sController") //controller名称
                .setBaseResultMap(true) //是否生成基本resultMap
                .setBaseColumnList(true); //是否生成所有字段名sql片段

        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/family_service_platform?serverTimezone=UTC")
                .setUsername("root")
                .setPassword("root");

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true) //设置全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) //字段名映射策略 _转为驼峰
                //.setTablePrefix("tbl_") //设置表名前缀
                .setInclude(); //需要包含的表名

        //包名配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.rookied")
                .setEntity("bean")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setXml("mapper.xml");

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        //生成器执行
        autoGenerator.execute();
    }
}
