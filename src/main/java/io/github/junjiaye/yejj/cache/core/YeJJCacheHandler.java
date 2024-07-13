package io.github.junjiaye.yejj.cache.core;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Commonds;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: yejjcache
 * @ClassName: YeJJCacheHandler
 * @description:
 * @author: yejj
 * @create: 2024-06-16 11:01
 */
public class YeJJCacheHandler extends SimpleChannelInboundHandler<String> {

    private static final String CRLF = "\r\n";
    private static final String STR_PREFIX = "+";
    private static final String BULK_PREFIX = "$";
    private static final String OK = "OK";
    private static final String INFO = "YeJJCache server[1.0.0.0],created by yejj." + CRLF;
    public static final YeJJCache cache = new YeJJCache();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {

        String[] args = message.split(CRLF);
        System.out.println("YeJJCacheHandler ==> " + String.join(",", args));
        String cmd = args[2].toUpperCase();
        Commond commond = Commonds.get(cmd);
        if (commond != null){
            try {
                Reply<?> reply = commond.exec(cache,args);
                System.out.println("CMD[" + cmd + "] => " + reply.getType() + "==>" + reply.getValue());
                replyContext(ctx,reply);
            } catch (Exception e) {
                Reply<?> reply = Reply.error("ERR exception with msg :'" + e.getMessage() + "'");
                replyContext(ctx,reply);
            }
        }else {
            Reply<?> reply = Reply.error("ERR unkonwn commons '" + cmd + "'");
            replyContext(ctx,reply);
        }
//        if ("COMMAND".equals(cmd)) {
//            writeByteBuf(ctx,
//                    "*2" + CRLF + "$7" + CRLF + "COMMAND" + CRLF + "$4" + CRLF + "PING" + CRLF);
//        }
//        else if ("PING".equals(cmd)) {
//            String ret = "PONG";
//            if (args.length >= 5) {
//                ret = args[4];
//            }
//            simpleString(ctx, "+" + ret + CRLF);
//        } else if ("INFO".equals(cmd)) {
//            bulkString(ctx, INFO);
//        }
//        else if ("SET".equals(cmd)) {
//            cache.set(args[4], args[6]);
//            simpleString(ctx, OK);
//        } else if ("GET".equals(cmd)) {
//            String value = cache.get(args[4]);
//            bulkString(ctx, value);
//        } else if ("STRLEN".equals(cmd)) {
//            String value = cache.get(args[4]);
//            integer(ctx, value == null ? 0 : value.length());
//        } else if ("EXISTS".equals(cmd)) {
//            int len = (args.length - 3) / 2;
//            String[] keys = new String[len];
//            for (int i = 0; i < len; i++) {
//                keys[i] = args[4 + i * 2];
//            }
//            integer(ctx, cache.exists(keys));
//        } else if ("DEL".equals(cmd)) {
//            int len = (args.length - 3) / 2;
//            String[] keys = new String[len];
//            for (int i = 0; i < len; i++) {
//                keys[i] = args[4 + i * 2];
//            }
//            integer(ctx, cache.del(keys));
//        } else if ("MGET".equals(cmd)) {
//            int len = (args.length - 3) / 2;
//            String[] keys = new String[len];
//            for (int i = 0; i < len; i++) {
//                keys[i] = args[4 + i * 2];
//            }
//            String[] values = cache.mget(keys);
//            array(ctx, values);
//        } else if ("MSET".equals(cmd)) {
//            int len = (args.length - 3) / 4;
//            String[] keys = new String[len];
//            String[] vals = new String[len];
//            for (int i = 0; i < len; i++) {
//                keys[i] = args[4 + i * 4];
//                vals[i] = args[6 + i * 4];
//            }
//            cache.mset(keys, vals);
//            simpleString(ctx, OK);
//        } else if ("INCR".equals(cmd)) {
//            String key = args[4];
//            try{
//                integer(ctx,cache.incr(key));
//            }catch (NumberFormatException e){
//                error(ctx,"NFE : " + key + "value [" + cache.get(key) +  "] is not a integer.");
//            }
//        } else if ("DECR".equals(cmd)) {
//            String key = args[4];
//            try{
//                integer(ctx,cache.decr(key));
//            }catch (NumberFormatException e){
//                error(ctx,"NFE : " + key + "value [" + cache.get(key) +  "] is not a integer.");
//            }
//
//        } else {
//            simpleString(ctx, OK);
//        }
    }

    private void replyContext(ChannelHandlerContext ctx, Reply<?> reply) {
        switch (reply.getType()){
            case INT:
                integer(ctx,(Integer) reply.getValue());
                break;
            case ARRAY:
                array(ctx,(String[]) reply.getValue());
                break;
            case ERROR:
                error(ctx,(String) reply.getValue());
                break;
            case BULK_STRING:
                bulkString(ctx,(String)reply.getValue());
                break;
            case SIMPLE_STRING:
                simpleString(ctx,(String)reply.getValue());
                break;
            default:
                simpleString(ctx,OK);
        }
    }

    private void error(ChannelHandlerContext ctx, String msg) {
        writeByteBuf(ctx, errorEncode(msg));

    }

    private String errorEncode(String msg) {
        return "-" + msg + CRLF;
    }


    private void integer(ChannelHandlerContext ctx, int i) {
        writeByteBuf(ctx, integerEncode(i));
    }
    private String integerEncode(int i) {
        return ":" + i + CRLF;
    }
    private void array(ChannelHandlerContext ctx, String[] arrays) {

        writeByteBuf(ctx, arrayEncode(arrays));

    }
    private String arrayEncode(Object[] arrays) {
        StringBuilder sb = new StringBuilder();
        if (arrays == null) {
            sb.append("*-1" + CRLF);
        } else if (arrays.length == 0) {
            sb.append("*0" + CRLF);
        } else {
            sb.append("*").append(arrays.length).append(CRLF);
            for (Object obj : arrays) {
                if (obj == null){
                    sb.append("$-1" + CRLF);
                }else {
                    if (obj instanceof Integer i){
                        sb.append(integerEncode(i));
                    }else if(obj instanceof String s){
                        sb.append(bulkStringEncode(s));
                    }else if (obj instanceof Object[] o){
                        sb.append(arrayEncode(o));
                    }
                }
            }
        }
        return sb.toString();
    }

    private void simpleString(ChannelHandlerContext ctx, String content) {
        writeByteBuf(ctx, simpleStringEncode(content));
    }

    private String simpleStringEncode(String content) {
        String ret;
        if (content == null) {
            ret = "$-1";
        } else if (content.isEmpty()) {
            ret = "$0";
        } else {
            ret = STR_PREFIX + content;
        }
        return ret + CRLF;
    }

    private void bulkString(ChannelHandlerContext ctx, String content) {
        writeByteBuf(ctx, bulkStringEncode(content));
    }

    private String bulkStringEncode(String content) {
        String ret;
        if (content == null) {
            ret = "$-1";
        } else if (content.isEmpty()) {
            ret = "$0";
        } else {
            ret = BULK_PREFIX + content.length() + CRLF + content;
        }
        return ret + CRLF;
    }

    private void writeByteBuf(ChannelHandlerContext ctx, String content) {
        System.out.println("wrap byte buffer and reply :" + content);
        ByteBuf buffer = Unpooled.buffer(128);
        buffer.writeBytes(content.getBytes());
        ctx.writeAndFlush(buffer);
    }
}
