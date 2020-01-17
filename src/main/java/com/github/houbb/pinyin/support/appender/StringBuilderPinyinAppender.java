package com.github.houbb.pinyin.support.appender;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.pinyin.spi.IPinyinAppender;

/**
 * StringBuilder 实现的功能
 *
 * @author binbin.hou
 * @since 0.0.1
 * @see StringBuilder
 * @see StringBuffer
 * @see ThreadLocal
 */
@NotThreadSafe
public class StringBuilderPinyinAppender implements IPinyinAppender {

    /**
     * 基于 StringBuilder 实现
     * @since 0.0.1
     */
    private StringBuilder stringBuilder;

    public StringBuilderPinyinAppender() {
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public Appendable append(CharSequence csq) {
        return stringBuilder.append(csq);
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) {
        return stringBuilder.append(csq, start, end);
    }

    @Override
    public Appendable append(char c) {
        return stringBuilder.append(c);
    }

    @Override
    public int length() {
        return stringBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return stringBuilder.subSequence(start, end);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    @Override
    public IPinyinAppender deleteCharAt(int index) {
        stringBuilder.deleteCharAt(index);
        return this;
    }

    /**
     * 参考资料
     * https://www.cnblogs.com/whsa/p/4262640.html
     * https://www.tuicool.com/articles/eERRra
     *
     * 整体而言，重置长度是最快的。
     * @return this
     * @since 0.0.1
     */
    @Override
    public IPinyinAppender clear() {
        stringBuilder.setLength(0);
        return this;
    }

}
