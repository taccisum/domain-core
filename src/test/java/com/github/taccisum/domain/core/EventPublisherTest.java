package com.github.taccisum.domain.core;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public class EventPublisherTest {
    @Rule
    public OutputCapture capture = new OutputCapture();

    @Test
    public void index() {
        capture.reset();
        Pub pub = new Pub();
        pub.setEventBus(event -> System.out.println("event public success."));
        Event.Base<Pub> event = new Event.Base<Pub>() {
        };
        pub.publish(event);

        assertThat(event.getPublisher()).isEqualTo(pub);
        capture.expect(containsString("event public success."));

        // 发布不匹配的事件
        capture.reset();
        pub.publish(new Event.Base<AnotherPub>() {
        });
        // 告警，但仍可发布成功
        capture.expect(containsString("not matched event"));
        capture.expect(containsString("event public success."));
    }

    private static class Pub extends EventPublisher.Base {
    }

    private static class AnotherPub extends EventPublisher.Base {
    }
}