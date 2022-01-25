package com.syy.router.provider;

import com.syy.router.bean.ModuleInfo;

import java.util.HashMap;
import java.util.Map;

public class ModuleInfoProvider {

    private Map<String, ModuleInfo> routeMap;
    private static ModuleInfoProvider mModuleInfoProvider;

    private ModuleInfoProvider() {
        routeMap = new HashMap<String, ModuleInfo>();
    }

    public synchronized static ModuleInfoProvider getInstance() {
        if (mModuleInfoProvider == null) {
            mModuleInfoProvider = new ModuleInfoProvider();
        }
        return mModuleInfoProvider;
    }

    public ModuleInfo getModuleInfo(String moduleName) {
        return routeMap.get(moduleName);
    }

    public boolean isModuleExist(String moduleName) {
        return routeMap.containsKey(moduleName);
    }

    public void addModuleInfo(String moduleName, ModuleInfo moduleInfo) {
        routeMap.put(moduleName, moduleInfo);
    }
}
