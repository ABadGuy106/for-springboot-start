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


  
  
  


