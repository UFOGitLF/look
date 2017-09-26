package com.fly.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序,如果AddGroup组失败,则UpdateGroup组不会再校验
 * Created by xinshidai on 17/9/18.
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {
}
