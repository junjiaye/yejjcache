package io.github.junjiaye.yejj.cache.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: yejjcache
 * @ClassName: CacheEntry
 * @description:
 * @author: yejj
 * @create: 2024-06-24 07:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheEntry<T> {
    private T value;
}
