package io.github.junjiaye.yejj.cache.commond.commons.string;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: ExistsCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class ExistsCommond implements Commond {

    @Override
    public String name() {
        return "EXISTS";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String[] keys = getParams(args);
        return Reply.integer(cache.exists(keys));
    }
}
