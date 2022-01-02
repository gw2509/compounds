Exscientia&trade; Compounds API
========================

Overview
--------

A Java-based web application which provides a CRUD REST API for compounds and their 
associated assay results. It is backed by an in-memory relational database.

The database is loaded from a SQL population script (src/main/resources/data.sql) each time the web 
application starts up. As a consequence, any changes are not persisted
between invocations.

The SQL population script was generated from the provided data/compounds.json and 
data/schema.json files using a Python script (scripts/json2sql.py). As of this 
writing, this is done manually, prior to building the application. 

See the [Usage](#usage) section for build and run instructions.

Notable Features
----------------

- Dockerisation (we use Docker&trade; containers to build and run the web application)
- Integration tests (to test the API's web-service endpoints)
- Swagger (provides a web-based interface to the API)
- Pageable (optional paging metadata provided for compound collections)


<div id="usage"></div>

Usage
-----

Working from the root directory of the project, instructions are as follows:

1. Execute the _build_ script (this may take a few minutes the first time you run it)
2. Execute the _run_ script to start the web application


    ./scripts/build.sh
    ./scripts/run.sh

You can exercise the web-service endpoints by navigating your browser to:
http://localhost:8080/swagger-ui/

Alternatively, you can use the supplied "collection" file (./postman_collection.json) in Postman&trade; to try it out from there.

Future Improvements
-------------------

- Improve automation by incorporating database population into the Docker&trade; configuration
- Make the API more "discoverable" by following HATEAOS principles in the API design 

