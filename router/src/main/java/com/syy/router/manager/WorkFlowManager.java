package com.syy.router.manager;

public class WorkFlowManager {

    private static WorkFlowManager workFlowManager;
    private boolean flowOnGoing = false;

    private WorkFlowManager() {
    }

    public static synchronized WorkFlowManager getInstance() {
        if (workFlowManager == null) {
            workFlowManager = new WorkFlowManager();
        }
        return workFlowManager;
    }

    public void setFlowStatus(boolean status) {
        flowOnGoing = status;
    }

    public boolean getFlowStatus() {
        return flowOnGoing;
    }
}
