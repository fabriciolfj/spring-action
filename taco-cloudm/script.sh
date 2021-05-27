docker network create cassandra-net;
docker run --name my-cassandra --network cassandra-net -p 9042:9042 -d cassandra:latest
docker run -it --network cassandra-net --rm cassandra cqlsh some-cassandra