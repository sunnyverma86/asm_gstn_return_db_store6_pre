-- COMMON_AUTH API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ACCESSTOKEN', 'application/json', 'false', 'COMMON_AUTH', '/govtapi/v0.2/authenticate', NULL, 'true');

--INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'NULL', 'application/tar.gzip', 'false', 'Get Return File - Gstr3B', '/govtapi/v3.0/returns', NULL, 'true');

-- Get Return File Count API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILECNT', 'application/json', 'false', 'Get Return File Count', '/govtapi/v1.0/returns', NULL, 'true');

-- Get Return File Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILEDET', 'application/json', 'false', 'Get Return File Details', '/govtapi/v1.0/returns', NULL, 'true');

-- Get Refund Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETCASEDATA', 'application/json', 'false', 'Get Return File Details refund data', '/govtapi/v3.0/refunds', NULL, 'true');

-- Get Refund Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'CRNLST', 'application/json', 'false', 'Get Return File Details refund', '/govtapi/v1.0/caselst', NULL, 'true');

-- Get Refund Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'RECONSUM', 'application/json', 'false', 'Get Return File Details refund data', '/govtapi/v0.3/returns', NULL, 'true');

-- Get GSTR2B File Count API
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILECNT', 'application/json', 'false', 'Get Return File Count Gstr2b', '/govtapi/v2.0/common', NULL, 'true');

-- Get GSTR2B File Details API 
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILEDET', 'application/json', 'false', 'Get Return File Details Gstr2b', '/govtapi/v2.0/common', NULL, 'true');


-- Get GSTR2A Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active)  VALUES (now(), now(), 'R2ADET', 'application/json', 'false', 'Get GSTR2A Details', '/govtapi/v2.0/returns', NULL, 'true');

-- Get New Taxpayer Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENT', 'application/json', 'false', 'Get Registration Normal Tax Payer', '/govtapi/v0.2/entity', NULL, 'true');

-- Get Recon Details API Details
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'RECONSUM', 'application/json', 'false', 'Get Return recon', '/govtapi/v0.3/returns', NULL, 'true');

INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'NOMATTER', 'application/json', 'false', 'Get Return ledger', '/govtapi/v0.3/ledgers', NULL, 'true');

--RECOVERY
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'RCPID', 'application/json', 'false', 'Get Return recovery AppPmtInstalmentsDeferredPmts', '/govtapi/v1.0/recovery', NULL, 'true');


-- Get Other than Return Ledger Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'NRTN', 'application/json', 'false', 'Get Other than Return Ledger Details', '/govtapi/v0.4/ledgers', NULL, 'true');

--Enforcement Officer - Get GSTR1 Sections
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFR1DET', 'application/json', 'false', 'Get Return Enforcement Gstr1 Sections', '/govtapi/v0.3/returns', NULL, 'true');

--Enforcement Officer - Get GSTR3B
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFR3BDET', 'application/json', 'false', 'Get Return Enforcement Gstr3b Sections', '/govtapi/v0.3/returns', NULL, 'true');

--Enforcement Officer - Get GSTR2A
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFR2ADET', 'application/json', 'false', 'Get Return Enforcement Gstr2a Sections', '/govtapi/v0.3/returns', NULL, 'true');

--ADJUDICATION-Determination of Tax
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETCASEDATAASSMT', 'application/json', 'false', 'Get Return Adjudication DeterminationTax', '/govtapi/v1.0/adj/m2', NULL, 'true');

--ADJUDICATION-Demand List -DRC07 Non Case
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETDEMANDLISTNONCASE', 'application/json', 'false', 'get demand list non case', '/govtapi/v1.0/adj/m2', NULL, 'true');

--ADJUDICATION-Demand Order -DRC07 Non Case
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETDEMANDDTLSNONCASE', 'application/json', 'false', 'get demand order data non case', '/govtapi/v1.0/adj/m2', NULL, 'true');

--Enforcement Officer - RecordSearch Payments
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFRECSRCH', 'application/json', 'false', 'Get Enforcement Officer Record Search Payments', '/govtapi/v1.0/records', NULL, 'true');

--Registration - Download Documents
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'DOC', 'image/jpeg', 'false', 'Get Registration Download Document', '/govtapi/v0.2/document', NULL, 'true');

-- COMMON_AUTH PUBLIC API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ACCESSTOKEN', 'application/json', 'false', 'COMMON_AUTH_PUBLIC', '/commonapi/v1.0/authenticate', NULL, 'true');

-- SEARCH TAX PAYER PUBLIC API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'TP', 'application/json', 'false', 'Get Search Normal Tax Payer', '/commonapi/v1.3/search', NULL, 'true');


