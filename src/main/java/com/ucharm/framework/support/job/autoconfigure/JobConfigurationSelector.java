package com.ucharm.framework.support.job.autoconfigure;

import com.ucharm.framework.support.job.annotation.EnableJob;
import com.ucharm.framework.support.job.constant.JobProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @Package com.ucharm.framework.support.job.autoconfigure
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 11:43
 */
public class JobConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableJob.class.getName());
        if (attributes == null) {
            throw new IllegalArgumentException(String.format("@%s is not present on importing class '%s' as expected", EnableJob.class.getSimpleName(), annotationMetadata.getClassName()));
        } else {
            JobProvider jobProvider = (JobProvider) attributes.get("provider");
            String[] imports = this.selectImports(jobProvider);
            if (imports == null) {
                throw new IllegalArgumentException("Unknown jobProvider: " + jobProvider);
            } else {
                return imports;
            }
        }
    }

    private String[] selectImports(JobProvider jobProvider) {
        switch (jobProvider) {
            case XXL:
                return new String[]{XxlJobConfiguration.class.getName()};
            default:
                return null;
        }
    }
}
