package io.github.junjiaye.yejj.cache.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: yejjcache
 * @ClassName: YeJJCacheDecoder
 * @description:
 * @author: yejj
 * @create: 2024-06-16 11:13
 */
public class YeJJCacheDecoder extends ByteToMessageDecoder {

    AtomicLong counter = new AtomicLong();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("YeJJCacheDecoder decodeCount : " + counter.incrementAndGet());
        if (in.readableBytes() <=0 ){
            return;
        }
        int count = in.readableBytes();
        int index = in.readerIndex();
        System.out.println("count : " + count + " , index : "+ index);
        byte[] bytes = new byte[count];
        in.readBytes(bytes);
        String ret = new String(bytes);
        System.out.println("ret: "+ ret);
        out.add(ret);
    }
}
