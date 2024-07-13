package io.github.junjiaye.yejj.cache.commond.commons.hash;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: HSetCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class HSetCommond implements Commond {

    @Override
    public String name() {
        return "HSET";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String[] hKeys = getHKeys(args);
        String[] hValues = getHValues(args);
        return Reply.integer(cache.hset(key,hKeys,hValues));
    }


}
