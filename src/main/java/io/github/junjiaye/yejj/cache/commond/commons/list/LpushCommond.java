package io.github.junjiaye.yejj.cache.commond.commons.list;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: LpushCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class LpushCommond implements Commond {

    @Override
    public String name() {
        return "LPUSH";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String[] values = getParamsNoKey(args);
        return Reply.integer(cache.lpush(key,values));
    }
}
