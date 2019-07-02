package io.chenyu.test_72.controller;

import com.github.cage.Cage;
import io.chenyu.test_72.DTO.UserRegisterDTO;
import io.chenyu.test_72.dao.UserMapper;
import io.chenyu.test_72.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Base64;

@RestController
@RequestMapping("/user")
@EnableAutoConfiguration
@CrossOrigin
public class UserController {
    @Autowired
    private SecureRandom secureRandom;
    @Autowired
    private Cage cage;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserMapper userMapper;
    @Value("${captcha.size}")
    private int captchasize;
    @Value("${captcha.timeout}")
    private Long captchatimeout;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping(value = "/getcaptcha",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] login(){
        byte[] bytes = secureRandom.generateSeed(captchasize);
        String captcha = DatatypeConverter.printHexBinary(bytes);
        logger.info(captcha);
        httpSession.setAttribute(httpSession.getId()+"captcha",captcha);
        byte[] draw = cage.draw(captcha);
        return draw;
    }
    @PostMapping("/register")
    public Integer register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) throws Exception {
        String inputcaptcha = userRegisterDTO.getCaptcha();
        String sessionId = httpSession.getId();
        String recaptcha = (String)httpSession.getAttribute(sessionId + "captcha");
        if(!inputcaptcha.equals(recaptcha)){
            throw new Exception("worry");
        }
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        byte[] bytes = secureRandom.generateSeed(2);
        String salt = Base64.getEncoder().encodeToString(bytes);
        user.setSalt(salt);
        String inputpwd = userRegisterDTO.getPassword() + salt;
        String password = DigestUtils.md5DigestAsHex(inputpwd.getBytes());
        user.setEncrypassword(password);
        userMapper.insert(user);
        return user.getUserId();
    }
    @GetMapping("/login")
    public void login(@RequestParam String username,
                      @RequestParam String password) throws Exception {
        User user = userMapper.searchByname(username);
        if(user==null){
            throw new Exception("null");
        }

        String salt = user.getSalt();
        String inputpassowrd = password + salt;
        String encrypwd = DigestUtils.md5DigestAsHex(inputpassowrd.getBytes());
        String realpwd = user.getEncrypassword();
        if(!realpwd.equals(encrypwd)){
            throw new Exception("worry");
        }
        else {
            logger.info("成功");
        }
    }
}
