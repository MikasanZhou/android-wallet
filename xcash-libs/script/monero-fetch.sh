#!/usr/bin/env bash

set -e

source script/env.sh

cd $EXTERNAL_LIBS_BUILD_ROOT

url="https://github.com/X-CASH-official/xcash-core.git"

if [ ! -d "xcash-core" ]; then
  git clone ${url}
  cd xcash-core
  git checkout master
  git submodule update --recursive --init
else
  cd xcash-core
  git checkout master
  git pull
  git submodule update --recursive --init
fi
