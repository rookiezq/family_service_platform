package com.rookied;

import com.rookied.bean.TblUserRecord;
import com.rookied.mapper.TblUserRecordMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FamilyServicePlatformApplicationTests {
    @Autowired
    TblUserRecordMapper userRecordMapper;

    @Test
    void contextLoads() {
        TblUserRecord admin = userRecordMapper.login("admin", "c4ca4238a0b923820dcc509a6f75849b");
        System.out.println(admin);
    }

}
