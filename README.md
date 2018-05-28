# springboot-cassandra-CurdOperation
# this app is integrated with Cassandra
# to run this application need to install cassanra and create below configuration
cassandra.contactpoints=localhost,
127.0.0.1 cassandra.port=9042
cassandra.keyspace=test01

# run the spring boot app
 mvn spring-boot:run
 

 # run the request from post man
 
 {
	"guestId":12312,
	"city": "Jabalpur",
	"country":"India",
	"pinCode":"560065",
	"locality":"Rammurthy Nagar"
}

#  URL http://localhost:8089//addressAPI/address/add


