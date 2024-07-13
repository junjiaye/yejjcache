package io.github.junjiaye.yejj.cache.commond.commons.zset;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

import java.util.Arrays;

/**
 * @program: yejjcache
 * @ClassName: ZAddCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class ZAddCommond implements Commond {

    @Override
    public String name() {
        return "ZADD";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        String[] scores = getHKeys(args);
        String[] vals = getHValues(args);
        return Reply.integer(cache.zadd(key,vals,toDouble(scores)));
    }

    private double[] toDouble(String[] scores) {
        return Arrays.stream(scores).mapToDouble(Double::parseDouble).toArray();
    }
}
