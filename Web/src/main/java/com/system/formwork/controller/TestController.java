package com.system.formwork.controller;

import com.system.formwork.entity.pojo.User;
import com.system.formwork.entity.vo.UserVo;
import com.system.formwork.mapstruct.UserConvertor;
import com.system.formwork.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Resource
    private TestService testService;

    @Autowired
    private UserConvertor userConvertor;

    @GetMapping(value = "/hello")
    public UserVo hello(){
        return userConvertor.UserToUserVo(new User().setId("123").setName("Nick"));
//        return testService.hello();
    }

    @PostMapping(value = "/upload")
    public void upload(@RequestParam("file")MultipartFile file,@RequestParam(value = "name",required = false)String name) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名。也可以在这里添加判断语句，规定特定格式的图片才能上传，否则拒绝保存。
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //为了避免发生图片替换，这里使用了文件名重新生成
        fileName = UUID.randomUUID()+suffixName;

        String path = ResourceUtils.getURL("classpath:").getPath()+"public/images/";
//        file.transferTo(new File(path+fileName));

        System.out.println(fileName);
        System.out.println(suffixName);
        System.out.println(path);

        System.out.println(name);
    }
}
