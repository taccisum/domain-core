# DDD Core

DDD with spring.

[CHANGELOG](./CHANGELOG.md)


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


