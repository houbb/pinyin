package com.github.houbb.pinyin.support.mapping;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.pinyin.spi.IPinyinTone;

import java.util.List;

/**
 * 抽象拼音映射实现类
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public abstract class AbstractPinyinTone implements IPinyinTone {

    /**
     * 获取单个字的映射
     * @param segment 单个的字
     * @return 结果
     * @since 0.0.1
     */
    protected abstract String getCharTone(final String segment);

    /**
     * 获取词组的映射
     * @param segment 单个的字
     * @return 结果
     * @since 0.0.1
     */
    protected abstract String getPhraseTone(final String segment);

    @Override
    public String tone(String segment) {
        int length = segment.length();

        if(length == 1) {
            getCharTone(segment);
        }

        String result = getPhraseTone(segment);
        if(StringUtil.isNotEmpty(result)) {
            return result;
        }

        // 直接拆分为单个字，然后用空格分隔
        List<String> chars = StringUtil.toCharStringList(segment);
        List<String> tones = CollectionUtil.toList(chars, new IHandler<String, String>() {
            @Override
            public String handle(String string) {
                return getCharTone(string);
            }
        });

        return StringUtil.join(tones, StringUtil.BLANK);
    }

}
