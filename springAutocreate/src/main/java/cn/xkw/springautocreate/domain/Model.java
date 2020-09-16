package cn.xkw.springautocreate.domain;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/4 17:27
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: 生成数据实体
 */
public class Model {

    private String packageName; // 包名

    private String className; // 类名

    private String centName; // 中心名

    private String note; // 描述

    private String authorName; // 作者名

    private String fileType; // 文件类型

    private String submitType; // 提交类型

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCentName() {
        return centName;
    }

    public void setCentName(String centName) {
        this.centName = centName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }
}
