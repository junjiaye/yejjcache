package io.github.junjiaye.yejj.cache.commond.commons.set;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: SMembersCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class SMembersCommond implements Commond {

    @Override
    public String name() {
        return "SMEMBERS";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        return Reply.array(cache.smembers(key));
    }
}
