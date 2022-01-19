package com.xiaozu.server.controller.admin;

import com.xiaozu.server.configuration.jwt.JwtTokenManager;
import com.xiaozu.server.constant.Constant;
import com.xiaozu.server.domain.vo.BasicResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author dongpo.li
 * @date 2022/1/14
 */
@RestController
@RequestMapping(Constant.SERVER_ADMIN_NAMESPACE)
public class AdminSystemController {
    private static final Logger logger = LoggerFactory.getLogger(AdminSystemController.class);

    @Resource
    private JwtTokenManager jwtTokenManager;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping({"", "/", "/index"})
    @ResponseBody
    public String index() {
        return "admin index";
    }

    @PostMapping("/add")
    @ResponseBody
    public String add() {
        return "admin add";
    }

    @PostMapping("/login")
    @ResponseBody
    public BasicResponseObject<String> login(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            return BasicResponseObject.fail(10000, "账号或密码错误");
        } catch (AuthenticationException e) {
            logger.error("登录校验失败", e);
            return BasicResponseObject.fail(10000, "登录未知异常");
        }
        String token = jwtTokenManager.generateJwtToken(username);

        return BasicResponseObject.ok(token);
    }

}
