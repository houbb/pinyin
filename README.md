# pinyin

[pinyin](https://github.com/houbb/pinyin) 是 java 实现的高性能中文拼音转换工具。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/pinyin/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/pinyin)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/pinyin/blob/master/LICENSE.txt)

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

# 快速开始

## 准备

jdk 1.7+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>pinyin</artifactId>
    <version>0.0.4</version>
</dependency>
```

## 快速开始

参考 [PinyinBsTest](https://github.com/houbb/pinyin/blob/master/src/test/java/com/github/houbb/pinyin/test/bs/PinyinBsTest.java)

### 返回中文的拼音

使用 `PinyinHelper.toPinyin(string)` 进行中文转换。

```java
String pinyin = PinyinHelper.toPinyin("我爱中文");
Assert.assertEquals("wǒ ài zhōng wén", pinyin);
```

### 返回多音字列表

使用 `PinyinHelper.toPinyin(char)` 获取多音字的读音列表。

```java
List<String> pinyinList = PinyinHelper.toPinyin('重');
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

# 后期 Road-Map

- 支持中文繁简体

默认关闭该功能

- 拼音转汉字

## 用户自定义相关

- 用户自定义词组

- 用户自定义分词

# benchmark

测试代码见 [BenchmarkTest.java](https://github.com/houbb/pinyin/blob/master/src/test/java/com/github/houbb/pinyin/test/benchmark/BenchmarkTest.java)

性能对比时使用相同的机器，相同测试文本，验证相同的次数。

均提前做好预热处理，可供参考。

## 单个分词

| 对比函数 | 对比次数 | 对比内容 | 耗时 | 备注 |
|:---|:---|:---|:---|:---|
| `Pinyin4j toHanyuPinyinStringArray()` | 100w 次 | 相同文本随机选择一个字符 | 621 ms | |
| `pinyin toPinyin()` | 100w 次 | 相同文本随机选择一个字符 | 317 ms | |

## 字符串分词

| 对比函数 | 对比次数 | 对比内容 | 耗时 | 备注 |
|:---|:---|:---|:---|:---|
| `Pinyin4j toHanyuPinyinString()` | 1w 次 | 相同长文本 | 33002 ms | |
| `pinyin toPinyin()` | 1w 次 | 相同长文本 | 17975 ms | |

而且 Pinyin4j 的汉语字符串转换是不支持分词的，本项目在支持分词的情况下速度基本依然是 pinyin4j 的两倍。

# 技术鸣谢

[pinyin-data](https://github.com/mozillazg/pinyin-data) 与 [phrase-pinyin-data](https://github.com/mozillazg/phrase-pinyin-data) 提供的拼音数据。

[segment](https://github.com/houbb/segment) 提供的中文分词。
