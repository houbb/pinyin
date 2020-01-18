package com.github.houbb.pinyin.support.mapping;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.pinyin.spi.IPinyinTone;

import java.util.Collections;
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
     * 获取单个字符的所有拼音列表
     * @param chinese 中文字符
     * @return 结果列表
     * @since 0.0.2
     */
    protected abstract List<String> getCharTones(final String chinese);

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
            getCharToneDefault(segment);
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
                return getCharToneDefault(string);
            }
        });

        return StringUtil.join(tones, StringUtil.BLANK);
    }

    /**
     * 处理单个字符没有结果的情况
     * 1. 如果有拼音，则返回对应的拼音
     * 2. 如果没有，则返回单个字符本身（比如字典中没有录入的繁体字等）
     * @param string 字符串
     * @return 结果
     * @since 0.0.3
     */
    private String getCharToneDefault(final String string) {
        String pinyin = getCharTone(string);

        if(StringUtil.isNotEmpty(pinyin)) {
            return pinyin;
        }

        return string;
    }

    @Override
    public List<String> toneList(String chinese) {
        return getCharTonesDefault(chinese);
    }

    /**
     * 多音字列表的默认处理
     * @param chinese 英文字符
     * @return 结果
     * @since 0.0.3
     */
    private List<String> getCharTonesDefault(final String chinese) {
        List<String> toneList = getCharTones(chinese);

        if(CollectionUtil.isNotEmpty(toneList)) {
            return toneList;
        }

        return Collections.singletonList(chinese);
    }

}
