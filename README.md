# pinyin

[pinyin](https://github.com/houbb/pinyin) 是 java 实现的中文拼音工具。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/pinyin/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/pinyin)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/pinyin/blob/master/LICENSE.txt)

> [变更日志](https://github.com/houbb/pinyin/blob/master/CHANGELOG.md)

## 创作目的

- 提升转换性能

- 压缩词典空间

并将这种思想应用到其他类似的框架中。

- 并行

能否并行转换？？

分词之后，直接 fork-join 处理。

- 其他尽可能提升的策略？？

- 缓存

有用吗？

分词需要 cache 吗？

预取

字符串拼接的最高性能实现方式？？

https://blog.csdn.net/sofeware333/article/details/91433540

- Benchmark

性能对标 TinyPinyin

pinyin4j

## 特性

- 内存与性能的平衡

- 首字母

- 是否启用声调

- 是否启用多音字

- 声调的形式

数字？字母？

- 繁简体

- 拼音转汉字

贪心匹配。

特性对标：

[js 版本](https://github.com/hotoo/pinyin)

[相关资料](https://github.com/overtrue/pinyin-resources)

https://github.com/mozillazg/pinyin-data

https://github.com/mozillazg/phrase-pinyin-data

