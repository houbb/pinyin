package com.github.houbb.pinyin.spi;

import java.util.List;

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
     * @param segment 分词后的片段
     * @return 结果
     * @since 0.0.1
     */
    String tone(final String segment);

    /**
     * 返回所有的列表
     * @param chinese 单个中文
     * @return 结果
     */
    List<String> toneList(final String chinese);

}
