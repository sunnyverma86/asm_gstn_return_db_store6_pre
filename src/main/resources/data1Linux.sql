-- COMMON_AUTH API Details
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'ACCESSTOKEN', 'application/json', 'false',
    'COMMON_AUTH', '/govtapi/v0.2/authenticate', NULL, 'true'
);

-- Get Return File Count API Details
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'FILECNT', 'application/json', 'false',
    'Get Return File Count', '/govtapi/v1.0/returns', NULL, 'true'
);

-- Get Return File Details API Details
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'FILEDET', 'application/json', 'false',
    'Get Return File Details', '/govtapi/v1.0/returns', NULL, 'true'
);

-- Get Refund Details API
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'GETCASEDATA', 'application/json', 'false',
    'Get Refund Case Data', '/govtapi/v3.0/refunds', NULL, 'true'
);

-- CRN LIST
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'CRNLST', 'application/json', 'false',
    'Get Refund CRN List', '/govtapi/v1.0/caselst', NULL, 'true'
);

-- RECON SUMMARY
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'RECONSUM', 'application/json', 'false',
    'Get Return Recon Summary', '/govtapi/v0.3/returns', NULL, 'true'
);

-- GSTR2B FILE COUNT
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'FILECNT', 'application/json', 'false',
    'Get GSTR2B File Count', '/govtapi/v2.0/common', NULL, 'true'
);

-- GSTR2B FILE DETAILS
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'FILEDET', 'application/json', 'false',
    'Get GSTR2B File Details', '/govtapi/v2.0/common', NULL, 'true'
);

-- GSTR2A DETAILS
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'R2ADET', 'application/json', 'false',
    'Get GSTR2A Details', '/govtapi/v2.0/returns', NULL, 'true'
);

-- REGISTRATION
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'ENT', 'application/json', 'false',
    'Get Registration Details', '/govtapi/v0.2/entity', NULL, 'true'
);

-- LEDGER
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'NOMATTER', 'application/json', 'false',
    'Get Ledger Details', '/govtapi/v0.3/ledgers', NULL, 'true'
);

-- RECOVERY
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'RCPID', 'application/json', 'false',
    'Get Recovery Details', '/govtapi/v1.0/recovery', NULL, 'true'
);

-- PUBLIC AUTH
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'ACCESSTOKEN', 'application/json', 'false',
    'COMMON_AUTH_PUBLIC', '/commonapi/v1.0/authenticate', NULL, 'true'
);

-- SEARCH TAXPAYER
INSERT INTO public.api_details(
    create_date_time, updated_date_time, id, api_action, api_content_type,
    api_encryption, api_name, api_path, api_url_parameters, is_active
)
VALUES (
    now(), now(),
    md5(random()::text || clock_timestamp()::text)::uuid,
    'TP', 'application/json', 'false',
    'Search Taxpayer', '/commonapi/v1.3/search', NULL, 'true'
);

INSERT INTO asm.return_date_log (insert_dt, start_date)
VALUES (
    CURRENT_TIMESTAMP,
    '27-04-2026'
);




CREATE TABLE document.dcupdtls_gstr9c
(LIKE gst_api_gstr_9c.dcupdtls_gstr9c INCLUDING ALL);

INSERT INTO document.dcupdtls_gstr9c
SELECT *
FROM gst_api_gstr_9c.dcupdtls_gstr9c;



//Total records          : 485,694
//Records having FY      : 330,241
//Records without FY     : 155,453

ALTER TABLE filecounter.crn_detail_common
ADD COLUMN dof VARCHAR(20),
ADD COLUMN gstin VARCHAR(20),
ADD COLUMN fy VARCHAR(20);


UPDATE filecounter.crn_detail_common
SET
    dof   = jsondata ->> 'dof',
    gstin = jsondata ->> 'gstin'
WHERE dof IS NULL
   OR gstin IS NULL;


   UPDATE filecounter.crn_detail_common
SET fy = jsonb_path_query_first(jsondata, '$.**.fy') #>> '{}'
WHERE fy IS NULL;
