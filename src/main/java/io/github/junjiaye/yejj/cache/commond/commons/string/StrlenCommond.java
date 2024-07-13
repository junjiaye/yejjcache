package io.github.junjiaye.yejj.cache.commond.commons.string;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: StrlengCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class StrlenCommond implements Commond {

    @Override
    public String name() {
        return "STRLEN";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.strlen(key));
    }
}
