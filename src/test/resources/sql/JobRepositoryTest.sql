INSERT INTO fjobs.portal (
  name,
  search_url,
  city,
  state,
  enabled
) VALUES (
  'Portal One',
  'https://portal-one.com/jobs',
  'Victoria',
  'BC',
  TRUE
);

INSERT INTO fjobs.job (
  job_title,
  company_name,
  job_type,
  job_description,
  published_at,
  job_url,
  portal_id,
  created_at
) VALUES (
  'Java Full-Stack',
  'Encora',
  'Full-time',
  'Grow existing culture and software development processes',
  '2023-02-21 10:15:11',
  'https://career.encora.com/jobs/java-full-stack',
  (SELECT p.id FROM fjobs.portal p WHERE p.name = 'Portal One'),
  '2023-01-21 10:15:11'
);

INSERT INTO fjobs.job (
  job_title,
  company_name,
  job_type,
  job_description,
  published_at,
  job_url,
  portal_id,
  created_at
) VALUES (
  'Java Back-end',
  'Encora',
  'Full-time',
  'Write code',
  '2023-02-21 10:15:11',
  'https://career.encora.com/jobs/java-backend',
  (SELECT p.id FROM fjobs.portal p WHERE p.name = 'Portal One'),
  '2023-02-21 10:15:11'
);
