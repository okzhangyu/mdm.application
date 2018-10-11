package com.avatech.edi.mdm.businessone.project;

import com.avatech.edi.mdm.bo.IProjectBudget;
import com.avatech.edi.mdm.config.B1Connection;

public interface B1ProjectBudgetService {

    String syncProjectBudget(IProjectBudget billOfMaterial, B1Connection b1Connection);
}
