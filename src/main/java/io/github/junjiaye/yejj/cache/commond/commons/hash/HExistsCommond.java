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
public class HExistsCommond implements Commond {

    @Override
    public String name() {
        return "HEXISTS";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String hKey = getValue(args);
        return Reply.integer(cache.hexists(key,hKey));
    }


}
