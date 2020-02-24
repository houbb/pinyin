package com.github.houbb.pinyin.test.benchmark;

import com.github.houbb.heaven.util.io.StreamUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p> project: pinyin-BenchmarkTest </p>
 * <p> create on 2020/1/18 21:36 </p>
 *
 * @author Administrator
 * @since 0.0.4
 */
@Ignore
public class BenchmarkTest {

    /**
     * 1w 次
     * @since 0.0.4
     */
    private static final int TIMES = 10000;

    /**
     * 单个字符次数统计
     * @since 0.0.4
     */
    private static final int SINGLE_TIMES = 1000000;

    /**
     * pinyin4j: Pinyin4j cost: 33002 ms
     *
     * Pinyin4j 没有中文分词
     */
    @Test
    public void pinyin4jNoSegmentTest() throws BadHanyuPinyinOutputFormatCombination {
        // 创建汉语拼音处理类
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 看的出来，没有支持分词。
        // zhong qing huo guo
        String result = PinyinHelper.toHanyuPinyinString("重庆火锅", defaultFormat, " ");
        System.out.println(result);

        // 验证
        final String text = getText();
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < TIMES; i++) {
            PinyinHelper.toHanyuPinyinString(text, defaultFormat, " ");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Pinyin4j cost: " + (endTime-startTime));
    }

    /**
     * 预热耗时：565 ms
     * 1w 次 cost: 17975 ms
     *
     * 本框架，支持分词的测试。
     */
    @Test
    public void pinyinWithSegmentTest() {
        // 预热
        String result = com.github.houbb.pinyin.util.PinyinHelper.toPinyin("重庆火锅");
        System.out.println(result);

        // 验证
        final String text = getText();
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < TIMES; i++) {
            com.github.houbb.pinyin.util.PinyinHelper.toPinyin(text);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Pinyin cost: " + (endTime-startTime));
    }

    /**
     * 获取文本内容
     * @return 内容
     * @since 0.0.1
     */
    private String getText() {
        return StreamUtil.getFileContent("永远的夏娃.txt");
    }

    /**
     * 单个字符耗时统计
     * 621ms
     */
    @Test
    public void pinyin4jCharTest() {
        String[] result = PinyinHelper.toHanyuPinyinStringArray('重');
        System.out.println(Arrays.toString(result));

        // 验证
        final String text = getText();

        long startTime = System.currentTimeMillis();
        // 随机选择一个单字
        for(int i = 0; i < SINGLE_TIMES; i++) {
            int index = ThreadLocalRandom.current().nextInt(text.length()-1);

            PinyinHelper.toHanyuPinyinStringArray(text.charAt(index));
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Pinyin4j cost: " + (endTime-startTime));
    }

    /**
     * 单个字符耗时统计
     * 317 ms
     */
    @Test
    public void pinyinCharTest() {
        List<String> result = com.github.houbb.pinyin.util.PinyinHelper.toPinyinList('重');
        System.out.println(result);

        // 验证
        final String text = getText();

        long startTime = System.currentTimeMillis();
        // 随机选择一个单字
        for(int i = 0; i < SINGLE_TIMES; i++) {
            int index = ThreadLocalRandom.current().nextInt(text.length()-1);

            com.github.houbb.pinyin.util.PinyinHelper.toPinyinList(text.charAt(index));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Pinyin cost: " + (endTime-startTime));
    }

}
