package com.avatech.edi.mdm.bo;

import java.util.Date;

public interface IProjectReportItem {
     Integer getDocEntry();

     void setDocEntry(Integer docEntry) ;

     Integer getLineNum();

     void setLineNum(Integer lineNum);

     String getRef1();

     void setRef1(String ref1);

     String getRef2() ;
     void setRef2(String ref2);

     String getPrjCode();

     void setPrjCode(String prjCode) ;
     String getPrjName();

     void setPrjName(String prjName);

     String getStageCode() ;

     void setStageCode(String stageCode);

     String getStageName();

     void setStageName(String stageName);

     String getCardCode() ;

     void setCardCode(String cardCode) ;
     String getCardName() ;

     void setCardName(String cardName) ;

     String getSrvcSbjct();

     void setSrvcSbjct(String srvcSbjct) ;

     String getSrvcCntnt() ;

     void setSrvcCntnt(String srvcCntnt) ;

     Date getAtlStd() ;

     void setAtlStd(Date atlStd);

     Date getAtlStt() ;

     void setAtlStt(Date atlStt) ;
     Date getAtlFnd() ;

     void setAtlFnd(Date atlFnd) ;

     Date getAtlFnt() ;

     void setAtlFnt(Date atlFnt);

     Date getAtlDur();

     void setAtlDur(Date atlDur);

     String getAtlDurUn() ;

     void setAtlDurUn(String atlDurUn);

     String getActType();

     void setActType(String actType) ;
     String getUdf1() ;

     void setUdf1(String udf1) ;

     String getUdf2() ;

     void setUdf2(String udf2) ;

     String getUdf3() ;

     void setUdf3(String udf3) ;

     String getUdf4() ;

     void setUdf4(String udf4) ;

     String getUdf5() ;

     void setUdf5(String udf5);
}
