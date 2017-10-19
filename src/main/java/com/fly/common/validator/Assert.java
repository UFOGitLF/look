package com.fly.common.validator;

import com.fly.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * <p>
 * Created by xinshidai on 17/9/25.
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object obj, String message) {
        if (obj == null) {
            throw new RRException(message);
        }

    }
}
