package io.github.junjiaye.yejj.cache.commond.commons.list;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: LIndexCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class LIndexCommond implements Commond {

    @Override
    public String name() {
        return "LINDEX";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        Integer index = Integer.valueOf(getValue(args));
        return Reply.bulkString(cache.lindex(key,index));
    }

}
