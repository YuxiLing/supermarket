package com.summertrain.pre_common.template;

public class RespTemplate {
    private int status;//状态码
    private String content;//内容

    public RespTemplate() {
    }

    public RespTemplate(int status) {
        this.status = status;
    }

    public RespTemplate(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
