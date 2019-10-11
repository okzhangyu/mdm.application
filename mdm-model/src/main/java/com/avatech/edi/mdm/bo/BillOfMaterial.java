package com.avatech.edi.mdm.bo;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "AVA_PM_VIEW_OBOM")
public class BillOfMaterial implements IBillOfMaterial {

    public static List<CompontOfMaterialListItem> createMaterialListItems(IBillOfMaterial billOfMaterial){
        for (int i = 0;i<billOfMaterial.getCompontOfMaterialListItems().size();i++){
            if(billOfMaterial.getCompontOfMaterialListItems().get(i).getIsLocked().equals("Y") &&
                    billOfMaterial.getCompontOfMaterialListItems().get(i).getDesLineNum().equals(-1)){
                billOfMaterial.getCompontOfMaterialListItems().get(i).setDesLineNum(i);
            }
        }
        return billOfMaterial.getCompontOfMaterialListItems();
    }

    public static List<ICompontOfMaterialListItem> createMaterialListItem(IBillOfMaterial billOfMaterial){
        try{
            List<ICompontOfMaterialListItem>  materialListItems = new LinkedList<>();
            HashMap<Integer,ICompontOfMaterialListItem> materialListItemHashMap = new HashMap<>();
            ICompontOfMaterialListItem currentBOMLine;
            List<ICompontOfMaterialListItem> lockedOfBOM = billOfMaterial.getCompontOfMaterialListItems().stream().filter(c->c.getIsLocked().equals("Y")).collect(Collectors.toList());
            List<ICompontOfMaterialListItem> lastLockedOfBOM = billOfMaterial.getCompontOfMaterialListItems().stream()
                    .filter(c->c.getDesLineNum()>=0).collect(Collectors.toList());
            List<ICompontOfMaterialListItem> handleOfBOM = billOfMaterial.getCompontOfMaterialListItems().stream()
                    .filter(c->c.getIsLocked().equals("Y") || c.getDesLineNum()>=0).collect(Collectors.toList());
            HashMap newBomAndOrder;
            int count ;
            if(lockedOfBOM.size()>=lastLockedOfBOM.size()){
                count = lockedOfBOM.size();
                newBomAndOrder = createBOMAndOrder(lockedOfBOM);
            }else {
                count = handleOfBOM.size();
                newBomAndOrder = createBOMAndOrder(handleOfBOM);
            }
            int deleteLines = getDeleteLines(lastLockedOfBOM);
            for (int i=0;i<count;i++) {
                Integer key = findByValue(newBomAndOrder, i + deleteLines);
                if (key == -1) {
                    Integer lineNum = i + deleteLines;
                    currentBOMLine = lastLockedOfBOM.stream().filter(c -> c.getIsLocked().equals("N") && c.getDesLineNum() == lineNum).findFirst().get();
                } else {
                    newBomAndOrder.put(key, i);
                    currentBOMLine = handleOfBOM.stream().filter(c -> c.getLineId().equals(key)).findFirst().get();
                    currentBOMLine.setDesLineNum(i);
                }
                materialListItems.add(currentBOMLine);
            }
            return materialListItems;
        }catch (Exception e){
            throw e;
        }
    }

    private static Integer getDeleteLines(List<ICompontOfMaterialListItem> compontOfMaterialListItems){
        if(compontOfMaterialListItems != null && compontOfMaterialListItems.size() == 0){
            return 0;
        }
        Integer deleteLines = 10000;
        for (ICompontOfMaterialListItem item:compontOfMaterialListItems) {
            if(deleteLines>item.getDesLineNum() && item.getDesLineNum()>= 0){
                deleteLines = item.getDesLineNum();
            }
        }
        return deleteLines;
    }
    private static Integer findByValue(HashMap<Integer,Integer> map,Integer value){
        Set set = map.entrySet(); //通过entrySet()方法把map中的每个键值对变成对应成Set集合中的一个对象
        Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();
        if(!map.values().contains(value)){
            value = -1;
        }
        while(iterator.hasNext()){
            //Map.Entry是一种类型，指向map中的一个键值对组成的对象
            Map.Entry<Integer, Integer> entry = iterator.next();
            if(entry.getValue().equals(value)){
                return entry.getKey();
            }
        }
        return -1;
    }

    private static HashMap createBOMAndOrder(List<ICompontOfMaterialListItem> compontOfMaterialListItems){
        HashMap bomAndOrder = new HashMap<Integer,Integer>();
        for (ICompontOfMaterialListItem item: compontOfMaterialListItems) {
            bomAndOrder.put(item.getLineId(),item.getDesLineNum());
        }
        return bomAndOrder;
    }

    @Id
    @Column(name = "Uniquekey")
    private String uniqueKey;

    @Column(name = "Companydb")
    private String companyDB;

    @Column(name = "Docentry")
    private Integer docEntry;

    @Column(name = "Bpcode")
    private String bpCode;

    @Column(name = "Itemcode")
    private String itemCode;

    @Column(name = "Itemname")
    private String itemName;

    @Column(name = "Version")
    private String version;

    @Column(name = "Versiondesc")
    private String versionDesc;

    @Column(name = "Actived")
    private String actived;

    @Column(name = "Treetype")
    private String treeType;

    @Column(name = "Unitqty")
    private Double unitQty;

    @Column(name = "Uom")
    private String uom;

    @Column(name = "Towh")
    private String toWH;

    @Column(name = "Project")
    private String project;

    @Column(name = "Workorderno")
    private String workOrderNo;

    @Column(name = "Routcode")
    private String routCode;

