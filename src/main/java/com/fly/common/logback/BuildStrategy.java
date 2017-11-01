package com.fly.common.logback;

/**
 * 构建策略
 *
 * Created by xinshidai on 17/10/31.
 */
public abstract class BuildStrategy {
    /**
     * 消息发送者
     */
    protected static MessageSender sender;

    /**
     * 获取发送者
     *
     */
    public static MessageSender sender() {
        return sender;
    }

    /**
     * 构建 {@link MessageSender}
     *
     */
    public abstract MessageSender build();

}
