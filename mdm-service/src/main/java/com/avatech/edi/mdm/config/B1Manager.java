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

    private List<B1Connection> getB1Connections() throws IOException {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(COMPANY_INFO_CONFIG);
            inputStreamReader = new InputStreamReader(stream); // 建立一个输入流对象reader
            bufferedReader = new BufferedReader(inputStreamReader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            StringBuffer stringBuffer = new StringBuffer();
            String line = bufferedReader.readLine().trim();
            while (line != null) {
                stringBuffer.append(line);
                line = bufferedReader.readLine(); // 一次读入一行数据
            }
            Gson gson = new GsonBuilder().create();
            List<B1Connection> companyInfos = gson.fromJson(stringBuffer.toString(), new TypeToken<List<B1Connection>>() {
            }.getType());
            return companyInfos;
        } catch (IOException e) {
            logger.info("读取配置文件出错", e);
            throw new ServiceException("50002", "读取配置文件出错");
        } finally {
            if (null != bufferedReader) bufferedReader.close();
            if (null != inputStreamReader) inputStreamReader.close();
            if (null != fileInputStream) fileInputStream.close();
        }
    }

    public B1Connection getB1ConnInstance(String companDB){
        B1Connection connection = null;
        try{
            if(b1Connections == null || b1Connections.size() == 0){
                b1Connections = getB1Connections();
            }
            for (B1Connection conn:b1Connections) {
                if(conn != null &&(companDB.equals(conn.getCompanyDB()) || companDB.equals(conn.getCompanyName()))){
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
