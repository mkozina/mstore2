#!/bin/sh

echo "************ UNDEPLOYING *******************"
../glassfish3/glassfish/bin/asadmin undeploy mstore2
echo "************ BUILDING **********************"
mvn package
echo "************ DEPLOYING *********************"
../glassfish3/glassfish/bin/asadmin deploy target/mstore2.war
