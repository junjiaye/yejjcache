package io.github.junjiaye.yejj.cache.commond.commons.hash;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: HGetallCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class HGetallCommond implements Commond {

    @Override
    public String name() {
        return "HGETALL";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        return Reply.array(cache.hgetall(key));
    }


}
