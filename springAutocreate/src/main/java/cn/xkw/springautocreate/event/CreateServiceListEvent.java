package cn.xkw.springautocreate.event;

import cn.xkw.springautocreate.service.ServiceListManage;
import cn.xkw.springautocreate.service.create.BaseSrBankCreate;
import cn.xkw.springautocreate.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CreateServiceListEvent implements ApplicationListener<ApplicationStartedEvent> {

    private final Logger LOGGER = LoggerFactory.getLogger(CreateServiceListEvent.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LOGGER.info("=================容器刷新完初始化service列表=====================");
        if (event instanceof ApplicationStartedEvent) {
            // 获取Service列表
            ServiceListManage serviceListManageInstance = ServiceListManage.getServiceListManageInstance();
            List<String> lists = serviceListManageInstance.getLists();
            Map<String, BaseSrBankCreate> maps = serviceListManageInstance.getMaps();
            for (String name : lists) {
                BaseSrBankCreate bean = (BaseSrBankCreate) SpringUtils.getBean(name);
                maps.put(name, bean);
            }
        }
    }
}
