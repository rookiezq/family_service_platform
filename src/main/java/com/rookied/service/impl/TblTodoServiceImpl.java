package com.rookied.service.impl;

import com.rookied.bean.TblTodo;
import com.rookied.mapper.TblTodoMapper;
import com.rookied.service.base.TblTodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 待办事项 服务实现类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@Service
public class TblTodoServiceImpl extends ServiceImpl<TblTodoMapper, TblTodo> implements TblTodoService {

}
