package com.fly.common.utils;


import org.apache.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * Created by xinshidai on 17/9/18.
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常,请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);

    }

    public static R error(int code) {

        //获取对应错误码的错误信息
        String msg = MessageContainer.getMsg(code);

        if (!StringUtils.isEmpty(msg)) {
            return R.error(code, msg);
        }
        return null;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;

    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}