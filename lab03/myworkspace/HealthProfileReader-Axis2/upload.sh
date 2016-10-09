ant generate.wsdl
ant generate.service

cp -r build/HealthProfileReader.aar /home/sara/Programs/apache-tomcat-8.5.5/webapps/axis2/WEB-INF/services

/home/sara/Programs/apache-tomcat-8.5.5/bin/./shutdown.sh
/home/sara/Programs/apache-tomcat-8.5.5/bin/./startup.sh

