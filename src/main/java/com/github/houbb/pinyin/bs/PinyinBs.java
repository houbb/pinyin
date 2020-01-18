package com.github.houbb.pinyin.bs;

import com.github.houbb.heaven.response.exception.CommonRuntimeException;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CharsetUtil;
import com.github.houbb.pinyin.api.IPinyin;
import com.github.houbb.pinyin.spi.IPinyinAppender;
import com.github.houbb.pinyin.spi.IPinyinChinese;
import com.github.houbb.pinyin.spi.IPinyinSegment;
import com.github.houbb.pinyin.spi.IPinyinTone;
import com.github.houbb.pinyin.support.appender.StringBuilderPinyinAppender;
import com.github.houbb.pinyin.support.chinese.SimplePinyinChinese;
import com.github.houbb.pinyin.support.mapping.DefaultPinyinTone;
import com.github.houbb.pinyin.support.segment.CharPinyinSegment;
import com.github.houbb.pinyin.support.segment.DefaultPinyinSegment;
import com.github.houbb.pinyin.support.segment.SinglePinyinSegment;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 拼音引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class PinyinBs implements IPinyin {

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
    private IPinyinChinese pinyinChinese = Instances.singleton(SimplePinyinChinese.class);

    /**
     * 注音映射
     * @since 0.0.1
     */
    private IPinyinTone pinyinTone = Instances.singleton(DefaultPinyinTone.class);

    /**
     * 连接类实现
     * （1）使用线程安全的方式进行处理
     * （2）小心避免内存泄漏
     * @since 0.0.1
     */
    private IPinyinAppender pinyinAppender = Instances.threadLocal(StringBuilderPinyinAppender.class);

    /**
     * 新建引导类实例
     * @return 引导类
     * @since 0.0.1
     */
    public static PinyinBs newInstance() {
        PinyinBs bs = new PinyinBs();
        return bs;
    }

    /**
     * 设置声调的格式化方法
     * @param pinyinTone 拼音格式化
     * @return this
     * @since 0.0.3
     */
    public PinyinBs pinyinTone(IPinyinTone pinyinTone) {
        this.pinyinTone = pinyinTone;
        return this;
    }

    @Override
    public String toPinyin(String string) {
        if(StringUtil.isEmptyTrim(string)) {
            return string;
        }

        // 分词
        IPinyinSegment segment = getSegment(string);
        List<String> entryList = segment.segment(string);

        try {
            // 映射处理与连接
            for(String entry : entryList) {
                // 这里理论上是不用判断是否为中文
                if(pinyinChinese.isChinese(entry)) {
                    // 是否简体转换也应该加一个开关
                    String simple = pinyinChinese.toSimple(entry);
                    String tone = pinyinTone.tone(simple);

                    // 中文拼音添加空格
                    pinyinAppender.append(tone).append(StringUtil.BLANK);
                } else {
                    // 直接加入原始信息
                    pinyinAppender.append(entry);
                }
            }

            // 如果最后一个为中文，则移除最后一个空格
            String last = entryList.get(entryList.size()-1);
            if(pinyinChinese.isChinese(last)) {
                pinyinAppender.deleteCharAt(pinyinAppender.length()-1);
            }
        } catch (IOException e) {
            // 这里能否移除这个显式异常
            throw new CommonRuntimeException(e);
        }

        // 拼接结果
        String result = pinyinAppender.toString();
        // 清空原始的 appender
        pinyinAppender.clear();

        return result;
    }

    @Override
    public List<String> toPinyin(char chinese) {
        String string = String.valueOf(chinese);
        if(CharsetUtil.isChinese(chinese)) {
            return pinyinTone.toneList(string);
        }

        return Collections.singletonList(string);
    }

    /**
     * 获取对应的分词实现
     * @param string 原始字符串
     * @return 分词实现
     * @since 0.0.1
     */
    private IPinyinSegment getSegment(final String string) {
        if(string.length() <= 1) {
            return Instances.singleton(SinglePinyinSegment.class);
        }
        return pinyinSegment;
    }

}
