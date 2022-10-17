USE everythingeverywhereallatonce_schema;

CREATE TABLE IF NOT EXISTS table_with_blob
(
  id IDENTITY NOT NULL PRIMARY KEY,
  name VARCHAR(255),
  contents BLOB
);
