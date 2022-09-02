package com.nad.model.base;

import com.nad.constant.ErrorCode;

/**
 * @author duynguyen
 **/
public abstract class RequestEntity {

    public abstract ErrorCode validate();
}
