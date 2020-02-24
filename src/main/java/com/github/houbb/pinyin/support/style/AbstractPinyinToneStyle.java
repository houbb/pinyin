package com.github.houbb.pinyin.support.style;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.model.CharToneInfo;
import com.github.houbb.pinyin.model.ToneItem;
import com.github.houbb.pinyin.spi.IPinyinToneStyle;
import com.github.houbb.pinyin.util.ToneHelper;

/**
 * <p> project: pinyin-IPinyinStyle </p>
 * <p> create on 2020/2/24 21:44 </p>
 *
 * @author Administrator
 * @since 0.1.1
 */
@ThreadSafe
public abstract class AbstractPinyinToneStyle implements IPinyinToneStyle {

    /**
     * 获取单个字符拼音的格式化
     * @param tone 分段后的拼音
     * @param toneInfo 字符的声调信息
     * @return 格式化结果
     * @since 0.0.3
     */
    protected abstract String getCharFormat(final String tone, final CharToneInfo toneInfo);

    @Override
    public String style(String charTone) {
        if(StringUtil.isEmpty(charTone)) {
            return charTone;
        }

        // 进行格式化
        CharToneInfo toneInfo = getCharToneInfo(charTone);
        return getCharFormat(charTone, toneInfo);
    }

    /**
     * 获取对应的声调信息
     * @param tone 拼音信息
     * @return 声调信息
     * @since 0.0.3
     */
    private CharToneInfo getCharToneInfo(final String tone) {
        CharToneInfo charToneInfo = new CharToneInfo();
        charToneInfo.setIndex(-1);

        int length = tone.length();
        for(int i = 0; i < length; i++) {
            char currentChar = tone.charAt(i);
            ToneItem toneItem = ToneHelper.getToneItem(currentChar);

            if (ObjectUtil.isNotNull(toneItem)) {
                charToneInfo.setToneItem(toneItem);
                charToneInfo.setIndex(i);
                break;
            }
        }

        return charToneInfo;
    }

    /**
     * 对信息进行连接
     * @param tone 拼音
     * @param index 标注的下表
     * @param letter 需要额外添加的信息
     * @return 结果
     * @since 0.0.3
     */
    String connector(final String tone,
                               final int index,
                               final String letter) {
        int maxIndex = index + 1;
        if (index + 1 == tone.length()) {
            return tone.substring(0, index) + letter;
        }
        // 默认返回 前+替换+后
        return tone.substring(0, index) + letter + tone.substring(maxIndex);
    }

}
