package xyz.timessuntech.cloud.mall.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import xyz.timessuntech.cloud.mall.dao.entity.MallUser;

@Repository
public interface MallUserDao extends PagingAndSortingRepository<MallUser, String>, JpaSpecificationExecutor<MallUser> {

	MallUser findByNumber(long number);

	MallUser findByEmail(String email);

	@Query("from MallUser t where id = :id")
	List<MallUser> queryList(@Param("id") String id, Pageable pageable);

	@Query("select max(CASE WHEN number IS NULL THEN 0 ELSE number END) from MallUser")
	Long queryMaxNumber();
}