    @Column(name = "Outputwkc")
    private String outPutWkc;

    @Column(name = "Validdatef")
    private String validDateF;

    @Column(name = "Validdatet")
    private String validDateT;

    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "Optype")
    private String opType;

    @Column(name = "Creator")
    private String creator;

    @Column(name = "Itemtype")
    private String itemType;

    @Column(name = "Projectname")
    private String projectName;

    @Column(name = "Docdate")
    private String docDtae;

    @Column(name="HTH")
    private String contractNo;

    @Column(name = "HTMC")
    private String contractName;

    @Column(name = "Manager")
    private String manager;

    @Column(name = "Depttype")
    private String deptType;

    @Override
    public String getManager() {
        return manager;
    }

    @Override
    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOpType(){
        return opType;
    }

    public void setOpType(String opType){
        this.opType = opType;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Bomdocentry")
    private List<CompontOfMaterialListItem> compontOfMaterialListItems;

    public BillOfMaterial(){
        this.compontOfMaterialListItems = new ArrayList<>();
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    @Override
    public String getVersionDesc() {
        return versionDesc;
    }

    @Override
    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    @Override
    public String getCompanyDB() {
        return companyDB;
    }

    @Override
    public void setCompanyDB(String companyDB) {
        this.companyDB = companyDB;
    }

    @Override
    public Integer getDocEntry() {
        return docEntry;
    }

    @Override
    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    @Override
    public String getBPCode() {
        return bpCode;
    }

    @Override
    public void setBPCode(String bPCode) {
        this.bpCode = bPCode;
    }

    @Override
    public String getItemCode() {
        return itemCode;
    }

    @Override
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getActived() {
        return actived;
    }

    @Override
    public void setActived(String actived) {
        this.actived = actived;
    }

    @Override
    public String getTreeType() {
        return treeType;
    }

    @Override
    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    @Override
    public Double getUnitQty() {
        return unitQty;
    }

    @Override
    public void setUnitQty(Double unitQty) {
        this.unitQty = unitQty;
    }

    @Override
    public String getUom() {
        return uom;
    }

    @Override
    public void setUom(String uom) {
        this.uom = uom;
    }

    @Override
    public String getToWH() {
        return toWH;
    }

    @Override
    public void setToWH(String toWH) {
        this.toWH = toWH;
    }

    @Override
    public String getProject() {
        return project;
    }

    @Override
    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String getWorkOrderNo() {
        return workOrderNo;
    }

    @Override
    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    @Override
    public String getRoutCode() {
        return routCode;
    }

    @Override
    public void setRoutCode(String routCode) {
        this.routCode = routCode;
    }

    @Override
    public String getOutPutWkc() {
        return outPutWkc;
    }

    @Override
    public void setOutPutWkc(String outPutWkc) {
        this.outPutWkc = outPutWkc;
    }

    @Override
    public String getValidDateF() {
        return validDateF;
    }

    @Override
    public void setValidDateF(String validDateF) {
        this.validDateF = validDateF;
    }

    @Override
    public String getValidDateT() {
        return validDateT;
    }

    @Override
    public void setValidDateT(String validDateT) {
        this.validDateT = validDateT;
    }


    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public List<CompontOfMaterialListItem> getCompontOfMaterialListItems() {
        return compontOfMaterialListItems;
    }

    @Override
    public void setCompontOfMaterialListItems(List<CompontOfMaterialListItem> compontOfMaterialListItems) {
        this.compontOfMaterialListItems = compontOfMaterialListItems;
    }

    @Override
    public void setDocDate(String docDate) {
        this.docDtae=docDate;

    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    @Override
    public String getDocDate() {
        return docDtae;
    }

    @Override
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Override
    public String getContractNo() {
        return contractNo;
    }

    @Override
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    @Override
    public String getContractName() {
        return contractName;
    }

    @Override
    public String getItemType() {
        return itemType;
    }

    @Override
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "BillOfMaterial{" +
                "\"uniqueKey\":\"" + uniqueKey + '\'' +
                "\",\" companyDB\":\"" + companyDB + '\'' +
                "\",\" docEntry=" + docEntry +
                "\",\" bpCode\":\"" + bpCode + '\'' +
                "\",\" itemCode\":\"" + itemCode + '\'' +
                "\",\" itemName\":\"" + itemName + '\'' +
                "\",\" version\":\"" + version + '\'' +
                "\",\" versionDesc\":\"" + versionDesc + '\'' +
                "\",\" actived\":\"" + actived + '\'' +
                "\",\" treeType\":\"" + treeType + '\'' +
                "\",\" unitQty=" + unitQty +
                "\",\" uom\":\"" + uom + '\'' +
                "\",\" toWH\":\"" + toWH + '\'' +
                "\",\" project\":\"" + project + '\'' +
                "\",\" workOrderNo\":\"" + workOrderNo + '\'' +
                "\",\" routCode\":\"" + routCode + '\'' +
                "\",\" outPutWkc\":\"" + outPutWkc + '\'' +
                "\",\" validDateF\":\"" + validDateF + '\'' +
                "\",\" validDateT\":\"" + validDateT + '\'' +
                "\",\" remarks\":\"" + remarks + '\'' +
                "\",\" opType\":\"" + opType + '\'' +
                "\",\" creator\":\"" + creator + '\'' +
                "\",\" compontOfMaterialListItems\":" + compontOfMaterialListItems +
                "\",\" docDate\":" + docDtae +
                "\",\" contractNo\":" + contractNo +
                "\",\" contractName\":" + contractName +
                '}';
    }
}
