## Environment

#### To run Postgres locally in docker
`docker run -d -p 5432:5432 --env POSTGRES_USER=admin --env POSTGRES_PASSWORD=admin123 --env POSTGRES_DB=hotels_app postgres` 

#### List Docker processes
`docker ps -a`

#### Stop and remove container
`docker stop [container_id]`<br>
`docker rm [container_id]`