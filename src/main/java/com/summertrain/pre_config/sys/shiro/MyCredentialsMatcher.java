package com.summertrain.pre_config.sys.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        Object tokenCredentials = getCredentials(token);
        Object accountCredentials = getCredentials(info);

        String tokenPassword = new String((char[]) tokenCredentials);
        String infoPassword = accountCredentials.toString();
        return tokenPassword.equals(infoPassword);
    }
}
