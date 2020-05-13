[getFunds]
select 
	fund.objid, fund.code, fund.title, 
	fund.groupid, fg.indexno as groupindexno 
from depositvoucher_fund dvf 
	inner join fund on fund.objid = dvf.fundid 
	inner join fundgroup fg on fg.objid = fund.groupid 
where dvf.parentid = $P{depositvoucherid} 
order by fg.indexno, LEN(fund.code), fund.code, fund.title 


[findDepositVoucherFund]
select dvf.amount, 0.0 as totalcash, 0.0 as totalcheck, 0.0 as totalcr, 
	fund.objid as fund_objid, fund.code as fund_code, fund.title as fund_title  
from depositvoucher_fund dvf 
	inner join fund on fund.objid = dvf.fundid 
	inner join fundgroup fg on fg.objid = fund.groupid 
where dvf.parentid = $P{depositvoucherid} 
	and dvf.fundid = $P{fundid}


[getLiquidations]
select 
	cv.liquidatingofficer_objid, cv.liquidatingofficer_name, cv.controlno, 
	cvf.amount, cvf.fund_objid, cvf.fund_title, cv.controldate 
from depositvoucher dv 
	inner join collectionvoucher cv on cv.depositvoucherid = dv.objid 
	inner join collectionvoucher_fund cvf on cvf.parentid = cv.objid 
where dv.objid = $P{depositvoucherid} 
	and cvf.fund_objid = $P{fundid}
order by cv.liquidatingofficer_name, cv.controldate, cv.controlno 


[getRemittances]
select 
	dv.objid as depositvoucherid, dv.createdby_name as cashier_name, 
	(ba.bank_code +' - Cash D/S: Account '+ ba.code) as refno, 
	sum(ds.amount) as amount 
from depositvoucher dv 
	inner join depositvoucher_fund df on df.parentid = dv.objid 
	inner join depositslip ds on ds.depositvoucherfundid = df.objid 
	inner join bankaccount ba on ba.objid = ds.bankacctid 
where dv.objid = $P{depositvoucherid} 
	and df.fundid = $P{fundid} 
group by 
	dv.objid, dv.createdby_name, (ba.bank_code +' - Cash D/S: Account '+ ba.code)
