package io.github.junjiaye.yejj.cache.commond.commons;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: InfoCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class InfoCommond implements Commond {
    private static final String INFO = "YeJJCache server[1.0.1],created by yejj." + CRLF;

    @Override
    public String name() {
        return "INFO";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        return Reply.bulkString(INFO);
    }
}
