/*
 * Chsi
 * Created on 2021-02-08
 */
package com.perfat.boot.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class JwtUtil {
    //距离过期有效期
    private static final int ADVANCE_EXPIRE_TIME = 5;
    private static final int JWT_EXPIRE_TIME_LONG = 30 * 60 * 1000;
    /**
     * 获取用户从token中
     */
    public String getUserFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch ( ExpiredJwtException expiredJwtException ) {
            return true;
        }
    }

    /**
     * 检查当前token是否还能继续使用
     * true：可以 false：不建议
     *
     * @param token 验证标识
     * @return
     */
    public boolean checkToken(String token) {
        SecretKey secretKey = this.createSecretKey();
        try {
            //jwt正常情况 则判断失效时间是否大于5分钟
            long expireTime = Jwts.parser()   //得到DefaultJwtParser
                              .setSigningKey(secretKey)  //设置签名的秘钥
                              .parseClaimsJws(token.replace("jwt_", ""))
                              .getBody().getExpiration().getTime();
            long diff = expireTime - System.currentTimeMillis();
            //如果有效期小于5分钟，则不建议继续使用该token
            if ( diff < ADVANCE_EXPIRE_TIME ) {
                return false;
            }
        } catch ( Exception e ) {
            return false;
        }
        return true;
    }

    /**
     * 创建新token
     *
     * @param userId 用户ID
     */
    public String createToken(String userId) {
        String jwtVersion = "V.1";
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        //Map<String,Object> claims = new HashMap<String,Object>();//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        SecretKey secretKey = createSecretKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
              //.setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(UUID.randomUUID().toString()) //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)                    //iat: jwt的签发时间
                .setSubject(userId + "-" + jwtVersion)//sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, secretKey);//设置签名使用的签名算法和签名使用的秘钥
        //设置过期时间
        long expMillis = nowMillis + JWT_EXPIRE_TIME_LONG;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        String newToken = "jwt_" + builder.compact();
        return newToken;
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt的payload部分
     */
    public Claims getClaimFromToken(String token) {
        SecretKey secretKey = createSecretKey();
        return Jwts.parser()   //得到DefaultJwtParser
                   .setSigningKey(secretKey)  //设置签名的秘钥
                   .parseClaimsJws(token.replace("jwt_", ""))
                   .getBody();
    }

    // 签名私钥
    private SecretKey createSecretKey() {
        String signKey = "qwertyuiopasdfghjklzxcvbnm123456789";
        byte[] encodedKey = Base64.decodeBase64(signKey);//本地的密码解码
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");//根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return secretKey;
    }
}
