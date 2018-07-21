#!/usr/bin/env bash
echo ""
echo "---------------------------------EXAM1 RESULT------------------------------------"
echo "`cd Exam1`"
echo "`./Exam1/build.sh`"
echo ""

echo ""
echo "---------------------------------EXAM2 RESULT------------------------------------"
exec ./Exam2/build.sh
echo ""

echo ""
echo "---------------------------------EXAM3 RESULT------------------------------------"
exec ./Exam3/build.sh