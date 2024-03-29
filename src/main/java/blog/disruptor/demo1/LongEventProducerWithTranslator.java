package blog.disruptor.demo1;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;
 
/**
 * post-3.0 the preferred approach for publishing messages is 
 * via the Event Publisher/Event Translator portion of the API. E.g.
 * @author harry
 *
 */
public class LongEventProducerWithTranslator {
	private final RingBuffer<LongEvent> ringBuffer;
 
    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }
 
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
        new EventTranslatorOneArg<LongEvent, ByteBuffer>()
        {
            @Override
            public void translateTo(LongEvent event, long sequence, ByteBuffer bb)
            {
                event.set(bb.getLong(0));
            }
        };
 
    public void product(ByteBuffer bb)
    {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}