package com.fly.dynamicdatasource;

/**
 * 多数据源在此配置
 * <p>
 * Created by xinshidai on 17/9/19.
 */
public enum DataSourceContext {
    FIRST("first"),
    SECOND("second");

    private String name;

    DataSourceContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
