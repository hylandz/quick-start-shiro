package com.xlx.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常日志打印工具类
 *
 * @author xielx at 2020/4/26 12:33
 */
public class ExceptionLogUtil {
    
    
    public static String getMessage(Exception e) {
        String msg = "";
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            msg = sw.toString();
        } catch (IOException io) {
            io.printStackTrace();
            msg = io.getMessage();
        }
        return msg;
    }
}
