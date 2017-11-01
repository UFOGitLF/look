package com.fly.common.logback;

import com.fly.common.utils.Constant;
import lombok.Data;

/**
 * Kafka配置类{@link org.apache.kafka.clients.producer.ProducerConfig}
 *
 * Created by xinshidai on 17/10/31.
 */
@Data
public class KafkaConfig {
    /**
     * 服务器地址
     */
    private String addresses;

    private String topic = Constant.DEFAULT_TOPIC;
    private String client = Constant.DEFAULT_KAFKA_CLIENT;
    private String keySerializer = Constant.DEFAULT_KEY_SERIALIZER;
    private String valueSerializer = Constant.DEFAULT_VALUE_SERIALIZER;
    private int connectionTimeout = Constant.DEFAULT_CONNECTION_TIMEOUT;
    private int maxBlockMs = 60000;
}
