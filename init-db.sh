#!/bin/bash

set -e

if [ -z "$DATABASE_URL" ]; then
  echo "Missing DATABASE_URL"
  exit 1
fi

if [ -z "$DATABASE_USERNAME" ]; then
  echo "Missing DATABASE_USERNAME"
  exit 1
fi

if [ -z "$DATABASE_PASSWORD" ]; then
  echo "Missing $DATABASE_PASSWORD"
  exit 1
fi

docker run \
  -- rm \
  --network=fastservice \
  --url="$DATABASE_URL" \
  --username="$DATABASE_USERNAME" \
  --password="$DATABASE_PASSWORD" \
  --log-level INFO \
  missing-finish