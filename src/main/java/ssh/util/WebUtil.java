package ssh.util;

import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * create by tan on 2018/5/11
 * 将request的请求参数封装到bean中
 **/
public class WebUtil {
    /**
     * 将request对象转换成T对象
     * @param request
     * @param clazz
     * return
     * */
    public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
