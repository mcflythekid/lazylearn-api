CREATE TABLE minpair_file (
  id varchar(100),
  name varchar(100),
  minpair_id varchar(100),
  side int(3),
  audio_path varchar(500),
  userid varchar(100),
  createddate datetime,
  updateddate datetime,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;