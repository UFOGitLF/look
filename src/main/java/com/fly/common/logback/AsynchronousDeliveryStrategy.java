package com.fly.common.logback;

/**
 * 异步传输策略
 *
 * Created by xinshidai on 17/10/31.
 */
public class AsynchronousDeliveryStrategy extends DeliveryStrategy {
    @Override
    public boolean send(MessageSender sender, JSONEvent event) {
        return sender.sendAsync(event);
    }
}
