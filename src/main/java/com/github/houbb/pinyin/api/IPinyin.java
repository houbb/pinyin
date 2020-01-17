package com.github.houbb.pinyin.api;

/**
 * 拼音核心用户 api
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IPinyin {

    /**
     * 获取指定字符串的拼音
     * @param string 原始字符串
     * @return 转换后的结果
     * @since 0.0.1
     */
    String toPinyin(final String string);

}
