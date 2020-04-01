package com.zkwp.administration.configBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @auther zhangkun
 * @date 2020/3/31 21:55
 **/
@Component
public class CommandRunner  implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(CommandRunner.class);
    @Value("${spring.web.loginurl}")
    private String indexUrl;

    @Value("${spring.web.excute}")
    private String ExcutePath;

    @Value("${spring.auto.openurl}")
    private boolean isOpen;

    @Override
    public void run(String... args) throws Exception {
        if(isOpen){
            String cmd = ExcutePath +" "+ indexUrl;
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                logger.debug("启动浏览器打开项目成功");
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }

}
