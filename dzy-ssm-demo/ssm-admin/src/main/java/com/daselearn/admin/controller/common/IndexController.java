package com.daselearn.admin.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.daselearn.common.dto.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
@Api(value = "管理后台", description = "首页")
public class IndexController {

    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/",method = RequestMethod.GET)
    @ApiOperation(value = "首页", httpMethod = "GET", notes = "首页")
    private String index(Model model) {
        return "redirect:/swagger-ui.html";
    }


    @RequestMapping("getVersion")
    @ApiOperation(value = "获取JAR工程的版本号", httpMethod = "GET", response = JsonResult.class, notes = "获取版本号")
    @ResponseBody
    private JSONObject version(Model model) {
        JSONObject jSONObject = new JSONObject();
        Map<String, Object> versionInfo = new HashMap<String, Object>();
        //查看jar包里面pom.properties版本号
        try {
//            String jarPath = MavenVersion.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            String pomVersion = "";
            try{
                String jarPath = IndexController.class.getProtectionDomain().getCodeSource().getLocation().getFile();
                jarPath = java.net.URLDecoder.decode(jarPath, "UTF-8");
                logger.info("version info : " + jarPath);
                logger.info(System.getProperty("user.dir") + File.separator);
                URL url = new URL("jar:file:" + jarPath + "!/META-INF/maven/com.daselearn.ssm/ssm-admin/pom.properties");
                Properties properties = new Properties();
                properties.load(url.openStream());
                pomVersion = properties.getProperty("version");
            }catch(Exception e){
                logger.error("read jar or pom error:",e);
            }

            versionInfo.put("pomVersion", pomVersion);
            versionInfo.put("jvmName", System.getProperty("java.vm.name"));
            versionInfo.put("jvmVendor", System.getProperty("java.vm.vendor"));
            versionInfo.put("javaVersion", System.getProperty("java.version"));
            jSONObject.put("result", "0");
            jSONObject.put("versionInfo", versionInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            jSONObject.put("result", "1");
        }
        return jSONObject;
    }
}
