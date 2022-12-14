package com.hello.jdbc1.repository;

import com.hello.jdbc1.domain.Member;
import java.sql.SQLException;

public interface MemberRepositoryEx {

    Member save(Member member) throws SQLException; // 특정 기술에 종속된 인터페이스

    Member findById(String memberId) throws SQLException;

    void update(String memberId, int money) throws SQLException;

    void delete(String memberId) throws SQLException;
}