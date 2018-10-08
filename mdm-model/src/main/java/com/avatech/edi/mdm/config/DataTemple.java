package com.avatech.edi.mdm.config;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Fancy
 * @date 2018/9/6
 * 数据模板
 */
@Entity
@Table(name="AVA_MDM_VIEW_DTM1")
public class DataTemple {

    @EmbeddedId
    private DataTempleKey dataTempleKey;

    @Column(name = "Object")
    private String objectCode;

    @Column(name = "U_Tablename")
    private String tableName;

    @Column(name="U_Fieldname")
    private String fieldName;

    @Column(name = "U_Issync")
    private String isSync;

    @Column(name = "U_Isprimarykey")
    private String isUniqueKey;

    @Column(name = "U_Fathertable")
    private String fatherTable;

    public DataTempleKey getDataTempleKey() {
        return dataTempleKey;
    }

    public void setDataTempleKey(DataTempleKey dataTempleKey) {
        this.dataTempleKey = dataTempleKey;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    public String getIsUniqueKey() {
        return isUniqueKey;
    }

    public void setIsUniqueKey(String isUniqueKey) {
        this.isUniqueKey = isUniqueKey;
    }

    public String getFatherTable() {
        return fatherTable;
    }

    public void setFatherTable(String fatherTable) {
        this.fatherTable = fatherTable;
    }
}
