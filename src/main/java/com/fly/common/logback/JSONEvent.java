package com.fly.common.logback;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;

/**
 * 系统日志消息实体
 *
 * Created by xinshidai on 17/10/31.
 */
@Data
@Builder
public class JSONEvent implements LoggerEvent{
    /**
     * 来源应用
     */
    private String source;
    /**
     * 来源应用IP地址
     */
    private String host;

    /**
     * 日志文本
     */
    private String message;

    /**
     * 时间戳
     */
    @JSONField(name = "@timestamp")
    private String timestamp;

    /**
     * 日志输出类名
     */
    private String logger;

    /**
     * 日志级别
     */
    private String level;

    /**
     * 日志输出线程
     */
    private String thread;

    /**
     * 异常堆栈
     */
    private String throwable;
}
