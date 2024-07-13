package io.github.junjiaye.yejj.cache.commond.commons.zset;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

import java.util.Arrays;

/**
 * @program: yejjcache
 * @ClassName: ZAddCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class ZRemCommond implements Commond {

    @Override
    public String name() {
        return "ZREM";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String[] vals = getParamsNoKey(args);
        return Reply.integer(cache.zrem(key,vals));
    }


}
