#util
如果你常常使用JSON接受参数，这个工具包里面的ParamFilterAop可以代为效劳，只需将springboot主类上导入componentScan（“com.rc.util.aop”），将注解打在控制器方法上，并在控制器类上写上静态常量，与方法名一致，里面写需要过滤的参数名即可过滤参数。此工具包还可以解析复杂结构的JSON，获取任意json结构内的Map，List，String。
