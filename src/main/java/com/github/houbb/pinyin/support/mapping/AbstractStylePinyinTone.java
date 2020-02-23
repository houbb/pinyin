package com.github.houbb.pinyin.support.mapping;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.model.CharToneInfo;
import com.github.houbb.pinyin.model.ToneItem;
import com.github.houbb.pinyin.util.ToneHelper;

import java.util.List;

/**
 * 抽象拼音格式化实现类
 *
 * @author binbin.hou
 * @since 0.0.3
 */
public abstract class AbstractStylePinyinTone extends DefaultPinyinTone {

    /**
     * 获取单个字符拼音的格式化
     * @param tone 分段后的拼音
     * @param toneInfo 字符的声调信息
     * @return 格式化结果
     * @since 0.0.3
     */
    protected abstract String getCharFormat(final String tone, final CharToneInfo toneInfo);

    /**
     * 单个字的拼音格式化处理
     * （1）如果和原来一样，则直接返回。
     * @param segment 分词结果
     * @return 对应的格式化结果
     */
    @Override
    protected String getCharTone(String segment) {
        String tone = super.getCharTone(segment);
        if(StringUtil.isEmpty(tone)) {
            return tone;
        }

        // 进行格式化
        CharToneInfo toneInfo = super.getCharToneInfo(tone);
        return getCharFormat(tone, toneInfo);
    }

    @Override
    protected String getPhraseTone(String segment) {
        String tone = super.getPhraseTone(segment);
        if(StringUtil.isEmpty(tone)) {
            return tone;
        }

        // 进行格式化
        return getPhraseFormat(tone);
    }

    /**
     * 获取单个词组拼音的格式化
     * （1）空格分隔
     * （2）调用单个字符的处理结果
     *
     * 词组一般只有4个字
     * 简单优化：使用 {@link ThreadLocal} 线程安全
     * @param phraseTone 词组的原始拼音
     * @return 格式化结果
     * @since 0.0.3
     */
    private String getPhraseFormat(final String phraseTone) {
        String[] strings = phraseTone.split(StringUtil.BLANK);

        List<String> toneList = Guavas.newArrayList(strings.length);
        for(String string : strings) {
            CharToneInfo toneInfo = super.getCharToneInfo(string);
            String formatTone = getCharFormat(string, toneInfo);
            toneList.add(formatTone);
        }
        return StringUtil.join(toneList, StringUtil.BLANK);
    }

    /**
     * 对信息进行连接
     * @param tone 拼音
     * @param index 标注的下表
     * @param letter 需要额外添加的信息
     * @return 结果
     * @since 0.0.3
     */
    protected String connector(final String tone,
                               final int index,
                               final String letter) {
        int maxIndex = index + 1;
        if (index + 1 == tone.length()) {
            return tone.substring(0, index) + letter;
        }
        // 默认返回 前+替换+后
        return tone.substring(0, index) + letter + tone.substring(maxIndex);
    }

}
