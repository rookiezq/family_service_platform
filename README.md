# 和家云

## 数据库设计

这里的需求分析主要是针对的是**数据库设计**，按传统的数据库设计思想和规则来说，应该是遵照**三范式**，保证数据不冗余。

但是随着表越来越多，越来越大，表与表之间的关联就变得特别复杂，所以如果使用**传统的外键**来做表关联，那么无论是在设计表还是开发的时候都会很繁琐。

最重要的一个问题是，随着表关联多了，查询速度一定会很受影响。所以目前的策略是用**时间换取空间**。

按照目前流行的数据库设计思想，可以总结为叫**反范式**，所有的表都是单表，无外键关联，或者极少的关联，表关联可以放到**代码业务层**进行实现。

## 快速启动

git clone当前项目

**数据库创建**

1. 创建一个名为`family_service_platform`的数据库
2. 执行后端项目目录下的`family_service_platform.sql`

修改数据库配置`resources/application.yaml`

直接主类启动

## 前端项目

本项目是前后端分离的，前端项目使用的是[Ant Design](https://ant.design/index-cn)+[Vue](https://cn.vuejs.org/index.html)实现的，下面简单介绍一下该项目。

### 快速启动

在项目目录`property-server-manage-master`下执行

- 安装依赖

    ```shell
    npm install
    ```

- 开发模式运行

    ```shell
    npm run serve
    ```

- 编译项目

    ```shell
    npm run build
    ```

- Lints and fixes files

    ```shell
    npm run lint
    ```

其他更多介绍参考前端各个目录下的`README`



> 下面开始后端项目

## 创建后端项目

项目名为`family_service_platform`，注意包名

![image-20210806185007461](https://demon-1258469613.cos.ap-shanghai.myqcloud.com/img/image-20210806185007461.png)

选择下面四个组件

![image-20210806184221505](https://demon-1258469613.cos.ap-shanghai.myqcloud.com/img/image-20210806184221505.png)

### 代码生成器

使用mybatis-plus生成实体类、映射文件、service、controller等

导入依赖

```xml
<!--mybatis代码快速生成-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.3.1</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.1</version>
</dependency>
<!--模板引擎，默认是velocity-->
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
    <version>2.2</version>
</dependency>
```

生成器

```java
public class MybatisPlusGenerator {

    @Test
    public void generate() {

        GlobalConfig globalConfig = new GlobalConfig();
        //代码输出路径
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
```

### 配置文件

将上面生成在`mapper/xml`下所有的xml文件放到`resources/com/rookied/mapper`下面

配置文件改为application.yaml

==注意==：serverTimezone=GMT%2B8代表东八区，否则插入时间时会少8小时

```yaml
#项目端口
server:
  port: 8080

#数据源
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/family_service_platform?useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: root

#配置mybatis
mybatis:
  mapper-locations: classpaths:com/rookied/mapper/*.xml
  configuration:
    map-underscore-to-camel-case: true

#log4j sql语句打印
logging:
  level:
    com:
      rookied:
        mapper: debug
```

这里还没加上log4j，等待会用到再加

### 项目启动

这个时候如果直接启动，会报错

```java
Error creating bean with name 'fcBuildingServiceImpl': Unsatisfied dependency expressed through field 'baseMapper'
```

原因是对应的mapper没加注解，导致service无法装配它，两种方法解决

1. 启动类加上mapper扫描（可以批量）

    ==注意==：这里必须指定扫描的包名，否则会报错，具体原因查看[@MapperScan导致的Service方法无法注入](./module/楼盘管理.md/#@MapperScan导致的Service方法无法注入)

    ```java
    @SpringBootApplication
    @MapperScan("com.rookied.mapper")
    public class FamilyServicePlatformApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(FamilyServicePlatformApplication.class, args);
        }
    
    }
    ```

2. 对应的mapper接口加上@Mapper（必须一个个加）

    ```java
    @Mapper
    public interface FcBuildingMapper extends BaseMapper<FcBuilding> {
    
    }
    ```

再次启动，成功，可以写一个controller测试一下请求是否成功。

到此为止，所有的环境准备工作完成，下面开始进一步开发。

## 前后端通信

以前的前后端当在同一个项目中启动时，前端请求后端不存在问题。

而现在前后端分离，存在**跨域请求**的问题。

### 前端

前端登录按钮请求顺序可以根据如下文件进行查看

Login.vue-user.js-login.js-request.js-mock/index.js

#### 修改开发环境

.env.development

```
NODE_ENV=development
VUE_APP_PREVIEW=false
VUE_APP_API_BASE_URL=http://localhost:8080/
```

注释掉`src/main.js`中的`import './mock'`，这样就可以保证请求不是请求的mock的模拟数据

修改完成查看请求是否变成了后端项目地址http://localhost:8080/，如果是，则表示前端环境修改完成。

### 后端

后端只要加一个**跨域请求**的配置即可，以下两种方式都可以

1. config/CorsConfig.java

    ```java
    @Configuration
    public class CorsConfig {
        private CorsConfiguration buildConfig() {
            CorsConfiguration config = new CorsConfiguration();
            //允许的协议+ip+端口 *表示所有
            //注意 localhost != 127.0.0.1
            config.addAllowedOrigin("*");
            //跨域请求头
            config.addAllowedHeader("*");
            //跨域请求方法 get put delete post
            config.addAllowedMethod("*");
            //这一句，表示可以带cookie，最终可以在跨域请求的时候获取同一个session
    		//如果不加这一句，请求能进来，创建session，响应后会创建cookie，但是响应结果无法获取
            config.setAllowCredentials(true);
            return config;
        }
    
        @Bean
        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            //配置 可以访问的地址 必须是/** 不可以是/*
            source.registerCorsConfiguration("/**", buildConfig());
            return new CorsFilter(source);
        }
    }
    ```

2. @CrossOrigin

    ```java
    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.POST})
    public class LoginController {
    
        @RequestMapping("/auth/login")
        public String login() {
            System.out.println("login");
            return "login";
        }
    }
    ```

    如果两个同时使用，全局配置优先级更高，意味着即使注解会不起作用

    

    如果控制台成功打印login，就表示能请求成功，前后端项目完成了通信。





