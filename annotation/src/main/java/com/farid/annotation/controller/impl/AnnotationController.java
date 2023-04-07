package com.farid.annotation.controller.impl;

import com.farid.annotation.controller.IAnnotationController;
import com.farid.annotation.service.IAnnotationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/annotation")
public class AnnotationController implements IAnnotationController {

    @Resource
    private IAnnotationService annotationService;

    @Override
    @GetMapping("/sayHello")
    public String sayHello(String deviceId, String params1) {
        return annotationService.sayHello();
    }
}
