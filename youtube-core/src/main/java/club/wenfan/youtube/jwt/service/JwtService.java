package club.wenfan.youtube.jwt.service;

import club.wenfan.youtube.jwt.JwtAuthenticationRequest;

import javax.naming.AuthenticationException;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/8 16:41
 */

public interface JwtService {


    /**
     * 创建token
     * @author wenfan
     */
    String createAuthenticationToken(JwtAuthenticationRequest jwtAuthenticationRequest) throws AuthenticationException;


}
