package cn.xkw.springautocreate.service.create;

import cn.xkw.springautocreate.domain.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/4 17:27
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: 总service控制类
 */
@Service("commonService")
public class CommonService {

    @Autowired
    private DataApiCreate dataApiCreate;

    @Autowired
    private DataRequestCreate dataRequestCreate;

    @Autowired
    private DataResponseCreate dataResponseCreate;

    @Autowired
    private ServiceApiCreate serviceApiCreate;

    @Autowired
    private ServiceModelImplCreate serviceModelImplCreate;

    @Autowired
    private DubboCreate dubboCreate;

    public Boolean all(Model model) {
        return dataApiCreate.create(model) && dataRequestCreate.create(model)
                && dataResponseCreate.create(model) && serviceApiCreate.create(model)
                && serviceModelImplCreate.create(model) && dubboCreate.create(model);
    }

    public Boolean dataApiCreate(Model model) {
        return dataApiCreate.create(model);
    }

    public Boolean dataRequestCreate(Model model) {
        return dataRequestCreate.create(model);
    }

    public Boolean dataResponseCreate(Model model) {
        return dataResponseCreate.create(model);
    }

    public Boolean serviceApiCreate(Model model) {
        return serviceApiCreate.create(model);
    }

    public Boolean serviceModelImplCreate(Model model) {
        return serviceModelImplCreate.create(model);
    }

    public Boolean dubboCreate(Model model) {
        return dubboCreate.create(model);
    }


}
