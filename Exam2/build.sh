#!/usr/bin/env bash
mvn package
java -cp target/*.jar com.example.demo.Server &
java -cp target/*.jar com.example.demo.Client
