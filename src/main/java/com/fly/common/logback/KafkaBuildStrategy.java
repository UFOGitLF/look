package com.fly.common.logback;

import lombok.Data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * Kafka 构建策略
 *
 * Created by xinshidai on 17/10/31.
 */
@Data
public class KafkaBuildStrategy extends BuildStrategy{

    private KafkaConfig config;

    @Override
    public MessageSender build() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getConfig().getAddresses());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, this.getConfig().getClient());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.getConfig().getConnectionTimeout());
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, this.getConfig().getMaxBlockMs());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        MessageSender sender = KafkaSender.builder().topic(this.getConfig().getTopic()).producer(new KafkaProducer<>(props)).build();
        BuildStrategy.sender = sender;

        return sender;
    }
}
