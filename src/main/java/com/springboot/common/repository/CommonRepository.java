package com.springboot.common.repository;

        import org.springframework.context.annotation.Bean;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by AlbertXmas on 2017/1/13.
 */

/***
 *
 * @param <R> repository
 */
@NoRepositoryBean
public interface CommonRepository<E> extends JpaRepository<E, Long>,JpaSpecificationExecutor<E> {

}
