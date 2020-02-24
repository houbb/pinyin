package com.github.houbb.pinyin.support.segment;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.pinyin.spi.IPinyinSegment;

/**
 * <p> project: pinyin-PinyinSegments </p>
 * <p> create on 2020/2/24 22:37 </p>
 *
 * @author Administrator
 * @since 0.1.1
 */
public final class PinyinSegments {

    private PinyinSegments(){}

    /**
     * chars 分词
     * @return 实现
     * @since 0.1.1
     */
    public static IPinyinSegment chars() {
        return Instances.singleton(CharPinyinSegment.class);
    }

    /**
     * 默认分词
     * @return 实现
     * @since 0.1.1
     */
    public static IPinyinSegment defaults() {
        return Instances.singleton(DefaultPinyinSegment.class);
    }

    /**
     * 单个分词
     * @return 实现
     * @since 0.1.1
     */
    public static IPinyinSegment single() {
        return Instances.singleton(SinglePinyinSegment.class);
    }
}
