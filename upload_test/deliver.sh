#!/bin/bash

echo "sudo cp -r target/classes /var/lib/tomcat9/webapps/upload/WEB-INF/"
sudo cp -r target/classes /var/lib/tomcat9/webapps/upload/WEB-INF/

echo "sudo cp web.xml /var/lib/tomcat9/webapps/upload/WEB-INF/"
sudo cp web.xml /var/lib/tomcat9/webapps/upload/WEB-INF/
