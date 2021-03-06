package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.common.* 

class UndepositedLiquidationMonitorModel extends DefaultListController { 

    @Service('LiquidationMonitorService') 
    def monitorSvc; 
    
    String tag = "undeposited_liquidation";
} 