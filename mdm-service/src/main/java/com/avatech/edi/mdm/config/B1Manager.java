package com.avatech.edi.mdm.config;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class B1Manager {

    private static final String COMPANY_INFO_CONFIG = "companyinfo.json";

    private static List<B1Connection> b1Connections;

    private List<B1Connection> getB1Connections(){

        try {
            String path = getClass().getClassLoader().getResource(COMPANY_INFO_CONFIG).toString();
            path = path.replace("\\", "/");
            if (path.contains(":")) {
                path = path.replace("file:", "");// 2
            }
            FileInputStream inputStream = null;
            inputStream = new FileInputStream(path);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            Gson gson = new GsonBuilder().create();
            List<B1Connection> companyInfos = gson.fromJson(reader, new TypeToken<List<B1Connection>>(){}.getType());
            return companyInfos;
        } catch (FileNotFoundException e) {
            throw new ServiceException("50001","公司信息配置文件未找到");
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("50002","读取公司信息配置文件错误");
        }

    }

    public B1Connection getB1ConnInstance(String companyName){
        B1Connection connection = null;
        if(b1Connections == null || b1Connections.size() == 0){
            b1Connections = getB1Connections();
        }
        try{
            for (B1Connection conn:b1Connections) {
                if(companyName.equals(conn.getCompanyName())){
                    connection = conn;
                }
            }
        }catch (Exception e){
            throw new ServiceException("50003","公司信息配置信息匹配异常");
        }
        if(connection == null){
            throw new ServiceException("50004","未找到匹配的公司信息");
        }
        return connection;
    }
}
