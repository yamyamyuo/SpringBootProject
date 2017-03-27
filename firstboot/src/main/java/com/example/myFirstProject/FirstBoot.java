package com.example.myFirstProject;
  
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @EnableAutoConfiguration:spring boot的注解，一般只用于主类，
 * 是无xml配置启动的关键部分,明确指定了扫描包的路径为其修饰的主类的包（这也就是为什么主类要放在根包路径下的原因）
 *
 * @ComponentScan 进行包的扫描，扫描路径由@EnableAutoConfiguration指定了
 *
 * 主类要位于根包路径下，方便之后的扫描(We generally recommend that you locate your main application class in a root package above other classes.)
 */

@SpringBootApplication        //same as @Configuration+@EnableAutoConfiguration+@ComponentScan

/**
 * 在主类上添加注解@SpringBootApplication，该注解相当于添加了如下三个注解
        @Configuration：该注解指明该类由spring容器管理
        @EnableAutoConfiguration：该注解是无xml配置启动的关键部分
        @ComponentScan：该注解指定扫描包（如果主类不是位于根路径下，这里需要指定扫描路径），类似于spring的包扫描注解
 */
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableSwagger2 //启动swagger注解
@EnableAsync
public class FirstBoot {

    public static void main(String[] args) {
        SpringApplication.run(FirstBoot.class, args);
    }
}