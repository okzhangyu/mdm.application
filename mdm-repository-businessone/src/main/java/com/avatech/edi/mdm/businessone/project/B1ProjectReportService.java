package com.avatech.edi.mdm.businessone.project;

import com.avatech.edi.mdm.bo.IProjectReport;
import com.avatech.edi.mdm.config.B1Connection;

public interface B1ProjectReportService {

    String syncProjectReport(IProjectReport projectReport, B1Connection b1Connection);
}
