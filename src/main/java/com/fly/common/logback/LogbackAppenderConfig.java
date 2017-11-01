package com.fly.common.logback;

import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.fly.common.utils.Constant;
import com.fly.common.utils.IPUtils;
import lombok.Data;

import java.text.DateFormat;

/**
 * Logback AppenderConfig
 *
 * Created by xinshidai on 17/10/31.
 */
@Data
public abstract class LogbackAppenderConfig<E> extends UnsynchronizedAppenderBase<E>{
    /**
     * 日期格式化
     */
    private DateFormat df = Constant.DEFAULT_DATEFORMAT;
    /**
     * 日志来源应用
     */
    private String source = null;
    /**
     * 日志来源应用IP
     */
    private String host = IPUtils.getIp();
    /**
     * 字符编码
     */
    private String charset = Constant.DEFAULT_CHARSET;
}
