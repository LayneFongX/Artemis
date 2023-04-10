package com.farid.artemis.controller.impl;

import com.farid.artemis.controller.IAnnotationController;
import com.farid.artemis.service.IAnnotationService;
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
