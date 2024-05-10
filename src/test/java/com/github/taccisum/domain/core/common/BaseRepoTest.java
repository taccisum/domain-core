package com.github.taccisum.domain.core.common;

import com.github.taccisum.domain.core.DAO;
import com.github.taccisum.domain.core.DataObject;
import com.github.taccisum.domain.core.Entity;
import lombok.Data;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author taccisum - liaojinfeng@baidu.com
 * @since 2024/5/10
 */
public class BaseRepoTest {

    @Test
    public void create() {
        DAO<FooDO> dao = mock(DAO.class);
        FooRepo repo = spy(new FooRepo(dao));
        Foo foo = repo.create(new FooDO(1L), Foo.class);
        assertThat(foo).isNotNull();
        verify(dao, times(1)).insert(any());
    }

    @Test
    public void canCast() {
        FooRepo repo = new FooRepo(null);
        assertThat(repo.canCast(new Foo(1L), Foo.class)).isTrue();
        assertThat(repo.canCast(new SubFoo(1L), SubFoo.class)).isTrue();
        assertThat(repo.canCast(new Foo(1L), SubFoo.class)).isFalse();
        assertThat(repo.canCast(new SubFoo(1L), Foo.class)).isTrue();
    }

    public static class Foo extends Entity.Base<Long> {
        public Foo(Long id) {
            super(id);
        }
    }

    public static class SubFoo extends Foo {
        public SubFoo(Long id) {
            super(id);
        }
    }

    @Data
    public static class FooDO implements DataObject<Long> {
        private Long id;

        public FooDO(Long id) {
            this.id = id;
        }
    }

    public static class FooRepo extends BaseRepo<Foo, FooDO> {
        public FooRepo(DAO<FooDO> dao) {
            super(dao);
        }

        @Override
        protected Foo toEntity(FooDO data) {
            return new Foo(data.getId());
        }
    }
}