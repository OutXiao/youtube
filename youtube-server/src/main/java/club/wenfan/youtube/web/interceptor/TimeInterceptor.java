package club.wenfan.youtube.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class TimeInterceptor implements HandlerInterceptor {

	private Logger log= LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		log.info("预处理开始......");
		log.info("controller处理的类："+((HandlerMethod) handler).getBean().getClass().getName());
		log.info("处理的方法："+((HandlerMethod) handler).getMethod().getName());
        return true;
    }


    /*
     * 如果controller抛出异常  postHandle不被调用
     *
     * @param
     * @return
     * @author wenfan
     * @date
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		log.info("proHandle方法开始处理");
    }

	/*
	 * 是否抛出异常都处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("拦截器处理完成.....");

	}


}
