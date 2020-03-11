package com.github.houbb.pinyin.api;

import com.github.houbb.pinyin.spi.*;

/**
 * 拼音核心用户 api 上下文
 * @author binbin.hou
 * @since 0.1.1
 */
public interface IPinyinContext {

    /**
     * 样式
     * @return 样式
     * @since 0.1.1
     */
    IPinyinToneStyle style();

    /**
     * 分词实现
     * @return 分词
     * @since 0.1.1
     */
    IPinyinSegment segment();

    /**
     * 拼音数据实现
     * @return 数据实现
     * @since 0.1.1
     */
    IPinyinData data();

    /**
     * 中文服务类
     * @return 中文服务类
     * @since 0.0.1
     */
    IPinyinChinese chinese();

    /**
     * 注音实现
     * @return 注音实现
     * @since 0.0.1
     */
    IPinyinTone tone();

    /**
     * 连接符
     * @since 0.1.2
     * @return 连接符
     */
    String connector();

}
