package cn.xkw.springautocreate.web;

import cn.xkw.springautocreate.domain.Model;
import cn.xkw.springautocreate.service.ServiceListManage;
import cn.xkw.springautocreate.service.create.BaseSrBankCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/4 17:30
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: 自动创建控制器
 */
@Controller
public class AutocreteController {

    private final Logger LOGGER = LoggerFactory.getLogger(AutocreteController.class);

    @GetMapping("/")
    public ModelAndView autocreteIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view/index");
        return mv;
    }

    @PostMapping("/autocreteFile")
    @ResponseBody
    public Boolean autocreteFile(@RequestBody Model model) {
        Boolean aBoolean = false;
        // 获取生成文件类型
        String filaType = model.getFileType();
        // 获取Service列表
        ServiceListManage serviceListManageInstance = ServiceListManage.getServiceListManageInstance();
        Map<String, BaseSrBankCreate> maps = serviceListManageInstance.getMaps();
        // 执行
        if ("全部".equals(filaType)) {
            for (Map.Entry<String, BaseSrBankCreate> map : maps.entrySet()) {
                aBoolean = map.getValue().create(model);
            }
        } else {
            BaseSrBankCreate baseSrBankCreate = maps.get(filaType);
            if (baseSrBankCreate != null) {
                aBoolean = baseSrBankCreate.create(model);
            }
        }
        return aBoolean;
    }

}
