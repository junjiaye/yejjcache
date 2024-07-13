package io.github.junjiaye.yejj.cache.core;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

/**
 * @program: yejjcache
 * @ClassName: YeJJCache
 * @description:
 * @author: yejj
 * @create: 2024-06-16 15:36
 */
public class YeJJCache {

    Map<String,CacheEntry<?>> map = new HashMap<>();

    // ------------------ 1.String --------------------------
    public String get(String key) {

        CacheEntry<String> entry = (CacheEntry<String>) map.get(key);
        return entry.getValue();
    }

    public void set(String key , String value) {
        map.put(key,new CacheEntry<>(value));
    }
    public int del(String... keys) {
         if (keys == null){
             return 0;
         }
        return  (int)Arrays.stream(keys).map(map::remove).filter(Objects::nonNull).count();
    }
    public int exists(String... keys) {
        if (keys == null){
            return 0;
        }
        return  (int)Arrays.stream(keys).map(map::containsKey).filter(x->x).count();
    }

    public String[] mget(String... keys){
        if (keys == null){
            return new String[0];
        }
        return  Arrays.stream(keys).map(this::get).toArray(String[]::new);
    }

    public void mset(String[] keys, String[] vals) {
        if (keys == null || keys.length == 0){
            return;
        }
        for (int i = 0 ;i < keys.length ; i ++ ){
            set(keys[i],vals[i]);
        }
    }

    public int incr(String key){
        String str = get(key);
        int val = 0;
        try{
            if (str != null){
                val = Integer.parseInt(str);
            }
            val ++ ;
            set(key,String.valueOf(val));
        }catch (NumberFormatException e){
            throw  e;
        }
        return val;
    }
    public int decr(String key){
        String str = get(key);
        int val = 0;
        try{
            if (str != null){
                val = Integer.parseInt(str);
            }
            val -- ;
            set(key,String.valueOf(val));
        }catch (NumberFormatException e){
            throw  e;
        }
        return val;
    }

    public Integer strlen(String key) {
        String value = this.get(key);
        return value == null ? 0 : value.length();
    }

