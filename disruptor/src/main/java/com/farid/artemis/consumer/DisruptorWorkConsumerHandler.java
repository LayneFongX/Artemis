package com.farid.artemis.consumer;


import com.farid.artemis.domain.DisruptorMessage;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisruptorWorkConsumerHandler implements WorkHandler<DisruptorMessage> {

    private final int consumerId;

    /**
     * @param consumerId ： 消费者ID
     */
    public DisruptorWorkConsumerHandler(int consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(DisruptorMessage disruptorMessage) throws Exception {
        log.info("consumer-{}消费的消息内容为:{}", consumerId, disruptorMessage.getMessage());
    }
}
