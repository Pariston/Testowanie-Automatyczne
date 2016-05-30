#!/bin/sh

java -cp ./scripts/hsqldb-2.3.4.jar org.hsqldb.util.DatabaseManagerSwing --url jdbc:hsqldb:hsql://localhost/workdb
