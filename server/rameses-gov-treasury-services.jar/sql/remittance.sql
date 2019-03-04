[getUnremittedAfFundGroupList]
SELECT f.title,f.remittancefundgroupid
FROM af_control af 
LEFT JOIN fund f ON af.fund_objid = f.objid 
WHERE af.owner_objid = $P{ownerid}
AND IFNULL(af.remittedseries,af.startseries) <= af.endseries
${filter}
GROUP BY f.title, f.remittancefundgroupid

[getUnremittedAfList]
SELECT c.*, 
( c.beginendseries - c.beginstartseries + 1) AS qtybegin,
CASE WHEN c.endingendseries IS NULL THEN 0 ELSE (c.endingendseries - c.endingstartseries + 1) END AS qtyending
FROM
(
	SELECT a.*,
		CASE WHEN b.issuedendseries = a.beginendseries THEN NULL 
		     WHEN b.issuedendseries IS NULL THEN a.beginstartseries
			 ELSE b.issuedendseries + 1 END AS endingstartseries,
		CASE WHEN b.issuedendseries = a.beginendseries THEN NULL 
			 ELSE a.beginendseries END AS endingendseries,
	b.issuedstartseries,
	b.issuedendseries,
	IFNULL(b.qtyissued,0) AS qtyissued,
	IFNULL(b.amount,0) AS amount, 
	IFNULL(b.totalcash,0) AS totalcash, 
	IFNULL(b.totalnoncash,0) AS totalnoncash, 
	IFNULL(b.voidqty,0) AS voidqty,
	IFNULL(b.qtyreceived,0) AS qtyreceived,
	IFNULL(b.qtycancelled,0) AS qtycancelled
	FROM 
	(
		SELECT 
		af.objid AS controlid,
		af.afid AS formno,
		af.dtfiled,
		af.stubno,
		IFNULL( af.remittedseries + 1, af.startseries ) AS beginstartseries,
		af.endseries AS beginendseries,
		aa.formtype,
		CASE aa.formtype WHEN 'serial' THEN 0 WHEN 'cashticket' THEN 1 ELSE 3 END AS formtypeindex,
		fund.remittancefundgroupid 
		FROM af_control af
		INNER JOIN af aa ON af.afid = aa.objid
		LEFT JOIN fund ON af.fund_objid = fund.objid
		WHERE af.owner_objid = $P{ownerid}
		AND IFNULL(af.remittedseries,af.startseries) <= af.endseries
		${filter}
	) a
	LEFT JOIN 
	(
		SELECT a1.controlid,
		SUM(a1.amount) AS amount,
		SUM(a1.totalcash) AS totalcash,
		SUM(a1.totalnoncash) AS totalnoncash,
		SUM(a1.qty) AS qtyissued,
		SUM(a1.voidqty) AS voidqty,
		MIN(series) AS issuedstartseries,
		MAX(series) AS issuedendseries,
		0 AS qtyreceived,
		0 AS qtycancelled
		FROM 
		(SELECT 
			cr.controlid, 
			CASE WHEN cv.objid IS NULL THEN cr.amount ELSE 0 END AS amount,
			CASE WHEN cv.objid IS NULL THEN cr.totalcash - cr.cashchange ELSE 0 END AS totalcash,
			CASE WHEN cv.objid IS NULL THEN cr.totalnoncash ELSE 0 END AS totalnoncash,
			1 AS qty,
			CASE WHEN cv.objid IS NULL THEN 0 ELSE 1 END AS voidqty,
			series
		FROM af_control af
		INNER JOIN cashreceipt cr ON cr.controlid = af.objid
		LEFT JOIN fund ON af.fund_objid = fund.objid
		LEFT JOIN cashreceipt_void cv ON cv.receiptid = cr.objid
		WHERE af.owner_objid = $P{ownerid}
		AND cr.series > IFNULL( af.remittedseries, af.startseries-1 )
		AND IFNULL(af.remittedseries,af.startseries) <= af.endseries
		${receiptfilter}
		${filter}
		) a1
		GROUP BY a1.controlid
	) b ON a.controlid = b.controlid
) c
ORDER BY c.formtypeindex, c.formno, c.dtfiled, c.stubno, c.beginstartseries

[getUnremittedNonCashPaymentList]
SELECT DISTINCT 'CHECK' AS reftype, cp.refno, cp.refdate, '' AS particulars, cp.amount, 
CASE WHEN cp.amount <> cp.amtused THEN 'INCOMPLETE' ELSE 'OK' END AS status  
FROM checkpayment cp
WHERE cp.objid IN 
( 
	SELECT refid 
	FROM cashreceiptpayment_noncash crpn 
	INNER JOIN cashreceipt cr ON crpn.receiptid = cr.objid 
	INNER JOIN af_control af ON cr.controlid = af.objid 
	LEFT JOIN fund ON af.fund_objid = fund.objid
	WHERE  af.owner_objid = $P{ownerid}
	AND cr.series > IFNULL( af.remittedseries, af.startseries-1 )
	AND IFNULL(af.remittedseries,af.startseries) <= af.endseries 
	${receiptfilter}
	${filter}
	AND cr.objid NOT IN ( SELECT objid FROM cashreceipt_void )
)
UNION
SELECT 'EFT' AS reftype, eft.refno, eft.refdate, eft.particulars, eft.amount, 'OK' AS status
FROM eftpayment eft
INNER JOIN cashreceiptpayment_noncash crpn ON crpn.refid = eft.objid
INNER JOIN cashreceipt cr ON crpn.receiptid = cr.objid 
INNER JOIN af_control af ON cr.controlid = af.objid 
LEFT JOIN fund ON af.fund_objid = fund.objid
WHERE  af.owner_objid = $P{ownerid}
AND cr.series > IFNULL( af.remittedseries, af.startseries-1 )
AND IFNULL(af.remittedseries,af.startseries) <= af.endseries
${receiptfilter} 
${filter}
AND cr.objid NOT IN ( SELECT objid FROM cashreceipt_void )
 
