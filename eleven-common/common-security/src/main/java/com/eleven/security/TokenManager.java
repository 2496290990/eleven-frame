package com.eleven.security;

import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author zhaojinhui
 * @date 2021/1/14 22:49
 * @apiNote 生成 token 工具类，采用jwt生成
 */
public class TokenManager {

    /** 设置token的有效时长 30分钟 */
    private long EXPIRATION_TIME = 30L * 60 * 1000;

    /** 编码秘钥 */
    private String PRIVATE_KEY = "eleven";

    public String createToken(String username){
        //生成token
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, PRIVATE_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 通过token信息获取用户信息
     * @param token 传过来的 token
     * @return
     */
    public String getUserInfo(String token){
        String userInfo = Jwts.parser()
                .setSigningKey(PRIVATE_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userInfo;

    }

    /**
     * 删除token
     * @param token 要删除的token信息
     */
    public void removeToken(String token){

    }

}
