package io.github.junjiaye.yejj.cache.commond.commons.string;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: MSetCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class MSetCommond implements Commond {

    @Override
    public String name() {
        return "MSET";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String[] keys = getKeys(args);
        String[] values = getValues(args);
        cache.mset(keys,values);
        return Reply.string(OK);
    }


}