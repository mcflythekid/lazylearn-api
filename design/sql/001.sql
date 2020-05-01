CREATE TABLE subscribe_form (
  id varchar(100),
  email varchar(300),
  createddate datetime,
  updateddate datetime,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE contact_form (
  id varchar(100),
  first_name varchar(100),
  last_name varchar(100),
  subject varchar(100),
  email varchar(300),
  content longtext,
  createddate datetime,
  updateddate datetime,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;