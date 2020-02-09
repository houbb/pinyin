package com.github.houbb.pinyin.support.chinese;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CharsetUtil;
import com.github.houbb.nlp.common.format.impl.CharFormats;
import com.github.houbb.pinyin.spi.IPinyinChinese;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SimplePinyinChinese implements IPinyinChinese {

    @Override
    public boolean isChinese(String original) {
        // 分词有一个问题。如果分词结果为 A栋
        // 直接判断，并不是中文。
        // 这个后续进行处理，暂时不做细致处理。
        // 应该是包含中文，就处理。
        // 如果没有全部匹配，那么就降级为单个字的拼音处理。
        return CharsetUtil.isAllChinese(original);
    }

    @Override
    public boolean isChinese(char original) {
        return CharUtil.isChinese(original);
    }

    @Override
    public String toSimple(String segment) {
        if(StringUtil.isEmptyTrim(segment)) {
            return segment;
        }

        char[] chars = segment.toCharArray();

        StringBuilder buffer = new StringBuilder(segment.length());
        for(char c : chars) {
            if(CharsetUtil.isChinese(c)) {
                char simpleChar = CharFormats.chineseSimple().format(c);
                buffer.append(simpleChar);
            } else {
                // 直接添加，避免出现非中文的情况。
                // 暂时这么处理，后期就可以避免修改。
                buffer.append(c);
            }
        }

        // 当然也可以直接使用 opencc4j，后期优化后统一调整。
        return buffer.toString();
    }

    @Override
    public String toSimple(char original) {
        if(isChinese(original)) {
            char simpleChar = CharFormats.chineseSimple().format(original);
            return String.valueOf(simpleChar);
        }

        return String.valueOf(original);
    }

}
