package com.rameses.treasury.common.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.util.*;

public class BillingRuleModel extends AbstractRuleProcessorModel  {

    //by default retrieve the billitems
    boolean include_billitems = true;

    //set this true for cash receipts 
    boolean include_items = false;

    def _ruleExecutor;
    public def  getRuleExecutor() {
        if(!_ruleExecutor) {
            _ruleExecutor = createService("BillingRuleService" );
        }
        return _ruleExecutor;
    }
    
    void init() {
        options = [:];
        options.include_billitems = include_billitems;
        options.include_items = include_items; 
        super.init();
    }

}