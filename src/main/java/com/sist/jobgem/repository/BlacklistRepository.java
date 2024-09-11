package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Blacklist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {

  @Query("SELECT b FROM Blacklist b " +
      "WHERE b.blState = :blState ")
  Page<Blacklist> findByStatus(
      @Param("blState") int blState, @Param("pageable2") Pageable pageable2);

  @Query("SELECT b FROM Blacklist b " +
      "WHERE b.blState = :blState " +
      "AND ((:searchType = 'title' AND b.blTitle LIKE %:searchValue%) " +
      "OR (:searchType = 'content' AND b.blContent LIKE %:searchValue%) " +
      "OR (:searchType = 'writer' AND b.user.usId LIKE %:searchValue%))")
  Page<Blacklist> findByWithSearch(
      @Param("blState") int blState, @Param("pageable2") Pageable pageable2,
      @Param("searchType") String searchType, @Param("searchValue") String searchValue);

  @Query("SELECT b FROM Blacklist b " +
      "WHERE b.blState = :blState " +
      "AND b.coIdx IS NOT NULL")
  Page<Blacklist> findByStatusAndCompany(@Param("blState") int blState, @Param("pageable2") Pageable pageable2);

  @Query("SELECT b FROM Blacklist b " +
      "WHERE b.blState = :blState " +
      "AND b.coIdx IS NOT NULL " +
      "AND ((:searchType = 'title' AND b.blTitle LIKE %:searchValue%) " +
      "OR (:searchType = 'content' AND b.blContent LIKE %:searchValue%) " +
      "OR (:searchType = 'writer' AND b.user.usId LIKE %:searchValue%))")
  Page<Blacklist> findByWithSearchAndCompany(
      @Param("blState") int blState, @Param("pageable2") Pageable pageable2,
      @Param("searchType") String searchType, @Param("searchValue") String searchValue);

  @Query("SELECT b FROM Blacklist b " +
      "WHERE b.blState = :blState " +
      "AND b.joIdx IS NOT NULL")
  Page<Blacklist> findByStatusAndJobseeker(@Param("blState") int blState, @Param("pageable2") Pageable pageable2);

  @Query("SELECT b FROM Blacklist b " +
      "WHERE b.blState = :blState " +
      "AND b.joIdx IS NOT NULL " +
      "AND ((:searchType = 'title' AND b.blTitle LIKE %:searchValue%) " +
      "OR (:searchType = 'content' AND b.blContent LIKE %:searchValue%) " +
      "OR (:searchType = 'writer' AND b.user.usId LIKE %:searchValue%))")
  Page<Blacklist> findByWithSearchAndJobseeker(
      @Param("blState") int blState, @Param("pageable2") Pageable pageable2,
      @Param("searchType") String searchType, @Param("searchValue") String searchValue);

  @Modifying
  @Query("UPDATE Blacklist b SET b.blState = 0 WHERE b.id = :id")
  int updateStateById(@Param("id") int id);

  @Modifying
  @Query("UPDATE Blacklist b SET b.blProcess = 0 WHERE b.id = :id")
  int updateProcessNo(@Param("id") int id);

  @Modifying
  @Query("UPDATE Blacklist b SET b.blProcess = 1 WHERE b.id = :id")
  int updateProcessYes(@Param("id") int id);

}
