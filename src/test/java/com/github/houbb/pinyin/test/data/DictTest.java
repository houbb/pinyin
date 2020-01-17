package com.github.houbb.pinyin.test.data;

import com.github.houbb.heaven.support.condition.ICondition;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.opencc4j.core.impl.ZhConvertBootstrap;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 词典生成辅助类
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class DictTest {

    @Test
    public void charTest() {
        List<String> oldList = FileUtil.readAllLines("D:\\code\\github\\pinyin\\src\\test\\resources\\pinyin_char_o.txt");

        List<String> resultList = CollectionUtil.toList(oldList, new IHandler<String, String>() {
            //U+3007: líng,yuán,xīng  # 〇
            // 〇 líng,yuán,xīng
            @Override
            public String handle(String string) {
                String[] strings = string.split("#");
                String word = strings[1].trim();

                if(ZhConvertBootstrap.newInstance().isTraditional(word)) {
                    return "";
                }
                String tone = strings[0].trim().split(":")[1].trim();
                return word + " " + tone;
            }
        });

        // 只保留简体
        List<String> simpleList = CollectionUtil.conditionList(resultList, new ICondition<String>() {
            @Override
            public boolean condition(String string) {
                // 是否为简体
                return StringUtil.isNotEmpty(string);
            }
        });

        final String target = "D:\\code\\github\\pinyin\\src\\main\\resources\\pinyin_dict_char.txt";
        FileUtil.write(target, simpleList);
    }

}
