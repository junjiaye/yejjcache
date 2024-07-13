package io.github.junjiaye.yejj.cache.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: yejjcache
 * @ClassName: ZsetEntry
 * @description:
 * @author: yejj
 * @create: 2024-07-04 07:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZsetEntry {
    private String value;
    private double score;
}