[updateCheckPaymentRemittanceId]
UPDATE checkpayment cp, cashreceiptpayment_noncash crpn, cashreceipt cr, remittance r 
SET cp.remittanceid = r.objid 
WHERE cp.objid = crpn.refid 
AND crpn.receiptid = cr.objid 
AND cr.remittanceid = r.objid
AND r.objid = $P{remittanceid}
AND cr.objid NOT IN (SELECT receiptid FROM cashreceipt_void)

[updateEftPaymentRemittanceId]
UPDATE eftpayment ep, cashreceiptpayment_noncash crpn, cashreceipt cr, remittance r
SET ep.remittanceid = r.objid 
WHERE ep.objid = crpn.refid 
AND crpn.receiptid = cr.objid 
AND cr.remittanceid = r.objid
AND r.objid = $P{remittanceid}
AND cr.objid NOT IN (SELECT receiptid FROM cashreceipt_void)


[insertRemittanceFund]
INSERT INTO remittance_fund ( 
	objid, remittanceid, fund_objid, fund_title, amount 
)
select 
	concat( t1.remittanceid, '-', fund.objid ) as objid, 
	t1.remittanceid, fund.objid as fund_objid, fund.title as fund_title,
	sum(t1.amount)-sum(t1.share) as amount  
from ( 
	select remittanceid, fundid, sum(amount) as amount, 0.0 as share 
	from vw_remittance_cashreceiptitem 
	where remittanceid = $P{remittanceid} 
	group by remittanceid, fundid 

	union all 

	select t1.remittanceid, t1.fundid, 0.0 as amount, sum(cs.amount) as share 
	from ( 
		select remittanceid, receiptid, fundid, acctid 
		from vw_remittance_cashreceiptitem 
		where remittanceid = $P{remittanceid} 
		group by remittanceid, receiptid, fundid, acctid
	)t1, vw_remittance_cashreceiptshare cs 
	where cs.receiptid = t1.receiptid and cs.refacctid = t1.acctid 
	group by t1.remittanceid, t1.fundid 

	union all 

	select remittanceid, fundid, sum(amount) as amount, 0.0 as share  
	from vw_remittance_cashreceiptshare  
	where remittanceid = $P{remittanceid} 
	group by remittanceid, fundid 
)t1, fund, remittance r  
where fund.objid = t1.fundid 
	and r.objid = t1.remittanceid 
group by t1.remittanceid, r.controlno, fund.objid, fund.code, fund.title  
order by fund.code, fund.title 

[insertRemittanceAf]


[updateRemittanceCashTotals]
UPDATE remittance r, 
( 
	SELECT remittanceid, 
	SUM(amount) AS amount,
	SUM(totalcash - cashchange ) AS totalcash 
	FROM cashreceipt cr 
	WHERE remittanceid = $P{remittanceid} 
	AND cr.objid NOT IN (SELECT receiptid FROM cashreceipt_void)
	GROUP BY remittanceid
) a
SET r.amount=a.amount, r.totalcash=a.totalcash 
WHERE r.remittanceid = $P{remittanceid}
AND r.objid = a.remittanceid

[updateRemittanceCheckTotals]
UPDATE remittance r, 
( 
	SELECT remittanceid, 
	SUM(amount) AS amount
	FROM checkpayment 
	WHERE remittanceid = $P{remittanceid} 
	GROUP BY remittanceid
) a
SET r.totalcheck =a.amount 
WHERE r.remittanceid = $P{remittanceid}
AND r.objid = a.remittanceid

[updateRemittanceNonCheckTotals]
UPDATE remittance r, 
( 
	SELECT remittanceid, 
	SUM(amount) AS amount
	FROM eftpayment 
	WHERE remittanceid = $P{remittanceid} 
	GROUP BY remittanceid
) a
SET r.totalcr = a.amount 
WHERE r.remittanceid = $P{remittanceid}
AND r.objid = a.remittanceid

[getRemittanceNoncashPayment]
SELECT a.* FROM 
	(SELECT 
	   objid AS refid, 'CHECK' AS reftype, 'checkpayment' AS schemaname,
	   refno, refdate, bank_name, CONCAT( 'CHECK ', refno, ' (', bank_name, ') dated ', refdate) AS particulars, amount, remittanceid
	FROM checkpayment 
	WHERE remittanceid = $P{remittanceid}
	UNION ALL 
	SELECT
	   eft.objid AS refid, 'EFT' AS reftype, 'eftpayment' AS schemaname,	 
	   eft.refno, eft.refdate, b.name AS bank_name, CONCAT('EFT ', ba.code, ' (', b.name, ') dated ', refdate) AS particulars, amount, remittanceid
	FROM eftpayment eft
	INNER JOIN bankaccount ba ON eft.bankacctid=ba.objid
	INNER JOIN bank b ON ba.bank_objid=b.objid
	WHERE remittanceid = $P{remittanceid}) a
ORDER BY a.refdate, a.refno	

[revertAfControlRemittedSeries]
UPDATE af_control af, af_control_detail ad
SET af.remittedseries = ad.issuedstartseries - 1
WHERE af.objid = ad.controlid
AND ad.refid = $P{remittanceid}
AND NOT(ad.issuedstartseries IS NULL)

