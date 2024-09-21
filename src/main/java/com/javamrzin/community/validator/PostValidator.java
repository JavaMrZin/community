package com.javamrzin.community.validator;

import com.javamrzin.community.entity.Post;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PostValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return Post.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Post p = (Post) obj;
        if (!StringUtils.hasText(p.getContent())) {
            errors.rejectValue("content", null, "내용을 입력하세요");
        }
    }
}
