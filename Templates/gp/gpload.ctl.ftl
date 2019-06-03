---
VERSION: 1.0.0.1
DATABASE: sample
USER: gpadmin
HOST: 192.168.8.82
PORT: 5432
GPLOAD:
   INPUT:
    - SOURCE:
         LOCAL_HOSTNAME:
           - MDW
         PORT: 46666
         FILE:
           - /app/gpload/data/${table.tableName?lower_case}
    - FORMAT: text
    - DELIMITER: E'\001'
    - NULL_AS: ''
    - ENCODING: utf8
    - ERROR_LIMIT: 25
    - ERROR_TABLE: chj.${table.tableName?lower_case}_error
   OUTPUT:
    - TABLE: chj.${table.tableName?lower_case}
    - MODE: INSERT
   PRELOAD:
    - TRUNCATE: TRUE
