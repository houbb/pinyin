package com.github.houbb.pinyin.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.pinyin.support.data.IPinyinData;

import java.util.Arrays;
import java.util.List;

/**
 * 声母韵母
 * @author binbin.hou
 * @since 0.1.1
 */
@ThreadSafe
public class PinyinData implements IPinyinData {

    /**
     * 零声母列表
     * a ai an ang ao e ê ei en eng er o ou
     * @since 0.1.1
     */
    private static final List<String> ZERO_SHENGMU_LIST = Arrays
            .asList("a", "ai", "an", "ang", "ao", "e", "ê", "ei", "en", "eng", "er", "o", "ou");


    @Override
    public String shengMu(String pinyinNormal) {
        return null;
    }

    @Override
    public String yunMu(String pinyinNormal) {
        return null;
    }

    @Override
    public boolean isZeroShengMu(String pinyinNormal) {
        return ZERO_SHENGMU_LIST.contains(pinyinNormal);
    }

}
