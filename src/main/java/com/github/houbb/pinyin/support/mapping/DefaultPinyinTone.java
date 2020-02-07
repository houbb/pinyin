package com.github.houbb.pinyin.support.mapping;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 正常的拼音注音形式
 *
 * （1）单个字的词库与词组的词库分离
 * （2）二者词库都是懒加载，只有使用的时候才会去初始化。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class DefaultPinyinTone extends AbstractPinyinTone {

    /**
     * 单个字的 Map
     * DCL 单例，惰性加载。
     *
     * （1）注意多音字的问题
     * （2）默认只返回第一个
     * （3）为了提升读取的性能，在初始化的时候，直接设计好。
     * @since 0.0.1
     */
    private static volatile Map<String, List<String>> charMap;

    /**
     * 词组 map
     *
     * DCL 单例，惰性加载。
     * @since 0.0.1
     */
    private static volatile Map<String, String> phraseMap;

    @Override
    protected List<String> getCharTones(String chinese) {
        return getCharMap().get(chinese);
    }

    @Override
    protected String getCharTone(String segment) {
        // 大部分拼音都是单个字，不是多音字。
        // 直接在初始化的时候，设置好。
        List<String> pinyinList = getCharMap().get(segment);
        if(CollectionUtil.isNotEmpty(pinyinList)) {
            return pinyinList.get(0);
        }

        // 没有则返回空
        return null;
    }

    @Override
    protected String getPhraseTone(String segment) {
        return getPhraseMap().get(segment);
    }

    /**
     * 获取单个字符的 map
     * @return map
     * @since 0.0.1
     */
    private Map<String, List<String>> getCharMap() {
        if(ObjectUtil.isNotNull(charMap)) {
            return charMap;
        }

        synchronized (DefaultPinyinTone.class) {
            if(ObjectUtil.isNull(charMap)) {
                List<String> lines = StreamUtil.readAllLines("/pinyin_dict_char.txt");
                charMap = Guavas.newHashMap(lines.size());

                for(String line : lines) {
                    String[] strings = line.split(":");
                    List<String> pinyinList = StringUtil.splitToList(strings[1]);
                    charMap.put(strings[0], pinyinList);
                }
            }
        }

        return charMap;
    }

    /**
     * 获取词组的拼音 Map
     * （1）词组的拼音是确定的。
     * @return map
     * @since 0.0.1
     */
    private Map<String, String> getPhraseMap() {
        if(ObjectUtil.isNotNull(phraseMap)) {
            return phraseMap;
        }
        synchronized (DefaultPinyinTone.class) {
            if(ObjectUtil.isNull(phraseMap)) {
                List<String> lines = StreamUtil.readAllLines("/pinyin_dict_phrase.txt");
                phraseMap = Guavas.newHashMap(lines.size());

                for(String line : lines) {
                    String[] strings = line.split(":");
                    phraseMap.put(strings[0], strings[1]);
                }
            }
        }

        return phraseMap;
    }

    @Override
    public Set<String> phraseSet() {
        Map<String, String> map = getPhraseMap();
        return map.keySet();
    }

}
