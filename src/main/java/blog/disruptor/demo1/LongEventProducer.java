package blog.disruptor.demo1;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 生产者，生产longEvent事件
 *
 * @author harry
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void product(ByteBuffer bb) {
        //可以把ringBuffer看作一个事件队列，那么next下一个时间槽
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence

            //bb.getLong(0) ?
            event.set(bb.getLong(0));  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}