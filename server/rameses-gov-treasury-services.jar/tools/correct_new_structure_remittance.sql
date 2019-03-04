#add remittedseries 
ALTER TABLE af_control ADD COLUMN remittedseries INT(11);

#update all remitted series
UPDATE af_control af 
SET af.remittedseries = ( 
	SELECT MAX(series) 
	FROM cashreceipt 
	WHERE controlid = af.objid 
	AND NOT(remittanceid IS NULL)
);

#To correct the cashreceiptpayment_noncash
ALTER TABLE remittance_fund DROP COLUMN totalcash;
ALTER TABLE remittance_fund DROP COLUMN totalcheck;
ALTER TABLE remittance_fund DROP COLUMN totalcr;
ALTER TABLE remittance_fund DROP COLUMN cashbreakdown;
ALTER TABLE remittance_fund DROP COLUMN controlno;

#alter cashreceipt item
ALTER TABLE cashreceiptitem ADD COLUMN remittancefundid VARCHAR(100);
ALTER TABLE `cashreceiptitem` ADD CONSTRAINT `fk_cashreceiptitem_remittancefund` FOREIGN KEY (`remittancefundid`) REFERENCES `remittance_fund` (`objid`);

#alter cashreceipt share
ALTER TABLE cashreceipt_share DROP COLUMN remittanceid;
ALTER TABLE cashreceipt_share ADD COLUMN remittancefundid VARCHAR(100);
ALTER TABLE cashreceipt_share ADD CONSTRAINT `fk_cashreceipt_share_remittancefund` FOREIGN KEY (`remittancefundid`) REFERENCES `remittance_fund` (`objid`);

#alter checkpayment
ALTER TABLE checkpayment ADD COLUMN remittanceid VARCHAR(50);
ALTER TABLE checkpayment ADD CONSTRAINT `fk_checkpayment_remittance` FOREIGN KEY (`remittanceid`) REFERENCES `remittance` (`objid`);


#alter eftpayment
ALTER TABLE eftpayment ADD COLUMN remittanceid VARCHAR(50);
ALTER TABLE eftpayment ADD CONSTRAINT `fk_eftpayment_remittance` FOREIGN KEY (`remittanceid`) REFERENCES `remittance` (`objid`);


