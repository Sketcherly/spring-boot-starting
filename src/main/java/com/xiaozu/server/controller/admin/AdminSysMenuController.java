package com.xiaozu.server.controller.admin;

import com.xiaozu.server.constant.Constant;
import com.xiaozu.server.domain.SysUser;
import com.xiaozu.server.domain.vo.BasicResponseObject;
import com.xiaozu.server.domain.vo.SysMenuNode;
import com.xiaozu.server.service.SysMenuService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dongpo.li
 * @date 2022/1/19
 */
@RestController
@RequestMapping(Constant.SERVER_ADMIN_NAMESPACE + "/sys/menu")
public class AdminSysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping("/tree")
    public BasicResponseObject<?> tree() {
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        List<SysMenuNode> menuNodeList = sysMenuService.selectUserMenuTree(user);

        return BasicResponseObject.ok(menuNodeList);
    }

}
