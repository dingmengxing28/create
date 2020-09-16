//package cn.xkw.springautocreate.service.create;
//
//import cn.xkw.springautocreate.domain.Model;
//import freemarker.template.Template;
//
//import java.io.*;
//import java.util.Map;
//
//public abstract class BaseCreate {
//
//    public void create(Model model) {
//        try {
//            outPut(config(), parsetData(model), createFile(model));
//        } catch (Exception e) {
//
//        }
//    }
//
//    abstract Template config() throws Exception;
//
//    abstract Map<String, Object> parsetData(Model model) throws Exception;
//
//    abstract File createFile(Model model) throws Exception;
//
//    public void outPut(Template template, Map<String, Object> map, File file) throws Exception{
//        // 获取输出流
//        OutputStream outputStream = new FileOutputStream(file);
//        Writer out = new OutputStreamWriter(outputStream);
//        // 数据与模板合并输出
//        template.process(map, out);
//        out.flush();
//    }
//}
