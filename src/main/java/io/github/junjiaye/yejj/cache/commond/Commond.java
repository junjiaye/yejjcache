package io.github.junjiaye.yejj.cache.commond;

import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: Commond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:43
 */
public interface Commond {
    String CRLF = "\r\n";
    String OK = "OK";

    String name();
    Reply<?> exec(YeJJCache cache, String[] args);

    //
    default String getKey(String[] args){
        return args[4];
    }
    default String getValue(String[] args){
        return args[6];
    }
    default String[] getParams(String[] args){
        int len = (args.length - 3) / 2;
        String[] keys = new String[len];
        for (int i = 0; i < len; i++) {
            keys[i] = args[4 + i * 2];
        }
        return keys;
    }
    default String[] getParamsNoKey(String[] args){
        int len = (args.length - 5) / 2;
        String[] params = new String[len];
        for (int i = 0; i < len; i++) {
            params[i] = args[6 + i * 2];
        }
        return params;
    }

    default String[] getValues(String[] args) {
        int len = (args.length - 3) / 4;
        String[] vals = new String[len];
        for (int i = 0; i < len; i++) {
            vals[i] = args[6 + i * 4];
        }
        return vals;
    }

    default String[] getKeys(String[] args) {
        int len = (args.length - 3) / 4;
        String[] keys = new String[len];
        for (int i = 0; i < len; i++) {
            keys[i] = args[4 + i * 4];
        }
        return keys;
    }
    default String[] getHValues(String[] args) {
        int len = (args.length - 5) / 4;
        String[] vals = new String[len];
        for (int i = 0; i < len; i++) {
            vals[i] = args[8 + i * 4];
        }
        return vals;
    }

    default String[] getHKeys(String[] args) {
        int len = (args.length - 5) / 4;
        String[] keys = new String[len];
        for (int i = 0; i < len; i++) {
            keys[i] = args[6 + i * 4];
        }
        return keys;
    }
}
