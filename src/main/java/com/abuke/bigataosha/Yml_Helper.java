package com.abuke.bigataosha;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static com.abuke.bigataosha.Bigataosha.config;
import static com.abuke.bigataosha.Bigataosha.file;

public class Yml_Helper {
    public void createYmlFile() {
        try {
            // 获取插件数据目录下名为data.yml的文件（如果不存在则创建）
            file = new File(getDataFolder(), "data.yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            // 创建一个YamlConfiguration对象，加载file中的数据，并赋值给config变量
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDataFolder() {
        return "/Plugin/data/";
    }

    // 向yml文件写入指定key值数据的方法，接受两个参数：一个字符串类型参数key和一个Object类型参数value（可以是任何类型）
    public void writeDataToYmlFile(String key, Object value) {
        try {
            // 调用config对象的set方法，将key和value存储到内存中（不会立即写入到file中）
            config.set(key, value);
            // 调用config对象的save方法，将内存中的数据保存到file中（会覆盖原有内容）
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从yml文件读取指定key值数据的方法，接受一个字符串类型参数key，返回一个Object类型值value（如果不存在则返回null）
    public Object readDataFromYmlFile(String key) {
        return config.get(key);
     /*
     如果想要获取特定类型的value，则需要强制转换或使用相应getXXX(key)方法；
     比如：
     String name = (String) config.get("name");
     或者：
     String name = config.getString("name");

     int age = (int) config.get("age");
     或者：
     int age = config.getInt("age");
     */
    }
}
