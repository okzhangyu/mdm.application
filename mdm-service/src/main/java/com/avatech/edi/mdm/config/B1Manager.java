package com.avatech.edi.mdm.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class B1Manager {

    private final Logger logger = LoggerFactory.getLogger(B1Manager.class);
    String path ="C:\\workspace\\mdmcompanyinfo.json";
    File file =new File(path);
    private static List<B1Connection> b1Connections;

    private List<B1Connection> getB1Connections() throws IOException {
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            StringBuffer stringBuffer = new StringBuffer();
            String line = bufferedReader.readLine().trim();
            while (line != null) {
                stringBuffer.append(line);
                line = bufferedReader.readLine(); // 一次读入一行数据
            }
            ObjectMapper mapper = new ObjectMapper();
            List<B1Connection> companyInfos = mapper.readValue(stringBuffer.toString(),new TypeReference<List<B1Connection>>(){});
            return companyInfos;
        } finally {
            if (null != bufferedReader) bufferedReader.close();
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
        }catch (IOException e){
            throw new ServiceException("50002", "读取配置文件出错");
        }catch (Exception e){
            throw new ServiceException("50003","公司信息配置信息匹配异常");
        }
        if(connection == null){
            throw new ServiceException("50004","未找到匹配的公司信息");
        }
        return connection;
    }

}
