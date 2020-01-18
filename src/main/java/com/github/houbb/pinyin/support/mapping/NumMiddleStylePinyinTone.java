package com.github.houbb.pinyin.support.mapping;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.pinyin.model.CharToneInfo;

/**
 * 数字放在标注位置的拼音注音形式
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
@Deprecated
public class NumMiddleStylePinyinTone extends AbstractStylePinyinTone {

    @Override
    protected String getCharFormat(String tone, CharToneInfo toneInfo) {
        int index = toneInfo.getIndex();

        // 轻声
        if (index < 0) {
            return tone + "5";
        }

        // 直接拼接在最后
        char letter = toneInfo.getToneItem().getLetter();
        int num = toneInfo.getToneItem().getTone();
        String extra = String.valueOf(letter)+ num;
        return super.connector(tone, index, extra);
    }

}
