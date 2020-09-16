package cn.xkw.springautocreate.service.create;

import cn.xkw.springautocreate.common.CommonData;
import cn.xkw.springautocreate.domain.Model;
import cn.xkw.springautocreate.service.annotation.ListManage;
import cn.xkw.springautocreate.utils.DateUtil;
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
 * @Date: 2020/9/4 17:27
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: DubboCreate
 */
@Service
@ListManage
public class DubboCreate implements BaseSrBankCreate {

    private final Logger LOGGER = LoggerFactory.getLogger(DubboCreate.class);

    @Override
    public Boolean create(Model model) {
        try {
            // 配置模板信息
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(CommonData.TEMPLATE_DATA_API));
            Template template = cfg.getTemplate(CommonData.TEMPLATE_FILE_DUBBO_NAME);

            // 解析数据
            // 日期
            String date = DateUtil.getCurrenceTimeToString("yyyy-MM-dd HH:mm");

            // 建立数据模型（Map）
            Map<String, Object> root = new HashMap<>();

            // 输出数据
            File file = new File(CommonData.SRBANK_WRITE_FILE_PATH + model.getClassName());
            if (!file.exists()) {
                file.mkdirs();
            }
            File moduleFile = new File(CommonData.SRBANK_WRITE_FILE_PATH + model.getClassName() + "/dubbo.txt");

            // 获取输出流
            OutputStream outputStream = new FileOutputStream(moduleFile);
            Writer out = new OutputStreamWriter(outputStream);
            root.put("className", model.getClassName());
            root.put("authorName", model.getAuthorName());
            root.put("date", date);
            root.put("note", model.getNote());
            root.put("centName", model.getCentName().split("-")[1]);

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
