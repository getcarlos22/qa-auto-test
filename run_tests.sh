#!/bin/bash -eu

begin=$(date +"%s")

# Set the context to root of repo
ROOT=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
cd ${ROOT}

echo "******************************************************"
echo "*             Building docker                        *"
echo "******************************************************"

docker build --no-cache -t github-api-bdd-tests:v0.1 --rm .

echo "******************************************************"
echo "*             Running tests in docker                *"
echo "******************************************************"

docker run --rm -v ~/.m2:/root/.m2 -ti github-api-bdd-tests:v0.1

termin=$(date +"%s")
difftimelps=$(($termin-$begin))
echo "$(($difftimelps / 60)) minutes and $(($difftimelps % 60)) seconds elapsed for Script Execution."

echo "******************************************************"
echo "*             End of script                         *"
echo "******************************************************"