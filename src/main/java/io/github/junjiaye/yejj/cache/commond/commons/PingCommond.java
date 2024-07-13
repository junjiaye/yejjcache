package io.github.junjiaye.yejj.cache.commond.commons;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: PingCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class PingCommond implements Commond {
    @Override
    public String name() {
        return "PING";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String ret = "PONG";
        if (args.length >= 5) {
            ret = args[4];
        }
        return Reply.string(ret);
    }
}
