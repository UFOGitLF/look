package com.fly.common.logback;

import ch.qos.logback.core.spi.ContextAwareBase;

/**
 * 日志传输策略
 *
 * Created by xinshidai on 17/10/31.
 */
public abstract class DeliveryStrategy extends ContextAwareBase{
    public abstract boolean send(MessageSender sender,JSONEvent event);
}
