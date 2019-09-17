package com.example.demo.core.crud;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

@Configuration
public class CrudProxyBeansRegistrar implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware {

	private ClassPathScanningCandidateComponentProvider classpathScanner;
	private ClassLoader classLoader;

	public CrudProxyBeansRegistrar() {
		configureClasspathScanner();
	}

	private void configureClasspathScanner() {
		classpathScanner = new ClasspathScanner();

		classpathScanner.resetFilters(false);
		classpathScanner.addIncludeFilter(new AnnotationTypeFilter(CRUD.class));
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		String[] basePackages = getBasePackages(importingClassMetadata);
		if (basePackages != null && basePackages.length > 0) {
			for (String basePackage : basePackages) {
				createCrudProxies(basePackage, registry);
			}
		}

	}

	private String[] getBasePackages(AnnotationMetadata importingClassMetadata) {

		String[] basePackages = null;

		MultiValueMap<String, Object> allAnnotationAttributes = importingClassMetadata
				.getAllAnnotationAttributes(EnableCRUD.class.getName());

		if (allAnnotationAttributes != null && !allAnnotationAttributes.isEmpty()) {
			basePackages = (String[]) allAnnotationAttributes.getFirst("basePackages");
		}

		return basePackages;
	}

	private void createCrudProxies(String basePackage, BeanDefinitionRegistry registry) {
		try {

			for (BeanDefinition beanDefinition : classpathScanner.findCandidateComponents(basePackage)) {

				Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());

				CRUD crud = clazz.getAnnotation(CRUD.class);

				String beanName = !StringUtils.isEmpty(crud.value()) ? crud.value()
						: ClassUtils.getShortNameAsProperty(clazz);

				GenericBeanDefinition proxyBeanDefinition = registerCrudControllerBeanDefinition(registry, clazz, crud,
						beanName);
				System.err.println(beanName + " " + proxyBeanDefinition);

			}
		} catch (Exception e) {
			System.out.println("Exception while createing proxy");
			e.printStackTrace();
		}

	}

	private GenericBeanDefinition registerCrudControllerBeanDefinition(BeanDefinitionRegistry registry, Class<?> clazz,
			CRUD crud, String beanName) {
		GenericBeanDefinition proxyBeanDefinition = new GenericBeanDefinition();
		proxyBeanDefinition.setBeanClass(clazz);

		ConstructorArgumentValues args = new ConstructorArgumentValues();

		args.addGenericArgumentValue(classLoader);
		args.addGenericArgumentValue(clazz);
		args.addGenericArgumentValue(crud);
		proxyBeanDefinition.setConstructorArgumentValues(args);

		proxyBeanDefinition.setFactoryBeanName("crudProxyBeanFactory");
		proxyBeanDefinition.setFactoryMethodName("createCrudControllerProxyBean");

		registry.registerBeanDefinition(beanName, proxyBeanDefinition);
		return proxyBeanDefinition;
	}

}

class ClasspathScanner extends ClassPathScanningCandidateComponentProvider {
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isIndependent();
	}
}
