package com.github.houbb.pinyin.util;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.constant.PinyinConst;
import com.github.houbb.pinyin.model.ToneItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <p> project: pinyin-ToneHelper </p>
 * <p> create on 2020/1/18 16:13 </p>
 *
 * @author Administrator
 * @since 0.0.3
 */
public final class ToneHelper {

    private ToneHelper(){}

    /**
     * 存放对应的拼音声调信息
     * @since 0.0.3
     */
    private static final Map<Character, ToneItem> TONE_ITEM_MAP;

    static {
        TONE_ITEM_MAP = new HashMap<>(34);

        List<String> allLines = StreamUtil.readAllLines(PinyinConst.PINYIN_DICT_TONE_SYSTEM);
        for(String line : allLines) {
            String[] strings = line.split(StringUtil.BLANK);
            ToneItem item = ToneItem.of(strings[0].charAt(0), Integer.parseInt(strings[1]));

            TONE_ITEM_MAP.put(strings[2].charAt(0), item);
        }
    }

    /**
     * 获取对应的拼音信息
     * @param c 拼音
     * @return 结果
     * @since 0.0.3
     */
    public static ToneItem getToneItem(final char c) {
        return TONE_ITEM_MAP.get(c);
    }

}
