package com.xiaozu.server.controller;

import com.xiaozu.server.configuration.jwt.JwtTokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author dongpo.li
 * @date 2022/1/14
 */
@Controller
@RequestMapping()
public class SystemController {
    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Resource
    private JwtTokenManager jwtTokenManager;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping({"", "/", "/index"})
    @ResponseBody
    public String index() {
        return "index";
    }

    @PostMapping("/add")
    @ResponseBody
    public String add() {
        return "add";
    }

}
