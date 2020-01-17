package com.github.houbb.pinyin.test.util;

import com.github.houbb.pinyin.util.PinyinHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class PinyinHelperTest {

    /**
     * 转换为中文测试
     * @since 0.0.1
     */
    @Test
    public void toPinyinTest() {
        String pinyin = PinyinHelper.toPinyin("我爱中文");
        Assert.assertEquals("wǒ ài zhōng wén", pinyin);
    }


    /**
     * 转换为中文测试
     * @since 0.0.1
     */
    @Test
    public void toPinyinNumEnTest() {
        String pinyin = PinyinHelper.toPinyin("my name is pinyin4j");
        Assert.assertEquals("my name is pinyin4j", pinyin);
    }

    /**
     * 转换为中文测试
     * @since 0.0.1
     */
    @Test
    public void toPinyinEmptyTest() {
        Assert.assertNull(PinyinHelper.toPinyin(null));
        Assert.assertEquals("", PinyinHelper.toPinyin(""));
    }

}
