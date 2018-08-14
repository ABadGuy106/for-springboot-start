# Spring Boot
## 1、Spring Boot简介
简化Spring应用开发的一个框架；<br>
整个Spring技术的一个大整合；<br>
J2EE开发的一站式解决方案；<br>
## 2、微服
微服是一种架构风格<br>
一个应用应该是一组小型服务，可以通过HTTP方式进行互通；<br>
每个功能元素最终都是一个可以替换和独立升级的软件单元；
## 3、SpringBoot POM文件解析

    <parent>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-parent</artifactId>
    	<version>2.0.4.RELEASE</version>
    	<relativePath/> 
    </parent>
父项目的父项目是：

    <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.0.4.RELEASE</version>
            <relativePath>../../spring-boot-dependencies</relativePath>
    </parent>
   这个最终的父项目是真正管理SpringBoot应用里面的所有依赖；<br>
   称为Spring Boot的版本仲裁中心;<br>
   导入依赖不需要写版本号，如果没有dependencies里边管理的依赖需要声明版本号<br>
   Spring Boot Web依赖

    <dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-web</artifactId>
    </dependency>
   spring-boot-starter-web：<br>
       spring-boot-starter：spring-boot场景启动器；<br>
       spring-boot-starter-web：包含了web模块正常运行所依赖的组件<br>
    Spring Boot将所有的功能场景都抽取出来，做成一个个的starters（启只动器），
    只需要在项目里引入starter相关的场景，所有的依赖都会被导入进来
## 4、主程序类，主入口类
    @SpringBootApplication
    public class ForSpringbootStartApplication {
    
    public static void main(String[] args) {
    	SpringApplication.run(ForSpringbootStartApplication.class, args);
        }
    }
