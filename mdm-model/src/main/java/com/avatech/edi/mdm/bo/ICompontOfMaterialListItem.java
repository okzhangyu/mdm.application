package com.avatech.edi.mdm.bo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

public interface ICompontOfMaterialListItem {

    Integer getDocEntry();

    void setDocEntry(Integer docEntry);

    String getBOMDocEntry();

    void setBOMDocEntry(String bomDocEntry);

   Integer getLineId();

   void setLineId(Integer lineId);

   String getLineStatus();

   void setLineStatus(String lineStatus);

   Integer getChildNum();

   void setChildNum(Integer childNum);

   String getItemCode();

   void setItemCode(String itemCode);

   String getItemName();

   void setItemName(String itemName);

   String getBOMVer();

   void setBOMVer(String bomVer);

   Double getQuantity();

   void setQuantity(Double quantity);

   String getWhsCode();

   void setWhsCode(String whsCode);

   Double getPrice();

   String getIsLocked();

   void setIsLocked(String isLocked);

   void setPrice(Double price);

   String getUOM();

   void setUOM(String uom);

   String getUDF1();

   void setUDF1(String udf1);

    String getUDF2();

    void setUDF2(String udf2);

    String getUDF3();

    void setUDF3(String udf3);

    String getUDF4();

    void setUDF4(String udf4);

    String getUDF5();

    void setUDF5(String udf5);

    void setDocDate(Date docDate);

    Date getDocDate();


}
