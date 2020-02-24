package com.github.houbb.pinyin.support.segment.trie;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.nlp.common.dfa.tree.impl.AbstractTrieTreeMap;
import com.github.houbb.pinyin.support.tone.PinyinTones;

import java.util.Collection;
import java.util.Map;

/**
 * <p> project: pinyin-DefaultPinyinTrieMap </p>
 * <p> create on 2020/2/7 17:39 </p>
 *
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class DefaultPinyinTrieTreeMap extends AbstractTrieTreeMap {

    /**
     * 内部单词 map
     *
     * @since 0.0.2
     */
    private static volatile Map innerWordMap = Guavas.newHashMap();

    @Override
    protected Map getStaticVolatileMap() {
        return innerWordMap;
    }

    @Override
    protected Collection<String> getWordCollection() {
        return PinyinTones.defaults().phraseSet();
    }

}
