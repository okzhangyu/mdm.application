package com.avatech.edi.mdm.businessone.approval;

import com.sap.smb.sbo.api.ICompany;

public interface B1ApprovalTempleService {

    int getApproveTemple(int objectCode,ICompany company,Object object);

    void inActiveApproveTemple(ICompany company);

    int activeApproveTemple(boolean isActive, int tempCode, ICompany company);
}
