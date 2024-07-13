package io.github.junjiaye.yejj.cache.commond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: yejjcache
 * @ClassName: Reply
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply<T> {
    T value;
    ReplyType type;

    public static Reply<String> string(String value){
        return new Reply<>(value,ReplyType.SIMPLE_STRING);
    }
    public static Reply<String> bulkString(String value){
        return new Reply<>(value,ReplyType.BULK_STRING);
    }
    public static Reply<Integer> integer(Integer value){
        return new Reply<>(value,ReplyType.INT);
    }
    public static Reply<String> error(String value){
        return new Reply<>(value,ReplyType.ERROR);
    }
    public static Reply<String[]> array(String[] values){
        return new Reply<>(values,ReplyType.ARRAY);
    }
}
