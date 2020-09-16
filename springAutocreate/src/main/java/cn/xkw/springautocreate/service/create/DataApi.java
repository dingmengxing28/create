//package cn.xkw.springautocreate.service.create;
//
//import cn.xkw.springautocreate.common.CommonData;
//import cn.xkw.springautocreate.domain.Model;
//import cn.xkw.springautocreate.utils.DateUtil;
//import cn.xkw.springautocreate.utils.StringUtils;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DataApi extends BaseCreate {
//
//    @Override
//    Template config() throws Exception {
//        // 配置模板信息
//        Configuration cfg = new Configuration();
//        cfg.setDirectoryForTemplateLoading(new File(CommonData.TEMPLATE_DATA_API));
//        Template template = cfg.getTemplate(CommonData.TEMPLATE_FILE_DATA_API_NAME);
//        return template;
//    }
//
//    @Override
//    Map<String, Object> parsetData(Model model) throws Exception {
//        // 包名
//        model.setPackageName(CommonData.DATA_API_PACKAGE_NAME + model.getCentName().split("-")[1] + ".per.api");
//        String XclassName = StringUtils.toLowerCaseFirstOne(model.getClassName());
//        // 日期
//        String date = DateUtil.getCurrenceTimeToString("yyyy-MM-dd HH:mm");
//        // 建立数据模型（Map）
//        Map<String, Object> map = new HashMap<>();
//        map.put("packageName", model.getPackageName());
//        map.put("className", model.getClassName());
//        map.put("authorName", model.getAuthorName());
//        map.put("date", date);
//        map.put("note", model.getNote());
//        map.put("XclassName", XclassName);
//        return map;
//    }
//
//    @Override
//    File createFile(Model model) throws Exception {
//        // 输出数据
//        File file = new File(CommonData.SRBANK_WRITE_FILE_PATH + model.getClassName());
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        File moduleFile = new File(CommonData.SRBANK_WRITE_FILE_PATH + model.getClassName() + "/" + "Api" + model.getClassName() + "Service.java");
//        return moduleFile;
//    }
//}
