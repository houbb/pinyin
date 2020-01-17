package com.github.houbb.pinyin.support.chinese;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.util.CharsetUtil;
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
    public String toSimple(String segment) {
        // TODO: 后期替换为 opencc4j
        // 这里为了简单，直接处理分词后的结果即可。
        return segment;
    }

}
