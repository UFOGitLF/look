package com.fly.modules.sys.dao;

import com.fly.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 * <p>
 * Created by xinshidai on 17/9/22.
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
    /**
     * 根据父菜单,查询子菜单
     *
     * @param parentId
     * @return
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return
     */
    List<SysMenuEntity> queryNotButtonList();

    List<SysMenuEntity> queryUserList(Long userId);
}
