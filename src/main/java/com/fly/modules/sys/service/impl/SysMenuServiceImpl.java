package com.fly.modules.sys.service.impl;

import com.fly.common.utils.Constant;
import com.fly.modules.sys.dao.SysMenuDao;
import com.fly.modules.sys.entity.SysMenuEntity;
import com.fly.modules.sys.service.SysMenuService;
import com.fly.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单
 *
 * Created by xinshidai on 17/9/26.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{

    @Autowired
    private SysMenuDao menuDao;

    @Autowired
    private SysUserService userService;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenuEntity> userMenuList = menuList.stream().filter(menu -> menuIdList.contains(menu.getMenuId())).collect(Collectors.toList());
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return menuDao.queryListParentId(parentId);
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return menuDao.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = userService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);

        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<>();

        for(SysMenuEntity entity : menuList){
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){//目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;

    }

    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return menuDao.queryObject(menuId);
    }

    @Override
    public List<SysMenuEntity> queryList(Map<String, Object> map) {
        return menuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return menuDao.queryTotal(map);
    }

    @Override
    public void save(SysMenuEntity menu) {
        menuDao.save(menu);
    }

    @Override
    public void update(SysMenuEntity menu) {
        menuDao.update(menu);
    }

    @Override
    public void deleteBatch(Long[] menuIds) {
        menuDao.deleteBatch(menuIds);
    }

    @Override
    public List<SysMenuEntity> queryUserList(Long userId) {
        return menuDao.queryUserList(userId);
    }
}
