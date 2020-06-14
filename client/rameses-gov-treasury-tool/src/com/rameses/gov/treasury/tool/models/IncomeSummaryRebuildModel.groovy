package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

class IncomeSummaryRebuildModel {

    @Binding 
    def binding;
    
    @Service('DateService')
    def dateSvc; 
    
    @Service('IncomeSummaryModifyService') 
    def modifySvc; 
    
    def entity; 
    
    def postingTypes = [
        [objid: 'BY_REMITTANCE_DATE', name: 'BY REMITTANCE DATE'],
        [objid: 'BY_LIQUIDATION_DATE', name: 'BY LIQUIDATION DATE']
    ]; 
    
    def months = [
        [index: 1, name: 'JANUARY'],
        [index: 2, name: 'FEBRUARY'],
        [index: 3, name: 'MARCH'],
        [index: 4, name: 'APRIL'],
        [index: 5, name: 'MAY'],
        [index: 6, name: 'JUNE'],
        [index: 7, name: 'JULY'],
        [index: 8, name: 'AUGUST'],
        [index: 9, name: 'SEPTEMBER'],
        [index: 10, name: 'OCTOBER'],
        [index: 11, name: 'NOVEMBER'],
        [index: 12, name: 'DECEMBER'] 
    ]; 
    
    def title = 'Rebuild Income Summary';
    
    def mode;

    void init() { 
        mode = 'init'; 
        def year = dateSvc.getServerYear(); 
        def month = dateSvc.getServerMonth();
        
        entity = [:]; 
        entity.year = year; 
        entity.postingtype = postingTypes.first(); 
        
        entity.startyear = entity.endyear = year; 
        entity.startmonth = entity.endmonth = months.find{ it.index == month }
    }  
        
    def getInfo() {
        def sb = new StringBuilder(); 
        if ( ! items ) return sb.toString(); 
        
        sb.append("<html>");
        sb.append('<h3>');
        if ( mode == 'processing') {
            sb.append('Process Status'); 
        } 
        else if (mode == 'done') {
            def item = items.find{ it.state == 'error' } 
            if ( item ) {
                sb.append('Processed with errors'); 
            } else if ( cancelled ) {
                sb.append('Processed with cancelled'); 
            } else {
                sb.append('Processed completed'); 
            }
        }
        sb.append('</h3>');
        
        sb.append('<table cellpadding="4" cellspacing="0" border="0">');
        items.each{ o-> 
            sb.append('<tr>');
            sb.append('<td>').append( o.title ).append('</td>');

            if ( o.state == 'waiting' ) {
                sb.append('<td style="padding-left:10px;">(Waiting)</td>');
            } 
            else if ( o.state == 'processing' ) {
                sb.append('<td style="padding-left:10px;">'); 
                sb.append('<img src="classpath://com/rameses/rcp/icons/loading16.gif"/>'); 
                sb.append('</td>');
                sb.append('<td style="padding-left:10px;"> Processing... '); 
                if ( o.percentage ) {
                    sb.append(' ('+ o.percentage.intValue() +'%)'); 
                }
                sb.append('</td>'); 
            }
            else if ( o.state == 'cancelled' ) {
                sb.append('<td style="padding-left:10px;">'); 
                sb.append('<img src="classpath://images/error-icon.png" height="16" width="16"/>'); 
                sb.append('</td>');
                sb.append('<td style="padding-left:10px;"> Cancelled </td>'); 
            }
            else if ( o.state == 'error' ) {
                sb.append('<td style="padding-left:10px;">'); 
                sb.append('<img src="classpath://images/error-icon.png" height="16" width="16"/>'); 
                sb.append('</td>');
                sb.append('<td style="padding-left:10px;">'); 
                sb.append('<a href="viewError" uuid="'+ o.uuid +'"> Error </a>'); 
                sb.append('</td>'); 
            }
            else if ( o.state == 'done' ) {
                sb.append('<td style="padding-left:10px;">'); 
                sb.append('<img src="classpath://images/check-mark.png" height="16" width="16"/>'); 
                sb.append('</td>'); 
                sb.append('<td style="padding-left:10px;"> Completed </td>'); 
            }
            else {
                sb.append('<td style="padding-left:10px;">&nbsp;</td>'); 
            }
            sb.append('</td>');
            sb.append('</tr>');
        }
        sb.append('</table>');
        sb.append("</html>");
        return sb.toString();
    }
    
