package com.github.houbb.pinyin.util;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.bs.PinyinBs;
import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.spi.IPinyinToneStyle;
import com.github.houbb.pinyin.support.style.PinyinToneStyles;

import java.util.List;

/**
 * 简化用于调用
 * @author binbin.hou
 * @since 0.0.1
 * @see com.github.houbb.pinyin.bs.PinyinBs 提供灵活性
 */
public final class PinyinHelper {

    private PinyinHelper(){}

    /**
     * 转换为拼音
     * @param string 原始信息
     * @return 结果
     * @since 0.0.1
     */
    public static String toPinyin(final String string) {
        return toPinyin(string, PinyinStyleEnum.DEFAULT);
    }

    /**
     * 转换为拼音
     * @param string 原始信息
     * @param styleEnum 样式枚举
     * @return 结果
     * @since 0.0.3
     */
    public static String toPinyin(final String string,
                                  final PinyinStyleEnum styleEnum) {
        return toPinyin(string, styleEnum, StringUtil.BLANK);
    }

    /**
     * 转换为拼音
     * @param string 原始信息
     * @param styleEnum 样式枚举
     * @param connector 连接符号
     * @return 结果
     * @since 0.1.2
     */
    public static String toPinyin(final String string,
                                  final PinyinStyleEnum styleEnum,
                                  final String connector) {
        ArgUtil.notNull(connector, "connector");

        if(StringUtil.isEmptyTrim(string)) {
            return string;
        }

        final IPinyinToneStyle style = PinyinToneStyles.getTone(styleEnum);
        return PinyinBs.newInstance()
                .style(style)
                .connector(connector)
                .toPinyin(string);
    }

    /**
     * 返回拼音列表
     * @param chinese 中文字符
     * @return 结果
     * @since 0.1.1
     */
    public static List<String> toPinyinList(final char chinese) {
        return PinyinBs.newInstance().toPinyinList(chinese);
    }

    /**
     * 返回拼音列表
     * @param chinese 中文字符
     * @param styleEnum 指定样式
     * @return 结果
     * @since 0.1.1
     */
    public static List<String> toPinyinList(final char chinese, final PinyinStyleEnum styleEnum) {
        final IPinyinToneStyle pinyinTone = PinyinToneStyles.getTone(styleEnum);
        return PinyinBs.newInstance().style(pinyinTone).toPinyinList(chinese);
    }

    /**
     * 是否拥有相同的拼音
     * @param chineseOne 汉字一
     * @param chineseTwo 汉字二
     * @return 是否相同
     * @since 0.0.8
     */
    public static boolean hasSamePinyin(final char chineseOne, final char chineseTwo) {
        return PinyinBs.newInstance().hasSamePinyin(chineseOne, chineseTwo);
    }

    /**
     * 获取拼音的声调
     *
     * @param chinese 中文字符串
     * @return 音调列表
     * @since 0.1.1
     */
    public static List<Integer> toneNumList(final String chinese) {
        return PinyinBs.newInstance().toneNumList(chinese);
    }

    /**
     * 获取拼音的声调
     *
     * @param chinese 中文字符
     * @return 音调列表
     * @since 0.1.1
     */
    public static List<Integer> toneNumList(final char chinese) {
        return PinyinBs.newInstance().toneNumList(chinese);
    }

    /**
     * 转换为声母列表
     * @param chinese 中文
     * @return 结果
     * @since 0.1.1
     */
    public static List<String> shengMuList(final String chinese) {
        final IPinyinToneStyle pinyinTone = PinyinToneStyles.getTone(PinyinStyleEnum.NORMAL);
        return PinyinBs.newInstance().style(pinyinTone).shengMuList(chinese);
    }

    /**
     * 转换为韵母列表
     * @param chinese 中文
     * @return 结果
     * @since 0.1.1
     */
    public static List<String> yunMuList(final String chinese) {
        final IPinyinToneStyle pinyinTone = PinyinToneStyles.getTone(PinyinStyleEnum.NORMAL);
        return PinyinBs.newInstance().style(pinyinTone).yunMuList(chinese);
    }

}
