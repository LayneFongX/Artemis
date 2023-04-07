package com.farid.annotation.service.impl;

import com.farid.annotation.service.IAnnotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnnotationService implements IAnnotationService {

    @Override
    public String sayHello() {
        return "Hello Layne Fong";
    }

}