    def infoHandler = [
        getValue: {
            return getInfo(); 
        }
    ] as HtmlViewModel; 
    
    def items = []; 
    def MMMYY = new java.text.SimpleDateFormat('MMM - yyyy'); 
    boolean cancelled;
    
    void doBuild() { 
        if ( mode == 'processing' ) return;
        
        def strstartdate = entity.startyear.toString() +'-'+ entity.startmonth.index.toString().padLeft(2,'0') +'-01'; 
        def strenddate   = entity.endyear.toString() +'-'+ entity.endmonth.index.toString().padLeft(2,'0') +'-01'; 
        def startdate = java.sql.Date.valueOf( strstartdate ); 
        def enddate   = java.sql.Date.valueOf( strenddate ); 
        if ( enddate.before( startdate )) 
            throw new Exception('End Date must be greater than or equal to the Start Date'); 
            
        items.clear(); 
        def dateBean = new com.rameses.util.DateBean( startdate ); 
        while (dateBean.date <= enddate) {
            def mm = [ startdate: dateBean.date, state: 'waiting' ]; 
            mm.title = MMMYY.format( mm.startdate ).toUpperCase(); 
            mm.uuid = new java.rmi.server.UID().toString(); 
            mm.enddate = dateBean.add("1M"); 
            items << mm; 
            
            buildSubDates( mm ); 
        }
        
        mode = 'processing'; 
        
        new Thread( taskHandler ).start(); 
    }
    
    void buildSubDates( mm ) { 
        mm.dates = []; 

        def subdateBean = new com.rameses.util.DateBean( mm.startdate );
        while ( subdateBean.date < mm.enddate ) {
            def dt1 = subdateBean.date; 
            def dt2 = subdateBean.add("1d"); 
            mm.dates << [
                postingtype : entity.postingtype, 
                startdate: new java.sql.Date( dt1.time ), 
                enddate  : new java.sql.Date( dt2.time ) 
            ]; 
        }
    }

    
    def taskHandler = { 
        items.each{ o-> 
            if ( cancelled ) {
                o.state = 'cancelled'; 
                return;
            }
            
            o.state = 'processing'; 
            binding.refresh('infoHandler'); 
            binding.refresh('do.*'); 
            
            try {
                def size = o.dates.size(); 
                o.dates.eachWithIndex{ oo,idx-> 
                    if ( cancelled ) {
                        o.state = 'cancelled'; 
                        return; 
                    }
                    
                    o.percentage = ((idx+1).doubleValue() / size.doubleValue()) * 100.0; 
                    if (( o.percentage.intValue() % 10 ) == 0 ) {
                        binding.refresh('infoHandler'); 
                    }
                    modifySvc.deleteData( oo ); 
                    modifySvc.rebuild( oo ); 
                }
                o.state = 'done';
            } 
            catch(Throwable t) {
                cancelled = true;
                o.state = 'error'; 
                o.error = t; 
            }
        }
        mode = 'done';
        binding.refresh(); 
    } as Runnable;
    
    void doCancel() { 
        cancelled = true; 
    }
    def doClose() {
        return '_close'; 
    }
    
    void doNew() {
        mode = 'init';
        cancelled = false; 
        items.clear(); 
    }
    
    void viewError( param ) {
        println 'view error -> '+ param;
        def item = items.find{ it.uuid == param.uuid }
        if ( item?.error ) { 
            MsgBox.err( item.error ); 
        } else {
            MsgBox.alert('No error message attached to this item'); 
        }
    }
}