package io.github.junjiaye.yejj.cache.commond.commons.list;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: LRangeCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class LRangeCommond implements Commond {

    @Override
    public String name() {
        return "LRANGE";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String[] paramsNoKey = getParamsNoKey(args);
        int start = Integer.parseInt(paramsNoKey[0]);
        int end = Integer.parseInt(paramsNoKey[1]);
        return Reply.array(cache.lranget(key,start,end));
    }

}
