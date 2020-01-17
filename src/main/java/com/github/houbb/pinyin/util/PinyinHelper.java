package com.github.houbb.pinyin.util;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.bs.PinyinBs;

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
        if(StringUtil.isEmptyTrim(string)) {
            return string;
        }

        return PinyinBs.newInstance().toPinyin(string);
    }

}
