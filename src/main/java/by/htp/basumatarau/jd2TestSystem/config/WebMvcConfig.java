package by.htp.basumatarau.jd2TestSystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScans(value =
        {@ComponentScan("by.htp.basumatarau.jd2TestSystem.controller"),
        @ComponentScan("by.htp.basumatarau.jd2TestSystem.config.advice")})
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/WEB-INF/view/").suffix(".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Override
    public Validator getValidator() {
        //todo message source to be set here
        return new LocalValidatorFactoryBean();
    }
}
