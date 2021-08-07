package com.rookied.service;

import com.rookied.bean.TblUserRecord;
import com.rookied.mapper.TblUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangqiang
 * @date 2021/8/7
 */
@Service
public class LoginService {
    @Autowired
    TblUserRecordMapper tblUserRecordMapper;

    public TblUserRecord login(String username, String password) {
        return tblUserRecordMapper.login(username, password);
    }
}
