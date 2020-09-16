package ${packageName};

<#if submitType == "TwoPhaseService" >
import com.csii.pisces.model.DomainTwoPhaseService;
</#if>
<#if submitType == "BaseQueryService" >
import com.csii.pisces.model.DomainBaseQueryService;
</#if>
import com.csii.pisces.product.per.api.Api${className}Service;
import com.csii.pisces.product.per.api.data.request.${className}Request;
import com.csii.pisces.product.per.api.data.response.${className}Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
* @Author: ${authorName}
* @Date: ${date}
* @ProjectName: srbank
* @Version 1.0
* @Description: ${note}
*/
public class Api${className}ServiceImpl implements Api${className}Service {

    @Autowired
    @Qualifier("${XclassName}ServiceImpl")
<#if submitType == "TwoPhaseService" >
    private DomainTwoPhaseService ${XclassName}Service;
</#if>
<#if submitType == "BaseQueryService" >
    private DomainBaseQueryService ${XclassName}Service;
</#if>

    @Override
    @ApiOperation(value = "${note}")
    public ${className}Response ${XclassName}(${className}Request ${XclassName}Request) {
        return (${className}Response) ${XclassName}Service.execute(${XclassName}Request);
    }
}