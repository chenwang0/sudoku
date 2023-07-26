package com.iiikn.execption;

/**
 * 类的作用和功能。
 * <p>
 * 类的设计思路。
 *
 * @author: cw
 * @since: 2023/7/25 19:20
 * @version: v0.1
 * <p>
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class IOCAssertException extends RuntimeException {

    public IOCAssertException(String msg) {
        super(msg);
    }

    public IOCAssertException(String message, Throwable cause) {
        super(message, cause);
    }
}
