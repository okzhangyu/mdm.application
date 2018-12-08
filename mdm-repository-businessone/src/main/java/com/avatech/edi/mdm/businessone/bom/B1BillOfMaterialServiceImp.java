package com.avatech.edi.mdm.businessone.bom;

import com.avatech.edi.mdm.bo.CompontOfMaterialListItem;
import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.bo.ICompontOfMaterialListItem;
import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.businessone.approval.B1ApprovalTempleService;
import com.avatech.edi.mdm.businessone.config.B1Data;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.vo.RequestOrder;
import com.sap.smb.sbo.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class B1BillOfMaterialServiceImp implements B1BillOfMaterialService {

    private final Logger logger = LoggerFactory.getLogger(B1BillOfMaterialServiceImp.class);

    private final String DOCENTRY = "DocEntry";
    private final String BASE_TYPE = "U_BaseType";
    private final String BASE_DOCENTRY = "U_BaseEntry";
    private final String BASE_LINENUM = "U_BaseLineNum";
    private final String BOM_ITEMCODE  = "U_ItemCode";
    private final String BOM_ITEMNAME = "U_ItemName";
    private final String BOM_TREETYPE = "U_TreeType";
    private final String BOM_CREATOR = "U_Creator";
    private final String BOM_QUANTITY = "U_Quantity";
    private final String BOM_WORKORDERNUM = "U_WorkOrderNo";
    private final String BOM_UOM = "U_Uom";
    private final String BOM_VERSION = "U_BOMVer";
    private final String BOM_IS_LOCK = "U_IsLocked";
    private final String BOM_UNITQTY = "U_UnitQty";
    private final String BOM_PROJECT = "U_PrjCode";
    private final String BOM_PROJECT_NAME = "U_PrjName";
    private final String DOCDATE="U_DocDate";
    private final String OBJECT_CODE = "AVA_PM_BOMVERSION";
    private final String HTH="U_HTH";
    private final String HTMC="U_HTMC";
    private final String ITEMTYPE="U_ItemType";
    private final String MODEL_NAME = "U_XH";
    private final String PROJECAT_MANAGER = "U_PrjManager";
    private final String DEPT_TYPE = "U_DeptType";


    @Autowired
    private B1ApprovalTempleService b1ApprovalTempleService;

    /**
     * BOM新增或变更后进入审批
     * @param billOfMaterial
     * @param b1Connection
     * @return
     */
    @Override
    public String syncBillOfMaterial(IBillOfMaterial billOfMaterial, B1Connection b1Connection) {
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        int tempCode = -1;
        try {
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            tempCode = b1ApprovalTempleService.getApproveTemple(B1Data.PURCHASEQUOTE,company,billOfMaterial);
            if(tempCode > 0){
                b1ApprovalTempleService.inActiveApproveTemple(company);
                b1ApprovalTempleService.activeApproveTemple(true,tempCode,company);
            }
            IDocuments document = SBOCOMUtil.newDocuments(company, B1Data.PURCHASEQUOTE);
            IRecordset res = SBOCOMUtil.newRecordset(company);

            document.setCardCode(B1Data.VISUAL_SUPPLIER);
            document.setDocDate(new Date());
            document.setTaxDate(new Date());
            document.setVatDate(new Date());
            document.setRequriedDate(new Date());
            document.setComments(billOfMaterial.getRemarks());

            document.getApprovalTemplates();
            if(document.getDocument_ApprovalRequests().getCount() > 0 && document.getDocument_ApprovalRequests().getApprovalTemplatesID() > 0){
                document.getDocument_ApprovalRequests().setCurrentLine(0);
                document.getDocument_ApprovalRequests().setRemarks(getBOMRemarks(billOfMaterial,company));
            }

            document.getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
            document.getUserFields().getFields().item(BASE_DOCENTRY).setValue(billOfMaterial.getDocEntry().toString());
            document.getUserFields().getFields().item(BOM_ITEMCODE).setValue(billOfMaterial.getItemCode());
            document.getUserFields().getFields().item(BOM_ITEMNAME).setValue(billOfMaterial.getItemName());
            document.getUserFields().getFields().item(BOM_WORKORDERNUM).setValue(billOfMaterial.getProject());
            document.getUserFields().getFields().item(BOM_PROJECT).setValue(billOfMaterial.getProject());

            if(billOfMaterial.getCreator() != null && !billOfMaterial.getCreator().isEmpty())
                document.getUserFields().getFields().item(BOM_CREATOR).setValue(billOfMaterial.getCreator());
            if(billOfMaterial.getTreeType() != null && ! billOfMaterial.getTreeType().isEmpty())
                document.getUserFields().getFields().item(BOM_TREETYPE).setValue(billOfMaterial.getTreeType());
            if(billOfMaterial.getUom() != null && !billOfMaterial.getUom().isEmpty())
                document.getUserFields().getFields().item(BOM_UOM).setValue(billOfMaterial.getUom());
            if(billOfMaterial.getVersion() != null && !billOfMaterial.getVersion().isEmpty())
                document.getUserFields().getFields().item(BOM_VERSION).setValue(billOfMaterial.getVersion());
            if(billOfMaterial.getDeptType() != null && !billOfMaterial.getDeptType().isEmpty())
                document.getUserFields().getFields().item(DEPT_TYPE).setValue(billOfMaterial.getDeptType());
            for (ICompontOfMaterialListItem item:billOfMaterial.getCompontOfMaterialListItems()) {
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setQuantity(1.0);
                document.getLines().setPrice(item.getPrice());
                document.getLines().getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
                document.getLines().getUserFields().getFields().item(BASE_DOCENTRY).setValue(item.getDocEntry().toString());
                document.getLines().getUserFields().getFields().item(BASE_LINENUM).setValue(item.getLineId().toString());
                document.getLines().getUserFields().getFields().item(BOM_QUANTITY).setValue(item.getQuantity().toString());
                document.getLines().getUserFields().getFields().item(BOM_IS_LOCK).setValue(item.getIsLocked());
                document.getLines().setProjectCode(billOfMaterial.getProject());
                SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd " );
                document.getLines().getUserFields().getFields().item(DOCDATE).setValue(sdf.format(item.getDocDate()));
                document.getLines().getUserFields().getFields().item(BOM_WORKORDERNUM).setValue(billOfMaterial.getWorkOrderNo());
                if(item.getModelName() != null && !item.getModelName().isEmpty()){
                    document.getLines().getUserFields().getFields().item(MODEL_NAME).setValue(item.getModelName());
                }
                document.getLines().add();
            }
            int rt = document.add();
            if(rt == 0 )
                return company.getNewObjectKey();
            else throw new B1Exception(company.getLastErrorCode() + ":" + company.getLastErrorDescription());
        }catch (SBOCOMException e){
            logger.error("同步BOM发生异常",e);
            throw new B1Exception(e);
        }finally {
            if(company!=null){
                if(tempCode > 0){
                    b1ApprovalTempleService.inActiveApproveTemple(company);
                }
                //company.disconnect();
            }
        }
    }


    /**
     * BOM审批完成后，生成（更新）生产订单和采购申请
     * @param billOfMaterial
     * @param b1Connection
     * @return
     */
    @Override
    public String approvedBillOfMaterial(IBillOfMaterial billOfMaterial, B1Connection b1Connection) {
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        String produceOrder = "";
        String purchaseOrer = "";
        try{
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();
            Integer docEntry = getProduceOrderNo(billOfMaterial.getProject(),billOfMaterial.getWorkOrderNo(),billOfMaterial.getContractNo(),billOfMaterial.getItemType(),company);
            if(!company.isInTransaction()){
                company.startTransaction();
            }
            produceOrder = createOrUpdateProduceOrder(billOfMaterial,company,docEntry);
            if(billOfMaterial.getItemType().equals("C"))
                purchaseOrer = createPurchaseOrder(billOfMaterial,company);
            if(company.isInTransaction()){
                company.endTransaction(SBOCOMConstants.BoWfTransOpt_wf_Commit);
            }
            return produceOrder + "_" + purchaseOrer;
        }catch (Exception e){
            if(company.isInTransaction()){
                company.endTransaction(SBOCOMConstants.BoWfTransOpt_wf_RollBack);
            }
            if(company != null){
                //company.disconnect();
            }
            logger.error("BOM处理生产订单/采购申请单异常"+e);
            throw e;
        }
    }

    private Integer getProduceOrderNo(String projectNo,String workOrderNo,String hth,String itemType,ICompany company) {
        try {
            String sql = "select \"DocEntry\" from OWOR where  \"Project\" = '%s' and \"U_WorkOrderNo\" = '%s' and \"U_HTH\"='%s'and \"U_ItemType\"='%s'";
            IRecordset res = SBOCOMUtil.newRecordset(company);
            res.doQuery(String.format(sql, projectNo, workOrderNo, hth, itemType));
            if (res.getRecordCount() > 0)
                return Integer.parseInt(res.getFields().item(DOCENTRY).getValue().toString());
            else return null;
        } catch (SBOCOMException e) {
            logger.error("查询生产订单异常" + e);
            throw new B1Exception(e);
        }
    }

    private String createOrUpdateProduceOrder(IBillOfMaterial billOfMaterial,ICompany company,Integer docEntry){
        try{
            IProductionOrders document = SBOCOMUtil.newProductionOrders(company);
            Boolean isExists = false;
            int rt = 0;
            if(docEntry != null){
                if(document.getByKey(docEntry)){
                    isExists = true;
                }
            }
            document.setProductionOrderType(SBOCOMConstants.SpecialProductTypeEnum_sptIO);
            document.setItemNo(billOfMaterial.getItemCode());
            document.setWarehouse(billOfMaterial.getToWH());
            document.setPlannedQuantity(billOfMaterial.getUnitQty());
            document.setProject(billOfMaterial.getProject());
            document.setRemarks(this.getBOMRemarks(billOfMaterial,company));
            document.getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
            document.getUserFields().getFields().item(BASE_DOCENTRY).setValue(billOfMaterial.getDocEntry().toString());
            document.getUserFields().getFields().item(BOM_WORKORDERNUM).setValue(billOfMaterial.getWorkOrderNo());
            document.getUserFields().getFields().item(HTH).setValue(billOfMaterial.getContractNo());
            document.getUserFields().getFields().item(HTMC).setValue(billOfMaterial.getContractName());
            document.getUserFields().getFields().item(ITEMTYPE).setValue(billOfMaterial.getItemType());

            if(billOfMaterial.getManager()!= null && !billOfMaterial.getManager().isEmpty()){
                document.getUserFields().getFields().item(PROJECAT_MANAGER).setValue(billOfMaterial.getManager());
            }
            if(billOfMaterial.getDeptType() != null && !billOfMaterial.getDeptType().isEmpty())
                document.getUserFields().getFields().item(DEPT_TYPE).setValue(billOfMaterial.getDeptType());
            for (ICompontOfMaterialListItem item:billOfMaterial.getCompontOfMaterialListItems()) {
                if(item.getIsLocked().equals("Y") && item.getQuantity() > 0) {
                    if (item.getDesLineNum() >= 0) {
                        document.getLines().setCurrentLine(item.getDesLineNum());
                    }
                    document.getLines().getIssuedQuantity();
                    document.getLines().setItemNo(item.getItemCode());
                    document.getLines().setBaseQuantity(item.getQuantity());
                    document.getLines().setPlannedQuantity(item.getQuantity());
                    document.getLines().setWarehouse(item.getWhsCode());
                    document.getLines().setProject(billOfMaterial.getProject());
                    document.getLines().getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
                    document.getLines().getUserFields().getFields().item(BASE_DOCENTRY).setValue(item.getDocEntry().toString());
                    document.getLines().getUserFields().getFields().item(BASE_LINENUM).setValue(item.getLineId().toString());
                    document.getLines().getUserFields().getFields().item(BOM_WORKORDERNUM).setValue(billOfMaterial.getWorkOrderNo());
                    SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd ");
                    document.getLines().getUserFields().getFields().item(DOCDATE).setValue(sdf.format(item.getDocDate()));
                    if (item.getModelName() != null && !item.getModelName().isEmpty()) {
                        document.getLines().getUserFields().getFields().item(MODEL_NAME).setValue(item.getModelName());
                    }
                    document.getLines().add();
                }
            }
            if(isExists){
                rt = document.update();
            }else {
                rt = document.add();
                if(rt == 0 && document.getByKey(Integer.valueOf(company.getNewObjectKey()))){
                    document.setProductionOrderStatus(SBOCOMConstants.BoProductionOrderStatusEnum_boposReleased);
                    document.update();
                }
            }
            if(rt == 0){
                return company.getNewObjectKey();
            }
            else throw new B1Exception(company.getLastErrorCode() + ":" + company.getLastErrorDescription());
        }catch (B1Exception e){
            throw  e;
        }catch (SBOCOMException e){
            throw new B1Exception(e);
        }
    }

    private String createPurchaseOrder(IBillOfMaterial billOfMaterial, ICompany company){
        try {
            IDocuments document = SBOCOMUtil.newDocuments(company, SBOCOMConstants.BoObjectTypes_Document_oPurchaseRequest);
            List<RequestOrder> requestOrders = this.getRequestOrder(billOfMaterial,company);
            document.setDocDate(new Date());
            document.setDocDueDate(new Date());
            document.setTaxDate(new Date());
            document.setRequriedDate(new Date());
            document.setComments(getBOMRemarks(billOfMaterial,company));
            document.getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
            document.getUserFields().getFields().item(BASE_DOCENTRY).setValue(billOfMaterial.getDocEntry().toString());
            document.getUserFields().getFields().item(BOM_ITEMCODE).setValue(billOfMaterial.getItemCode());
            document.getUserFields().getFields().item(BOM_ITEMNAME).setValue(billOfMaterial.getItemName());
            document.getUserFields().getFields().item(BOM_TREETYPE).setValue(billOfMaterial.getTreeType());
            document.getUserFields().getFields().item(BOM_VERSION).setValue(billOfMaterial.getVersion());
            document.getUserFields().getFields().item(HTH).setValue(billOfMaterial.getContractNo());
            document.getUserFields().getFields().item(HTMC).setValue(billOfMaterial.getContractName());

            if(billOfMaterial.getUom() != null && !billOfMaterial.getUom().isEmpty())
            document.getUserFields().getFields().item(BOM_UOM).setValue(billOfMaterial.getUom());
            document.setProject(billOfMaterial.getProject());
            if(billOfMaterial.getProject() != null && !billOfMaterial.getProject().isEmpty()) {
                document.getUserFields().getFields().item(BOM_PROJECT).setValue(billOfMaterial.getProject());
            }
            if(billOfMaterial.getManager()!= null && !billOfMaterial.getManager().isEmpty()){
                document.getUserFields().getFields().item(PROJECAT_MANAGER).setValue(billOfMaterial.getManager());
            }
            if(billOfMaterial.getDeptType() != null && !billOfMaterial.getDeptType().isEmpty())
                document.getUserFields().getFields().item(DEPT_TYPE).setValue(billOfMaterial.getDeptType());
            List<CompontOfMaterialListItem> compontOfMaterialListItems = this.getRequestQuantityNextTime(requestOrders,billOfMaterial.getCompontOfMaterialListItems());
            if(compontOfMaterialListItems == null)
                return "" ;
            for (ICompontOfMaterialListItem item:compontOfMaterialListItems) {
                if(item.getIsLocked().equals("Y") && item.getQuantity() > 0){
                    document.getLines().setItemCode(item.getItemCode());
                    document.getLines().setQuantity(item.getQuantity());
                    document.getLines().setPrice(item.getPrice());
                    document.getLines().setWarehouseCode(item.getWhsCode());
                    document.getLines().setProjectCode(billOfMaterial.getProject());
                    document.getLines().getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
                    document.getLines().getUserFields().getFields().item(BASE_DOCENTRY).setValue(item.getDocEntry().toString());
                    document.getLines().getUserFields().getFields().item(BASE_LINENUM).setValue(item.getLineId().toString());
                    document.getLines().setProjectCode(billOfMaterial.getProject());
                    document.getLines().getUserFields().getFields().item(BOM_WORKORDERNUM).setValue(billOfMaterial.getWorkOrderNo());
                    SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd " );
                    document.getLines().getUserFields().getFields().item(DOCDATE).setValue(sdf.format(item.getDocDate()));
                    if(item.getModelName() != null && !item.getModelName().isEmpty())
                        document.getLines().getUserFields().getFields().item(MODEL_NAME).setValue(item.getModelName());
                    document.getLines().add();
                }
            }
            int rt = document.add();
            if(rt == 0 )
                return company.getNewObjectKey();
            else throw new B1Exception(company.getLastErrorCode() + ":" + company.getLastErrorDescription());
        }catch (SBOCOMException e){
            logger.error("BOM生成采购申请发生异常",e);
            throw new B1Exception(e);
        }
    }

    /**
     * 获取已经申请的BOM数量
     * @param
     * @return
     */
    private List<RequestOrder> getRequestOrder(IBillOfMaterial billOfMaterial, ICompany company){
        try{
            String sql = "select t1.\"U_BaseLineNum\",\"ItemCode\",sum(\"Quantity\") as \"Quantity\" from OPRQ t0 inner join PRQ1 t1 on t0.\"DocEntry\" = t1.\"DocEntry\"\n" +
                    "\t\t\t\t\t where t0.\"U_ItemCode\" = '%s'\n" +
                    "\t\t\t\t\t \tand t0.\"U_PrjCode\" = '%s'\n" +
                    "\t\t\t\t\t \tand ifnull(t1.\"U_WorkOrderNo\",'') = '%s'\n" +
                    "                   \t\tand ifnull(t0.\"U_HTH\",'') = '%s'\n" +
                    "                    \tgroup by t1.\"U_BaseLineNum\",\"ItemCode\"";
            IRecordset res = SBOCOMUtil.newRecordset(company);
            res.doQuery(String.format(sql,billOfMaterial.getItemCode(),billOfMaterial.getProject(),billOfMaterial.getWorkOrderNo(),billOfMaterial.getContractNo()));
            List<RequestOrder> requestOrders = new ArrayList<>();
            RequestOrder order;
            while (!res.isEoF()){
                order = new RequestOrder();
                order.setBaseLineNUum(Integer.valueOf(res.getFields().item("U_BaseLineNum").getValue().toString()) );
                order.setItemCode(res.getFields().item("ItemCode").getValue().toString());
                order.setQuantity(Double.valueOf(res.getFields().item("Quantity").getValue().toString()));
                requestOrders.add(order);
                res.moveNext();
            }
            return requestOrders;
        }catch (Exception e){
            logger.error("获取已申请BOM异常：",e);
            throw new B1Exception(e);
        }
    }

    /**
     * 获取需申请的数量
     * @param orders
     * @return
     */
    private List<CompontOfMaterialListItem> getRequestQuantityNextTime(List<RequestOrder> orders,List<CompontOfMaterialListItem> billOfMaterialItems){
        if(billOfMaterialItems ==null || billOfMaterialItems.size()<= 0)
            return null;
        if(orders == null || orders.size() <= 0)
            return billOfMaterialItems;
        for (int i =0;i<billOfMaterialItems.size();i++){
            if(!billOfMaterialItems.get(i).getIsLocked().equals("Y"))
                continue;
            for (int j=0;j<orders.size();j++){
                if(orders.get(j).getBaseLineNum().equals(billOfMaterialItems.get(i).getLineId())
                    && orders.get(j).getItemCode().equals(billOfMaterialItems.get(i).getItemCode())) {
                    if(billOfMaterialItems.get(i).getQuantity() > orders.get(j).getQuantity()){
                        billOfMaterialItems.get(i).setQuantity(billOfMaterialItems.get(i).getQuantity() - orders.get(j).getQuantity());
                    }
                    break;
                }
            }
        }
        return billOfMaterialItems;
    }

    private String getBOMRemarks(IBillOfMaterial billOfMaterial,ICompany company){
        try{
            IRecordset res = SBOCOMUtil.newRecordset(company);
            String sqlUser = "select \"U_NAME\" from OUSR where \"USERID\" = %s";
            res.doQuery(String.format(sqlUser,billOfMaterial.getCreator()));
            return  "创建人：" + res.getFields().item("U_NAME").getValue()+";[" + billOfMaterial.getDocEntry() +"]BOM;工单号：" + billOfMaterial.getWorkOrderNo() +";项目:"
                    + billOfMaterial.getProject()+";合同号："+billOfMaterial.getContractNo()+";合同名称："+billOfMaterial.getContractName() + ";物料类型：" + billOfMaterial.getItemType();

        }catch (Exception e){
            logger.error("查询用户信息异常",e);
            throw new B1Exception(e);
        }
    }
}


