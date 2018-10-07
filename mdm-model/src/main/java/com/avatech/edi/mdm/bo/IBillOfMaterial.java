package com.avatech.edi.mdm.bo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface IBillOfMaterial {

     String getCompanyName();

     void setCompanyName(String companyName);

     Integer getDocEntry();

     void setDocEntry(Integer docEntry);

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

     String getRoutCode();

     void setRoutCode(String routCode);

     String getOutPutWkc();

     void setOutPutWkc(String outPutWkc);

     Date getValidDateF();

     void setValidDateF(Date validDateF);

     Date getValidDateT();

     void setValidDateT(Date validDateT);

     Integer getBPLId();

     void setBPLId(Integer bplId);

    List<ICompontOfMaterialListItem> getCompontOfMaterialListItems();

    void setCompontOfMaterialListItems( List<ICompontOfMaterialListItem> compontOfMaterialListItems);
}
