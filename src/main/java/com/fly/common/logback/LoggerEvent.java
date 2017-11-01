package com.fly.common.logback;

/**
 * 日志时间,自定义日志需实现该接口
 *
 * Created by xinshidai on 17/10/31.
 */
public interface LoggerEvent {
    String getTimestamp();
}
