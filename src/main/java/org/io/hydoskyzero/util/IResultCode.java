package org.io.hydoskyzero.util;

import java.io.Serializable;

/**
 * @author 念着倒才子傻
 */
public interface IResultCode extends Serializable {
    String getMessage();

    int getCode();
}

