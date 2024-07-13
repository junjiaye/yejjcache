package io.github.junjiaye.yejj.cache.commond.commons;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Commonds;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: CommondCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 20:14
 */
public class CommondCommond implements Commond {

    @Override
    public String name() {
        return "COMMOND";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        return Reply.array(Commonds.getCommonsNames());
    }
}