Exscientia Compounds API
========================

Description
-----------

A Java web application which provides a REST API for compounds and their 
associated assay results backed by an in-memory relational database.

The database is loaded from a SQL script (data.sql) each time the web 
application starts up. As a consequence, any changes are not persisted
between invocations.

The SQL script was generated from the provided data/compounds.json and 
data/schema.json files using a Python script (scripts/json2sql.py).

todo
----

- integration tests
- swagger
- postman
- api brief summary
- dockerisation
- pageable
- future improvements


Usage
-----

It's pretty simple:

1. execute the BUILD script (this may take a few minutes the first time you run it)
2. execute the RUN script to start the web application


    ./scripts/build.sh
    ./scripts/run.sh

You can exercise the web-service endpoints by navigating your browser to:
http://localhost:8080/swagger-ui/

Alternatively, you can use the supplied Postman collection (./postman_collection.json) file to test from there.

