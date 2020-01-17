package com.github.houbb.pinyin.test.bs;

import com.github.houbb.pinyin.bs.PinyinBs;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class PinyinBsTest {

    @Test
    public void toPinyinTest() {
        String text = "我爱中文";
        PinyinBs pinyinBs = PinyinBs.newInstance();
        String pinyin = pinyinBs.toPinyin(text);
        Assert.assertEquals("wǒ ài zhōng wén", pinyin);

        String text2 = "也爱编程";
        String pinyin2 = pinyinBs.toPinyin(text2);
        Assert.assertEquals("yě ài biān chéng", pinyin2);
    }

}
