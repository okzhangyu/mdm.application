package com.avatech.edi.mdm.businessone.approval;

import com.avatech.edi.mdm.businessone.B1Exception;
import com.sap.smb.sbo.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class B1ApprovalTempleServiceImp implements B1ApprovalTempleService {

    private final static Logger logger = LoggerFactory.getLogger(B1ApprovalTempleServiceImp.class);


    /**
     * 获取审批模板
     * @return
     */
    @Override
    public int getApproveTemple(int objectCode,ICompany company,Object object) {
        try {
            if (objectCode == 0) throw new B1Exception("无效的审批对象");
            IRecordset res = SBOCOMUtil.newRecordset(company);
            String queryApprovalTempleSql = "select * from \"@AVA_PM_APPRVTEMP\" where \"U_B1Object\" = '%s'";
            res.doQuery(String.format(queryApprovalTempleSql, objectCode));
            String conditionSql;
            int templeCode = -1;
            while (!res.isEoF()) {
                conditionSql = res.getFields().item("U_CondSQL").getValue().toString();
                boolean isValid = isValidOfApprovalTemple(company,conditionSql,object);
                if(isValid){
                    templeCode = Integer.valueOf(res.getFields().item("U_WtmCode").getValue().toString());
                    break;
                }
                res.moveNext();
            }
            return templeCode;
        } catch (Exception e) {
            throw new B1Exception(e);
        }
    }

    private boolean isValidOfApprovalTemple(ICompany company,String conditionSql,Object object) {
        try{
            IRecordset resCondition = SBOCOMUtil.newRecordset(company);
            Pattern pattern = Pattern.compile("(\\b|\\B)\\$\\[.*?\\](\\b|\\B)");
            Matcher matcher = pattern.matcher(conditionSql);
            while (matcher.find()){
                String fieldName = matcher.group().replace("$[","").replace("]","").trim();
                String fieldValue = getFieldValue(fieldName,object);
                conditionSql = conditionSql.replace( matcher.group(), fieldValue);
            }
            resCondition.doQuery(conditionSql);
            if(resCondition.getRecordCount() > 0){
                String result = resCondition.getFields().item(0).getValue().toString();
                if(result.toUpperCase().equals("TRUE")){
                    return true;
                }
            }
            return false;
        }catch (SBOCOMException e){
            throw new B1Exception(e);
        }catch (Exception e){
            throw new B1Exception(e);
        }

    }

    private String getFieldValue(String fieldName,Object object){
       try{
           String firstLetter = fieldName.substring(0, 1).toUpperCase();
           String getter = "get" + firstLetter + fieldName.substring(1);
           Method method = object.getClass().getMethod(getter, new Class[] {});
           String value = method.invoke(object, new Object[] {}).toString();
           return value;
       }catch (IllegalArgumentException e){
           throw new B1Exception(e);
       }
       catch (Exception e){
           throw new B1Exception(e);
       }
    }

    /**
     * 禁用审批模板
     * @param company 公司信息
     */
    public void inActiveApproveTemple(ICompany company){

        try{
            IRecordset res = SBOCOMUtil.newRecordset(company);
            String queryTempSql = "select distinct t0.* from \"OWTM\" t0\n" +
                    "                    left join \"WTM1\" t1 on t0.\"WtmCode\" = t1.\"WtmCode\"\n" +
                    "                    where t1.\"UserID\" = %s\n" +
                    "                    and  t0.\"Active\" ='Y'";
            res.doQuery(String.format(queryTempSql,company.getUserSignature()));
            if (res.getRecordCount() > 0)
            {
                int temp_id = -1;
                while (!res.isEoF()){
                    temp_id = Integer.valueOf(res.getFields().item("WtmCode").getValue().toString());
                    activeApproveTemple(false, temp_id,company);
                    res.moveNext();
                }
            }
        }catch (Exception e){
            throw new B1Exception(e.getMessage());
        }
    }

    /**
     * 激活审批模板
     * @param isActive 是否激活
     * @param tempCode 审批模板编码
     */
    public int activeApproveTemple(boolean isActive, int tempCode, ICompany company) {
        if(tempCode == 0){
            throw new B1Exception("无效的审批模板");
        }
        int returnValue = -1;
        ApprovalTemplatesService approvalTemplatesService = null;
        ApprovalTemplateParams approvalTemplateParams = null;
        ApprovalTemplatesParams approvalTemplatesParams = null;
        IApprovalTemplate approvalTemplate = null;
        try {
            approvalTemplatesService = (ApprovalTemplatesService) SBOCOMUtil.newApprovalTemplatesService(company.getCompanyService());
            approvalTemplatesParams = (ApprovalTemplatesParams) approvalTemplatesService.getApprovalTemplateList();
            approvalTemplateParams = (ApprovalTemplateParams) approvalTemplatesParams.add();
            approvalTemplateParams.setCode(tempCode);

            approvalTemplate = approvalTemplatesService.getApprovalTemplate(approvalTemplateParams);
            if (approvalTemplate != null) {
                if (isActive) {
                    if (!approvalTemplate.getIsActive().equals(SBOCOMConstants.BoYesNoEnum_tYES)) {
                        approvalTemplate.setIsActive(SBOCOMConstants.BoYesNoEnum_tYES);
                        approvalTemplatesService.updateApprovalTemplate(approvalTemplate);
                    }
                } else {
                    if (!approvalTemplate.getIsActive().equals(SBOCOMConstants.BoYesNoEnum_tNO)) {
                        approvalTemplate.setIsActive(SBOCOMConstants.BoYesNoEnum_tNO);
                        approvalTemplatesService.updateApprovalTemplate(approvalTemplate);
                    }
                }
                approvalTemplate = approvalTemplatesService.getApprovalTemplate(approvalTemplateParams);
                returnValue = isActive == (approvalTemplate.getIsActive().equals(SBOCOMConstants.BoYesNoEnum_tYES)) ? 0:-1;
            }
            if (returnValue != 0) throw new B1Exception(String.format("Activate the B1 [%s] approval template is failed!", tempCode));
            else logger.info(String.format("[Approval Template][{%s}]{%s}successful!",
                    tempCode, isActive ? "activated" : "forozen"));
        } catch (SBOCOMException e) {
            throw new B1Exception(e);
        } catch (Exception e) {
            throw e;
        }
        return returnValue;
    }

}
