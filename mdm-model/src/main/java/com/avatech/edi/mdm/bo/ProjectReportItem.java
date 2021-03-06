package com.avatech.edi.mdm.bo;

import java.util.Date;

public class ProjectReportItem implements IProjectReportItem{

    private Integer docEntry;
    private Integer lineNum;
    private String ref1;
    private String ref2;
    private String prjCode;
    private String prjName;
    private String stageCode;
    private String stageName;
    private String cardCode;
    private String cardName;
    private String srvcSbjct;
    private String srvcCntnt;
    private String atlStd;
    private Integer atlStt;
    private String atlFnd;
    private Integer atlFnt;
    private Double atlDur;
    private String atlDurUn;
    private String actType;
    private String udf1;
    private String udf2;
    private String udf3;
    private String udf4;
    private String udf5;

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getPrjCode() {
        return prjCode;
    }

    public void setPrjCode(String prjCode) {
        this.prjCode = prjCode;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSrvcSbjct() {
        return srvcSbjct;
    }

    public void setSrvcSbjct(String srvcSbjct) {
        this.srvcSbjct = srvcSbjct;
    }

    public String getSrvcCntnt() {
        return srvcCntnt;
    }

    public void setSrvcCntnt(String srvcCntnt) {
        this.srvcCntnt = srvcCntnt;
    }

    public String getAtlStd() {
        return atlStd;
    }

    public void setAtlStd(String atlStd) {
        this.atlStd = atlStd;
    }

    public Integer getAtlStt() {
        return atlStt;
    }

    public void setAtlStt(Integer atlStt) {
        this.atlStt = atlStt;
    }

    public String getAtlFnd() {
        return atlFnd;
    }

    public void setAtlFnd(String atlFnd) {
        this.atlFnd = atlFnd;
    }

    public Integer getAtlFnt() {
        return atlFnt;
    }

    public void setAtlFnt(Integer atlFnt) {
        this.atlFnt = atlFnt;
    }

    public Double getAtlDur() {
        return atlDur;
    }

    public void setAtlDur(Double atlDur) {
        this.atlDur = atlDur;
    }

    public String getAtlDurUn() {
        return atlDurUn;
    }

    public void setAtlDurUn(String atlDurUn) {
        this.atlDurUn = atlDurUn;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getUdf1() {
        return udf1;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public void setUdf4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUdf5() {
        return udf5;
    }

    public void setUdf5(String udf5) {
        this.udf5 = udf5;
    }

    @Override
    public String toString() {
        return "ProjectReportItem{" +
                "\"docEntry\":\"" + docEntry +
                "\",\" lineNum\":\"" + lineNum +
                "\",\" ref1\":\"" + ref1 + '\'' +
                "\",\" ref2\":\"" + ref2 + '\'' +
                "\",\" prjCode\":\"" + prjCode + '\'' +
                "\",\" prjName\":\"" + prjName + '\'' +
                "\",\" stageCode\":\"" + stageCode + '\'' +
                "\",\" stageName\":\"" + stageName + '\'' +
                "\",\" cardCode\":\"" + cardCode + '\'' +
                "\",\" cardName\":\"" + cardName + '\'' +
                "\",\" srvcSbjct\":\"" + srvcSbjct + '\'' +
                "\",\" srvcCntnt\":\"" + srvcCntnt + '\'' +
                "\",\" atlStd\":\"" + atlStd + '\'' +
                "\",\" atlStt\":\"" + atlStt +
                "\",\" atlFnd\":\"" + atlFnd + '\'' +
                "\",\" atlFnt\":\"" + atlFnt +
                "\",\" atlDur\":\"" + atlDur +
                "\",\" atlDurUn\":\"" + atlDurUn + '\'' +
                "\",\" actType\":\"" + actType + '\'' +
                "\",\" udf1\":\"" + udf1 + '\'' +
                "\",\" udf2\":\"" + udf2 + '\'' +
                "\",\" udf3\":\"" + udf3 + '\'' +
                "\",\" udf4\":\"" + udf4 + '\'' +
                "\",\" udf5\":\"" + udf5 + '\'' +
                '}';
    }
}
