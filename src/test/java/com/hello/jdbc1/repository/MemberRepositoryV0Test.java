package com.hello.jdbc1.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.hello.jdbc1.domain.Member;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void save() throws SQLException {
        Member member = new Member("memberV0", 10000);
        repository.save(member);
    }
}