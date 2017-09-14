# zj-framework
本项目为笔者常用的各种框架脚手架和工具类二次封装，"zj-"后面是原项目名称。

思路：不重复造轮子，只是对这些开源项目依赖并且做常用配置的标准化，用起来更方便。

### 项目简述
* zj-baseto：一些常用的基础模型，为了分布式服务接口层依赖尽量少，所以单独抽取出来。
* zj-email：邮件处理工具。
* zj-excel：excel读写工具，使用还是不方便，待优化。
* zj-fastjson：json处理工具，默认格式化时间。
* zj-httpclient：httpclient二次封装，待优化。
* zj-logback：日志处理，默认配置，让项目依赖基本零配置。
* zj-mybatis：mybatis封装，针对enum处理handler优化。
* zj-pagehelper：分页插件。
* zj-qrcode：二维码处理，目前用到zxing。
* zj-redis：redis工具类，哨兵模式配置，抽象jedis基本操作。
* zj-sms：各短信平台集成，待优化。
* zj-springmvc：springmvc集成框架。
* zj-springtest：junit和springtest整合。
* zj-tkmapper：mybatis通用mapper。
* zj-util：一些自定义的常用工具类，待优化。
