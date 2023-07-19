package com.opensource.module.reflection.handle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/14 10:13
 * @Version V1.0
 */
public class PropertiesHandler {

    public static Properties buildProperties(String path) throws IOException {
        InputStream inputStream = PropertiesHandler.class.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}
