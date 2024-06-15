package cn.chinatelecom.utils;

import cn.chinatelecom.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSonData<T> {

    /**
     * 状态码 0 表示成功
     */

    private Integer code;
    /**
     * 数据
     */
    private T data;
    /**
     * 描述
     */
    private String msg;


    /**
     * 成功，不传入数据
     *
     * @return
     */
    public static JSonData buildSuccess() {
        return new JSonData(0, null, null);
    }

    /**
     * 成功，传入数据
     *
     * @param data
     * @return
     */
    public static <T> JSonData buildSuccess(T data) {
        return new JSonData(0, data, null);
    }

    /**
     * 失败，传入描述信息
     *
     * @param msg
     * @return
     */
    public static JSonData buildError(String msg) {
        return new JSonData(-1, null, msg);
    }


    /**
     * 自定义状态码和错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static JSonData buildCodeAndMsg(int code, String msg) {
        return new JSonData(code, null, msg);
    }

    /**
     * 传入枚举，返回信息
     *
     * @param codeEnum
     * @return
     */
    public static JSonData buildResult(BizCodeEnum codeEnum) {
        return JSonData.buildCodeAndMsg(codeEnum.getCode(), codeEnum.getMessage());
    }
}

