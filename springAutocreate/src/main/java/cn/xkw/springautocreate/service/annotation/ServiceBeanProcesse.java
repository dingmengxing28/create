package cn.xkw.springautocreate.service.annotation;

import cn.xkw.springautocreate.service.ServiceListManage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/8 18:59
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: 把所有加了ListManage注解的类名加到list中管理
 */
@Component
public class ServiceBeanProcesse implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            if (beanDefinition != null) {
                String beanClassName = beanDefinition.getBeanClassName();
                if (StringUtils.isNotEmpty(beanClassName)) {
                    try {
                        Class<?> aClass = Class.forName(beanClassName);
                        if (aClass.isAnnotationPresent(ListManage.class)) {
                            List<String> lists = ServiceListManage.getServiceListManageInstance().getLists();
                            lists.add(beanDefinitionName);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
