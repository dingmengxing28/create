consumer:
<!--${note}-->
<dubbo:reference id="api${className}Service" interface="com.csii.pisces.${centName}.per.api.Api${className}Service"
                 protocol="dubbo" check="false" group="pisces-${centName}">
</dubbo:reference>
provider:
<!-- ${note} -->
<dubbo:service id="api${className}Service" interface="com.csii.pisces.product.per.api.Api${className}Service"
               ref="api${className}ServiceImpl" protocol="dubbo" group="pisces-${centName}"/>

swwger:
<pe-rest:provider ref="api${className}ServiceImpl" serviceName="api${className}Service" /> <!-- ${note} -->

<!-- ${note} -->
<bean id="api${className}ServiceImpl" class="com.csii.pisces.${centName}.per.api.impl.Api${className}ServiceImpl"/>