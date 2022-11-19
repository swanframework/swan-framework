package com.swan.web.utils;

import com.swan.core.exception.SwanBaseException;
import com.swan.web.excetion.SwanValidateException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

/** hibernate 字段约束校验工具类
 * @author zongf
 * @date 2019-11-28
 * @company autohome
 */
public class HibernateValidatorUtil {

    // 标准校验器
    private static final Validator VALIDATOR;

    // 短路校验器
    private static final Validator FAST_VALIDATOR;

    static {
        VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
        FAST_VALIDATOR = Validation.byProvider(HibernateValidator.class).configure()
                .failFast(true).buildValidatorFactory().getValidator();
    }

    /** 短路校验对象属性约束，遇到第一个违约属性则停止继续校验
     * @param object 使用hibernate validator注解修饰的vo对象
     * @author zongf
     * @date 2019-11-28
     * @company autohome
     */
    public static void fastValidate(Object object) throws ValidationException{
        validate(object, FAST_VALIDATOR);
    }

    /** 校验对象属性约束，返回所有违约信息
     * @param object 使用hibernate validator注解修饰的vo对象
     * @author zongf
     * @date 2019-11-28
     * @company autohome
     */
    public static void validate(Object object) throws ValidationException{
        validate(object, VALIDATOR);
    }

    /**
     * @param object  待校验约束对象
     * @param validator 校验器
     * @author zongf
     * @date 2019-11-28
     * @company autohome
     */
    private static void validate(Object object, Validator validator) {
        // 校验字段约束
        Set<ConstraintViolation<Object>> validateResults = validator.validate(object);

        if (!validateResults.isEmpty()){
            StringBuffer sb = new StringBuffer();
            for (ConstraintViolation<Object> validateResult : validateResults) {
                sb.append(validateResult.getMessage()).append("\n");
            }
            System.out.println(sb.toString());
            throw new SwanValidateException(sb.toString());
        }
    }

}
