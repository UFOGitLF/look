package com.fly.modules.job.task;

import com.fly.modules.sys.entity.SysUserEntity;
import com.fly.modules.sys.service.SysUserService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 第一个测试task
 * <p>
 * Created by xinshidai on 17/10/19.
 */
@Component("firstTask")
public class FirstTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService userService;

    //定时任务只能接受一个参数,如果有多个参数,使用JSON数据即可
    public void first(String params) {
        logger.info("我是一个带参数的first方法,正在被运行,参数为: {}", params);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SysUserEntity user = userService.queryObject(1L);

        System.out.println(ToStringBuilder.reflectionToString(user));
    }

    public void second() {
        logger.info("我是一个不带参数的second方法");
    }
}
