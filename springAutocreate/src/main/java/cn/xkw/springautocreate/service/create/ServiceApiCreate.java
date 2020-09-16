package cn.xkw.springautocreate.service.create;

import cn.xkw.springautocreate.common.CommonData;
import cn.xkw.springautocreate.domain.Model;
import cn.xkw.springautocreate.service.annotation.ListManage;
import cn.xkw.springautocreate.utils.DateUtil;
import cn.xkw.springautocreate.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/4 17:29
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: ServiceApiCreate
 */
@Service
@ListManage
public class ServiceApiCreate implements BaseSrBankCreate {

    private final Logger LOGGER = LoggerFactory.getLogger(DataResponseCreate.class);

    @Override
    public Boolean create(Model model) {
        try {
            // 配置模板信息
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(CommonData.TEMPLATE_DATA_API));
            Template template = cfg.getTemplate(CommonData.TEMPLATE_FILE_SERVICE_API_NAME);

            // 解析数据
            // 包名
            model.setPackageName(CommonData.DATA_API_PACKAGE_NAME + model.getCentName().split("-")[1] + ".per.api.impl");

            String XclassName = StringUtils.toLowerCaseFirstOne(model.getClassName());
            // 日期
            String date = DateUtil.getCurrenceTimeToString("yyyy-MM-dd HH:mm");

            // 建立数据模型（Map）
            Map<String, Object> root = new HashMap<>();

            // 输出数据
            File file = new File(CommonData.SRBANK_WRITE_FILE_PATH + model.getClassName());
            if (!file.exists()) {
                file.mkdirs();
            }
            File moduleFile = new File(CommonData.SRBANK_WRITE_FILE_PATH + model.getClassName() + "/Api" + model.getClassName() + "ServiceImpl.java");

            // 获取输出流
            OutputStream outputStream = new FileOutputStream(moduleFile);
            Writer out = new OutputStreamWriter(outputStream);
            root.put("packageName", model.getPackageName());
            root.put("className", model.getClassName());
            root.put("authorName", model.getAuthorName());
            root.put("submitType", model.getSubmitType());
            root.put("date", date);
            root.put("note", model.getNote());
            root.put("XclassName", XclassName);

            // 数据与模板合并输出
            template.process(root, out);
            out.flush();
        } catch (Exception e) {
            LOGGER.info("--- data response处理异常 ---", e);
            return false;
        }
        return true;
    }
}
