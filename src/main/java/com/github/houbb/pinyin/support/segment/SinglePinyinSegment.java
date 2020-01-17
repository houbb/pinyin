package com.github.houbb.pinyin.support.segment;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.pinyin.spi.IPinyinSegment;

import java.util.Collections;
import java.util.List;

/**
 * 单个字符的分词实现
 *
 * （1）为了保证 api 的统一性
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SinglePinyinSegment implements IPinyinSegment {

    @Override
    public List<String> segment(String string) {
        return Collections.singletonList(string);
    }

}
