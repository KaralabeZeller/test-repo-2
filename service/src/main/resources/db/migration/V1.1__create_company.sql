CREATE SEQUENCE IF NOT EXISTS company_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE company (
    id SERIAL  PRIMARY KEY,
    old_id TEXT,
    name TEXT NOT NULL,
    tax_number TEXT NOT NULL,
    accounting_address TEXT NOT NULL,
    web TEXT,
    logo TEXT,
    contact_name TEXT,
    contact_email TEXT,
    contact_phone TEXT,
    owner TEXT,
    discount decimal,
    unas_id TEXT,
    monthly_billing BOOLEAN
);
