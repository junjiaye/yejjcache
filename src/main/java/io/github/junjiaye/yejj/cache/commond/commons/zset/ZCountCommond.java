package io.github.junjiaye.yejj.cache.commond.commons.zset;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: SCardCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class ZCountCommond implements Commond {

    @Override
    public String name() {
        return "ZCOUNT";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        double min = Double.parseDouble(getValue(args));
        double max = Double.parseDouble(args[8]);
        return Reply.integer(cache.zcount(key,min,max));
    }
}
