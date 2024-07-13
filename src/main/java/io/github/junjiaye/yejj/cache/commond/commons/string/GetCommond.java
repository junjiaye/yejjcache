package io.github.junjiaye.yejj.cache.commond.commons.string;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: GetCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class GetCommond implements Commond {

    @Override
    public String name() {
        return "GET";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        cache.get(key);
        return Reply.bulkString(cache.get(key));
    }
}
