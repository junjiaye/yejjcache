package io.github.junjiaye.yejj.cache.commond;

import io.github.junjiaye.yejj.cache.commond.commons.*;
import io.github.junjiaye.yejj.cache.commond.commons.hash.*;
import io.github.junjiaye.yejj.cache.commond.commons.list.*;
import io.github.junjiaye.yejj.cache.commond.commons.set.*;
import io.github.junjiaye.yejj.cache.commond.commons.string.*;
import io.github.junjiaye.yejj.cache.commond.commons.zset.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: yejjcache
 * @ClassName: Commonds
 * @description: register Commons
 * @author: yejj
 * @create: 2024-06-23 19:55
 */
public class Commonds {

    private static Map<String,Commond> ALL = new LinkedHashMap<>();

    static {
        initCommonds();
    }

    private static void initCommonds() {
        register(new PingCommond());
        register(new InfoCommond());
        register(new CommondCommond());
        //String
        register(new SetCommond());
        register(new GetCommond());
        register(new StrlenCommond());
        register(new ExistsCommond());
        register(new DelCommond());
        register(new IncrCommond());
        register(new DecrCommond());
        register(new MSetCommond());
        register(new MGetCommond());

        //list
        register(new LpushCommond());
        register(new LpopCommond());
        register(new RpopCommond());
        register(new RpushCommond());
        register(new LLenCommond());
        register(new LIndexCommond());
        register(new LRangeCommond());

        //set
        register(new SAddCommond());
        register(new SMembersCommond());
        register(new SIsMemberCommond());
        register(new SCardCommond());
        register(new SPopCommond());
        register(new SRemCommond());
        //hash
        register(new HSetCommond());
        register(new HGetCommond());
        register(new HGetallCommond());
        register(new HLenCommond());
        register(new HDelCommond());
        register(new HMGetCommond());
        register(new HExistsCommond());
        //zset
        register(new ZAddCommond());
        register(new ZCardCommond());
        register(new ZCountCommond());
        register(new ZRankCommond());
        register(new ZRemCommond());
        register(new ZScoreCommond());


    }

    public static void register(Commond commond){
        ALL.put(commond.name(),commond);
    }

    public static Commond get(String name){
        return ALL.get(name);
    }
    public static String[] getCommonsNames(){
        return ALL.keySet().toArray(new String[0]);
    }
}
