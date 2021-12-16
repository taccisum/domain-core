# Domain Core

For DDD layer domain with spring.

[CHANGELOG](./CHANGELOG.md)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.taccisum/domain-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.taccisum%22%20AND%20a:%22domain-core%22)
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

### 实体

实现接口 `Entity` 以表示该类是一个实体。你也可以选择继承更方便的 `Entity.Base`

```java
public class Foo extends Entity.Base<Long> {
    public Foo(Long id) {
        super(id);
    }
    
    // define more methods...
}
```

```java
Foo foo = new Foo(1L)
foo.id();
foo.xxx();
```

当实体的依赖多了以后，创建实体的工作将会变得非常繁琐。你可以引入工厂概念来解决此问题，具体可参考 `工厂` 章节。

### 领域事件

实现接口 `Event` 以表示该类是一个事件。你也可以选择继承更方便的 `Event.Base`

```java
public class FooCreateEvent extends Event.Base<Foo> {
}
```

接着你可以通过事件发布者发布此事件

```java
// 每类事件均会关联一类发布者，请避免通过不匹配的发布者进行事件发布。
FooCreateEvent event = new FooCreateEvent()
foo.publish(event);
```

如何订阅该事件取决于你采用的 `EventBus` 具体实现，可参考事件发布者章节。

### 事件发布者

事件发布者，可以对领域事件进行发布。它是一个抽象的概念，具体可能是一个实体、领域服务、或其它。

实现接口 `EventPublisher` 以表示该类是一个实体。你也可以选择继承更方便的 `EventPublisher.Base`

若你的发布者继承自基类，它会在发布事件时自动将事件与当前发布者建立关联

```java
// 以下代码可以执行通过
FooCreateEvent event = new FooCreateEvent()
foo.publish(event);
assert event.getPublisher() == foo;
```

不过在发布之前，请先确保你的发布者关联了事件总线

```java
foo.setEventBus(eventBus);
```

在实际项目中，此工作会变得非常繁琐。如果你的事件发布者本身是一个实体，你可以通过工厂来解决该问题。

#### 事件总线

事件总线是最终负责将领域事件与订阅者进行匹配的角色。

为了兼容不同的实现，我们将使用统一的抽象 `com.github.taccisum.domain.core.EventBus`，再通过不同的适配器集成不同的事件总线。

```java
// 适配 Google Guava EventBus
EventBus eventBus = new GuavaEventBusAdapter(new com.google.common.eventbus.EventBus());
foo.setEventBus(eventBus);
```

```java
// 订阅事件，应遵循 Guava 定义的方式
import com.google.common.eventbus.Subscribe;

public interface DomainEventSubscriber {
    @Subscribe
    void listen(FooCreateEvent e) throws Throwable {
        // do something here
    }
}
```


### 工厂

工厂用于处理复杂的实体构造工作。

实现接口 `Factory` 以表示该类是一个工厂。`FactoryAspect` 会对所有工厂类的方法进行增强，自动完成 `EventBus` 关联，Spring Beans 注入等通用操作，你的代码只需要实现个性化的装配逻辑即可。

```java
@Component
public class MyFactory implements Factory {
    public Foo newFoo(long id, int type) {
        switch (type) {
            case 1:
                return new FooSubType1(id);
            case 2:
                return new FooSubType2(id);
            case 3:
                return new FooSubType3(id);
            default:
                return new Foo(id);
        }
    }
}
```

#### FactoryAspect 规则

- 只作用于实现了 `Factory` 接口的类中定义的方法。
- 为实现了接口 `EventBusAware` 的类自动注入 EventBus
- 查找目标类及其父类中标注有 `@Autowired` 注解的字段，自动寻找到相应的 Spring Bean 进行注入


#### 为普通对象注入 bean

不论你的类是一个实体还是普通的类，你都可以使用标准的 Spring 注解 `@Autowired`，结合 `SpringUtils` 完成 Spring Beans 依赖注入

```java
public class Foo {
    @Autowired
    Bar bar;
    Bar bar1;
}
```

```java
Foo foo = new Foo();
SpringUtils.inject(foo);
assert foo.bar != null;
assert foo.bar1 == null;
```

