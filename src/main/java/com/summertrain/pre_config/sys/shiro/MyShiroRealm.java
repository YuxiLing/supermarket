package com.summertrain.pre_config.sys.shiro;

import com.summertrain.se_users.service.StaffService;
import com.summertrain.se_users.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import java.util.Map;

public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private StaffService staffService;
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username = (String) SecurityUtils.getSubject().getSession().getAttribute("currentUserId");

        Map<String,String> map = userService.getUserToken(username);
        if (map==null||map.size()==0){
            map = staffService.getStaffToken(username);
            info.addStringPermission(map.get("identity"));
        }else{
            info.addStringPermission("boss");
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken usertoken=(UsernamePasswordToken) token;
        String realm = "boss";
        Map<String,String> map = userService.getUserToken(usertoken.getUsername());
        if (map==null||map.size()==0){
            map = staffService.getStaffToken(usertoken.getUsername());
            realm = map.get("identity");
            if (realm==null){
                realm = "null";
            }
        }
        String username = map.get("username");
        String password = map.get("password");
        if (username==null||password==null){
            username = "";
            password = "";
        }

        return new SimpleAuthenticationInfo(username,password, realm);

    }

}
