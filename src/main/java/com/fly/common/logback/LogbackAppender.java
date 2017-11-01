package com.fly.common.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import lombok.Data;

import java.util.Date;

/**
 * Logback Appender
 *
 * Created by xinshidai on 17/10/31.
 */
@Data
public class LogbackAppender extends LogbackAppenderConfig<ILoggingEvent> {

    private BuildStrategy buildStrategy;

    private DeliveryStrategy deliveryStrategy = new AsynchronousDeliveryStrategy();

    private MessageSender sender;

    @Override
    protected void append(ILoggingEvent event) {
        if (sender != null) {
            IThrowableProxy tp = event.getThrowableProxy();
            deliveryStrategy.send(sender, JSONEvent.builder().host(this.getHost())
                    .level(event.getLevel().toString())
                    .source(this.getSource())
                    .message(event.getFormattedMessage())
                    .timestamp(this.getDf().format(new Date(event.getTimeStamp())))
                    .logger(event.getLoggerName())
                    .thread(event.getThreadName())
                    .throwable(tp == null ? null : ThrowableProxyUtil.asString(tp)).build());
        }
    }
    public LogbackAppender(){
        super();
    }

    @Override
    public void start() {
        addInfo("LogbackAppender 启动");
        sender = buildStrategy.build();
        if (sender != null) {
            sender.init();
        }
        super.start();
    }

    @Override
    public void stop() {
        addInfo("LogbackAppender 停止");
        super.stop();
        if (sender != null) {
            sender.destroy();
        }
    }
}
