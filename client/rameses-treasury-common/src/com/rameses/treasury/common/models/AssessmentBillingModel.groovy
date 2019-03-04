package com.rameses.treasury.common.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.util.*;

public class AssessmentBillingModel extends AbstractRuleProcessorModel  {

    def _ruleExecutor;
    public def  getRuleExecutor() {
        if(!_ruleExecutor) {
            _ruleExecutor = createService("AssessmentBillingService" );
        }
        return _ruleExecutor;
    }
    
}