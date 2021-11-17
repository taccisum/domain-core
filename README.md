# Domain Core

For DDD layer domain with spring.

[CHANGELOG](./CHANGELOG.md)

[![Build Status](https://app.travis-ci.com/taccisum/domain-core.svg?branch=master)](https://app.travis-ci.com/taccisum/domain-core)
[![codecov](https://codecov.io/gh/taccisum/domain-core/branch/main/graph/badge.svg?token=lCBxSWyiWU)](https://codecov.io/gh/taccisum/domain-core)

## How to

引入依赖

```xml
<dependency>
    <groupId>com.github.taccisum</groupId>
    <artifactId>domain-core</artifactId>
    <version>{latest-version}</version>
</dependency>
```

## References

### 为普通对象注入 bean

定义你的类，并使用标准 Spring 注解 `@Autowired`

```java
public class Foo {
    @Autowired
    Bar bar;
    Bar bar1;
}
```

使用 SpringUtils 注入 Bar 的 spring bean 实体

```java
Foo foo = new Foo();
SpringUtils.inject(foo);
assertThat(foo.bar).isNotNull();
assertThat(foo.bar1).isNull();
```


