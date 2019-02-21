package club.wenfan.youtube.jwt.service.impl;

import club.wenfan.youtube.jwt.JwtAuthenticationRequest;
import club.wenfan.youtube.jwt.service.JwtService;
import club.wenfan.youtube.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/8 16:47
 */
@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public String createAuthenticationToken(JwtAuthenticationRequest jwtAuthenticationRequest) throws AuthenticationException {

        return null;
    }
}
