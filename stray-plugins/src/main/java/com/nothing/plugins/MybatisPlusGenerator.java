package com.nothing.plugins;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 */
public class MybatisPlusGenerator {
    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入表名，空格隔开");
        String[] input = scanner.nextLine().split(" ");
        ArrayList<String> target = new ArrayList<>();
        for (String s : input) target.add(s);
        generate(target);
    }

    private static void generate(List<String> targets){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/stray_home?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                "root", "20001030") //数据库连接配置
                .globalConfig(builder -> {
                    builder.author("zhuhodor") //作者
                            .enableSwagger() //开启swagger注解
                            .fileOverride()
                            .commentDate("yyyy-MM-dd")
                            .outputDir(System.getProperty("user.dir")+"\\stray-plugins\\src\\main\\java");
                })
                .packageConfig(builder -> {//生成文件夹位置配置
                    builder.parent("com.nothing")
                            .moduleName("plugins")
                            .entity("model")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"\\stray-plugins\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(targets)
                            .addTablePrefix("s_") //表前缀
                            .serviceBuilder()
                            .formatServiceFileName("I%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted") //逻辑删除列
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
