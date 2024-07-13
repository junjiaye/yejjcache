package io.github.junjiaye.yejj.cache.commond.commons.list;

import io.github.junjiaye.yejj.cache.commond.Commond;
import io.github.junjiaye.yejj.cache.commond.Reply;
import io.github.junjiaye.yejj.cache.core.YeJJCache;

/**
 * @program: yejjcache
 * @ClassName: LpopCommond
 * @description:
 * @author: yejj
 * @create: 2024-06-23 19:54
 */
public class LpopCommond implements Commond {

    @Override
    public String name() {
        return "LPOP";
    }

    @Override
    public Reply<?> exec(YeJJCache cache, String[] args) {
        String key = getKey(args);
        int count = 1;
        if (args.length >6){
            String val = getValue(args);
            count = Integer.parseInt(val);
            return Reply.array(cache.rpop(key,count));

        }
        //不带参数返回字符串
        String[] lpop = cache.rpop(key, count);
        return Reply.bulkString(lpop == null?null:lpop[0]);
    }

}
