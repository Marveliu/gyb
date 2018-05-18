package com.marveliu.app.commons.utils;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.nutz.integration.jedis.RedisService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.security.Key;
import java.io.*;
import java.util.Date;

/**
 * @author Marveliu
 * @since 09/05/2018
 **/

@IocBean
public class TokenUtil {
    @Inject
    private RedisService redisService;

    /**
     * 获取KEY
     *
     * @param appid appid
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Key getKey(String appid) throws IOException, ClassNotFoundException {
        Key key;
        byte[] obj = redisService.get(("api_token:" + appid).getBytes());
        if (obj != null) {
            ObjectInputStream keyIn = new ObjectInputStream(new ByteArrayInputStream(obj));
            key = (Key) keyIn.readObject();
            keyIn.close();
        } else {
            key = MacProvider.generateKey();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bao);
            oos.writeObject(key);
            obj = bao.toByteArray();
            redisService.set(("api_token:" + appid).getBytes(), obj);
            redisService.expire(("api_token:" + appid).getBytes(), 7202);//2小时零2秒后自动删除
        }
        return key;
    }

    /**
     * 生成token
     *
     * @param date  失效时间
     * @param appid appid
     * @return
     */
    public String generateToken(Date date, String appid) throws IOException, ClassNotFoundException {
        return Jwts.builder()
                .setSubject(appid)
                .signWith(SignatureAlgorithm.HS512, getKey(appid))
                .setExpiration(date)
                .compact();
    }

    /**
     * 验证token
     *
     * @param appid appid
     * @param token token
     * @return
     */
    public boolean verifyToken(String appid, String token) {
        try {
            return Jwts.parser().setSigningKey(getKey(appid)).parseClaimsJws(token).getBody().getSubject().equals(appid);
        } catch (Exception e) {
            return false;
        }
    }
}