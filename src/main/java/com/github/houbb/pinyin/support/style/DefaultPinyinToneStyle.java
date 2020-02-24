package com.github.houbb.pinyin.support.style;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.pinyin.spi.IPinyinToneStyle;

/**
 * 默认格式
 * <p> project: pinyin-IPinyinStyle </p>
 * <p> create on 2020/2/24 21:44 </p>
 *
 * @author Administrator
 * @since 0.1.1
 */
@ThreadSafe
public class DefaultPinyinToneStyle implements IPinyinToneStyle {

    @Override
    public String style(String charTone) {
        return charTone;
    }

}
