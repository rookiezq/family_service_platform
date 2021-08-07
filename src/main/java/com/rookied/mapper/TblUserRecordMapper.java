package com.rookied.mapper;

import com.rookied.bean.TblUserRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户档案 Mapper 接口
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
//@Component
public interface TblUserRecordMapper extends BaseMapper<TblUserRecord> {
    TblUserRecord login(@Param("username") String username,@Param("password") String password);
}