@SpringBootApplication：Spring Boot应用，这个注解标注了Spring Boot
的主配置类Spring Boot就应该运行该类的main方法启动应用
#####@SpringBootApplication是一个组合注解：
<pre name="code" class="java">
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
</pre>
@SpringBootConfiguration：Spring Boot的配置类，该注解标注了SpringBoot的
配置类，其底层实现时Spring的@Configuration<br>
配置类-----------配置文件；<br>
配置类也是容器中的一个组件<br>
@EnableAutoConfiguration：开启自动配置功能；<br>
在Spring开发中需要手动配置的东西，SpringBoot现在可以自动配置;<br>
SpringBoot开启自动配置功能，这样自动配置才能生效
<pre name="code" class="java">
 @AutoConfigurationPackage
 @Import({AutoConfigurationImportSelector.class})
 public @interface EnableAutoConfiguration {
 </pre>
  @AutoConfigurationPackage:自动配置包<br>
  @Import({AutoConfigurationImportSelector.class})<br>
  Spring的底层注解@Import实现，给容器导入一个组件；<br>
  导入的组件由AutoConfigurationImportSelector.class<br>
  @AutoConfigurationPackage作用是：将主配置类（@SpringBootApplication标注的类）的所在包及下边所有的子包
  里的所有组件扫描到容器中<br>
  @Import({AutoConfigurationImportSelector.class})<br>
  给容器中导入组件<br>
  AutoConfigurationImportSelector:导入组件选择器;<br>
将所有需要导入的组件以全类名的方式返回，这些组件就会被添加到容器中<br>
会给容器中导入非常多的自动配置类（XXXAutoConfiguration）;<br>
给容器中导入这个场景需要的组件，并配置好这些组件<br>
有了自动配置类，就免去了我们手动编写配置注入功能组件等的工作<br>
SpringBoot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值
，将这些值作为自动配置类导入到容器中,自动配置类就生效了<br>
## 配置文件
SpringBoot使用一个全局的配置文件，配置文件的名称是固定的:<br>
application.properties<br>
application.yml<br>
配置文件的作用：修改SpringBoot自动配置的默认值；<br>
yml是YAML（YAML Ain't Markup Language）语言的文件，以数据问中心，
比json、xml等更合适做配置文件<br>
## YAML语法
### 基本语法
k:(空格)v——表示一对键值对（空格必须有）；<br>
以空格的缩进来控制层级关系；<br>
只要是左对齐的一列数据，都是在同一个层级的
### 值的写法
字面量：普通的值（数字，字符，布尔）<br>
k: v  字面量直接来写，字符串默认不用加单引号或这双引号<br>
""-双引号：不会转义字符串里的特殊字符，特殊字符会作为本身表示<br>
例如：name: "zhanngsan\n lisi" ： 输出:zhangsan 换行 lisi<br>

‘’-单引号：会转义特殊字符，特殊字符最终只是一个普通的字符串<br>
例如：name: 'zhanngsan\n lisi' ： 输出:zhangsan \n lisi
<br>
对象（属性和值）（键值对）：<br>
k: v-在下一行来写对象的属性<br>
  对象的还是k: v的方式<br>
  例如：<br>
<pre name="code" class="js">
  friends:
    lastName:zhansan
    age:20
</pre>
行内写法：
<pre name="code" class="js">
frineds:{lastName:zhangsan,age:18}
</pre>

数组（List、Set）<br>
用 -(空格)值 表示数组中你那个的一个元素<br>

<pre name="code" class="js">
    pets:
        - cat
        - dog
        - pig
</pre>
行内写法：
<pre name="code" class="js">
pets:[cat,dog,pig]
</pre>
### SpringBoot配置注解
<pre name="code" class="js">
@ConfigurationProperties(prefix="前缀")

@Value("")
</pre>
如果在某项业务逻辑中只需要回去一下配置文件中的某个值，使用@Value；
如果专门编写了一个JavaBean来和配置文件进行映射，就直接直接使用@ConfigurationProperties
<br>
@PropertySource:加载指定的配置文件；
<br>
@ImportResource:导入Spring的配置文件，让配置文件里面的内容生效；

## Profile
Profile是Spring对不同环境提供不同配置功能的支持，可以通过指定激活参数等方式快速切换环境
<br>
1、多profile文件形式：<br>
    -格式：application-{profile}.properties:
    <br>
    application-dev.properties
    <br>
    application-prod.properties<br>
2、多profile文档模式：<br>
3、激活方式：<br>
    -命令 --spring.profiles.active=dev<br>
    -配置文件 spring.profiles.active=dev<br>
    -jvm参数 -Dspring.profiles.active=dev<br>
### a、多profile文件
在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml
<br>
默认使用application.properties的配置<br>


### c、激活
1、在配置文件中指定 psring.profiles.active=dev
## 配置文件加载位置
springboot启动会扫描以下位置的applicatio.properties或者application.yml文件作为Spring Boot
的默认配置文件<br>
- file:./config/<br>
- file:./<br>
- classpath:/config/<br>
- classpath:/<br>
以上是按优先级从高到低的顺序，所有位置的文件都会被加载，高优先级配置的内容会覆盖低优先级配置的内容
<br>
也可以通过配置spring.config.location来改变默认配置
##SpringBoot日志框架
如何让系统中所有的日志都统一到slf4j：<br>
1、将系统中其他日志框架排除出去<br>
2、用中间包来替换原有的日志框架<br>
3、导入slf4j其他的实现<br>
如果在springboot中要引入其他
框架，一定要把这个框架默认的日志依赖排除掉
<br>
例如：<br>

    <dependency>
        <groupId>net.sourceforge.htmlunit</groupId>
        <artifactId>htmlunit</artifactId>
        <version>${htmlunit.version}</version>
        <exclusions>
            <exclusion>
                <artifactId>commons-logging</artifactId>
                <groupId>commons-logging</groupId>
            </exclusion>
        </exclusions>
    </dependency>

SpringBoot能自动适配所有日志框架，而且底层使用slf4j+logback
的方式记录日志，引入其他框架时，只需要把这个框架依赖的日志框架排除掉
<br>
SpringBoot默认已经配置好了日志框架
##Web开发






  



