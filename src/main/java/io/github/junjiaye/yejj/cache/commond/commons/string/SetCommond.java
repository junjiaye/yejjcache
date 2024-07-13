package io.github.junjiaye.yejj.cache.commond.commons.string;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: SetCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class SetCommond implements Commond {

    @Override
    public String name() {
        return "SET";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String value = getValue(args);
        cache.set(key,value);
        return Reply.string(OK);
    }
}
