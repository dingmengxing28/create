package ${packageName};

<#if submitType == "TwoPhaseService" >
import com.csii.pisces.model.action.DomainTwoPhaseServiceImpl;
</#if>
<#if submitType == "BaseQueryService" >
import com.csii.pe.core.PeException;
import com.csii.pisces.model.action.DomainBaseQueryServiceImpl;
</#if>

import com.csii.pisces.product.per.api.data.request.${className}Request;
import com.csii.pisces.product.per.api.data.response.${className}Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
* @Author: ${authorName}
* @Date: ${date}
* @ProjectName: srbank
* @Version 1.0
* @Description: ${note}
*/
@Service
public class ${className}ServiceImpl extends <#if submitType == "TwoPhaseService" >DomainTwoPhaseServiceImpl</#if><#if submitType == "BaseQueryService" >DomainBaseQueryServiceImpl</#if><${className}Request, ${className}Response> {

    private static final Log log = LogFactory.getLog(${className}ServiceImpl.class);

//    TODO
//    @Autowired(required = false)
//    private RTPDepositProductsService rtPDepositProductsServiceImpl;

<#if submitType == "TwoPhaseService" >
    @Override
    public ${className}Response executeInner(${className}Request request) {
        ${className}Response ${XclassName}Response = new ${className}Response();
        // TODO
        return ${XclassName}Response;
    }
</#if>
<#if submitType == "BaseQueryService" >
    @Override
    public ${className}Response query(${className}Request request) {
        return null;
    }
</#if>
    @Override
    public ${className}Response newInstanceRS() {
        return new ${className}Response();
    }
}
