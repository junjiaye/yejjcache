package io.github.junjiaye.yejj.cache.commond.commons.hash;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: HLenCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class HLenCommond implements Commond {

    @Override
    public String name() {
        return "HLEN";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.hlen(key));
    }


}
