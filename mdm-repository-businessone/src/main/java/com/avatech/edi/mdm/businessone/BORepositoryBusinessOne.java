package com.avatech.edi.mdm.businessone;

import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.api.SBOErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BORepositoryBusinessOne {

    private final Logger logger = LoggerFactory.getLogger(BORepositoryBusinessOne.class);
    private String server;
    private String companyDB;
    private String userName;
    private String password;
    private Integer laguage;
    private String licenseServer;
    private String sldServer;
    private Integer dbServerType;
    private String dbUsername;
    private String dbPassword;
    private Boolean useTrusted;

    private volatile static BORepositoryBusinessOne boRepositoryBusinessOne = null;

    private volatile static ICompany company = null;

    public final static BORepositoryBusinessOne getInstance(B1Connection ib1Connection) {
        synchronized (BORepositoryBusinessOne.class) {
            if (null == boRepositoryBusinessOne ) {
                boRepositoryBusinessOne = new BORepositoryBusinessOne(ib1Connection);
            }else if( boRepositoryBusinessOne != null && !boRepositoryBusinessOne.getCompany().getCompanyDB().equals(ib1Connection.getCompanyDB())){
                boRepositoryBusinessOne.server = ib1Connection.getServer();
                boRepositoryBusinessOne.companyDB = ib1Connection.getCompanyDB();
                boRepositoryBusinessOne.userName = ib1Connection.getUserName();
                boRepositoryBusinessOne.password = ib1Connection.getPassword();
                boRepositoryBusinessOne.laguage = ib1Connection.getLanguage();
                boRepositoryBusinessOne.licenseServer = ib1Connection.getLicenseServer();
                boRepositoryBusinessOne.sldServer = ib1Connection.getSLDServer();
                boRepositoryBusinessOne.dbServerType = ib1Connection.getDBServerType();
                boRepositoryBusinessOne.dbUsername = ib1Connection.getDBUserName();
                boRepositoryBusinessOne.dbPassword = ib1Connection.getDBPassword();
                boRepositoryBusinessOne.useTrusted = ib1Connection.getIsUserTrusted();
            }
        }
        return boRepositoryBusinessOne;
    }


    private BORepositoryBusinessOne(B1Connection connection){
        this.server = connection.getServer();
        this.companyDB = connection.getCompanyDB();
        this.userName = connection.getUserName();
        this.password = connection.getPassword();
        this.laguage = connection.getLanguage();
        this.licenseServer = connection.getLicenseServer();
        this.sldServer = connection.getSLDServer();
        this.dbServerType = connection.getDBServerType();
        this.dbUsername = connection.getDBUserName();
        this.dbPassword = connection.getDBPassword();
        this.useTrusted = connection.getIsUserTrusted();
    }

    public final ICompany getCompany() throws B1Exception {
        synchronized (BORepositoryBusinessOne.class) {
            if (null == company) {
                return this.connect();
            } else {
                if(!companyDB.equals(company.getCompanyDB())){
                    company.disconnect();
                    return this.connect();
                }
                if(!company.isConnected())
                    return this.connect();
                return company;
            }
        }
    }

    public void dispose(){
        if(company!=null ){
            if(company.isConnected()){
                company.disconnect();
                company.release();
            }
        }
    }

    private ICompany connect()throws B1Exception {
        try{
            if(company == null)
                company = SBOCOMUtil.newCompany();
            company.setServer(this.server);
            company.setCompanyDB(this.companyDB);
            company.setUserName(this.userName);
            company.setPassword(this.password);
            company.setDbServerType(this.dbServerType);
            company.setUseTrusted(this.useTrusted);
            company.setLanguage(this.laguage);
            company.setDbUserName(this.dbUsername);
            company.setDbPassword(this.dbPassword);
            company.setSLDServer(this.sldServer);
            company.setLicenseServer(this.licenseServer);
            int connectionResult = company.connect();
            if (connectionResult == 0) {
                logger.info("Successfully connected to " + company.getCompanyName());
            } else {
                SBOErrorMessage errMsg = company.getLastError();
                throw new B1Exception("Cannot connect to server: "
                        + errMsg.getErrorMessage()
                        + " "
                        + errMsg.getErrorCode());
            }
            return company;
        }catch (Exception e){
            logger.error("",e);
            String errorMsg = "server:'" + server + '\'' +
                    ", companyDB:'" + companyDB + '\'' +
                    ", userName:'" + userName + '\'' +
                    ", password:'" + password + '\'' +
                    ", laguage=" + laguage +
                    ", licenseServer:'" + licenseServer + '\'' +
                    ", sldServer:'" + sldServer + '\'' +
                    ", dbServiceType:" + this.dbServerType +
                    ", dbUsername:'" + dbUsername + '\'' +
                    ", dbPassword:'" + dbPassword + '\'' +
                    ", useTrusted:" + useTrusted +
                    '}';
            logger.info("连接DI失败："+ errorMsg);
            throw new B1Exception(e.getMessage());
        }
    }
}
