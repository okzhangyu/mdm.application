package com.avatech.edi.mdm.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AVA_OA_TASK")
public class TaskRecord {
    @Id
    @Column(name="Docentry")
    private Integer docEntry;

    @Column(name="Objectcode")
    private String objectCode;

    @Column(name="Companycode")
    private String companyName;

    @Column(name="Uniquekey")
    private String uniqueKey;

    @Column(name="Issync")
    private String isSync;

    @Column(name="Errortime")
    private Integer errorTime;

    @Column(name="Syncmessage")
    private String syncMessage;

    @Column(name="Optype")
    private String opType;

    @Column(name="Createdate")
    private String createDate;

    @Column(name="Createtime")
    private Integer createTime;

    @Column(name="Syncdate")
    private String syncDate;

    @Column(name="Synctime")
    private Integer syncTime;

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    public Integer getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Integer errorTime) {
        this.errorTime = errorTime;
    }

    public String getSyncMessage() {
        return syncMessage;
    }

    public void setSyncMessage(String syncMessage) {
        this.syncMessage = syncMessage;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }

    public Integer getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Integer syncTime) {
        this.syncTime = syncTime;
    }

    public TaskRecord() {
    }
}
