package com.github.houbb.pinyin.support.mapping;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.spi.IPinyinTone;

/**
 * 拼音标注的不同形式
 * <p> project: pinyin-PinyinTones </p>
 * <p> create on 2020/1/18 17:04 </p>
 *
 * @author Administrator
 * @since 0.0.3
 */
public final class PinyinToneStyles {

    private PinyinToneStyles(){}

    /**
     * 获取对应的标注实现
     * @param styleEnum 样式枚举
     * @return 标注实现
     * @since 0.0.3
     */
    public static IPinyinTone getTone(final PinyinStyleEnum styleEnum) {
        if(PinyinStyleEnum.DEFAULT.equals(styleEnum)) {
            return Instances.singleton(DefaultPinyinTone.class);
        }
        if(PinyinStyleEnum.NORMAL.equals(styleEnum)) {
            return Instances.singleton(NormalStylePinyinTone.class);
        }
        if(PinyinStyleEnum.NUM_LAST.equals(styleEnum)) {
            return Instances.singleton(NumLastStylePinyinTone.class);
        }
        if(PinyinStyleEnum.FIRST_LETTER.equals(styleEnum)) {
            return Instances.singleton(FirstLetterStylePinyinTone.class);
        }

        // 返回默认
        return Instances.singleton(DefaultPinyinTone.class);
    }

}
