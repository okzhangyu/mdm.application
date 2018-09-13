package com.avatech.edi.mdm.businessone;

import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.api.SBOErrorMessage;

public class BORepositoryBusinessOne {
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
            if (null == boRepositoryBusinessOne || (boRepositoryBusinessOne != null && !boRepositoryBusinessOne.getCompany().getCompanyDB().equals(ib1Connection.getCompanyDB()))) {
                boRepositoryBusinessOne = new BORepositoryBusinessOne(ib1Connection);
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
                if(!company.isConnected() || !companyDB.equals(company.getCompanyDB())){
                    company.disconnect();
                    return this.connect();
                }
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
                //XxlJobLogger.log("Successfully connected to " + company.getCompanyName());
            } else {
                SBOErrorMessage errMsg = company.getLastError();
                throw new B1Exception("Cannot connect to server: "
                        + errMsg.getErrorMessage()
                        + " "
                        + errMsg.getErrorCode());
            }
            return company;
        }catch (Exception e){
            throw new B1Exception(e);
        }
    }
}
