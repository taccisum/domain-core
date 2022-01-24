# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

### [0.3](https://github.com/taccisum/domain-core/compare/v0.2.1...v0.3) (2022-01-24)

### Features

* add constructor for DataNotFoundException ([e608794](https://github.com/taccisum/domain-core/commit/e608794108dcebfbf30814f32425e62823deb2f4))
* provide common domain exceptions ([f387583](https://github.com/taccisum/domain-core/commit/f3875839a518ffae6d0155f5ad7db1bded00aff4))
* support to inject bean for fields that annotated by @Resource ([82726da](https://github.com/taccisum/domain-core/commit/82726da182b1520477275c8f7fad7b9dc373abc6))

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
