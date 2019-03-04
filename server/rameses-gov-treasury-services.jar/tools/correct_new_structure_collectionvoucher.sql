#To correct the cashreceiptpayment_noncash

ALTER TABLE collectionvoucher_fund DROP COLUMN totalcash;
ALTER TABLE collectionvoucher_fund DROP COLUMN totalcheck;
ALTER TABLE collectionvoucher_fund DROP COLUMN totalcr;
ALTER TABLE collectionvoucher_fund DROP COLUMN cashbreakdown;
ALTER TABLE collectionvoucher_fund DROP COLUMN controlno;


