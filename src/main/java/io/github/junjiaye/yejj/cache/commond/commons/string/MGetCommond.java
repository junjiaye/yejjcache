package io.github.junjiaye.yejj.cache.commond.commons.string;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: MGetCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class MGetCommond implements Commond {

    @Override
    public String name() {
        return "MGET";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String[] keys = getParams(args);
        return Reply.array(cache.mget(keys));
    }

}
