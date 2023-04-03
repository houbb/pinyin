package com.github.houbb.pinyin.constant;

/**
 * 拼音常量
 * @author binbin.hou
 * @since 0.0.1
 */
public final class PinyinConst {

    private PinyinConst(){}

    /**
     * 单个字拼音系统内置
     * @since 0.0.5
     */
    public static final String PINYIN_DICT_CHAR_SYSTEM = "/pinyin_dict_char.txt";

    /**
     * 单个字拼音自定义
     * @since 0.0.5
     */
    public static final String PINYIN_DICT_CHAR_DEFINE = "/pinyin_dict_char_define.txt";

    /**
     * 词组拼音系统内置
     * @since 0.0.5
     */
    public static final String PINYIN_DICT_PHRASE_SYSTEM = "/pinyin_dict_phrase.txt";

    /**
     * 词组拼音自定义
     * @since 0.0.5
     */
    public static final String PINYIN_DICT_PHRASE_DEFINE = "/pinyin_dict_phrase_define.txt";

    /**
     * 拼音音调标注字典
     * @since 0.0.5
     */
    public static final String PINYIN_DICT_TONE_SYSTEM = "/pinyin_dict_tone.txt";

    /**
     * 拼音音调标注词典
     */
    public static String PINYIN_DICT_PHRASE_LOCAL = "";

    /**
     * 拼音音调标注字典
     */
    public static String PINYIN_DICT_CHAR_LOCAL = "";

}
