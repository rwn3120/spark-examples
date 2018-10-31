#!/bin/bash

PORT=12345
for i in $(seq 10); do
       echo "Starting netcat server on port ${PORT}"
       nc -l -p 12345
       echo "Netcat server stopped"
done

