# pinyin

[pinyin](https://github.com/houbb/pinyin) 是 java 实现的高性能中文拼音转换工具。

[![Build Status](https://travis-ci.com/houbb/segment.svg?branch=master)](https://travis-ci.com/houbb/pinyin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/pinyin/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/pinyin)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/pinyin/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/nlp-common)

> [变更日志](https://github.com/houbb/pinyin/blob/master/CHANGELOG.md)

## 创作目的

想为 java 设计一款便捷易用的拼音工具。

[如何为 java 设计一款高性能的拼音转换工具 pinyin4j](https://houbb.github.io/2020/01/09/how-to-design-pinyin4j)

## 特性

- [性能是 pinyin4j 的两倍](#benchmark)

- 极简的 api 设计

- 支持转换长文本

- 支持多音字

- 支持多种拼音标注方式

- 支持中文分词

- 支持中文繁简体

- 支持自定义拼音词库

- 支持判断是否为同音字

- 支持单独获取声调信息

- 支持获取声母韵母信息

### v0.1.4 主要变更

- 支持输入法的拼音模式

# 快速开始

## 准备

jdk 1.7+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>pinyin</artifactId>
    <version>0.1.4</version>
</dependency>
```

## 快速开始

参考 [PinyinHelperTest](https://github.com/houbb/pinyin/blob/master/src/test/java/com/github/houbb/pinyin/test/util/PinyinHelperTest.java)

### 返回中文的拼音

使用 `PinyinHelper.toPinyin(string)` 进行中文转换。

```java
String pinyin = PinyinHelper.toPinyin("我爱中文");
Assert.assertEquals("wǒ ài zhōng wén", pinyin);
```

### 返回多音字列表

使用 `PinyinHelper.toPinyinList(char)` 获取多音字的读音列表。

```java
List<String> pinyinList = PinyinHelper.toPinyinList('重');
Assert.assertEquals("[zhòng, chóng, tóng]", pinyinList.toString());
```

### 分词特性

默认支持中文分词，对用户透明。

```java
String pinyin = PinyinHelper.toPinyin("重庆火锅");
Assert.assertEquals("chóng qìng huǒ guō", pinyin);

String pinyin2 = PinyinHelper.toPinyin("分词也很重要");
Assert.assertEquals("fēn cí yě hěn zhòng yào", pinyin2);
```

# 指定拼音标注形式

## api 

```java
/**
 * 转换为拼音
 * @param string 原始信息
 * @param styleEnum 样式枚举
 * @return 结果
 * @since 0.0.3
 */
public static String toPinyin(final String string, final PinyinStyleEnum styleEnum)
```

### PinyinStyleEnum 样式枚举

| 枚举 | 说明 | 例子 |
|:---|:---|:---|
| `DEFAULT` | 默认模式，拼音声调在韵母第一个字母上。| pīn yīn |
| `NORMAL` | 普通模式，即不带声调。| pin yin |
| `NUM_LAST` | 数字标注模式，即拼音声调以数字形式在各个拼音之后，用数字 1-5 进行表示。| pin1 yin1 |
| `FIRST_LETTER` | 首字母模式，只返回拼音的首字母部分。| p y |
| `INPUT` | 键盘输入模式，使用 v 替代 ü。| nv hai |

## 测试案例

### DEFAULT

```java
String pinyin = PinyinHelper.toPinyin("我爱中文", PinyinStyleEnum.DEFAULT);
Assert.assertEquals("wǒ ài zhōng wén", pinyin);
```

### NORMAL

```java
String pinyin = PinyinHelper.toPinyin("我爱中文", PinyinStyleEnum.NORMAL);
Assert.assertEquals("wo ai zhong wen", pinyin);
```

### NUM_LAST

```java
String pinyin = PinyinHelper.toPinyin("我爱中文", PinyinStyleEnum.NUM_LAST);
Assert.assertEquals("wo3 ai4 zhong1 wen2", pinyin);
```

### FIRST_LETTER

```java
String pinyin = PinyinHelper.toPinyin("我爱中文", PinyinStyleEnum.FIRST_LETTER);
Assert.assertEquals("w a z w", pinyin);
```

### 指定连接符号

有时候使用者希望指定特定的连接符号。

```java
final String text = "我爱中文";
Assert.assertEquals("wazw", PinyinHelper.toPinyin(text, PinyinStyleEnum.FIRST_LETTER, StringUtil.EMPTY));
```

第三个参数用于指定一个非 null 的字符串作为拼音连接符号。 （默认是空格进行连接）

# 更多特性

## 是否为同音字

`PinyinHelper.hasSamePinyin()` 用来判断两个汉字是否为同音字，包括对多音字的处理。

```java
char one = '花';
char two = '重';
char three = '中';
char four = '虫';

Assert.assertFalse(PinyinHelper.hasSamePinyin(one, three));
Assert.assertTrue(PinyinHelper.hasSamePinyin(two, three));
Assert.assertTrue(PinyinHelper.hasSamePinyin(two, four));
```

## 支持繁体中文

本框架支持繁体中文获取对应拼音。

当然你也可以使用 [opencc4j](https://github.com/houbb/opencc4j) 统一转换为简体再做拼音获取，从而提高准确率。

```java
String pinyin = PinyinHelper.toPinyin("奮斗");
Assert.assertEquals("fèn dòu", pinyin);
```

# 自定义拼音词库

已有的词库很难满足各种各样的场景，本工具提供自定义拼音词库的功能。

## 自定义单个字的拼音

### 自定义字典

自定义 `resources/pinyin_dict_char_define.txt` 文件内容，格式如下：

```
莪:wǒ
噯:ài,āi,ǎi
```

汉字与拼音使用英文`:` 分割，多音字使用英文`,`做拼音的分割。

### 测试

```java
String pinyin = PinyinHelper.toPinyin("莪");
Assert.assertEquals("wǒ", pinyin);
```

## 自定义词组的拼音

### 自定义字典

自定义 `resources/pinyin_dict_phrase_define.txt` 文件内容，格式如下：

```
褈慶炎鍋:chóng qìng huǒ guō
```

### 测试

以一串火星文为例。

```java
String pinyin = PinyinHelper.toPinyin("莪噯褈慶炎鍋");
Assert.assertEquals("wǒ ài chóng qìng huǒ guō", pinyin);
```

## 注意点

1. 仅支持汉语的自定义拼音。

2. 为了保持功能的一致性，如果你自定义的是繁体字（词），对应的简体也会变成自定义注音。 

# Benchmark

测试代码见 [BenchmarkTest.java](https://github.com/houbb/pinyin/blob/master/src/test/java/com/github/houbb/pinyin/test/benchmark/BenchmarkTest.java)

性能对比时使用相同的机器，相同测试文本，验证相同的次数。

均提前做好预热处理，可供参考。

## 单个分词

| 对比函数 | 对比次数 | 对比内容 | 耗时 |
|:---|:---|:---|:---|
| `Pinyin4j toHanyuPinyinStringArray()` | 100w 次 | 相同文本随机选择一个字符 | 621 ms |
| `pinyin toPinyin()` | 100w 次 | 相同文本随机选择一个字符 | 317 ms |

## 字符串分词

| 对比函数 | 对比次数 | 对比内容 | 耗时 |
|:---|:---|:---|:---|
| `Pinyin4j toHanyuPinyinString()` | 1w 次 | 相同长文本 | 33002 ms |
| `pinyin toPinyin()` | 1w 次 | 相同长文本 | 17975 ms |

而且 Pinyin4j 的汉语字符串转换是不支持分词的，本项目在支持分词的情况下速度基本依然是 pinyin4j 的两倍。

# 技术鸣谢

[pinyin-data](https://github.com/mozillazg/pinyin-data) 与 [phrase-pinyin-data](https://github.com/mozillazg/phrase-pinyin-data) 提供的拼音数据。

[segment](https://github.com/houbb/segment) 提供的中文分词。

# 后期 Road-Map

- [x] 键盘输入拼音形式支持

- [ ] 同音字列表返回

- [ ] 谐音字判断

- [ ] 谐音字列表返回

- [ ] 拼音转汉字