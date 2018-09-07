package com.avatech.edi.mdm.config;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public class B1Connection {
    private String server;
    private String companyDB;
    private String userName;
    private String password;
    private Integer laguage;
    private String licenseServer;
    private String sldServer;
    private Integer dbServiceType;
    private String dbUsername;
    private String dbPassword;
    private Boolean useTrusted;
    private String companyName;

    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String value) {
        this.companyName = value;
    }
    
    public final String getServer() {
        return server;
    }
    
    public final void setServer(String value){
        this.server = value;
    }
    
    public final String getCompanyDB() {
        return companyDB;
    }
    
    public final void setCompanyDB(String value){
        this.companyDB = value;
    }
    
    public final String getUserName() {
        return userName;
    }
    
    public final void setUserName(String value){
        this.userName = value;
    }
    
    public final String getPassword() {
        return password;
    }
    
    public final void setPassword(String value){
        this.password = value;
    }
    
    public final Integer getLanguage() {
        return laguage;
    }
    
    public final void setLanguage(Integer value){
        this.laguage = value;
    }
    
    public final String getLicenseServer() {
        return licenseServer;
    }
    
    public final void setLicenseServer(String value){
        this.licenseServer = value;
    }
    
    public final String getSLDServer() {
        return sldServer;
    }
    
    public final void setSLDServer(String value){
        this.sldServer = value;
    }
    
    public final Integer getDBServerType() {
        return dbServiceType;
    }

    public final void setDBServerType(Integer value){
        this.dbServiceType = value;
    }
    
    public final String getDBUserName() {
        return dbUsername;
    }
    
    public final void setDBUserName(String value){
        this.dbUsername = value;
    }
    
    public final String getDBPassword() {
        return dbPassword;
    }
    
    public final void setDBPassword(String value){
        this.dbPassword = value;
    }
    
    public final Boolean getIsUserTrusted() {
        return useTrusted;
    }
    
    public final void setIsUserTrusted(Boolean value){
        this.useTrusted = value;
    }
    
    public void setIsUserTrusted(String value) {
        this.setIsUserTrusted(Boolean.valueOf(value));
    }
}
