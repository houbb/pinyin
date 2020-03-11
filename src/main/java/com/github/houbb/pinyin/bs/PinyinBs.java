package com.github.houbb.pinyin.bs;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.api.IPinyin;
import com.github.houbb.pinyin.api.IPinyinContext;
import com.github.houbb.pinyin.api.impl.Pinyin;
import com.github.houbb.pinyin.api.impl.PinyinContext;
import com.github.houbb.pinyin.spi.*;
import com.github.houbb.pinyin.support.chinese.PinyinChineses;
import com.github.houbb.pinyin.support.data.PinyinData;
import com.github.houbb.pinyin.support.segment.DefaultPinyinSegment;
import com.github.houbb.pinyin.support.style.PinyinToneStyles;
import com.github.houbb.pinyin.support.tone.DefaultPinyinTone;

import java.util.List;

/**
 * 拼音引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class PinyinBs {

    private PinyinBs(){}

    /**
     * 默认分词
     * @since 0.0.1
     */
    private IPinyinSegment pinyinSegment = Instances.singleton(DefaultPinyinSegment.class);

    /**
     * 中文服务类
     * @since 0.0.1
     */
    private IPinyinChinese pinyinChinese = PinyinChineses.simple();

    /**
     * 注音映射
     * @since 0.0.1
     */
    private IPinyinTone pinyinTone = Instances.singleton(DefaultPinyinTone.class);

    /**
     * 拼音数据实现
     * @since 0.1.1
     */
    private IPinyinData data = Instances.singleton(PinyinData.class);

    /**
     * 拼音的形式
     * @since 0.1.1
     */
    private IPinyinToneStyle style = PinyinToneStyles.defaults();

    /**
     * 默认核心实现
     * @since 0.1.1
     */
    private IPinyin pinyin = Instances.singleton(Pinyin.class);

    /**
     * 连接符号
     * @since 0.1.2
     */
    private String connector = StringUtil.BLANK;

    /**
     * 新建引导类实例
     * @return 引导类
     * @since 0.0.1
     */
    public static PinyinBs newInstance() {
        return new PinyinBs();
    }

    /**
     * 设置样式
     * @param style 样式
     * @since 0.1.1
     * @return this
     */
    public PinyinBs style(IPinyinToneStyle style) {
        ArgUtil.notNull(style, "style");

        this.style = style;
        return this;
    }

    /**
     * 设置连接符号
     * @param connector 连接符号
     * @return this
     * @since 0.1.2
     */
    public PinyinBs connector(String connector) {
        this.connector = connector;
        return this;
    }

    /**
     * 转换为拼音字符串
     * @param string 字符串
     * @return 结果
     * @since 0.0.1
     */
    public String toPinyin(String string) {
        return pinyin.toPinyin(string, buildPinyinContext());
    }

    /**
     * 转换为拼音列表
     * @param chinese 中文字符
     * @return 结果
     * @since 0.0.1
     */
    public List<String> toPinyinList(char chinese) {
        return pinyin.toPinyinList(chinese, buildPinyinContext());
    }

    /**
     * 拥有相同的拼音
     * @param chineseOne 中文1
     * @param chineseTwo 中文2
     * @return 结果
     * @since 0.0.1
     */
    public boolean hasSamePinyin(char chineseOne, char chineseTwo) {
        return pinyin.hasSamePinyin(chineseOne, chineseTwo, buildPinyinContext());
    }

    /**
     * 声调编号列表
     * @param chinese 中文
     * @return 结果
     * @since 0.1.0
     */
    public List<Integer> toneNumList(String chinese) {
        return pinyin.toneNumList(chinese, buildPinyinContext());
    }

    /**
     * 声调编号列表
     * @param chinese 中文
     * @return 结果
     * @since 0.1.0
     */
    public List<Integer> toneNumList(char chinese) {
        return pinyin.toneNumList(chinese, buildPinyinContext());
    }

    /**
     * 声母列表
     * @param chinese 中文
     * @return 结果
     * @since 0.1.0
     */
    public List<String> shengMuList(final String chinese) {
        return pinyin.shengMuList(chinese, buildPinyinContext());
    }

    /**
     * 韵母列表
     * @param chinese 中文
     * @return 结果
     * @since 0.1.0
     */
    public List<String> yunMuList(final String chinese) {
        return pinyin.yunMuList(chinese, buildPinyinContext());
    }

    /**
     * 构建上下文
     * @return 结果
     * @since 0.1.1
     */
    private IPinyinContext buildPinyinContext() {
        return PinyinContext.newInstance()
                .chinese(pinyinChinese)
                .data(data)
                .segment(pinyinSegment)
                .style(style)
                .tone(pinyinTone)
                .connector(connector);
    }

}
