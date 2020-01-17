# pinyin

[pinyin](https://github.com/houbb/pinyin) 是 java 实现的中文拼音工具。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/pinyin/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/pinyin)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/pinyin/blob/master/LICENSE.txt)

> [变更日志](https://github.com/houbb/pinyin/blob/master/CHANGELOG.md)

## 创作目的

想为 java 设计一款好用的拼音工具。

[如何为 java 设计一款高性能的拼音转换工具 pinyin4j](https://houbb.github.io/2020/01/09/how-to-design-pinyin4j)

## 版本特性

- 返回中文拼音

# 快速开始

## 准备

jdk 1.7+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>pinyin</artifactId>
    <version>0.0.1</version>
</dependency>
```

## 使用案例

参考 [PinyinBsTest](https://github.com/houbb/pinyin/blob/master/src/test/java/com/github/houbb/pinyin/test/bs/PinyinBsTest.java)

### 返回中文的拼音

直接使用 `PinyinHelper.toPinyin(string)` 进行中文转换。

```java
String pinyin = PinyinHelper.toPinyin("我爱中文");
Assert.assertEquals("wǒ ài zhōng wén", pinyin);
```

# 后期 Road-Map

- 支持多音字列表返回

- 支持中文繁简体

- 支持不同的拼音样式

- 拼音转汉字

- 添加对比 pinyin4j 的 benchmark

## 用户自定义相关

- 用户自定义词组拼音

- 用户自定义分词