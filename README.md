# 3rd_Party_Auth
An application will give e2e solution for the 3rd party(Google,Facebook etc) authentication and authorization 

# e-delivery

<a href="https://travis-ci.org/cooligc/e-Delivery"><img src="https://travis-ci.org/cooligc/e-Delivery.svg?branch=master"/></a>
#Technology used :
<hr/>
<ul>
<li>Spring (Spring-core,Spring-MVC,Spring-Security,Spring-data-mongodb,Spring-Scheduler)</li>
<li>Thymeleaf</li>
<li>Mongo DB</li>
<li>jQuery</li>
<li>Bootstrap CSS</li>
</ul>

#Prerequisite Software to be installed
<hr/>
<ul>
<li>Java 7 or above</li>
<li>Maven 3</li>
<li>Application Server Like Tomcat or jBoss</li>
<li>Mongo DB 2.4 or above</li>
</ul>
#How to run the application ?
<hr/>
<ul>
<li>Build the Application using maven (mvn clean install)</li>
<li>Run mongodb with default port (27017) (We can run, mongo on any port for that we need to change the port in mongo-config.xml file)</li>
<li>Deploy the war in the Apllication Server</li>
</ul>
Now Test the Application by putting <code>http://localhost:8080</code> on your browser

#Dummy Credit Card Details to be used while placing Order
<hr/>
<table>
<tr>
  <th>Credit Card Type</th>
  <th>Credit Card Number</th>
</tr>
<tr>
  <td>MasterCard	</td>
  <td>5555555555554444</td>
<tr>
   <td>MasterCard	</td>
   <td>5105105105105100</td>
<tr>
  <td>Visa	</td></td>
  <td>4111111111111111</td>
<tr>
  <td>Visa	</td>
  <td>4012888888881881</td>
</tr>
</table>
