package com.github.houbb.pinyin.test.util;

import com.github.houbb.pinyin.constant.enums.PinyinToneNumEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * 音调获取
 * @author binbin.hou
 * @since 0.1.0
 */
public class PinyinHelperToneNumTest {

    @Test
    public void toneNumTest() {
        final char ch = '赵';
        final char ch2 = '钱';
        final char ch3 = '孙';
        final char ch4 = '李';

        Assert.assertEquals(PinyinToneNumEnum.FOUR.num(), PinyinHelper.toneNum(ch));
        Assert.assertEquals(PinyinToneNumEnum.TWO.num(), PinyinHelper.toneNum(ch2));
        Assert.assertEquals(PinyinToneNumEnum.ONE.num(), PinyinHelper.toneNum(ch3));
        Assert.assertEquals(PinyinToneNumEnum.THREE.num(), PinyinHelper.toneNum(ch4));
    }

    @Test
    public void toneNumCategoryTest() {
        Assert.assertTrue(PinyinToneNumEnum.isPing(1));
        Assert.assertTrue(PinyinToneNumEnum.isPing(2));
        Assert.assertTrue(PinyinToneNumEnum.isZe(3));
        Assert.assertTrue(PinyinToneNumEnum.isZe(4));
        Assert.assertTrue(PinyinToneNumEnum.isSoftly(5));
    }

}
