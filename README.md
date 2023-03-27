# pinyin

[pinyin](https://github.com/houbb/pinyin) 是 java 实现的高性能中文拼音转换工具。

[![Build Status](https://travis-ci.com/houbb/segment.svg?branch=master)](https://travis-ci.com/houbb/pinyin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/pinyin/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/pinyin)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/pinyin/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/nlp-common)

> [在线体验](https://houbb.github.io/opensource/pinyin)

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

- 支持同音字

### v0.4.0 主要变更

- 更新依赖版本，移除控台日志

# 快速开始

## 准备

jdk 1.7+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>pinyin</artifactId>
    <version>0.4.0</version>
</dependency>
```

## 快速开始

参考 [PinyinHelperTest](https://github.com/houbb/pinyin/blob/master/src/test/java/com/github/houbb/pinyin/test/util/PinyinHelperTest.java)

### 方法概览

| 方法 | 返回值 | 说明 |
|:----|:----|:----|
| toPinyin(String) | String | 文本转换为拼音 |
| toPinyin(String, PinyinStyleEnum) | String | 文本转换为拼音，可指定拼音样式 |
| toPinyin(String, PinyinStyleEnum, String) | String | 文本转换为拼音，可指定拼音样式，可指定连接符号 |
| toPinyinList(char) | List<String> | 返回汉字所有拼音列表 |
| toPinyinList(char, PinyinStyleEnum) | List<String> | 返回汉字所有拼音列表，指定拼音样式 |
| hasSamePinyin(char, char) | boolean | 判断两个汉字是否有相同的读音 |
| samePinyinMap(char) | Map<String, List<String>> | 返回汉字的同音字MAP，key 为拼音 NUM_LAST 模式 |
| samePinyinList(String) | List<String> | 返回拼音 NUM_LAST 模式对应的同音字 |

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

# 同音字

## 同音字 map

返回一个汉字，所有拼音对应的同音字列表。

```java
final char hanzi2 = '重';
Map<String,List<String>> map2 = PinyinHelper.samePinyinMap(hanzi2);
```

对应的同音字结果为：

```
{tong2=[㠉, 㠽, 㣚, 㣠, 㤏, 㮔, 㸗, 㼧, 㼿, 䂈, 䆚, 䮵, 䳋, 䴀, 䶱, 仝, 佟, 侗, 偅, 僮, 勭, 同, 哃, 垌, 峂, 峒, 峝, 庝, 彤, 晍, 曈, 朣, 桐, 橦, 氃, 洞, 浵, 湩, 潼, 烔, 燑, 爞, 犝, 狪, 獞, 痌, 眮, 瞳, 砼, 硐, 硧, 秱, 穜, 童, 筒, 筩, 粡, 絧, 膧, 艟, 茼, 蚒, 蜼, 蟲, 衕, 詷, 赨, 酮, 重, 鉖, 鉵, 銅, 铜, 餇, 鮦, 鲖, 鼕, 𠖄, 𡦜, 𢈉, 𢏕, 𢓘, 𣑸, 𣪯, 𤱇, 𤺄, 𥩌, 𥫂, 𦏆, 𦒍, 𦨴, 𧇌, 𧊚, 𧋒, 𧋚, 𧌝, 𧳆, 𨚯, 𨜳, 𨝯, 𨠌, 𩍅, 𩩅, 𩻡, 𪀭, 𫍣], zhong4=[㐺, 㲴, 㼿, 䱰, 中, 乑, 仲, 众, 偅, 堹, 妕, 媑, 狆, 眾, 祌, 种, 種, 穜, 筗, 緟, 茽, 蚛, 蟲, 衆, 衶, 衷, 褈, 諥, 踵, 重, 𠱧, 𡥿, 𢝆, 𣱧, 𤚏, 𥻝, 𦌋, 𦔉, 𧬤, 𧳮, 𨉢, 𩾋, 𩿀], chong2=[㓽, 㹐, 䌬, 䖝, 䳯, 崇, 崈, 漴, 烛, 爞, 痋, 种, 種, 緟, 茧, 虫, 蝩, 蟲, 褈, 酮, 重, 隀, 𡿂, 𢖄, 𢝈, 𣐯, 𧝎, 𨛱, 𩅃, 𩌨, 𩜖, 𩞉, 𩞋]}
```

每一个读音作为 key，对应的同音字作为 list。

当然，有时候我们希望获取指定拼音的同音字列表。

## 同音字 List

```java
final String pinyinNumLast = "zhong4";
List<String> pinyinList = PinyinHelper.samePinyinList(pinyinNumLast);
```

对应结果：

```
[㐺, 㲴, 㼿, 䱰, 中, 乑, 仲, 众, 偅, 堹, 妕, 媑, 狆, 眾, 祌, 种, 種, 穜, 筗, 緟, 茽, 蚛, 蟲, 衆, 衶, 衷, 褈, 諥, 踵, 重, 𠱧, 𡥿, 𢝆, 𣱧, 𤚏, 𥻝, 𦌋, 𦔉, 𧬤, 𧳮, 𨉢, 𩾋, 𩿀]
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

对比 pinyin4j 版本为 v2.5.1

## 单个分词

| 对比函数 | 对比次数 | 对比内容 | 耗时 |
|:---|:---|:---|:---|
| `Pinyin4j toHanyuPinyinStringArray()` | 100w 次 | 相同文本随机选择一个字符 | 650 ms |
| `pinyin toPinyin()` | 100w 次 | 相同文本随机选择一个字符 | 410 ms |

## 字符串分词

| 对比函数 | 对比次数 | 对比内容 | 耗时 |
|:---|:---|:---|:---|
| `Pinyin4j toHanyuPinyinString()` | 1w 次 | 相同长文本 | 26324 ms |
| `pinyin toPinyin()` | 1w 次 | 相同长文本 | 16260 ms |
| `pinyin toPinyin()` | 1w 次 | 相同长文本, chars 分词模式 | 14804 ms |

pinyin4j 的汉语字符串转换是不支持分词的，本项目在支持分词的情况下速度基本是 pinyin4j 的两倍。

# 技术鸣谢

[pinyin-data](https://github.com/mozillazg/pinyin-data) 与 [phrase-pinyin-data](https://github.com/mozillazg/phrase-pinyin-data) 提供的拼音数据。

[segment](https://github.com/houbb/segment) 提供的中文分词。

# NLP 开源矩阵

[pinyin 汉字转拼音](https://github.com/houbb/pinyin)

[pinyin2hanzi 拼音转汉字](https://github.com/houbb/pinyin2hanzi)

[segment 高性能中文分词](https://github.com/houbb/segment)

[opencc4j 中文繁简体转换](https://github.com/houbb/opencc4j)

[nlp-hanzi-similar 汉字相似度](https://github.com/houbb/nlp-hanzi-similar)

[word-checker 拼写检测](https://github.com/houbb/word-checker)

[sensitive-word 敏感词](https://github.com/houbb/sensitive-word)
    
# 后期 Road-Map

- [x] 键盘输入拼音形式支持

- [x] 引导类开放分词的自定义配置

- [x] 同音字列表返回

- [ ] 同韵字列表返回

- [ ] 音近字

- [ ] 拼音转汉字
