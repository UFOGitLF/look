package com.fly.common.utils;

import com.google.common.base.Charsets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 常量
 * <p>
 * Created by xinshidai on 17/9/20.
 */
public class Constant {
    //默认字符编码
    public static final String DEFAULT_CHARSET = Charsets.UTF_8.name();
    //默认日志格式化
    public static final DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");

    //默认Topic
    public static final String DEFAULT_TOPIC = "log-stash";

    public static final String DEFAULT_KAFKA_CLIENT = "LogstashClient";

    public static final String DEFAULT_KEY_SERIALIZER = "org.apache.kafka.common.serialization.IntegerSerializer";

    public static final String DEFAULT_VALUE_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

    public static final int DEFAULT_CONNECTION_TIMEOUT = 5000;



    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1);
        /**
         * 阿里云
         */
        /**
         * ALIYUN(2);
         */

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

}
