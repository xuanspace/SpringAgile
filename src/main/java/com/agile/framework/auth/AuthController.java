package com.agile.framework.auth;

import com.agile.framework.controller.AbstractController;
import com.agile.framework.entity.AjaxResult;
import com.agile.framework.entity.LoginUser;
import com.agile.framework.utils.StringUtils;
import com.agile.framework.utils.VerifyCodeUtils;
import com.agile.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 系统登陆认证控制器
 *
 * 配置文件添AuthController扫描加载
 * <context:component-scan base-package="com.agile.framework.auth"/>
 *
 */

@Controller
@RequestMapping("/auth")
public class AuthController extends AbstractController {

    public static final Logger logger = LoggerFactory.getLogger(AuthController.class.getName());

    /**
     * 系统用户服务
     */
    private UserService userService;

    /**
     * 校验信息实例
     */
    @Autowired
    MessageSource messageSource;

    /**
     * 构造函数,注入服务
     * @service 服务实例
     * @validator 校验实例
     */
    @Autowired
    public AuthController(UserService service) {
        this.userService = service;
    }

    /**
     * 登陆页面请求
     * http://localhost/auth
     * @return void
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model){
        model.addAttribute("loginUser", new LoginUser());
        return "/user/login";
    }

    /**
     * 获取验证码请求
     * http://localhost/auth/captcha
     * @return void
     */
    @ResponseBody
    @RequestMapping(value="/captcha", method= RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            //生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            //存入会话session
            HttpSession session = request.getSession(true);
            session.setAttribute("captcha", verifyCode.toLowerCase());
            //生成图片
            int w = 146, h = 33;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (Exception e) {
            logger.error("获取验证码异常：%s",e.getMessage());
        }
    }

    /**
     * 用户登陆请求(form submit)
     * http://localhost/auth/signin
     * @return String
     */
    @RequestMapping(value="/signin", method= RequestMethod.POST)
    public String signin(@Valid LoginUser user, BindingResult result, ModelMap model, HttpServletRequest request) throws Exception {
        model.addAttribute("loginUser", user);
        HttpSession session = request.getSession(true);
        if (validate(session, user, result)) {
            return "redirect:/user/index";
        }
        return "/user/login";
    }

    /**
     * 用户登陆请求(Ajax)
     * http://localhost/auth/validate
     * @return String
     */
    @ResponseBody
    @RequestMapping(value="login", method=RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public AjaxResult post(@Valid LoginUser user, BindingResult result, HttpServletRequest request, HttpSession session) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        validate(session, user, result);
        ajaxResult.add(result);
        return ajaxResult;
    }

    /**
     * 用户退出请求
     * http://localhost/auth/logout
     * @return String
     */
    @RequestMapping(value="logout", method=RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpSession session) throws Exception {
        return "/index";
    }

    /**
     * 登陆请求校验
     * @return boolean
     */
    public boolean validate(HttpSession session, LoginUser user, BindingResult result) {
        // 登陆信息校验错误
        if (result.hasErrors()) {
            return false;
        }

        // 获取回话中的验证码
        String captcha = (String)session.getAttribute("captcha");
        if (StringUtils.isEmpty(captcha)) {
            result.addError(new FieldError("user", "captcha", "验证码错误"));
        }

        // 检查验证码是否相同
        if (!captcha.equals(user.getCaptcha().toLowerCase())) {
            result.addError(new FieldError("user", "captcha", "验证码错误"));
            return false;
        }
        return true;
    }


}
