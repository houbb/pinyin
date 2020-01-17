package com.github.houbb.pinyin.util;

import com.github.houbb.heaven.annotation.CommonEager;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.ArrayPrimitiveUtil;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class StringUtils {

    /**
     * 转换为 char 字符串列表
     * @param string 字符串
     * @return 字符串列表
     * @since 0.0.1
     */
    @CommonEager
    public static List<String> toCharStringList(final String string) {
        if(StringUtil.isEmpty(string)) {
            return Guavas.newArrayList();
        }

        char[] chars = string.toCharArray();
        return ArrayPrimitiveUtil.toList(chars, new IHandler<Character, String>() {
            @Override
            public String handle(Character character) {
                return String.valueOf(character);
            }
        });
    }

}
