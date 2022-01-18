package com.github.houbb.pinyin.support.chinese;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.pinyin.spi.IPinyinChinese;

/**
 * <p> project: pinyin-PinyinChineses </p>
 * <p> create on 2020/2/8 11:35 </p>
 *
 * @author Administrator
 * @since 0.0.7
 */
public final class PinyinChineses {

    private PinyinChineses(){}

    /**
     * 默认实现
     * @return 简单实现
     * @since 0.2.2
     */
    public static IPinyinChinese defaults() {
        return Instances.singleton(DefaultsPinyinChinese.class);
    }

}
