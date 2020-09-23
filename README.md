![image](https://github.com/yydzxz/ByteDanceOpen/workflows/Master%20Branch%20Deploy/badge.svg)
![license](https://img.shields.io/github/license/yydzxz/ByteDanceOpen)
![issues](https://img.shields.io/github/issues/yydzxz/ByteDanceOpen)
![starts](https://img.shields.io/github/stars/yydzxz/ByteDanceOpen)
![forks](https://img.shields.io/github/forks/yydzxz/ByteDanceOpen)
# ByteDanceOpen

- 字节跳动 Java SDK
- 支持字节跳动(抖音，头条..)开放平台小程序代开发功能的后端开发。
部分代码参考了[WxJava](https://github.com/Wechat-Group/WxJava)的实现，使用方式也大同小异。
- 支持`JoddHttp`, `OkHttpClient`, `RestTemplate`
- 支持`Gson`, `Jackson`, `FastJson`(不推荐使用FastJson,[原因](https://segmentfault.com/a/1190000015634321))
- 同时支持字节的v1,v2接口
## 使用方式

#### Maven 引用
  ##### 稳定版
  ```xml
  <dependency>
   <groupId>com.github.yydzxz</groupId>
   <artifactId>bytedance-open</artifactId>
   <version>3.0.4.RELEASE</version>
  </dependency>
  ```
  ##### 最新版
  ```xml
  <dependency>
    <groupId>com.github.yydzxz</groupId>
    <artifactId>bytedance-open</artifactId>
    <version>3.1.0.SNAPSHOT</version>
  </dependency>
  ```
具体可以参考[ByteDance-Open-Demo](https://github.com/yydzxz/ByteDance-Open-Demo)
  - Demo提供了Docker版本, 填写好第三方配置后, 通过`docker-compose`一键启动所有所需服务

