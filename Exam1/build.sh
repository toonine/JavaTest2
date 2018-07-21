#!/usr/bin/env bash
mvn assembly:assembly
java -cp target/Exam1-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.example.demo.App