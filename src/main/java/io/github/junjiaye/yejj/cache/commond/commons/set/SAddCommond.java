package io.github.junjiaye.yejj.cache.commond.commons.set;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: SAddCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class SAddCommond implements Commond {

    @Override
    public String name() {
        return "SADD";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String[] values = getParamsNoKey(args);
        return Reply.integer(cache.sadd(key,values));
    }
}
