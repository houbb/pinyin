# 数据来源

# version: 0.8.1
# source: https://github.com/mozillazg/pinyin-data

# version: 0.10.2
# source: https://github.com/mozillazg/phrase-pinyin-data

============================================================

# 瘦身

这里存在一个问题，单字有简体+繁体。

词组中只有简体。

# 所以有几种方案：

（1）词组也加上繁体

这会导致 dict 内存翻倍

（2）简体保留，移除所有繁体。

需要提供一个繁简体转换的工具，

感觉这种方式比较合理。