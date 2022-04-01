package com.hwadee.tools;

import com.hwadee.entity.ProjectAlterApplication;
import com.hwadee.entity.ProjectAlterForManager;
import com.hwadee.entity.ProjectAlterForRisk;
import com.hwadee.entity.ProjectAlterForSales;

public class Tools {
    public static ProjectAlterForManager transferAlterForManager(ProjectAlterApplication projectAlterApplication) {
        return new ProjectAlterForManager(projectAlterApplication.getProjectId(),
                projectAlterApplication.getLeaseScheduleUnitPrice(),
                projectAlterApplication.getLeaseScheduleUnitTime(),
                projectAlterApplication.getLeaseScheduleDuration(),
                projectAlterApplication.getRetakeEffortTime(),
                projectAlterApplication.getRiskEvaluatorId(),
                projectAlterApplication.getRiskEvaluation(),
                projectAlterApplication.getFinanceEvaluatorId(),
                projectAlterApplication.getFinanceEvaluation()
        );
    }

    public static ProjectAlterForRisk transferProjectAlterForRisk(ProjectAlterApplication projectAlterApplication) {
        return new ProjectAlterForRisk(projectAlterApplication.getProjectId(),
                projectAlterApplication.getLeaseScheduleUnitPrice(),
                projectAlterApplication.getLeaseScheduleUnitTime(),
                projectAlterApplication.getLeaseScheduleDuration(),
                projectAlterApplication.getRetakeEffortTime(),
                projectAlterApplication.getRiskEvaluatorId(),
                projectAlterApplication.getRiskEvaluation());
    }

    public static ProjectAlterForSales transferProjectAlterForSales(ProjectAlterApplication projectAlterApplication) {
        return new ProjectAlterForSales(projectAlterApplication.getProjectId(),
                projectAlterApplication.getLeaseScheduleUnitPrice(),
                projectAlterApplication.getLeaseScheduleUnitTime(),
                projectAlterApplication.getLeaseScheduleDuration(),
                projectAlterApplication.getRetakeEffortTime());
    }
}
