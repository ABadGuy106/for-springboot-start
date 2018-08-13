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
    这个最终的父项目是真正管理SpringBoot应用里面的所有依赖；
    称为Spring Boot的版本仲裁中心;
    导入依赖不需要写版本号，如果没有dependencies里边管理的依赖需要声明版本号
    Spring Boot Web依赖
    <dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    spring-boot-starter-web：
        spring-boot-starter：spring-boot场景启动器；
        spring-boot-starter-web：包含了web模块正常运行所依赖的组件
        
