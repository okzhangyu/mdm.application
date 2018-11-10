package com.avatech.edi.mdm.bo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface IBillOfMaterial {

     String getCompanyDB();

     void setCompanyDB(String companyName);

     Integer getDocEntry();

     void setDocEntry(Integer docEntry);

     String getOpType();

     void setOpType(String opType);

     String getBPCode();

     void setBPCode(String bpCode);

     String getVersionDesc();

     void setVersionDesc(String versionDesc);

     String getItemCode();

     void setItemCode(String itemCode);

     String getItemName();

     void setItemName(String itemName);

     String getVersion();

     void setVersion(String version);

     String getActived();

     void setActived(String actived);

     String getTreeType();

     void setTreeType(String treeType);

     Double getUnitQty();

     void setUnitQty(Double unitQty);

     String getUom();

     void setUom(String uom);

     String getToWH();

     void setToWH(String toWH);

     String getProject();

     void setProject(String project);

     String getProjectName();

     void setProjectName(String projectName);

     String getWorkOrderNo();

     void setWorkOrderNo(String workOrderNo);

     String getRoutCode();

     void setRoutCode(String routCode);

     String getOutPutWkc();

     void setOutPutWkc(String outPutWkc);

     String getValidDateF();

     void setValidDateF(String validDateF);

     String getValidDateT();

     void setValidDateT(String validDateT);

     String getRemarks();

     void setRemarks(String remarks);

     String getItemType();

     void setItemType(String itemType);

     String getCreator();

     void setCreator(String creator);

    List<CompontOfMaterialListItem> getCompontOfMaterialListItems();

    void setCompontOfMaterialListItems( List<CompontOfMaterialListItem> compontOfMaterialListItems);

    void setDocDate(String docDate);

    String getDocDate();

    void setHTH(String hth);
    String getHTH();

    void setHTMC(String HTMC);
    String getHTMC();


}
