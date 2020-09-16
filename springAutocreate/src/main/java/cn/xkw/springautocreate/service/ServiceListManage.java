package cn.xkw.springautocreate.service;


import cn.xkw.springautocreate.service.create.BaseSrBankCreate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/8 19:40
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: service列表管理
 */
public class ServiceListManage {

    private static volatile ServiceListManage serviceListManage;

    private List<String> lists = new ArrayList<>();

    private Map<String, BaseSrBankCreate> maps = new HashMap<>();


    private ServiceListManage() {
    }

    public static ServiceListManage getServiceListManageInstance() {
        if (serviceListManage == null) {
            synchronized (ServiceListManage.class) {
                if (serviceListManage == null) {
                    serviceListManage = new ServiceListManage();
                }
            }
        }
        return serviceListManage;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public Map<String, BaseSrBankCreate> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, BaseSrBankCreate> maps) {
        this.maps = maps;
    }
}
