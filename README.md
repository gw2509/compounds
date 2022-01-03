#Exscientia&trade; Compounds API

##Overview

###Web Application
A Java-based web application which provides a CRUD REST API for compounds and their 
associated assay results. It is backed by an in-memory relational database.

###Database
The database is populated from a SQL _population_ script (src/main/resources/data.sql) 
each time the web application starts up. It's a series of INSERT statements. 
As a consequence, any changes are not persisted between invocations of the web application.

###Python (SQL generation) Script
The SQL population script is generated from the provided data/compounds.json and 
data/schema.json files using a Python script (scripts/json2sql.py). As of this 
writing, this is done manually, prior to building the application. __Note__: _A pre-generated 
SQL population script is provided for ease of deployment._

See the [Usage](#usage) section for build and run instructions.

##Notable Features

- Dockerisation (we use Docker&trade; containers to build and run the web application)
- Integration tests (to test the API's web-service endpoints)
- Swagger (provides a web-based interface to the API for testing and comprehension)
- Pageable (optional endpoint with paging metadata provided for Compound collections)


<div id="usage"></div>

##Usage

Working from the root directory of the project, instructions are as follows:

1. Execute the _build_ script (this may take a few minutes the first time you run it)
2. Execute the _run_ script to start the web application


    ./scripts/build.sh
    ./scripts/run.sh

You can exercise the web-service endpoints by navigating your browser to:
http://localhost:8080/swagger-ui/

Alternatively, you can use the supplied "collection" file (./postman_collection.json) in Postman&trade; to try it out from there.

##Future Improvements

- Data validation on POST and PUT operations 
- Implement filtering and ordering features
- Make the API more "hypermedia-driven" by following HATEOAS principles in the API design 
- Improve automation by moving database script generation into the Docker&trade; configuration
- One or two of the (ORM) auto-generated SQL queries are inefficient and should be optimised