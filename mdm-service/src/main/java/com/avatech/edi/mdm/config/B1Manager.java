package com.avatech.edi.mdm.config;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class B1Manager {

    private final Logger logger = LoggerFactory.getLogger(B1Manager.class);
    private static final String COMPANY_INFO_CONFIG = "companyinfo.json";

    private static List<B1Connection> b1Connections;

    private List<B1Connection> getB1Connections(){

        try {
//            String path = getClass().getResourceAsStream(COMPANY_INFO_CONFIG).toString();
//            logger.info(path);
//            path = path.replace("\\", "/");
//            if (path.contains(":")) {
//                path = path.replace("file:", "");// 2
//            }
//            FileInputStream inputStream = null;
//            inputStream = new FileInputStream(path);
//            Reader reader = new InputStreamReader(inputStream, "UTF-8");
//            Gson gson = new GsonBuilder().create();
//            List<B1Connection> companyInfos = gson.fromJson(reader, new TypeToken<List<B1Connection>>(){}.getType());

         Gson gson = new GsonBuilder().create();
            List<B1Connection> companyInfos = gson.fromJson("[{\"companyName\":\"技术公司-开发测试02\",\"server\":\"10.10.8.41:30015\",\"companyDB\":\"SBO_JS_KFCS02\",\"userName\":\"A100\",\"password\":\"123456\",\"licenseServer\":\"10.10.8.41:40000\",\"sldServer\":\"10.10.8.41:40000\",\"dbServiceType\":9,\"dbUsername\":\"SYSTEM\",\"dbPassword\":\"Consen2018\",\"laguage\":15,\"useTrusted\":\"false\"},{\"companyName\":\"技术公司-开发测试03\",\"server\":\"10.10.8.41:30015\",\"companyDB\":\"SBO_JS_KFCS03\",\"userName\":\"A100\",\"password\":\"123456\",\"licenseServer\":\"10.10.8.41:40000\",\"sldServer\":\"10.10.8.41:40000\",dbServiceType\":9,\"dbUsername\":\"SYSTEM\",\"dbPassword\":\"Consen2018\",\"laguage\":15,\"useTrusted\":\"false\"}]"
                    , new TypeToken<List<B1Connection>>(){}.getType());
            return companyInfos;
//        } catch (FileNotFoundException e) {
//            logger.info("公司信息配置文件未找到");
//            throw new ServiceException("50001","公司信息配置文件未找到");
//        } catch (UnsupportedEncodingException e) {
//            logger.info("读取公司信息配置文件错误");
//            throw new ServiceException("50002","读取公司信息配置文件错误");
        }catch (Exception e){
            logger.info("读取配置文件出错",e);
            throw new ServiceException("50002","读取配置文件出错");
        }

    }

    public B1Connection getB1ConnInstance(String companyName){
        B1Connection connection = null;
        try{
            if(b1Connections == null || b1Connections.size() == 0){
                b1Connections = getB1Connections();
            }
            for (B1Connection conn:b1Connections) {
                if(companyName.equals(conn.getCompanyName()) || companyName.equals(conn.getCompanyDB())){
                    connection = conn;
                }
            }
        }catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw new ServiceException("50003","公司信息配置信息匹配异常");
        }
        if(connection == null){
            throw new ServiceException("50004","未找到匹配的公司信息");
        }
        return connection;
    }

}
