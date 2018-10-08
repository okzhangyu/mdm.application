package com.avatech.edi.mdm.dto;

/**
 * @author Fancy
 * @date 2018/9/5
 */
public class SyncResult {

    public final static String ERROR = "-1";
    public final static String OK = "0";

    public static SyncResult error(String objectKey, String message){
        SyncResult syncResult = new SyncResult();
        syncResult.setCode(ERROR) ;
        syncResult.setMessage(message);
        syncResult.setObjectKey(objectKey);
        return syncResult;
    }

    public static SyncResult ok(String objectKey){
        SyncResult syncResult = new SyncResult();
        syncResult.setCode(OK) ;
        syncResult.setMessage("同步成功");
        syncResult.setObjectKey(objectKey);
        return syncResult;
    }


    private String code;

    private String objectKey;

    private String message;

    private String returnKey;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnKey() {
        return returnKey;
    }

    public void setReturnKey(String returnKey) {
        this.returnKey = returnKey;
    }

    @Override
    public String toString() {
        return "SyncResult{" +
                "code:'" + code + '\'' +
                ", objectKey:'" + objectKey + '\'' +
                ", message:'" + message + '\'' +
                ", returnKey:'" + returnKey + '\'' +
                '}';
    }
}
