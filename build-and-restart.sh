docker build -t servicesupply .
docker stop servicesupply || true
docker rm servicesupply || true
docker run -d -p 20289:8080 --name servicesupply servicesupply