    //--------------------- 2.list ---------------------------------
    public Integer lpush(String key, String... values) {
        CacheEntry<LinkedList<String>>  entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null){
            entry = new CacheEntry<>(new LinkedList<>());
            this.map.put(key,entry);
        }
        LinkedList<String> vals = entry.getValue();
        Stream.of(values).forEach(vals::addFirst);
        return values.length;
    }

    public String[] lpop(String key, int count) {
        CacheEntry<LinkedList<String>>  entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null){
            return null;
        }
        LinkedList<String> values = entry.getValue();
        if (values == null){
            return null;
        }
        int len = Math.min(values.size(),count);
        String[] result = new String[len];
        int index = 0;
        while (index < len){
            result[index ++ ] = values.removeFirst();
        }
        return result;
    }

    public String[] rpop(String key, int count) {
        CacheEntry<LinkedList<String>>  entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null){
            return null;
        }
        LinkedList<String> values = entry.getValue();
        if (values == null){
            return null;
        }
        int len = Math.min(values.size(),count);
        String[] result = new String[len];
        int index = 0;
        while (index < len){
            result[index ++ ] = values.removeLast();
        }
        return result;
    }

    public Integer rpush(String key, String[] values) {
        CacheEntry<LinkedList<String>>  entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null){
            entry = new CacheEntry<>(new LinkedList<>());
            this.map.put(key,entry);
        }
        LinkedList<String> vals = entry.getValue();
        Stream.of(values).forEach(vals::addLast);
        return values.length;
    }

    public Integer llen(String key) {
        CacheEntry<LinkedList<String>>  entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null || entry.getValue() == null){
            return 0;
        }
        return entry.getValue().size();
    }

    public String lindex(String key, Integer index) {
        CacheEntry<LinkedList<String>>  entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null || entry.getValue() == null || index > entry.getValue().size()){
            return null;
        }
        return entry.getValue().get(index);
    }

    public String[] lranget(String key, int start, int end) {
        CacheEntry<LinkedList<String>>  entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) {
            return null;
        }
        LinkedList<String> exist = entry.getValue();
        if (exist == null || start > exist.size()) {
            return null;
        }
        int size = exist.size();
        if (end >= size){
            end = size;
        }
        int min = Math.min(size, end - start + 1);
        String[] ret = new String[min];
        for (int i = 0 ; i < min ; i++){
            ret[i] = exist.get(start + i);
        }
        return ret;
    }
    //--------------------- 3.set ---------------------------------
    public Integer sadd(String key, String[] values) {
        this.map.computeIfAbsent(key,k->new CacheEntry<>(new LinkedHashSet<>()));
        CacheEntry<LinkedHashSet<String>>  entry = (CacheEntry<LinkedHashSet<String>>) this.map.get(key);
        LinkedHashSet<String> vals = entry.getValue();
        vals.addAll(Arrays.asList(values));
        return values.length;
    }
    //获取set所有元素
    public String[] smembers(String key) {

        CacheEntry<LinkedHashSet<String>>  entry = (CacheEntry<LinkedHashSet<String>>) this.map.get(key);
        if (entry == null){
            return null;
        }
        LinkedHashSet<String> vals = entry.getValue();
        return vals.toArray(String[]::new);
    }

    public Integer scard(String key) {
        CacheEntry<LinkedHashSet<String>>  entry = (CacheEntry<LinkedHashSet<String>>) this.map.get(key);
        if (entry == null){
            return 0;
        }
        LinkedHashSet<String> vals = entry.getValue();
        return vals.size();
    }

    public Integer sismember(String key,String val) {
        CacheEntry<LinkedHashSet<String>>  entry = (CacheEntry<LinkedHashSet<String>>) this.map.get(key);
        if (entry == null){
            return 0;
        }
        LinkedHashSet<String> vals = entry.getValue();
        return vals.contains(val) ? 1 : 0;
    }

    public Integer srem(String key, String[] values) {
        CacheEntry<LinkedHashSet<String>>  entry = (CacheEntry<LinkedHashSet<String>>) this.map.get(key);
        if (entry == null){
            return 0;
        }
        LinkedHashSet<String> exist = entry.getValue();
        return  values==null ? 0 : (int)Arrays.stream(values).map(exist::remove).filter(x -> x).count();
    }

    Random random = new Random();

    public String[] spop(String key, int count) {
        CacheEntry<LinkedHashSet<String>>  entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null){
            return null;
        }
        LinkedHashSet<String> exist = entry.getValue();
        if (exist == null){
            return null;
        }
        int len = Math.min(exist.size(),count);
        String[] result = new String[len];
        int index = 0;
        while (index < len){
            String[] array = exist.toArray(new String[0]);
            String obj = array[random.nextInt(exist.size())];
            exist.remove(obj);
            result[index ++ ] = obj;
        }
        return result;
    }


    //--------------------- 4.hash ---------------------------------

    public Integer hset(String key, String[] hKeys, String[] hValues) {
        if (hKeys == null || hKeys.length ==0){
            return 0;
        }
        if (hValues == null || hValues.length ==0){
            return 0;
        }
        this.map.computeIfAbsent(key,k->new CacheEntry<>(new LinkedHashMap<>()));
        CacheEntry<LinkedHashMap<String,String>>  entry = (CacheEntry<LinkedHashMap<String,String>>) this.map.get(key);
        LinkedHashMap<String,String> exist = entry.getValue();
        for (int i = 0; i < hKeys.length; i++) {
            exist.put(hKeys[i],hValues[i]);
        }
        return hKeys.length;
    }

    public String hget(String key, String hKey) {
        CacheEntry<LinkedHashMap<String,String>>  entry = (CacheEntry<LinkedHashMap<String,String>>) this.map.get(key);
        if (entry == null){
            return null;
        }
        LinkedHashMap<String,String> exist = entry.getValue();
        return exist.get(hKey);
    }

    public String[] hgetall(String key) {
        CacheEntry<LinkedHashMap<String,String>>  entry = (CacheEntry<LinkedHashMap<String,String>>) this.map.get(key);
        if (entry == null){
            return null;
        }
        LinkedHashMap<String,String> exist = entry.getValue();
        return exist.entrySet().stream().flatMap(e -> Stream.of(e.getKey(),e.getValue())).toArray(String[]::new);
    }

    public String[] hmget(String key, String[] hkeys) {
        CacheEntry<LinkedHashMap<String,String>>  entry = (CacheEntry<LinkedHashMap<String,String>>) this.map.get(key);
        if (entry == null){
            return null;
        }
        LinkedHashMap<String,String> exist = entry.getValue();
        return hkeys == null ? new String[0] : Arrays.stream(hkeys).map(exist::get).toArray(String[]::new);
    }

    public Integer hlen(String key) {
        CacheEntry<LinkedHashMap<String,String>>  entry = (CacheEntry<LinkedHashMap<String,String>>) this.map.get(key);
        if (entry == null){
            return 0;
        }
        LinkedHashMap<String,String> exist = entry.getValue();
        return exist.size();
    }

    public Integer hexists(String key, String hKey) {
        CacheEntry<LinkedHashMap<String,String>>  entry = (CacheEntry<LinkedHashMap<String,String>>) this.map.get(key);
        if (entry == null){
            return 0;
        }
        LinkedHashMap<String,String> exist = entry.getValue();
        return exist.containsKey(hKey) ? 1 : 0;
    }

    public Integer hdel(String key, String[] hkeys) {
        CacheEntry<LinkedHashMap<String,String>>  entry = (CacheEntry<LinkedHashMap<String,String>>) this.map.get(key);
        if (entry == null){
            return 0;
        }
        LinkedHashMap<String,String> exist = entry.getValue();
        return hkeys == null ? 0 : (int)Arrays.stream(hkeys).map(exist::remove).filter(Objects::nonNull).count();
    }

    //--------------------- 5.zadd ---------------------------------

    public Integer zadd(String key, String[] vals, double[] scores) {
        this.map.computeIfAbsent(key,k->new CacheEntry<>(new LinkedHashSet<>()));
        CacheEntry<LinkedHashSet<ZsetEntry>>  entry = (CacheEntry<LinkedHashSet<ZsetEntry>>) this.map.get(key);
        LinkedHashSet<ZsetEntry> exits = entry.getValue();

        for (int i = 0; i < vals.length; i++) {
            exits.add(new ZsetEntry(vals[i],scores[i]));
        }

        return vals.length;
    }

    public Integer zcard(String key) {
        CacheEntry<LinkedHashSet<ZsetEntry>>  entry = (CacheEntry<LinkedHashSet<ZsetEntry>>) this.map.get(key);
        if (entry == null){
            return 0;
        }
        LinkedHashSet<ZsetEntry> vals = entry.getValue();
        return vals.size();
    }

    public Integer zcount(String key, double min, double max) {
        CacheEntry<LinkedHashSet<ZsetEntry>>  entry = (CacheEntry<LinkedHashSet<ZsetEntry>>) this.map.get(key);
        if (entry == null){
            return 0;
        }
        LinkedHashSet<ZsetEntry> vals = entry.getValue();
        return (int) vals.stream().filter(x -> x.getScore() > min && x.getScore() <= max).count();
    }

    public Double zscore(String key, String val) {
        CacheEntry<LinkedHashSet<ZsetEntry>>  entry = (CacheEntry<LinkedHashSet<ZsetEntry>>) this.map.get(key);
        if (entry == null){
            return null;
        }
        LinkedHashSet<ZsetEntry> vals = entry.getValue();
        return vals.stream().filter(x -> x.getValue().equals(val)).map(x -> x.getScore()).findFirst().orElse(null);
    }

    public Integer zrank(String key, String val) {
        CacheEntry<LinkedHashSet<ZsetEntry>>  entry = (CacheEntry<LinkedHashSet<ZsetEntry>>) this.map.get(key);
        if (entry == null){
            return null;
        }
        LinkedHashSet<ZsetEntry> exist = entry.getValue();
        Double score = zscore(key, val);
        if (score == null){
            return 0;
        }
        return (int)exist.stream().filter(x -> x.getScore() > score).count();
    }

    public Integer zrem(String key, String[] vals) {
        CacheEntry<LinkedHashSet<ZsetEntry>>  entry = (CacheEntry<LinkedHashSet<ZsetEntry>>) this.map.get(key);
        if (entry == null){
            return null;
        }
        LinkedHashSet<ZsetEntry> exist = entry.getValue();
        return  exist==null ? 0 : (int)Arrays.stream(vals).map(x -> exist.removeIf(y -> y.getValue().equals(x))).filter(x -> x).count();

    }
}
