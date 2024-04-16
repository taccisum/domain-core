package com.github.taccisum.domain.core.common.dict.impl;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng@baidu.com
 * @since 2024/4/16
 */
public class SimpleNamespaceTest {

    @Test
    public void ofCode() {
        SimpleDict dict = new SimpleDict();
        dict.addItem("foo", "bar0", 0);
        dict.addItem("foo", "bar1", 1);
        SimpleNamespace ns = new SimpleNamespace("foo", dict);
        assertThat(ns.of("bar0").code()).isEqualTo("bar0");
        assertThat(ns.of("bar1").code()).isEqualTo("bar1");
        assertThat(ns.of("bar0").val()).isEqualTo(0);
        assertThat(ns.of("bar1").val()).isEqualTo(1);

        assertThat(ns.of("bar2")).isNull();
    }

    @Test
    public void ofVal() {
        SimpleDict dict = new SimpleDict();
        dict.addItem("foo", "bar0", 0);
        dict.addItem("foo", "bar1", 1);
        SimpleNamespace ns = new SimpleNamespace("foo", dict);

        assertThat(ns.of(0).code()).isEqualTo("bar0");
        assertThat(ns.of(1).code()).isEqualTo("bar1");
        assertThat(ns.of(0).val()).isEqualTo(0);
        assertThat(ns.of(1).val()).isEqualTo(1);
    }

    @Test
    public void ofValWhenThereIsMultiSameValueInDifferentNs() {
        SimpleDict dict = new SimpleDict();
        dict.addItem("foo", "bar", 0);
        dict.addItem("foo1", "bar1", 0);
        dict.addItem("foo2", "bar2", 0);

        assertThat(new SimpleNamespace("foo", dict).of(0).code()).isEqualTo("bar");
        assertThat(new SimpleNamespace("foo1", dict).of(0).code()).isEqualTo("bar1");
        assertThat(new SimpleNamespace("foo2", dict).of(0).code()).isEqualTo("bar2");
    }
}