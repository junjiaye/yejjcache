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
public class ZScoreCommond implements Commond {

    @Override
    public String name() {
        return "ZCOUNT";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String val = getValue(args);
        return Reply.string(String.valueOf(cache.zscore(key,val)));
    }
}
