package com.summertrain.pre_config.sys.shiro;

import com.alibaba.fastjson.JSON;
import com.summertrain.pre_common.template.RespTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempDeal {

    @RequestMapping("/unauthorized")
    public String noauthorized(){
        RespTemplate respTemplate = new RespTemplate(803);//无权限
        return JSON.toJSONString(respTemplate);
    }

    @RequestMapping("/loginfail")
    public String loginfail(){
        RespTemplate respTemplate = new RespTemplate(800);
        return JSON.toJSONString(respTemplate);
    }

    @RequestMapping("/nologin")
    public String nologin(){
        RespTemplate respTemplate = new RespTemplate(802);//未登录
        return JSON.toJSONString(respTemplate);
    }

}
