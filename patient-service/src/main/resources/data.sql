-- Ensure the 'patient' table exists
CREATE TABLE IF NOT EXISTS patient
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    address         VARCHAR(255)        NOT NULL,
    date_of_birth   DATE                NOT NULL,
    registered_date DATE                NOT NULL
);

-- Insert well-known UUIDs for specific patients
INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174000',
       'Rajesh Kumar',
       'rajesh.kumar@example.in',
       '123 MG Road, Bengaluru',
       '1985-06-15',
       '2024-01-10'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174001',
       'Priya Sharma',
       'priya.sharma@example.in',
       '456 Park Street, Kolkata',
       '1990-09-23',
       '2023-12-01'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '123e4567-e89b-12d3-a456-426614174001');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174002',
       'Anita Verma',
       'anita.verma@example.in',
       '789 Lajpat Nagar, Delhi',
       '1978-03-12',
       '2022-06-20'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '123e4567-e89b-12d3-a456-426614174002');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174003',
       'Amit Singh',
       'amit.singh@example.in',
       '321 Banjara Hills, Hyderabad',
       '1982-11-30',
       '2023-05-14'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '123e4567-e89b-12d3-a456-426614174003');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174004',
       'Sneha Iyer',
       'sneha.iyer@example.in',
       '654 Koregaon Park, Pune',
       '1995-02-05',
       '2024-03-01'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '123e4567-e89b-12d3-a456-426614174004');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174005',
       'Manoj Pillai',
       'manoj.pillai@example.in',
       '987 Vyttila, Kochi',
       '1988-07-25',
       '2024-02-15'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174005');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174006',
       'Aarohi Deshmukh',
       'aarohi.deshmukh@example.in',
       '123 Shaniwar Peth, Pune',
       '1992-04-18',
       '2023-08-25'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174006');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174007',
       'Ravi Mehta',
       'ravi.mehta@example.in',
       '456 Cuffe Parade, Mumbai',
       '1975-01-11',
       '2022-10-10'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174007');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174008',
       'Neha Reddy',
       'neha.reddy@example.in',
       '789 Jubilee Hills, Hyderabad',
       '1989-09-02',
       '2024-04-20'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174008');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174009',
       'Karan Das',
       'karan.das@example.in',
       '321 Salt Lake, Kolkata',
       '1993-11-15',
       '2023-06-30'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174009');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174010',
       'Meera Nair',
       'meera.nair@example.in',
       '654 Brigade Road, Bengaluru',
       '1980-08-09',
       '2023-01-22'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174010');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174011',
       'Arjun Kapoor',
       'arjun.kapoor@example.in',
       '987 Anna Nagar, Chennai',
       '1984-05-03',
       '2024-05-12'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174011');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174012',
       'Pooja Bhatt',
       'pooja.bhatt@example.in',
       '123 Sector 22, Chandigarh',
       '1991-12-25',
       '2022-11-11'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174012');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174013',
       'Vikram Chauhan',
       'vikram.chauhan@example.in',
       '456 Civil Lines, Allahabad',
       '1976-06-08',
       '2023-09-19'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174013');

INSERT INTO patient (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174014',
       'Ritika Jain',
       'ritika.jain@example.in',
       '789 Boring Road, Patna',
       '1987-10-17',
       '2024-03-29'
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE id = '223e4567-e89b-12d3-a456-426614174014');