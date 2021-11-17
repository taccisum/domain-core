package com.github.taccisum.domain.core.autoconfigure;

import com.github.taccisum.domain.core.utils.SpringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainCoreAutoConfigurationTest {
    @Test
    public void injectContext4SpringUtils() {
        assertThat(SpringUtils.getContext()).isNotNull();
    }
}