package cn.bupt.dssc.interceptor;

import cn.bupt.dssc.common.utils.UserContext;
import cn.bupt.dssc.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtTool jwtTool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 由于前端没有做JWT相关，所以暂时不做校验

        // 1.获取请求头中的 token
//        String token = request.getHeader("authorization");
        // 2.校验token
//        String userId = jwtTool.parseToken(token);
        // 3.存入上下文
//        UserContext.setUser(userId);
        // 4.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户
//        UserContext.removeUser();
    }
}
