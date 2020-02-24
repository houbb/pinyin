package com.github.houbb.pinyin.spi;

/**
 * 拼音连接接口
 * @author binbin.hou
 * @since 0.0.1
 */
@Deprecated
public interface IPinyinAppender extends CharSequence, Appendable {

    /**
     * 指定指定字符的信息
     * @param index 下标
     * @return this
     * @since 0.0.1
     */
    IPinyinAppender deleteCharAt(final int index);

    /**
     * 清空内容
     * @return this
     * @since 0.0.1
     */
    IPinyinAppender clear();

}
