<html>
    <head>
        <style>
            body {font-family:arial; font-size:10px;}
            th { background-color: #848482; color:white; }
            table { border: 1px solid #848482; }
            td { background-color: #E0FFFF; }
            table.items { border: none; }
            table.items td { padding-bottom:2px; }
        </style>
    </head>
    <body>
        <table cellpadding="2" cellspacing="2" width="100%">
             <tr>
                <th align="left">AF No</th>
                <th align="left">Description</th>
                <th align="center">Unit</th>
                <th align="center">Qty</th>
                <th align="center">Qty Served</th>
                <th align="center">Unit Cost</th>
                <th align="center">Txn Type</th>
             </tr>
             
             <%entity.items.each { o-> %>
                <tr style="border-top:1px solid black;">
                    <td>${o.item.objid}</td>
                    <td>${o.item.title}</td>
                    <td align="center">${o.unit}</td>
                    <td align="center">${o.qty}</td>
                    <td align="center">${o.qtyserved}</td>
                    <td align="center">${o.cost}</td>
                    <td align="center">
                        <a href="changeTxnType" aftxnitemid="${o.objid}"> 
                            ${o.txntype} 
                        </a>
                    </td>
                </tr>

                <% if ( o.items ) { %>
                <tr>
                    <td>&nbsp;</td>
                    <td colspan="6">
                        <table class="items" cellpadding="0" cellspacing="0" border="0">
                        <% o.items.each{ ii-> %>
                        <tr>
                            <td> &nbsp;&nbsp; Batch:${ii.batchno} </td>
                            <% if ( o.afunit.formtype == 'serial' ) { %> 
                            <td> &nbsp; ${ii.prefix ? ii.prefix : ''} </td> 
                            <td> &nbsp; ${ii.startseries} - ${ii.endseries} </td> 
                            <td> &nbsp; ${ii.suffix ? ii.suffix : ''} </td> 
                            <% } %>
                            <td>
                                &nbsp;&nbsp;
                                Stub: ${ii.startstub == ii.endstub ? ii.startstub : (''+ ii.startstub +' - '+ ii.endstub)} 
                            </td>
                            <% if ( o.txntype == 'SALE' ) { %> 
                            <td> &nbsp;&nbsp; Cost/Stub: </td> 
                            <td align="right"> &nbsp; ${ii.cost ? ii.cost : 0.0} </td>
                            <td> 
                                &nbsp;&nbsp; 
                                <a href="changeSaleCost" aftxnitemid="${o.objid}" uuid="${ii.uuid}"> Sale Cost </a> 
                            </td> 
                            <td align="right"> : ${ii.salecost ? ii.salecost : 0.0} </td>
                            <% } %>
                        </tr>
                        <% } %>
                        </table>
                    </td>
                </tr>
                <% } %> 

             <% } %>
        </table>
    </body>
</html>