-- Get All Application Requests API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'LST', 'application/json', 'false', 'Get All Application Requests', '/govtapi/v2.0/application', NULL, 'true');

--Advance Ruling - Get Case Data - Appeal
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETCASEDATAAR', 'application/json', 'false', 'Get Advance ruling Appeal', '/govtapi/v1.0/ar', NULL, 'true');

--Appeal - Get Case Data - Appeal Tax Department
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETCASEDATAPPEAL', 'application/json', 'false','Get Return Appeal Tax Department','/govtapi/v1.0/appeal', NULL, 'true');

INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ARNUPDATE', 'application/json', 'false','Get Return Arn Update','/govtapi/v0.2/taxpayer/m2', NULL, 'true');
--APPEL
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETDATA', 'application/json', 'false','Get Return Appel Adjudication','/govtapi/v1.0/appeal', NULL, 'true');

--Get Comparison Report
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active)  VALUES (now(), now(), 'COMPREPORT', 'application/json', 'false', 'Get Comparison Report', '/govtapi/v2.1/returns/getcompdata', NULL, 'true');



INSERT INTO common.return_date_log (insert_dt, start_date)
VALUES (
    CURRENT_TIMESTAMP,
    '12-05-2026'
);
common
CREATE SCHEMA IF NOT EXISTS common   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS filecounter   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS asm    AUTHORIZATION postgres;
http://localhost:8084/masterData/create
-- For testing purpose only 
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILECNT', 'application/json', 'false', 'Get Payment File Count', '/govtapi/v0.2/payment', NULL, 'true');


--------------------------------------------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS asm    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS recon    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS common   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS eway_bill_not   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS eway_bill   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr7   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS filecounter   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr1   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr3b   AUTHORIZATION postgres;
INSERT INTO asm.return_date_log (start_date, insert_dt) VALUES ('01-04-2025', CURRENT_TIMESTAMP);








-- COMMON_AUTH API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ACCESSTOKEN', 'application/json', 'false', 'COMMON_AUTH', '/govtapi/v0.2/authenticate', NULL, 'true');

--INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'NULL', 'application/tar.gzip', 'false', 'Get Return File - Gstr3B', '/govtapi/v3.0/returns', NULL, 'true');

-- Get Return File Count API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILECNT', 'application/json', 'false', 'Get Return File Count', '/govtapi/v1.0/returns', NULL, 'true');

-- Get Return File Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILEDET', 'application/json', 'false', 'Get Return File Details', '/govtapi/v1.0/returns', NULL, 'true');

-- Get Refund Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETCASEDATA', 'application/json', 'false', 'Get Return File Details refund data', '/govtapi/v3.0/refunds', NULL, 'true');

-- Get Refund Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'CRNLST', 'application/json', 'false', 'Get Return File Details refund', '/govtapi/v1.0/caselst', NULL, 'true');

-- Get Refund Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'RECONSUM', 'application/json', 'false', 'Get Return File Details refund data', '/govtapi/v0.3/returns', NULL, 'true');

-- Get GSTR2B File Count API
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILECNT', 'application/json', 'false', 'Get Return File Count Gstr2b', '/govtapi/v2.0/common', NULL, 'true');

-- Get GSTR2B File Details API 
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILEDET', 'application/json', 'false', 'Get Return File Details Gstr2b', '/govtapi/v2.0/common', NULL, 'true');


-- Get GSTR2A Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active)  VALUES (now(), now(), 'R2ADET', 'application/json', 'false', 'Get GSTR2A Details', '/govtapi/v2.0/returns', NULL, 'true');

-- Get New Taxpayer Details API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENT', 'application/json', 'false', 'Get Registration Normal Tax Payer', '/govtapi/v0.2/entity', NULL, 'true');

-- Get Recon Details API Details
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'RECONSUM', 'application/json', 'false', 'Get Return recon', '/govtapi/v0.3/returns', NULL, 'true');

INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'NOMATTER', 'application/json', 'false', 'Get Return ledger', '/govtapi/v0.3/ledgers', NULL, 'true');

--RECOVERY
INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'RCPID', 'application/json', 'false', 'Get Return recovery AppPmtInstalmentsDeferredPmts', '/govtapi/v1.0/recovery', NULL, 'true');


-- Get Other than Return Ledger Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'NRTN', 'application/json', 'false', 'Get Other than Return Ledger Details', '/govtapi/v0.4/ledgers', NULL, 'true');

