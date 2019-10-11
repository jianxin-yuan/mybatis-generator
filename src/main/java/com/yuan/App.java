package com.yuan;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuan
 * @date 2019/10/11 10:59 上午
 */
public class App {
    public static void main(String[] args) {
        try {
            List<String> warnings = new ArrayList<>();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("generatorConfig.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(inputStream);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(new VerboseProgressCallback());
            for (String warning : warnings) {
                System.out.println(warning);
            }
        } catch (IOException | SQLException | InterruptedException
                | InvalidConfigurationException | XMLParserException e) {
            e.printStackTrace();
        }

    }
}
