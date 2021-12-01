# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## 0.2.1 (2021-12-01)

### Features

* fix: missing pom.xm when deploy jar to maven central (8e3b93d)

## 0.2 (2021-11-25)

### Features

* provide FactoryAspect for inject event bus, spring beans into entity automatically ([dd11341](https://github.com/taccisum/domain-core/commit/dd11341d27c6b6f4caeba9c74903b967b8c28858))
* provides relevant classes about entity, event, event publisher, etc... ([81ca27c](https://github.com/taccisum/domain-core/commit/81ca27c5f9d53ab3ae5d4cae92bc6dca55d63cec))
* support autoconfigure on app startup ([e342853](https://github.com/taccisum/domain-core/commit/e342853feb9085370887b29c646edea4ac195fbd))


### Bug Fixes

* include self class ([fdfcce1](https://github.com/taccisum/domain-core/commit/fdfcce1994b3bb45d17269e243a9b06afda169b6))


## 0.1 (2021-09-26)

### Features

* provide tools SpringUtils#inject for inject spring bean into exist obj 027edc5
* support inject field of super class via SpringUtils#inject 636aae6
