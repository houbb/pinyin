package com.github.houbb.pinyin.spi;

/**
 * 拼音连接接口
 *
 * （1）可以使用模式来制定对应的映射实现。
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IPinyinTone {

    /**
     * 获取映射后的信息
     * @param original 原始字符串
     * @return 结果
     * @since 0.0.1
     */
    String tone(final String original);

}
