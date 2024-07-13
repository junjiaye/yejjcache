package io.github.junjiaye.yejj.cache;

import org.springframework.stereotype.Component;

/**
 * @program: yejjcache
 * @ClassName: YeJJPlugin
 * @description:
 * @author: yejj
 * @create: 2024-06-16 10:40
 */

public interface YeJJPlugin {
    void init();
    void startup();
    void shutdown();
}
