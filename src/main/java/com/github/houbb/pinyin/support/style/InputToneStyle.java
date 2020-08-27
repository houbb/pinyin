package com.github.houbb.pinyin.support.style;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.pinyin.model.CharToneInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 符合输入法的方式
 *
 * nv 女
 * lv 绿
 * @author binbin.hou
 * @since 0.1.4
 */
@ThreadSafe
public class InputToneStyle extends AbstractPinyinToneStyle {

    @Override
    protected String getCharFormat(String tone, CharToneInfo toneInfo) {
        int index = toneInfo.getIndex();

        // 没有音调，直接返回
        String result = tone;
        if (index >= 0) {
            char letter = toneInfo.getToneItem().getLetter();
            result = super.connector(tone, index, String.valueOf(letter));
        }
        // 替换掉输入法不支持的部分
        return result.replace('ü', 'v');
    }

}