--Enforcement Officer - Get GSTR1 Sections
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFR1DET', 'application/json', 'false', 'Get Return Enforcement Gstr1 Sections', '/govtapi/v0.3/returns', NULL, 'true');

--Enforcement Officer - Get GSTR3B
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFR3BDET', 'application/json', 'false', 'Get Return Enforcement Gstr3b Sections', '/govtapi/v0.3/returns', NULL, 'true');

--Enforcement Officer - Get GSTR2A
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFR2ADET', 'application/json', 'false', 'Get Return Enforcement Gstr2a Sections', '/govtapi/v0.3/returns', NULL, 'true');

--ADJUDICATION-Determination of Tax
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETCASEDATAASSMT', 'application/json', 'false', 'Get Return Adjudication DeterminationTax', '/govtapi/v1.0/adj/m2', NULL, 'true');

--ADJUDICATION-Demand List -DRC07 Non Case
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETDEMANDLISTNONCASE', 'application/json', 'false', 'get demand list non case', '/govtapi/v1.0/adj/m2', NULL, 'true');

--ADJUDICATION-Demand Order -DRC07 Non Case
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETDEMANDDTLSNONCASE', 'application/json', 'false', 'get demand order data non case', '/govtapi/v1.0/adj/m2', NULL, 'true');

--Enforcement Officer - RecordSearch Payments
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFRECSRCH', 'application/json', 'false', 'Get Enforcement Officer Record Search Payments', '/govtapi/v1.0/records', NULL, 'true');

--Registration - Download Documents
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'DOC', 'image/jpeg', 'false', 'Get Registration Download Document', '/govtapi/v0.2/document', NULL, 'true');

-- COMMON_AUTH PUBLIC API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ACCESSTOKEN', 'application/json', 'false', 'COMMON_AUTH_PUBLIC', '/commonapi/v1.0/authenticate', NULL, 'true');

-- SEARCH TAX PAYER PUBLIC API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'TP', 'application/json', 'false', 'Get Search Normal Tax Payer', '/commonapi/v1.3/search', NULL, 'true');


-- Get All Application Requests API Details
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'LST', 'application/json', 'false', 'Get All Application Requests', '/govtapi/v2.0/application', NULL, 'true');

--Advance Ruling - Get Case Data - Appeal
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETCASEDATAAR', 'application/json', 'false', 'Get Advance ruling Appeal', '/govtapi/v1.0/ar', NULL, 'true');

--Appeal - Get Case Data - Appeal Tax Department
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'GETCASEDATAPPEAL', 'application/json', 'false','Get Return Appeal Tax Department','/govtapi/v1.0/appeal', NULL, 'true');

--BO-GSTR3B
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFR3BDET', 'application/json', 'false','ENFR3BDET','/govtapi/v0.3/returns', NULL, 'true');

--BO-Enforcement Officer - Get Entity
INSERT INTO public.api_details(	create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'ENFREGENT', 'application/json', 'false','ENFREGENT','/govtapi/v1.0/registration', NULL, 'true');


-- For testing purpose only 
--INSERT INTO public.api_details(create_date_time, updated_date_time,  api_action, api_content_type, api_encryption, api_name, api_path, api_url_parameters, is_active) VALUES (now(), now(), 'FILECNT', 'application/json', 'false', 'Get Payment File Count', '/govtapi/v0.2/payment', NULL, 'true');



--asm,recon,public,common,eway_bill_not,eway_bill
CREATE SCHEMA IF NOT EXISTS gstr1    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr1a    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr2a   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr2b   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr3b   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr4   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr5   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr6   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr7   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr8   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr9   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr9_8a   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr9a    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr9c    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr10   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gstr11   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS itc02   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS pmt   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS recon   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS refund   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS refund_details   AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS regis    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS registds    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS report    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS eway_bill    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS eway_bill_not    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS ekosh    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS pwd    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS cmp08    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS gst    AUTHORIZATION postgres;

CREATE SCHEMA IF NOT EXISTS crn_details    AUTHORIZATION postgres;

CREATE SCHEMA IF NOT EXISTS ledger_cash    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS ledger_itc    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS ledger_liability    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS ledger_other    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS not_used    AUTHORIZATION postgres;

CREATE SCHEMA IF NOT EXISTS adjudication_appeal    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_determination_tax    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_general_penality    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_nonfilers_returns    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_provisional_assessment    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_rectification_orders    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_remanded_and_appeal_effects    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_scrutiny_returns    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_summary_assessment    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_tax_collected_but_not_deposited    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_unregistered_persons    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_voluntary_payment    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS demand_list_drc07    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS demand_order_drc07    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS adjudication_restoration_attachment    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS advance_ruling_appeal    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS advance_ruling_reference    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS advance_ruling_taxpayer    AUTHORIZATION postgres;


CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr1    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr2a    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr3b    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr7    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr4    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr5    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr6    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr8    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr9    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_gstr1_sum    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_RSP    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_RSE    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_RSRegis    AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS enforcement_officer_RSR    AUTHORIZATION postgres;


CREATE SCHEMA IF NOT EXISTS payment  AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS download_document  AUTHORIZATION postgres;


SELECT * FROM filecounter."ReturnFileCount"
ORDER BY "ReturnFileCountId" desc 

SELECT "ReturnFileCountId", file_num, cnt, url, "IsSuccess", msg, "ReturnFileDetailId", insertdatetime, dt, hash
	FROM filecounter."ReturnFileDetail" 
	where "ReturnFileCountId" ='41963'
	order by  "ReturnFileDetailId" desc limit 100;

	SELECT "Id", "ReturnFileDetailId", filepath, jsondata, filenumber, dt, category, insert_dt, "ReturnFileCountId", sequence_number, is_processed
	FROM log.payment_initial_json 	where "ReturnFileCountId" ='41963';
	
	
	
	-- =========================================================
-- SCHEMA CREATE (IF NOT EXISTS)
-- =========================================================

CREATE SCHEMA IF NOT EXISTS analytical_dashboard;

-- =========================================================
-- SEQUENCE FOR return_comparison_report_gstin
-- =========================================================

CREATE SEQUENCE IF NOT EXISTS analytical_dashboard.return_comparison_report_gstin_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    CACHE 1;

-- =========================================================
-- TABLE : return_comparison_report_gstin
-- =========================================================

CREATE TABLE IF NOT EXISTS analytical_dashboard.return_comparison_report_gstin
(
    id BIGINT NOT NULL DEFAULT nextval(
        'analytical_dashboard.return_comparison_report_gstin_id_seq'
    ),

    gstin VARCHAR(20),

    fy VARCHAR(10),

    counter_attempt INTEGER DEFAULT 0,

    is_processed BOOLEAN DEFAULT FALSE,

    CONSTRAINT return_comparison_report_gstin_pkey
        PRIMARY KEY (id)
);

-- =========================================================
-- SEQUENCE OWNED BY
-- =========================================================

ALTER SEQUENCE analytical_dashboard.return_comparison_report_gstin_id_seq
OWNED BY analytical_dashboard.return_comparison_report_gstin.id;

-- =========================================================
-- SEQUENCE FOR return_comparison_report_gstin_json
-- =========================================================

CREATE SEQUENCE IF NOT EXISTS analytical_dashboard.return_comparison_report_gstin_json_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    CACHE 1;

-- =========================================================
-- TABLE : return_comparison_report_gstin_json
-- =========================================================

CREATE TABLE IF NOT EXISTS analytical_dashboard.return_comparison_report_gstin_json
(
    id BIGINT NOT NULL DEFAULT nextval(
        'analytical_dashboard.return_comparison_report_gstin_json_id_seq'
    ),

    gstin VARCHAR(20),

    fy VARCHAR(10),

    jsondata JSONB,

    counter_attempt INTEGER DEFAULT 0,

    is_processed BOOLEAN DEFAULT FALSE,

    create_date_time TIMESTAMP,

    updated_date_time TIMESTAMP,

    CONSTRAINT return_comparison_report_gstin_json_pkey
        PRIMARY KEY (id)
);

-- =========================================================
-- SEQUENCE OWNED BY
-- =========================================================

ALTER SEQUENCE analytical_dashboard.return_comparison_report_gstin_json_id_seq
OWNED BY analytical_dashboard.return_comparison_report_gstin_json.id;

-- =========================================================
-- INDEXES (OPTIONAL BUT RECOMMENDED)
-- =========================================================

CREATE INDEX IF NOT EXISTS idx_rcg_gstin
ON analytical_dashboard.return_comparison_report_gstin(gstin);

CREATE INDEX IF NOT EXISTS idx_rcg_processed
ON analytical_dashboard.return_comparison_report_gstin(is_processed);

CREATE INDEX IF NOT EXISTS idx_rcgj_gstin
ON analytical_dashboard.return_comparison_report_gstin_json(gstin);

CREATE INDEX IF NOT EXISTS idx_rcgj_processed
ON analytical_dashboard.return_comparison_report_gstin_json(is_processed);

CREATE INDEX IF NOT EXISTS idx_rcgj_jsondata
ON analytical_dashboard.return_comparison_report_gstin_json
USING GIN (jsondata);














