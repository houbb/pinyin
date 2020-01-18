package com.github.houbb.pinyin.support.segment;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.util.ArrayPrimitiveUtil;
import com.github.houbb.pinyin.spi.IPinyinSegment;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.data.impl.SegmentDatas;

import javax.swing.text.Segment;
import java.util.List;

/**
 *
 * 默认的中文繁简体转换
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class DefaultPinyinSegment implements IPinyinSegment {

    /**
     * 分词的实现保持不变
     *
     * （1）此处基于用户自定义的字典。
     * @since 0.0.4
     */
    private static final SegmentBs SEGMENT_BS = SegmentBs.newInstance()
            .segmentData(SegmentDatas.define());

    @Override
    public List<String> segment(String string) {
        return SEGMENT_BS.segmentWords(string);
    }

}
